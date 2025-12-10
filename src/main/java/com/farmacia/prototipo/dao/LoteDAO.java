package com.farmacia.prototipo.dao;

import com.farmacia.prototipo.model.Lote;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class LoteDAO extends TemplateDAO<Lote> {

    private static final String SQL_GET_BY_ID
            = "SELECT l.*, p.nombre AS nombre_producto "
            + "FROM lotes l "
            + "LEFT JOIN productos p ON l.id_producto = p.id_producto "
            + "WHERE l.id_lote = ?";

    private static final String SQL_INVENTARIO
            = "SELECT l.*, p.nombre AS nombre_producto "
            + "FROM lotes l "
            + "INNER JOIN productos p ON l.id_producto = p.id_producto "
            + "WHERE l.estado = 'Activo' "
            + "ORDER BY l.fecha_vencimiento ASC";

    private static final String SQL_LOTES_POR_PRODUCTO
            = "SELECT l.*, p.nombre AS nombre_producto "
            + "FROM lotes l "
            + "LEFT JOIN productos p ON l.id_producto = p.id_producto "
            + "WHERE l.id_producto = ? AND l.cantidad > 0 AND l.estado = 'Activo' "
            + "ORDER BY l.fecha_vencimiento ASC";

    private static final String SQL_INSERT
            = "INSERT INTO lotes (id_producto, codigo_lote, fecha_vencimiento, cantidad, precio_compra, estado) "
            + "VALUES (?, ?, ?, ?, ?, 'Activo')";

    private static final String SQL_DESCONTAR_STOCK 
            = "UPDATE lotes SET "
            + "cantidad = cantidad - ?, "
            + "estado = CASE WHEN (cantidad - ?) <= 0 THEN 'Agotado' ELSE estado END "
            + "WHERE id-lote - ?";

    private static final RowMapper<Lote> MAPPER = rs -> {
        Lote lot = new Lote();
        lot.setIdLote(rs.getInt("id_lote"));
        lot.setIdProducto(rs.getInt("id_producto"));
        lot.setCodigoLote(rs.getString("codigo_lote"));
        lot.setFechaVencimiento(rs.getObject("fecha_vencimiento", LocalDate.class));
        lot.setCantidad(rs.getInt("cantidad"));
        lot.setPrecioCompra(rs.getObject("precio_compra", BigDecimal.class));
        lot.setEstado(rs.getString("estado"));

        String nombreProducto = rs.getString("nombre_producto");
        lot.setMostrarNombreProducto(nombreProducto != null ? nombreProducto : "---");

        return lot;
    };

    //obtener 1 lote por ID
    public Optional<Lote> obtenerPorId(int idLote) {
        return querySingle(SQL_GET_BY_ID,
                ps -> ps.setInt(1, idLote),
                MAPPER
        );
    }

    //Inventario completo ordenado por fecha
    public List<Lote> obtenerInventario() {
        return queryList(SQL_INVENTARIO, ps -> {
        }, MAPPER);
    }

    //Lotes por producto (para determinar desde qué lote vender)
    public List<Lote> obtenerLotesPorProducto(int idProducto) {
        return queryList(SQL_LOTES_POR_PRODUCTO,
                ps -> ps.setInt(1, idProducto),
                MAPPER
        );
    }

    //Insertar un lote nuevo
    public boolean insertarLote(Lote lote) {
        return update(SQL_INSERT, ps -> {
            ps.setInt(1, lote.getIdProducto());
            ps.setString(2, lote.getCodigoLote());
            ps.setDate(3, Date.valueOf(lote.getFechaVencimiento()));
            ps.setInt(4, lote.getCantidad());
            ps.setBigDecimal(5, lote.getPrecioCompra());
        });
    }

     //Restar cantidad de un lote (se usa en ventas)
    public boolean restarCantidad(int idLote, int cantidad) {
        return update(SQL_DESCONTAR_STOCK, ps -> {
            ps.setInt(1, cantidad);
            ps.setInt(2, cantidad);
            ps.setInt(3, idLote);
        });
    }

}
