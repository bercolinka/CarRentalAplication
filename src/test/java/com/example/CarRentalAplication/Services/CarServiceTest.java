package com.example.CarRentalAplication.Services;

import com.example.CarRentalAplication.Exceptions.InvalidCarID;
import com.example.CarRentalAplication.Repositories.CarRepository;
import com.example.CarRentalAplication.contract.CarDTO;
import com.example.CarRentalAplication.contract.ClientDTO;
import com.example.CarRentalAplication.models.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {
    @Mock
    private CarRepository carRepository;
    @Mock
    private LocalizationService localizationService;

    private CarService carService;

    private final Car car = new Car(
            1,
            "make",
            "model",
            "color",
            2022,
            100,
            500,
            (byte) 1,
            1
    );


    @BeforeEach
    void setUp() {
        carService = new CarService(carRepository, localizationService);
    }

    @Test
    void getCarsshouldReturnListOfOneCar() {
        String query = "quert";


        //when
        when(carRepository.getCarsUsingQuery(query)).thenReturn(List.of(car));
        carService.getCars(query);
        //then
        verify(carRepository).getCarsUsingQuery(query);
        verify(localizationService).getCityName(car.getCurrentLocation());
        assertEquals(1,carRepository.getCarsUsingQuery(query).size());

    }

    @Test
    void findByIDshoudreturnOneCar() {
        Integer id = 1;

        //when
        when(carRepository.findByID(id)).thenReturn(car);

        carService.findByID(id);

        //then
        verify(carRepository).findByID(id);
        verify(localizationService).getCityName(car.getCurrentLocation());
    }
    @Test
    void findCarByInvalidID() {
        //given
        Integer id = 1;

        //when
        when(carRepository.findByID(id)).thenReturn(null);

        //then
        assertThrows(InvalidCarID.class,() -> carService.findByID(id));

    }

    @Test
    void rentalFeeshouldBe500() {
        Integer carID = 1;

        when(carRepository.findByID(carID)).thenReturn(car);
        assertEquals(500,carService.findRentalFeeByID(carID));
    }

    @Test
    void findEntityByValidIDshoudReturnCar() {
        Integer carID = 1;

        when(carRepository.findByID(carID)).thenReturn(car);

        assertEquals(car,carService.findEntityByID(carID));

    }
    @Test
    void findEntityByInvalidIDShouldThrowException() {
        Integer carID = 1;

        when(carRepository.findByID(carID)).thenReturn(null);

        assertThrows( InvalidCarID.class, ()-> carService.findEntityByID(carID));

    }

    @Test
    void updateCar() {

        carService.updateCar(car);
        verify(carRepository).updateCar(car);
    }
}