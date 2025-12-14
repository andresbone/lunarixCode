package com.farmacia.prototipo.view;

import java.awt.Color;

/*
 * @author Samantha Moreira
 */
import com.farmacia.prototipo.logic.service.InventarioService;
import com.farmacia.prototipo.logic.service.InventarioService.ResumenInventario;
import com.farmacia.prototipo.model.Lote;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Inventario extends javax.swing.JPanel {

    /**
     * Creates new form Inventario
     */
    private final InventarioService inventarioService = new InventarioService();

    public Inventario() {
        initComponents();
        configurarTabla();
        cargarDatosDeBaseDeDatos();
    }

    private void configurarTabla() {
        // Definimos las columnas que se verán en la tabla
        String[] columnas = {"Producto", "Lote", "Vencimiento", "Stock", "Precio"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        jtProductos.setModel(modelo);
        jtProductos.setRowHeight(25); // Altura de filas para que se vea mejor
    }

    public void cargarDatosDeBaseDeDatos() {
        try {
            
            ResumenInventario datos = inventarioService.obtenerDatosDashboard();

            // Total Productos
            jLabel5.setText(String.valueOf(datos.totalProductosUnicos));
            
            // Stock Bajo (Si hay alerta, ponemos el texto en rojo)
            jLabel8.setText(String.valueOf(datos.alertasStockBajo));
            if(datos.alertasStockBajo > 0) jLabel8.setForeground(Color.RED);
            else jLabel8.setForeground(new Color(0, 153, 51)); // Verde si está bien
            
            // Próximos a Vencer
            jLabel11.setText(String.valueOf(datos.alertasPorVencer));
            if(datos.alertasPorVencer > 0) jLabel11.setForeground(Color.RED);
            else jLabel11.setForeground(new Color(0, 153, 51));
            
            // Valor Total del Inventario
            jLabel2.setText(String.format("$ %,.2f", datos.valorMonetarioTotal));

            // C. Llenamos la Tabla
            DefaultTableModel modelo = (DefaultTableModel) jtProductos.getModel();
            modelo.setRowCount(0);

            for (Lote lote : datos.listaCompletaLotes) {
                modelo.addRow(new Object[]{
                    lote.getMostrarNombreProducto(),
                    lote.getCodigoLote(),            
                    lote.getFechaVencimiento(),     
                    lote.getCantidad(),            
                    String.format("$ %.2f", lote.getPrecioCompra())
                });
            }

        } catch (Exception e) {
            System.err.println("Error al cargar datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbGestion = new javax.swing.JLabel();
        lbTexto = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jpProductosTotal = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtProductos = new javax.swing.JTable();
        btnExportar = new javax.swing.JButton();
        btnAnadir = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        btnExportar1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbGestion.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lbGestion.setForeground(new java.awt.Color(0, 0, 0));
        lbGestion.setText("Gestión de Inventario");
        add(lbGestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        lbTexto.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lbTexto.setForeground(new java.awt.Color(51, 51, 51));
        lbTexto.setText("Administre el stock y control de los productos farmacéuticos");
        add(lbTexto, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Valor Total");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("...");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 80, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/valorTotal.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 60, 70));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 90, 260, 130));

        jpProductosTotal.setBackground(new java.awt.Color(255, 255, 255));
        jpProductosTotal.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpProductosTotal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Total Productos");
        jpProductosTotal.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("...");
        jpProductosTotal.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 80, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/productos.png"))); // NOI18N
        jLabel6.setText("jLabel3");
        jpProductosTotal.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 70, 60));

        add(jpProductosTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 260, 130));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Stock Bajo");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("...");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 80, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/senal-de-alerta.png"))); // NOI18N
        jLabel9.setText("jLabel3");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 60, 60));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 260, 130));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Proximos a Vencer");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel11.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("...");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 80, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/precaucion.png"))); // NOI18N
        jLabel12.setText("jLabel3");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 60, 50));

        add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 90, 260, 130));

        jtProductos.setBackground(new java.awt.Color(255, 255, 255));
        jtProductos.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jtProductos.setForeground(new java.awt.Color(0, 0, 0));
        jtProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jScrollPane1.setViewportView(jtProductos);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 1290, 430));

        btnExportar.setBackground(new java.awt.Color(255, 255, 255));
        btnExportar.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        btnExportar.setForeground(new java.awt.Color(0, 0, 0));
        btnExportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        btnExportar.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnExportar.setBorderPainted(false);
        btnExportar.setFocusPainted(false);
        btnExportar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnExportar.addActionListener(this::btnExportarActionPerformed);
        add(btnExportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 70, 40));

        btnAnadir.setBackground(new java.awt.Color(240, 28, 35));
        btnAnadir.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        btnAnadir.setForeground(new java.awt.Color(255, 255, 255));
        btnAnadir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar.png"))); // NOI18N
        btnAnadir.setText(" Añadir Producto");
        btnAnadir.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnAnadir.setBorderPainted(false);
        btnAnadir.setFocusPainted(false);
        btnAnadir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnAnadir.addActionListener(this::btnAnadirActionPerformed);
        add(btnAnadir, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 280, -1, 40));

        jTextField1.setBackground(new java.awt.Color(255, 255, 255));
        jTextField1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(0, 0, 0));
        jTextField1.setText(" Buscar pruducto, código, lote...");
        jTextField1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        jTextField1.addActionListener(this::jTextField1ActionPerformed);
        add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 540, 40));

        btnExportar1.setBackground(new java.awt.Color(255, 255, 255));
        btnExportar1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        btnExportar1.setForeground(new java.awt.Color(0, 0, 0));
        btnExportar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/descargar.png"))); // NOI18N
        btnExportar1.setText("Exportar Datos");
        btnExportar1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnExportar1.setBorderPainted(false);
        btnExportar1.setFocusPainted(false);
        btnExportar1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnExportar1.addActionListener(this::btnExportar1ActionPerformed);
        add(btnExportar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 280, -1, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        // TODO add your handling code here:0
    }//GEN-LAST:event_btnExportarActionPerformed

    private void btnAnadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnadirActionPerformed
        JOptionPane.showMessageDialog(this, "La creación de productos se verá el lunes.");
    }//GEN-LAST:event_btnAnadirActionPerformed

    private void btnExportar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportar1ActionPerformed
        cargarDatosDeBaseDeDatos();
    }//GEN-LAST:event_btnExportar1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnadir;
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnExportar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel jpProductosTotal;
    private javax.swing.JTable jtProductos;
    private javax.swing.JLabel lbGestion;
    private javax.swing.JLabel lbTexto;
    // End of variables declaration//GEN-END:variables
}
