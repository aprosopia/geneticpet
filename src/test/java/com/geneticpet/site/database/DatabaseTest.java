package com.geneticpet.site.database;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseTest {

    @Autowired
    Database database;


    @Test
    public void executeInsertAndGetId() throws Exception {

        PreparedStatement insert = database.prepareStatement("INSERT INTO BREED(NAME,SPECIES) VALUES (?,?)");
        insert.setString(1, "name");
        insert.setString(2, "system");
        int id1 = database.executeUpdateAndReturnGeneratedKeys(insert);

        int id2 = database.executeUpdateAndReturnGeneratedKeys(insert);

        assertTrue (id1 + 1 == id2);

    }

}