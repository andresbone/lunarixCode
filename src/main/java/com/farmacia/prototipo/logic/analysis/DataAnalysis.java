package com.farmacia.prototipo.logic.analysis;

/*
 * @author hitomi
 */
import com.farmacia.prototipo.model.Lote;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class DataAnalysis {


    private long tiempoVidaUtil(Lote lote) {
        
        LocalDate hoy = LocalDate.now();
        
        return ChronoUnit.DAYS.between(hoy, lote.getFechaVencimiento());
    }

    public enum EstadoLote {
        NORMAL,
        OFERTA,
        DEVOLUCION,
        VENCIDO
    }

    public EstadoLote calcularEstadoLote(Lote lote) {
        
        long dias = tiempoVidaUtil(lote);

        if (dias <= 0) {
            return EstadoLote.VENCIDO;
        } else if (dias <= 65) {
            return EstadoLote.DEVOLUCION;
        } else if (dias <= 185) {
            return EstadoLote.OFERTA;
        } else {
            return EstadoLote.NORMAL;
        }
    }

    public BigDecimal valorTotalInventario(List<Lote> inventario) {
        
        BigDecimal valorTotal = BigDecimal.ZERO;
        
        for (Lote lote : inventario) {
            
            BigDecimal precio = lote.getPrecioCompra();
            BigDecimal cantidad = BigDecimal.valueOf(lote.getCantidad());

            valorTotal = valorTotal.add(precio.multiply(cantidad));
        }
        return valorTotal;
    }

}
