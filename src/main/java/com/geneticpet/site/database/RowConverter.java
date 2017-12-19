package com.geneticpet.site.database;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface RowConverter<A> {
    A convert(ResultSet rs) throws SQLException;
}