package com.geneticpet.site.domain;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * Created by aprosopia on 12/18/17.
 */
public class Breed {
    public final int id;
    public final String name;
    public final String species;
    public final Map<String, List<DiseaseListEntry>> diseasesBySystem;


    @JsonCreator
    public Breed(@JsonProperty("id") int id, @JsonProperty("name") String name,
                 @JsonProperty("species") String species, @JsonProperty("diseasesBySystem") Map<String, List<DiseaseListEntry>> diseasesBySystem) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.diseasesBySystem = diseasesBySystem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Breed breed = (Breed) o;

        if (id != breed.id) return false;
        if (!name.equals(breed.name)) return false;
        if (!species.equals(breed.species)) return false;
        return diseasesBySystem != null ? diseasesBySystem.equals(breed.diseasesBySystem) : breed.diseasesBySystem == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + species.hashCode();
        result = 31 * result + (diseasesBySystem != null ? diseasesBySystem.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Breed{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", diseasesBySystem=" + diseasesBySystem +
                '}';
    }
}
