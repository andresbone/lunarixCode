package com.farmacia.prototipo.logic.service;

/*
 * @author hitomi
 */
import com.farmacia.prototipo.dao.LoteDAO;
import com.farmacia.prototipo.logic.analysis.DataAnalysis;
import com.farmacia.prototipo.logic.analysis.DataAnalysis.EstadoLote;
import com.farmacia.prototipo.model.Lote;
import java.math.BigDecimal;
import java.util.List;

public class InventarioService {

    private final LoteDAO loteDAO = new LoteDAO();
    private final DataAnalysis dataAnalysis = new DataAnalysis();

    public static class ResumenInventario {

        public int totalProductosUnicos;
        public int alertasStockBajo;
        public int alertasPorVencer;
        public BigDecimal valorMonetarioTotal;
        public List<Lote> listaCompletaLotes;
    }

    public ResumenInventario obtenerDatosDashboard() {

        ResumenInventario resumen = new ResumenInventario();

        // 1. Traer datos de la BD
        resumen.listaCompletaLotes = loteDAO.obtenerInventario();

        // 2. Calcular KPIs usando DataAnalysis
        resumen.totalProductosUnicos = (int) resumen.listaCompletaLotes.stream()
                .map(Lote::getIdProducto)
                .distinct()
                .count();
        resumen.valorMonetarioTotal = dataAnalysis.valorTotalInventario(resumen.listaCompletaLotes);

        // 3. Recorrer la lista una sola vez para contar alertas
        int contadorBajo = 0;
        int contadorVencer = 0;

        for (Lote lote : resumen.listaCompletaLotes) {
            if (lote.getCantidad() < 10) {
                contadorBajo++;
            }
            EstadoLote estado = dataAnalysis.calcularEstadoLote(lote);
            if (estado == EstadoLote.DEVOLUCION || estado == EstadoLote.OFERTA || estado == EstadoLote.VENCIDO) {
                contadorVencer++;
            }
        }

        resumen.alertasStockBajo = contadorBajo;
        resumen.alertasPorVencer = contadorVencer;

        return resumen;
    }

}
