package com.farmacia.prototipo.dao;

import com.farmacia.prototipo.config.ConnectBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class TemplateDAO<T> {

    @FunctionalInterface
    protected interface RowMapper<T> {
        T mapRow(ResultSet rs) throws SQLException;
    }

    @FunctionalInterface
    protected interface ParameterSetter {
        void setParameters(PreparedStatement ps) throws SQLException;
    }

    // SELECT -> SINGLE RESULT
    protected Optional<T> querySingle(String sql, ParameterSetter setter, RowMapper<T> mapper) {
        try (Connection con = ConnectBD.connectBD();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (setter != null)
                setter.setParameters(ps);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapper.mapRow(rs));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error SQL (querySingle): " + e.getMessage());
        }

        return Optional.empty();
    }

    // SELECT -> LIST
    protected List<T> queryList(String sql, ParameterSetter setter, RowMapper<T> mapper) {

        List<T> lista = new ArrayList<>();

        try (Connection con = ConnectBD.connectBD();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (setter != null)
                setter.setParameters(ps);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapper.mapRow(rs));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error SQL (queryList): " + e.getMessage());
        }

        return lista;
    }

    // INSERT / UPDATE / DELETE
    protected boolean update(String sql, ParameterSetter setter) {

        try (Connection con = ConnectBD.connectBD();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (setter != null)
                setter.setParameters(ps);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error SQL (update): " + e.getMessage());
            return false;
        }
    }
}
