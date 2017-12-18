package com.geneticpet.site.controllers;

import com.geneticpet.site.domain.Breed;
import com.geneticpet.site.domain.Disease;
import com.geneticpet.site.domain.DiseaseListEntry;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/disease")
public class DiseaseController {


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<DiseaseListEntry>> list() {
        return null;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Disease> byId(@PathVariable("id") int id) {

        return null;
    }


    @RequestMapping(value = "/create/", method = RequestMethod.POST)
    public ResponseEntity createDisease(@RequestBody Disease disease,
                                        UriComponentsBuilder ucBuilder) throws URISyntaxException {


        return null;
    }

}
