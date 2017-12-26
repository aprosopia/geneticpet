package com.geneticpet.site.dao;

import com.geneticpet.site.domain.Breed;
import com.geneticpet.site.domain.Disease;
import com.geneticpet.site.domain.DiseaseListEntry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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


        int d1Id = diseaseDao.saveAndGenerateId(new Disease(-1, "cataracts", "eyes", "desc1", null));
        int d2Id = diseaseDao.saveAndGenerateId(new Disease(-1, "bataracts", "eyes", "desc1", null));


        int b1Id = breedDao.saveAndGenerateId(new Breed(-1, "Akita", "dog", null));

        affectingBreedsDao.link(d1Id, b1Id);
        affectingBreedsDao.link(d2Id, b1Id);


        HashMap<String, List<DiseaseListEntry>> expected = new HashMap<>();
        List<DiseaseListEntry> diseasesListEntries = asList(new DiseaseListEntry(d1Id, "cataracts"),
                new DiseaseListEntry(d2Id, "bataracts"));
        expected.put("eyes", diseasesListEntries);


        assertThat(diseaseDao.listEntryDiseasesForBreedBySystem(b1Id), is(expected));


    }

}