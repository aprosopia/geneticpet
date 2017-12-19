package com.geneticpet.site.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbUtil {

    public static <A> List<A> asList(ResultSet rs, RowConverter<A> converter) {

        ArrayList<A> list = new ArrayList<>();

        try {
            while (rs.next()) {

                list.add(converter.convert(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;

    }
}
