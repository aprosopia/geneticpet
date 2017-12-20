package com.geneticpet.site.dao;

import com.geneticpet.site.database.Database;
import com.geneticpet.site.database.DbUtil;
import com.geneticpet.site.database.RowConverter;
import com.geneticpet.site.domain.Breed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


/**
 * Created by aprosopia on 12/19/17.
 */

@Component
public class BreedDao {

    @Autowired
    Database database;

    private static final RowConverter<Breed> BREED_CONVERTER = rs -> new Breed(rs.getInt("id"),
            rs.getString("name"), rs.getString("species"), new HashMap<>());


    /**
     * Ignores the breed id, it will generate one automatically
     *
     * @param breed
     * @return generated breed id
     */
    public int saveAndGenerateId(Breed breed) {


        PreparedStatement preparedStatement = database.prepareStatement("INSERT INTO BREED (name,species) VALUES (?,?)");

        try {
            preparedStatement.setString(1, breed.name);
            preparedStatement.setString(2, breed.species);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return database.executeUpdateAndReturnGeneratedKeys(preparedStatement);

    }

    public void save(Breed breed) {


        PreparedStatement preparedStatement = database.prepareStatement("INSERT INTO BREED (id, name, species) VALUES (?,?,?)");

        try {
            preparedStatement.setInt(1, breed.id);
            preparedStatement.setString(2, breed.name);
            preparedStatement.setString(3, breed.species);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        database.execute(preparedStatement);

    }


    public Breed readById(int id) {
        PreparedStatement ps = database.prepareStatement("SELECT * FROM BREED WHERE ID = ? ");

        try {
            ps.setInt(1, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ResultSet rs = database.executeQuery(ps);
        return DbUtil.getSingle(rs, BREED_CONVERTER);
    }


}