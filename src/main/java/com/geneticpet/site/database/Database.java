package com.geneticpet.site.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class Database {

    @Autowired
    private DataSource dataSource;

    public ResultSet executeQuery(PreparedStatement statement) {
        try {
            return statement.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void execute(PreparedStatement statement) {
        try {
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public PreparedStatement prepareStatement(String sql) {

        try {
            return dataSource.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int executeUpdateAndReturnGeneratedKeys(PreparedStatement statement) {

        try {

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new RuntimeException("No generated keys returned");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }

}
