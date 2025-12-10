package com.farmacia.prototipo.dao;

import com.farmacia.prototipo.model.DetalleCompra;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

public class DetalleCompraDAO extends TemplateDAO<DetalleCompra> {
    
    private static final String SQL_INSERT =
        "INSERT INTO detalle_compras (id_compra, id_producto, id_lote, cantidad, precio_unitario, subtotal) " +
        "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_LIST =
        "SELECT * FROM detalle_compras WHERE id_compra = ?";
    
    private static final RowMapper<DetalleCompra> MAPPER = rs -> {
        DetalleCompra d = new DetalleCompra();
        d.setIdDetalleCompra(rs.getInt("id_det_compra"));
        d.setIdCompra(rs.getInt("id_compra"));
        d.setIdProducto(rs.getInt("id_producto"));
        d.setCantidad(rs.getInt("cantidad"));
        d.setCostoUnitario(rs.getObject("costo_unitario", BigDecimal.class));
        d.setLoteCodigo(rs.getString("lote_codigo"));
        d.setLoteVencimiento(rs.getObject("lote_vencimiento", LocalDate.class));
        return d;
    };

    public boolean insertarDetalleCompra(DetalleCompra d) {
        return update(SQL_INSERT, ps -> {
            ps.setInt(1, d.getIdCompra());
            ps.setInt(2, d.getIdProducto());
            ps.setInt(3, d.getCantidad());
            ps.setBigDecimal(4, d.getCostoUnitario());
            ps.setString(5, d.getLoteCodigo());
            ps.setDate(6, Date.valueOf(d.getLoteVencimiento()));
        });
    }

    public List<DetalleCompra> obtenerDetallesPorCompra(int idCompra) {
        return queryList(SQL_LIST, ps -> ps.setInt(1, idCompra), MAPPER);
    }
}
