package com.geneticpet.site.dao;

import com.geneticpet.site.database.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by aprosopia on 12/26/17.
 */

@Component
public class DiseasesAffectingBreedsDao {


    @Autowired
    Database database;

    public int link(int diseaseId, int breedId) {

        try {

            try (Connection connection = database.getConnection()) {

                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO geneticpet.DISEASES_AFFECTING_BREEDS (disease_id, breed_id) VALUES (?,?)");
                preparedStatement.setInt(1, diseaseId);
                preparedStatement.setInt(2, breedId);
                return preparedStatement.executeUpdate();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
