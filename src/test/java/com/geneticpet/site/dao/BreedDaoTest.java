package com.geneticpet.site.dao;

import com.geneticpet.site.domain.Breed;
import com.geneticpet.site.domain.DiseaseListEntry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by aprosopia on 12/19/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BreedDaoTest {

    @Autowired
    BreedDao breedDao;

    @Test
    public void readById() throws Exception {


        Breed breed = new Breed(-1, "akita", "dog", new HashMap<>());

        int generatedId = breedDao.saveAndGenerateId(breed);

        Breed expected = new Breed(generatedId,breed.name,breed.species,breed.diseasesBySystem);

        Breed breedFromDb = breedDao.readById(generatedId);

        assertTrue(breedFromDb.equals(expected));


    }

}