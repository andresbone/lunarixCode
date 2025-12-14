package com.farmacia.prototipo.model;

/*
 * @author andres Bone
*/

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data; 
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Producto {

    private int id;
    private String codigoBarras;
    private String nombre;
    private String descripcion;
    private String unidadDeMedida;
    private int stockMinimo;
    private BigDecimal precioVenta;
    private Boolean tieneReceta;
    private int stockTotal;

}
