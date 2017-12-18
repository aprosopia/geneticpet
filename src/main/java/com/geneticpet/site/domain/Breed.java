package com.geneticpet.site.domain;

import java.util.Map;

/**
 * Created by aprosopia on 12/18/17.
 */
public class Breed {
    public final int id;
    public final String title;
    public final Map<String, DiseaseListEntry> diseasesBySystem;


    public Breed(int id, String title, Map<String, DiseaseListEntry> diseasesBySystem) {
        this.id = id;
        this.title = title;
        this.diseasesBySystem = diseasesBySystem;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Breed that = (Breed) o;

        if (id != that.id) return false;
        if (!title.equals(that.title)) return false;
        return diseasesBySystem.equals(that.diseasesBySystem);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + title.hashCode();
        result = 31 * result + diseasesBySystem.hashCode();
        return result;
    }
}
