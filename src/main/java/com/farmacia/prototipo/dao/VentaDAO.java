package com.farmacia.prototipo.dao;

import com.farmacia.prototipo.model.Venta;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class VentaDAO extends TemplateDAO<Venta> {
    
    private static final String SQL_INSERT =
        "INSERT INTO ventas (fecha_venta, id_usuario, total) VALUES (?, ?, ?)";

    private static final String SQL_GET =
        "SELECT * FROM ventas WHERE id_venta = ?";

    private static final String SQL_LIST =
        "SELECT * FROM ventas ORDER BY fecha_venta DESC";

    private static final String SQL_FECHA =
        "SELECT * FROM ventas WHERE DATE(fecha_venta) = ?";

    private static final String SQL_RANGO =
        "SELECT * FROM ventas WHERE fecha_venta BETWEEN ? AND ? ORDER BY fecha_venta DESC";


    private static final RowMapper<Venta> MAPPER = rs -> {
        Venta v = new Venta();
        v.setIdVenta(rs.getInt("id_venta"));
        v.setIdUsuario(rs.getInt("id_usuario"));
        v.setNombreCliente(rs.getString("cliente_nombre"));
        v.setFechaVenta(rs.getObject("fecha_venta", LocalDate.class));
        v.setTotalVenta(rs.getObject("total", BigDecimal.class));
        return v;
    };

    public boolean registrarVenta(Venta v) {
        return update(SQL_INSERT, ps -> {
            ps.setDate(1, Date.valueOf(v.getFechaVenta()));
            ps.setInt(2, v.getIdUsuario());
            ps.setBigDecimal(3, v.getTotalVenta());
        });
    }

    public Optional<Venta> obtenerVentaPorId(int id) {
        return querySingle(SQL_GET, ps -> ps.setInt(1, id), MAPPER);
    }

    public List<Venta> listarVentas() {
        return queryList(SQL_LIST, ps -> {}, MAPPER);
    }

    public List<Venta> listarVentasDelDia(LocalDate fecha) {
        return queryList(SQL_FECHA, ps -> ps.setDate(1, Date.valueOf(fecha)), MAPPER);
    }

    public List<Venta> listarVentasEntreFechas(LocalDate inicio, LocalDate fin) {
        return queryList(SQL_RANGO, ps -> {
            ps.setDate(1, Date.valueOf(inicio));
            ps.setDate(2, Date.valueOf(fin));
        }, MAPPER);
    }

}

