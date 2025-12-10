package com.farmacia.prototipo.dao;

import com.farmacia.prototipo.model.Producto;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ProductoDAO extends TemplateDAO<Producto> {

    private static final String SQL_GET_BY_ID =
            "SELECT * FROM productos WHERE id_producto = ?";

    private static final String SQL_GET_ALL =
            "SELECT * FROM productos";

    private static final RowMapper<Producto> MAPPER = rs -> {
        Producto p = new Producto();
        p.setId(rs.getInt("id_producto"));
        p.setCodigoBarras(rs.getString("codigo_barras"));
        p.setNombre(rs.getString("nombre"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setUnidadDeMedida(rs.getString("unidad_medida"));
        p.setStockMinimo(rs.getInt("stock_minimo"));
        p.setPrecioVenta(rs.getObject("precio_venta", BigDecimal.class));
        p.setTieneReceta(rs.getBoolean("tiene_receta"));
        p.setStockTotal(rs.getInt("stock_total"));
        return p;
    };

    public Optional<Producto> obtenerPorId(int id) {
        return querySingle(SQL_GET_BY_ID,
                ps -> ps.setInt(1, id),
                MAPPER
        );
    }

    public List<Producto> obtenerTodos() {
        return queryList(SQL_GET_ALL, ps -> {}, MAPPER);
    }

    public List<Producto> buscarPorNombre(String textoBusqueda) {
        String sql = "SELECT * FROM productos WHERE nombre LIKE ?";
        return queryList(sql,
                ps -> ps.setString(1, "%" + textoBusqueda + "%"),
                MAPPER
        );
    }
}
