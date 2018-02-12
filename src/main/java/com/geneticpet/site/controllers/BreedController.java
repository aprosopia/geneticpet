package com.geneticpet.site.controllers;

import com.geneticpet.site.dao.BreedDao;
import com.geneticpet.site.dao.DiseaseDao;
import com.geneticpet.site.domain.Breed;
import com.geneticpet.site.domain.BreedListEntry;
import com.geneticpet.site.domain.DiseaseListEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/breed")
public class BreedController {


    @Autowired
    BreedDao breedDao;

    @Autowired
    DiseaseDao diseaseDao;

    @RequestMapping(value = "/{species}", method = RequestMethod.GET)
    public ResponseEntity<List<BreedListEntry>> bySpecies(@PathVariable("species") String species) {

        List<BreedListEntry> breeds = breedDao.readBySpecies(species);

        return new ResponseEntity<>(breeds, HttpStatus.OK);
    }


    @RequestMapping(value = "/{species}/{id}", method = RequestMethod.GET)
    public ResponseEntity<Breed> bySpeciesAndId(@PathVariable("species") String species,
                                                @PathVariable("id") int id) {
        Breed breed = breedDao.readBySpeciesAndId(species, id);
        Map<String, List<DiseaseListEntry>> diseasesForBreedBySystem = diseaseDao.listEntryDiseasesForBreedBySystem(breed.id);
        Breed result = new Breed(breed.id, breed.name, breed.species, diseasesForBreedBySystem);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/create/", method = RequestMethod.POST)
    public ResponseEntity createBreed(@RequestBody Breed breed, UriComponentsBuilder ucBuilder) throws URISyntaxException {


        int id = breedDao.saveAndGenerateId(breed);

        HttpHeaders headers = new HttpHeaders();
        URI uriForRedirect = ucBuilder.path("/breed/{species}/{id}").buildAndExpand(breed.species, id).toUri();
        headers.setLocation(uriForRedirect);

        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
}

