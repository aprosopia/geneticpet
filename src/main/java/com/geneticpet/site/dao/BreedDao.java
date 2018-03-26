package com.geneticpet.site.dao;

import com.geneticpet.site.database.Database;
import com.geneticpet.site.database.DbUtil;
import com.geneticpet.site.database.RowConverter;
import com.geneticpet.site.domain.Breed;
import com.geneticpet.site.domain.BreedListEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.HashMap;
import java.util.List;

/**
 * Created by aprosopia on 12/19/17.
 */

@Component
public class BreedDao {

    @Autowired
    Database database;

    private static final RowConverter<Breed> BREED_CONVERTER = rs -> new Breed(rs.getInt("id"), rs.getString("name"),
            rs.getString("species"), new HashMap<>());

    /**
     * Ignores the breed id, it will generate one automatically
     *
     * @param breed
     * @return generated breed id
     */
    public int saveAndGenerateId(Breed breed) {


        try {

            try (Connection connection = database.getConnection()) {

                PreparedStatement preparedStatement = connection
                        .prepareStatement("INSERT INTO geneticpet.BREED(name,species) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, breed.name);
                preparedStatement.setString(2, breed.species);

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

    public int save(Breed breed) {


        try {

            try (Connection connection = database.getConnection()) {

                PreparedStatement preparedStatement = connection
                        .prepareStatement("INSERT INTO geneticpet.BREED (id, name, species) VALUES (?,?,?)");


                preparedStatement.setInt(1, breed.id);
                preparedStatement.setString(2, breed.name);
                preparedStatement.setString(3, breed.species);


                return preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public Breed readById(int id) {


        try {

            try (Connection connection = database.getConnection()) {

                PreparedStatement ps = connection.prepareStatement("SELECT * FROM geneticpet.BREED WHERE ID = ? ");

                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();
                return DbUtil.getSingle(rs, BREED_CONVERTER);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public Breed readBySpeciesAndId(String species, int id) {


        try {

            try (Connection connection = database.getConnection()) {

                PreparedStatement ps = connection.prepareStatement("SELECT * FROM geneticpet.BREED WHERE ID = ?  AND SPECIES = ?");

                ps.setInt(1, id);
                ps.setString(2, species);

                ResultSet rs = ps.executeQuery();
                return DbUtil.getSingle(rs, BREED_CONVERTER);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public List<BreedListEntry> readBySpecies(String species) {
        try {

            try (Connection connection = database.getConnection()) {

                PreparedStatement ps = connection.prepareStatement("SELECT * FROM geneticpet.BREED WHERE SPECIES = ?");

                ps.setString(1, species);

                ResultSet rs = ps.executeQuery();

                return DbUtil.asList(rs, rs1 -> new BreedListEntry(rs1.getString("name"), rs1.getInt("id")));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int deleteById(int id) {
        try {
            try (Connection connection = database.getConnection()) {
                PreparedStatement psAffected = connection.prepareStatement("DELETE FROM geneticpet.DISEASES_AFFECTING_BREEDS WHERE disease_id= ?");
                psAffected.setInt(1,id);
                psAffected.executeUpdate();
                PreparedStatement ps = connection.prepareStatement("DELETE FROM geneticpet.BREED WHERE ID = ?");
                ps.setInt(1, id);
                return ps.executeUpdate();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

