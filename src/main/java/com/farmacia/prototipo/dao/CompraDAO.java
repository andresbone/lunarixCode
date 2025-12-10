package com.farmacia.prototipo.dao;

import com.farmacia.prototipo.model.Compra;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class CompraDAO extends TemplateDAO<Compra> {
    
    private static final String SQL_INSERT =
        "INSERT INTO compras (fecha_compra, id_proveedor, total) VALUES (?, ?, ?)";

    private static final String SQL_GET_BY_ID =
        "SELECT * FROM compras WHERE id_compra = ?";

    private static final String SQL_GET_ALL =
        "SELECT * FROM compras ORDER BY fecha_compra DESC";

    private static final String SQL_GET_FECHAS =
        "SELECT * FROM compras WHERE fecha_compra BETWEEN ? AND ? ORDER BY fecha_compra DESC";

    private static final String SQL_GET_BY_PROV =
        "SELECT * FROM compras WHERE id_proveedor = ? ORDER BY fecha_compra DESC";

    private static final RowMapper<Compra> MAPPER = rs -> {
        Compra c = new Compra();
        c.setIdCompra(rs.getInt("id_compra"));
        c.setFechaCompra(rs.getObject("fecha_compra", LocalDate.class));
        c.setIdProveedor(rs.getInt("id_proveedor"));
        c.setTotalCompra(rs.getObject("total", BigDecimal.class));
        return c;
    };

    public boolean registrarCompra(Compra compra) {
        return update(SQL_INSERT, ps -> {
            ps.setDate(1, Date.valueOf(compra.getFechaCompra()));
            ps.setInt(2, compra.getIdProveedor());
            ps.setBigDecimal(3, compra.getTotalCompra());
        });
    }

    public Optional<Compra> obtenerCompraPorId(int id) {
        return querySingle(SQL_GET_BY_ID, ps -> ps.setInt(1, id), MAPPER);
    }

    public List<Compra> listarCompras() {
        return queryList(SQL_GET_ALL, ps -> {}, MAPPER);
    }

    public List<Compra> listarComprasEntreFechas(LocalDate inicio, LocalDate fin) {
        return queryList(SQL_GET_FECHAS, ps -> {
            ps.setDate(1, Date.valueOf(inicio));
            ps.setDate(2, Date.valueOf(fin));
        }, MAPPER);
    }

    public List<Compra> listarComprasPorProveedor(int idProveedor) {
        return queryList(SQL_GET_BY_PROV, ps -> ps.setInt(1, idProveedor), MAPPER);
    }


}
