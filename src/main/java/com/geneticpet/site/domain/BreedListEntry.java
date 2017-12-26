package com.geneticpet.site.domain;

/**
 * Created by aprosopia on 12/18/17.
 */
public class BreedListEntry {

    public final String name;
    public final int id;


    public BreedListEntry(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BreedListEntry that = (BreedListEntry) o;

        if (id != that.id) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + id;
        return result;
    }

    @Override
    public String toString() {
        return "BreedListEntry{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
