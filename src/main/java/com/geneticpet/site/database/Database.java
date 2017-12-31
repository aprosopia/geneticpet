package com.geneticpet.site.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Repository
public class Database {

    @Autowired
    private DataSource dataSource;


    public Connection getConnection() throws SQLException {

        return dataSource.getConnection();
    }

}
