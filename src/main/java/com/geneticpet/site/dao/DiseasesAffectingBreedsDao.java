package com.geneticpet.site.dao;

import com.geneticpet.site.database.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by aprosopia on 12/26/17.
 */

@Component
public class DiseasesAffectingBreedsDao {


    @Autowired
    Database database;

    public void link(int diseaseId, int breedId) {

        PreparedStatement preparedStatement = database.prepareStatement("INSERT INTO DISEASES_AFFECTING_BREEDS (disease_id, breed_id) VALUES (?,?)");

        try {
            preparedStatement.setInt(1, diseaseId);
            preparedStatement.setInt(2, breedId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        database.execute(preparedStatement);

    }

}
