
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

public class Venta {
    
    private int idVenta;
    private int idUsuario;
    private String nombreCliente;
    private LocalDate fechaVenta;
    private BigDecimal totalVenta;
    
}
