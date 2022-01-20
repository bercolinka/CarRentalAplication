package com.example.CarRentalAplication.Services;

import com.example.CarRentalAplication.contract.CarDTO;
import com.example.CarRentalAplication.Repositories.CarRepository;
import com.example.CarRentalAplication.models.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private CarRepository carRepository;
    private LocalizationService localizationService;


    @Autowired
    public CarService(CarRepository carRepository, LocalizationService localizationService) {
        this.carRepository = carRepository;
        this.localizationService = localizationService;
    }

    public List<CarDTO> getCarsEM(){
        return carRepository.getCars().stream().
                map(car -> new CarDTO(car.getMake(), car.getModel(), car.getColor(),
                        car.getYear(), car.getHorsepower(), car.getRentfee(), car.getAvailable(),
                        localizationService.getCityName(car.getCurrentLocation())))
                .collect(Collectors.toList());
    }

    public CarDTO findByID(Integer carId) {
        Car car = carRepository.findByID(carId);
        return new CarDTO(car.getMake(), car.getModel(), car.getColor(), car.getYear(), car.getHorsepower(),
                            car.getRentfee(), car.getAvailable(), localizationService.getCityName(car.getCurrentLocation()));
    }
}
