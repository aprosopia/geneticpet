package com.geneticpet.site.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by aprosopia on 12/18/17.
 */
public class Disease {

    public final int id;
    public final String name;
    public final String system;
    public final String description;
    public final List<BreedListEntry> affectedBreeds;


    public Disease(@JsonProperty("id") int id, @JsonProperty("name") String name,
                   @JsonProperty("system") String system, @JsonProperty("description") String description,
                   @JsonProperty("affectedBreeds") List<BreedListEntry> affectedBreeds) {
        this.id = id;
        this.name = name;
        this.system = system;
        this.description = description;
        this.affectedBreeds = affectedBreeds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Disease disease = (Disease) o;

        if (id != disease.id) return false;
        if (!name.equals(disease.name)) return false;
        if (!system.equals(disease.system)) return false;
        if (!description.equals(disease.description)) return false;
        return affectedBreeds != null ? affectedBreeds.equals(disease.affectedBreeds) : disease.affectedBreeds == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + system.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + (affectedBreeds != null ? affectedBreeds.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Disease{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", system='" + system + '\'' +
                ", description='" + description + '\'' +
                ", affectedBreeds=" + affectedBreeds +
                '}';
    }
}
