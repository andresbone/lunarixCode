
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

public class Lote {

    private int idLote;
    private int idProducto;         
    private String codigoLote;      
    private LocalDate fechaVencimiento;  
    private int cantidad;           
    private BigDecimal precioCompra;    
    private String estado;
    
    private String mostrarNombreProducto;

}
