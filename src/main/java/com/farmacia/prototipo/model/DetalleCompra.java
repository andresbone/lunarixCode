
package com.farmacia.prototipo.model;

/*
 * @author hitomi
*/

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                // Genera Getters, Setters, toString, etc.
@NoArgsConstructor   // Constructor vacío (necesario para el DAO)
@AllArgsConstructor  // Constructor lleno

public class DetalleCompra {
    
    private int idDetalleCompra;
    private int idCompra;
    private int idProducto;
    private int cantidad;
    private BigDecimal costoUnitario;
    private String loteCodigo;
    private LocalDate loteVencimiento;
    
}
