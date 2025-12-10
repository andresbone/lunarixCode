package com.farmacia.prototipo.model;

/*
 * @author andres Bone
*/

import java.math.BigDecimal;
import lombok.AllArgsConstructor; //Crea el constructor con todos los datos.
import lombok.Data; //Genera automáticamente Getters, Setters, toString, equals y hashCode.
import lombok.NoArgsConstructor; //Crea el constructor vacío: public Producto() {}

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
