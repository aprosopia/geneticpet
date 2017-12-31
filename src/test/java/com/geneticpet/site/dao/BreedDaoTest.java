package com.geneticpet.site.dao;

import com.geneticpet.site.domain.Breed;
import com.geneticpet.site.domain.BreedListEntry;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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

        Breed expected = new Breed(generatedId, breed.name, breed.species, breed.diseasesBySystem);

        Breed breedFromDb = breedDao.readById(generatedId);

        assertTrue(breedFromDb.equals(expected));


    }

    @Test
    public void readBySpecies() throws Exception {
        Breed breed1 = new Breed(-1, "akita", "dog", new HashMap<>());
        Breed breed2 = new Breed(-1, "dkita", "dog", new HashMap<>());
        Breed breed3 = new Breed(-1, "fkita", "dog", new HashMap<>());

        int id1 = breedDao.saveAndGenerateId(breed1);
        int id2 = breedDao.saveAndGenerateId(breed2);
        int id3 = breedDao.saveAndGenerateId(breed3);
        List<BreedListEntry> list = new ArrayList<BreedListEntry>();
        list.add(new BreedListEntry(breed1.name, id1));
        list.add(new BreedListEntry(breed2.name, id2));
        list.add(new BreedListEntry(breed3.name, id3));

        List<BreedListEntry> result = breedDao.readBySpecies("dog");

        assertThat(result, Matchers.hasItems(new BreedListEntry(breed1.name, id1),
                new BreedListEntry(breed2.name, id2), new BreedListEntry(breed3.name, id3)));

    }
}

