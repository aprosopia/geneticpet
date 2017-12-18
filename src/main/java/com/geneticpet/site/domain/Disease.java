package com.geneticpet.site.domain;

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


    public Disease(int id, String name, String system, String description,
                   List<BreedListEntry> affectedBreeds) {
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

        Disease that = (Disease) o;

        if (id != that.id) return false;
        if (!name.equals(that.name)) return false;
        if (!system.equals(that.system)) return false;
        if (!description.equals(that.description)) return false;
        return affectedBreeds.equals(that.affectedBreeds);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + system.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + affectedBreeds.hashCode();
        return result;
    }
}
