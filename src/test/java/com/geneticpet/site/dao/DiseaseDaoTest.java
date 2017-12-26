package com.geneticpet.site.dao;

import com.geneticpet.site.domain.Breed;
import com.geneticpet.site.domain.Disease;
import com.geneticpet.site.domain.DiseaseListEntry;
import jdk.internal.instrumentation.TypeMapping;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by aprosopia on 12/26/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DiseaseDaoTest {


    @Autowired
    DiseaseDao diseaseDao;


    @Autowired
    BreedDao breedDao;

    @Autowired
    DiseasesAffectingBreedsDao affectingBreedsDao;


    @Test
    public void listEntryDiseasesForBreedBySystem() {


        diseaseDao.save(new Disease(1, "cataracts", "eyes", "desc1", null));
        diseaseDao.save(new Disease(2, "bataracts", "eyes", "desc1", null));


        breedDao.save(new Breed(1, "Akita", "dog", null));

        affectingBreedsDao.link(1, 1);
        affectingBreedsDao.link(2, 1);


        HashMap<String, List<DiseaseListEntry>> expected = new HashMap<>();
        List<DiseaseListEntry> diseasesListEntries = asList(new DiseaseListEntry(1, "cataracts"),
                new DiseaseListEntry(2, "bataracts"));
        expected.put("eyes", diseasesListEntries);


        assertThat(diseaseDao.listEntryDiseasesForBreedBySystem(1), is(expected));


    }

}