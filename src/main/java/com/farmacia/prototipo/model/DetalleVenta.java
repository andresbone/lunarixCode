
package com.farmacia.prototipo.model;

/*
 * @author hitomi
*/

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                // Genera Getters, Setters, toString, etc.
@NoArgsConstructor   // Constructor vacío (necesario para el DAO)
@AllArgsConstructor  // Constructor lleno

public class DetalleVenta {

    private int idDetalleVenta;
    private int idVenta;
    private int idProducto;
    private int idLote;
    private int cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
    
}
