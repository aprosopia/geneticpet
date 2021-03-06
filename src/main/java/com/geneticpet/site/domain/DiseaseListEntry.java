package com.geneticpet.site.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by aprosopia on 12/18/17.
 */
public class DiseaseListEntry {
    public final int id;
    public final String name;

    public DiseaseListEntry(@JsonProperty("id")int id,@JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiseaseListEntry that = (DiseaseListEntry) o;

        if (id != that.id) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "DiseaseListEntry{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
