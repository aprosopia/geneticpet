package com.geneticpet.site.controllers;

import com.geneticpet.site.domain.Breed;
import com.geneticpet.site.domain.BreedListEntry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URISyntaxException;
import java.util.ArrayList;


@RestController
@RequestMapping("/breed")
public class BreedController {


    @RequestMapping(value = "/{species}", method = RequestMethod.GET)
    public ResponseEntity bySpecies(@PathVariable("species") String species) {

        ArrayList<BreedListEntry> breeds = new ArrayList<>();


        return new ResponseEntity<>(breeds, HttpStatus.OK);
    }


    @RequestMapping(value = "/{species}/{id}", method = RequestMethod.GET)
    public ResponseEntity<Breed> bySpeciesAndId(@PathVariable("species") String species,
                                                @PathVariable("id") int id) {

        return null;
    }

    @RequestMapping(value = "/create/", method = RequestMethod.POST)
    public ResponseEntity createBreed(@RequestBody Breed breed, UriComponentsBuilder ucBuilder) throws URISyntaxException {


        return null;
    }

}