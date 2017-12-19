package com.geneticpet.site.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbUtil {

    /**
     *
     * Converts a ResultSet into List of A
     *
     * @param rs
     * @param converter
     * @param <A>
     * @return
     */
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


    /**
     * Get a single A. If there are no results, return null.
     */
    public static <A> A getSingle(ResultSet rs, RowConverter<A> converter) {

        try {
            if (!rs.next()) {
                return null;
            }
            return converter.convert(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Get a unique A. If there are no results, return null. If there is more than one result, throw IllegalStateException
     */
    public static <A> A getUnique(ResultSet rs, RowConverter<A> converter) {


        A result = getSingle(rs, converter);
        try {
            if (rs.next()) {
                throw new IllegalStateException("more than 1 result");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
