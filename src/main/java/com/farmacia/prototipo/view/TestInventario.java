
package com.farmacia.prototipo.view;

/**
 *
 * @author hitomi
 */

import com.farmacia.prototipo.logic.service.InventarioService;
import com.farmacia.prototipo.logic.service.InventarioService.ResumenInventario;
import com.farmacia.prototipo.model.Lote;
import java.text.NumberFormat;
import java.util.Locale;

public class TestInventario {
    
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("🏭 INICIANDO PRUEBA DE BACKEND: INVENTARIO");
        System.out.println("==========================================\n");

        try {
            // 1. Instanciamos el Servicio (Tu Lógica)
            InventarioService service = new InventarioService();
            System.out.println("✅ Servicio instanciado correctamente.");

            // 2. Pedimos los datos (Esto va a la BD, calcula y retorna)
            System.out.println("⏳ Consultando base de datos y calculando KPIs...");
            long inicio = System.currentTimeMillis();
            
            ResumenInventario datos = service.obtenerDatosDashboard();
            
            long fin = System.currentTimeMillis();
            System.out.println("✅ Datos obtenidos en " + (fin - inicio) + " ms.\n");

            // 3. IMPRIMIMOS LAS "TARJETAS" (Lo que iría arriba en la GUI)
            System.out.println("------------------------------------------");
            System.out.println("📊 RESUMEN EJECUTIVO (KPIs)");
            System.out.println("------------------------------------------");
            System.out.println("📦 Total Productos Únicos: " + datos.totalProductosUnicos);
            System.out.println("⚠️ Alertas Stock Bajo:     " + datos.alertasStockBajo);
            System.out.println("📅 Alertas por Vencer:     " + datos.alertasPorVencer);
            
            // Formato de dinero
            NumberFormat moneda = NumberFormat.getCurrencyInstance(Locale.US);
            System.out.println("💰 Valor Total Inventario: " + moneda.format(datos.valorMonetarioTotal));
            System.out.println("------------------------------------------\n");

            // 4. IMPRIMIMOS LA "TABLA" (Primeros 10 registros para no saturar)
            System.out.println("📋 DETALLE DE LOTES (Mostrando primeros 10)");
            System.out.println("-----------------------------------------------------------------------------------------------");
            System.out.printf("%-10s %-25s %-12s %-12s %-8s %-12s\n", 
                    "CODIGO", "PRODUCTO", "LOTE", "VENCE", "STOCK", "PRECIO");
            System.out.println("-----------------------------------------------------------------------------------------------");

            int contador = 0;
            for (Lote l : datos.listaCompletaLotes) {
                System.out.printf("%-10s %-25.25s %-12s %-12s %-8d %-12s\n",
                        "P-" + l.getIdProducto(),      // Simulación EAN
                        l.getMostrarNombreProducto(),
                        l.getCodigoLote(),
                        l.getFechaVencimiento(),
                        l.getCantidad(),
                        moneda.format(l.getPrecioCompra())
                );
                
                contador++;
                if (contador >= 10) break; // Solo mostramos 10 para probar
            }
            System.out.println("-----------------------------------------------------------------------------------------------");
            System.out.println("... y " + (datos.listaCompletaLotes.size() - 10) + " lotes más.");

        } catch (Exception e) {
            System.err.println("❌ ERROR CRÍTICO EN LA PRUEBA:");
            e.printStackTrace();
        }
    }
}
