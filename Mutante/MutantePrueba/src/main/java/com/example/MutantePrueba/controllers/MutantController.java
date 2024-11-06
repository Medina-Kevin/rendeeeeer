package com.example.MutantePrueba.controllers;

import com.example.MutantePrueba.model.RequestDNA;
import com.example.MutantePrueba.services.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mutant/")
public class MutantController {

    @Autowired
    private MutantService mutantService;

    @PostMapping
    public ResponseEntity<String> isMutant(@RequestBody RequestDNA dnaRequest) {
        boolean isMutant = mutantService.isMutant(dnaRequest.getDna());
        if (isMutant) {
            return new ResponseEntity<>("¡¡¡ Es mutante !!!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("¡¡¡ No es mutante !!!", HttpStatus.FORBIDDEN);
        }
    }
}
