package com.geneticpet.site.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbUtil {
    public static <A> List<A> asList(ResultSet rs, RowConverter<A> converter) throws SQLException {

        ArrayList<A> list = new ArrayList<>();

        while (rs.next()) {

            list.add(converter.convert(rs));
        }

        return list;

    }
}
