package com.geneticpet.site.dao;

import com.geneticpet.site.domain.Breed;
import com.geneticpet.site.domain.Disease;
import com.geneticpet.site.domain.DiseaseListEntry;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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


        int d1Id = diseaseDao.saveAndGenerateId(new Disease(-1, "cataracts", "eyes", "desc1", null));
        int d2Id = diseaseDao.saveAndGenerateId(new Disease(-1, "bataracts", "eyes", "desc1", null));


        int b1Id = breedDao.saveAndGenerateId(new Breed(-1, "Akita", "dog", null));

        affectingBreedsDao.link(d1Id, b1Id);
        affectingBreedsDao.link(d2Id, b1Id);


        Map<String, List<DiseaseListEntry>> result = diseaseDao.listEntryDiseasesForBreedBySystem(b1Id);

        List<DiseaseListEntry> eyeDiseases = result.get("eyes");

        assertNotNull(eyeDiseases);

        assertThat(eyeDiseases, Matchers.hasItems(new DiseaseListEntry(d1Id, "cataracts"),
                new DiseaseListEntry(d2Id,
                        "bataracts")));


    }

    @Test
    public void listDiseases() {
        Disease d1 = new Disease(-1, "cataracts", "eyes", "desc1", null);
        Disease d2 = new Disease(-1, "bataracts", "eyes", "desc1", null);

        int d1Id = diseaseDao.saveAndGenerateId(d1);
        int d2Id = diseaseDao.saveAndGenerateId(d2);

        List<DiseaseListEntry> diseases = diseaseDao.listDiseases();
        assertThat(diseases, Matchers.hasItems(new DiseaseListEntry(d1Id, d1.name), new DiseaseListEntry(d2Id, d2.name)));
    }

    @Test
    public void readDiseaseById() {
        Disease d1 = new Disease(-1, "cataracts", "eyes", "desc1", new ArrayList<>());
        int d1Id = diseaseDao.saveAndGenerateId(d1);

        assertTrue(diseaseDao.readDiseaseById(d1Id).equals(new Disease(d1Id,d1.name,d1.system,d1.description,new ArrayList<>())));
    }

}