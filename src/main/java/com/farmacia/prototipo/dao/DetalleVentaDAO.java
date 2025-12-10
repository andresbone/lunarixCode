
package com.farmacia.prototipo.dao;

import com.farmacia.prototipo.model.DetalleVenta;
import java.math.BigDecimal;
import java.util.List;

public class DetalleVentaDAO extends TemplateDAO<DetalleVenta> {

    private static final String SQL_INSERT =
        "INSERT INTO detalle_ventas (id_venta, id_producto, id_lote, cantidad, precio_unitario, subtotal) " +
        "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_LIST =
        "SELECT * FROM detalle_ventas WHERE id_venta = ?";

    private static final RowMapper<DetalleVenta> MAPPER = rs -> {
        DetalleVenta d = new DetalleVenta();
        d.setIdDetalleVenta(rs.getInt("id_det_venta"));
        d.setIdVenta(rs.getInt("id_venta"));
        d.setIdProducto(rs.getInt("id_producto"));
        d.setIdLote(rs.getInt("id_lote"));
        d.setCantidad(rs.getInt("cantidad"));
        d.setPrecioUnitario(rs.getObject("precio_unitario", BigDecimal.class));
        d.setSubtotal(rs.getObject("subtotal", BigDecimal.class));
        return d;
    };

    public boolean insertarDetalleVenta(DetalleVenta d) {
        return update(SQL_INSERT, ps -> {
            ps.setInt(1, d.getIdVenta());
            ps.setInt(2, d.getIdProducto());
            ps.setInt(3, d.getIdLote());
            ps.setInt(4, d.getCantidad());
            ps.setBigDecimal(5, d.getPrecioUnitario());
            ps.setBigDecimal(6, d.getSubtotal());
        });
    }

    public List<DetalleVenta> obtenerDetallesPorVenta(int idVenta) {
        return queryList(SQL_LIST, ps -> ps.setInt(1, idVenta), MAPPER);
    }
}
