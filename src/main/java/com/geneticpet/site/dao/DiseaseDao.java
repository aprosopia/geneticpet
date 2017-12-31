package com.geneticpet.site.dao;

import com.geneticpet.site.database.Database;
import com.geneticpet.site.database.DbUtil;
import com.geneticpet.site.database.RowConverter;
import com.geneticpet.site.domain.BreedListEntry;
import com.geneticpet.site.domain.Disease;
import com.geneticpet.site.domain.DiseaseListEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aprosopia on 12/26/17.
 */
@Component
public class DiseaseDao {
    @Autowired
    Database database;


    private static final RowConverter<Disease> DISEASE_ROW_CONVERTER = rs -> new Disease(rs.getInt("id"), rs.getString("name"),
            rs.getString("system"), rs.getString("description"), new ArrayList<>());

    private static final RowConverter<BreedListEntry> BREED_LIST_ENTRY_ROW_CONVERTER = rs -> new BreedListEntry(rs.getString("name"), rs.getInt("id"));

    private static final RowConverter<DiseaseListEntry> DISEASE_LIST_ENTRY_ROW_CONVERTER = rs -> new DiseaseListEntry(rs.getInt("id"), rs.getString("name"));

    public Map<String, List<DiseaseListEntry>> listEntryDiseasesForBreedBySystem(int breedId) {


        try {

            try (Connection connection = database.getConnection()) {

                PreparedStatement ps = connection.prepareStatement("SELECT\n" +
                        "  DISEASE.name,\n" +
                        "  DISEASE.system,\n" +
                        "  DISEASE.description,\n" +
                        "  DISEASE.id\n" +
                        "FROM DISEASE\n" +
                        "  JOIN DISEASES_AFFECTING_BREEDS ON DISEASE.id = DISEASES_AFFECTING_BREEDS.disease_id\n" +
                        "  JOIN BREED ON DISEASES_AFFECTING_BREEDS.breed_id = BREED.id\n" +
                        "WHERE BREED.id = ?;");

                ps.setInt(1, breedId);

                ResultSet rs = ps.executeQuery();


                List<Disease> diseases = DbUtil.asList(rs, DISEASE_ROW_CONVERTER);

                Map<String, List<DiseaseListEntry>> diseasesBySystem = new HashMap<>();

                for (Disease d : diseases) {
                    if (diseasesBySystem.get(d.system) == null) {
                        List<DiseaseListEntry> list = new ArrayList<>();
                        diseasesBySystem.put(d.system, list);
                        list.add(new DiseaseListEntry(d.id, d.name));
                    } else {
                        List<DiseaseListEntry> list = diseasesBySystem.get(d.system);
                        list.add(new DiseaseListEntry(d.id, d.name));
                    }
                }

                return diseasesBySystem;

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public int saveAndGenerateId(Disease disease) {


        try {

            try (Connection connection = database.getConnection()) {

                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO DISEASE (name,system,description) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);

                preparedStatement.setString(1, disease.name);
                preparedStatement.setString(2, disease.system);
                preparedStatement.setString(3, disease.description);

                preparedStatement.executeUpdate();

                ResultSet rs = preparedStatement.getGeneratedKeys();

                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new RuntimeException("No generated keys returned");
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public int save(Disease disease) {

        try {

            try (Connection connection = database.getConnection()) {

                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO DISEASE (id,name,system,description) VALUES (?,?,?,?)");


                preparedStatement.setInt(1, disease.id);
                preparedStatement.setString(2, disease.name);
                preparedStatement.setString(3, disease.system);
                preparedStatement.setString(4, disease.description);

                return preparedStatement.executeUpdate();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public List<DiseaseListEntry> listDiseases() {


        try {

            try (Connection connection = database.getConnection()) {
                PreparedStatement ps = connection.prepareStatement("SELECT DISEASE.name,DISEASE.id FROM DISEASE");

                ResultSet rs = ps.executeQuery();

                return DbUtil.asList(rs, DISEASE_LIST_ENTRY_ROW_CONVERTER);


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Disease readDiseaseById(int diseaseID) {

        try {

            try (Connection connection = database.getConnection()) {

                PreparedStatement ps = connection.prepareStatement("SELECT * FROM DISEASE WHERE DISEASE.id = ?");
                try {
                    ps.setInt(1, diseaseID);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                ResultSet rs = ps.executeQuery();


                return DbUtil.getSingle(rs, DISEASE_ROW_CONVERTER);


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<BreedListEntry> getAffectedBreedsForDiseaseId(int diseaseId) {
        try {

            try (Connection connection = database.getConnection()) {


                PreparedStatement psAffectedBreeds = connection.prepareStatement("SELECT BREED.name, BREED.id\n" +
                        "FROM BREED\n" +
                        "  JOIN DISEASES_AFFECTING_BREEDS ON BREED.id = DISEASES_AFFECTING_BREEDS.breed_id\n" +
                        "JOIN DISEASE ON DISEASES_AFFECTING_BREEDS.disease_id = DISEASE.id WHERE DISEASE.id = ?;");

                psAffectedBreeds.setInt(1, diseaseId);

                ResultSet rsAffectedBreeds = psAffectedBreeds.executeQuery();

                return DbUtil.asList(rsAffectedBreeds, BREED_LIST_ENTRY_ROW_CONVERTER);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
