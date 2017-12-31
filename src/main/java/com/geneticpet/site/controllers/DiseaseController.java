package com.geneticpet.site.controllers;

import com.geneticpet.site.dao.DiseaseDao;
import com.geneticpet.site.domain.BreedListEntry;
import com.geneticpet.site.domain.Disease;
import com.geneticpet.site.domain.DiseaseListEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/disease")
public class DiseaseController {

    @Autowired
    DiseaseDao diseaseDao;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<DiseaseListEntry>> list() {
        List<DiseaseListEntry> diseases = diseaseDao.listDiseases();
        return new ResponseEntity<>(diseases, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Disease> byId(@PathVariable("id") int id) {
        Disease disease = diseaseDao.readDiseaseById(id);

        List<BreedListEntry> affectedBreedsForDiseaseId = diseaseDao.getAffectedBreedsForDiseaseId(id);

        Disease result = new Disease(disease.id, disease.name, disease.system, disease.description,
                affectedBreedsForDiseaseId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/create/", method = RequestMethod.POST)
    public ResponseEntity createDisease(@RequestBody Disease disease,
                                        UriComponentsBuilder ucBuilder) throws URISyntaxException {


        return null;
    }

}
