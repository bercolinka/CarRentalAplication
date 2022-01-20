package com.example.CarRentalAplication.Controlers;

import com.example.CarRentalAplication.Services.LocalizationService;
import com.example.CarRentalAplication.contract.LocalizationDTO;
import com.example.CarRentalAplication.models.Localization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/localization")
public class LocalizationControler {
    private LocalizationService localizationService;

    @Autowired
    public LocalizationControler(LocalizationService localizationService) {
        this.localizationService = localizationService;
    }

    @GetMapping("/getLocalizations")
    public ResponseEntity<List<LocalizationDTO>> getLocalizations(){
        return new ResponseEntity<>(localizationService.getLocalizations(), HttpStatus.OK);
    }
}
