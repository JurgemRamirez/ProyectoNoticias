/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package NOTICIAS;

import CATEGORIAS.categoriaDTO;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jurgemramirez
 */
public class FrmCrearNoticia extends javax.swing.JFrame {

    /**
     * Creates new form FrmCrearNoticia
     */
    int id_categoria;

    public FrmCrearNoticia() {
      initComponents();
        this.setLocationRelativeTo(this);
        cargarComboBoxCategorias();

    }

        private void cargarComboBoxCategorias() {
                NoticiaDAO notiDAO = new NoticiaDAO();
    DefaultComboBoxModel<categoriaDTO> model = notiDAO.cargarDatosCategorias();
        jComboBox_categoria.setModel(model);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_titulo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_escritor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboBox_categoria = new javax.swing.JComboBox<>();
        txtFechaPublicada = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_contenido = new javax.swing.JTextArea();
        button_guardar2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel2.setText("Titulo");

        txt_titulo.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        txt_titulo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_titulo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel6.setText("Escritor");

        txt_escritor.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        txt_escritor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_escritor.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel7.setText("Categoria");

        jComboBox_categoria.setSelectedIndex(-1);
        jComboBox_categoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_categoriaItemStateChanged(evt);
            }
        });

        txtFechaPublicada.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        txtFechaPublicada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaPublicada.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel9.setText("FechaPublicada yyyy-MM-dd");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txt_escritor, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jComboBox_categoria, 0, 525, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(361, 361, 361))
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING))
                                .addComponent(txt_titulo)))
                        .addComponent(txtFechaPublicada, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_escritor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFechaPublicada, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel8.setText("Contenido");

        txt_contenido.setColumns(20);
        txt_contenido.setRows(5);
        jScrollPane1.setViewportView(txt_contenido);

        button_guardar2.setBackground(new java.awt.Color(0, 153, 0));
        button_guardar2.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        button_guardar2.setForeground(new java.awt.Color(255, 255, 255));
        button_guardar2.setText("Registrar");
        button_guardar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_guardar2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel3.setText("Nueva Noticia");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button_guardar2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button_guardar2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_guardar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_guardar2ActionPerformed

        
            String titulo = txt_titulo.getText();
            String escritor = txt_escritor.getText();
            String contenido = txt_contenido.getText(); // Usamos new String para convertir el JPasswordField a String
            
            
              // Validar formato de la fecha
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilFecha = null;
            try {
                utilFecha = dateFormat.parse(txtFechaPublicada.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto. Use el formato: yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
             // Convertir la fecha a java.sql.Date
            Date fechaPublicada = new Date(utilFecha.getTime());

                // Validar que los campos no estén vacíos
            if (titulo.isEmpty() || escritor.isEmpty() || contenido.isEmpty()) {
                // Mostrar un mensaje de alerta si algún campo está vacío
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;  // Detener la ejecución si algún campo está vacío
            }
            
            NoticiaDTO noticia = new NoticiaDTO(titulo, contenido, escritor, fechaPublicada, id_categoria );

                NoticiaDAO notiDAO = new NoticiaDAO();
        try {
            notiDAO.insertarNoticia(noticia);
        } catch (SQLException ex) {
            Logger.getLogger(FrmCrearNoticia.class.getName()).log(Level.SEVERE, null, ex);
        }

             JOptionPane.showMessageDialog(this, "Noticia guardada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Limpiar campos
            txt_titulo.setText("");
            txt_contenido.setText("");
            txt_escritor.setText("");
            txtFechaPublicada.setText("");


            
    }//GEN-LAST:event_button_guardar2ActionPerformed

    private void jComboBox_categoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_categoriaItemStateChanged
        // TODO add your handling code here:
        
              // Obtener el elemento seleccionado del JComboBox
        categoriaDTO categoriaSeleccionada = (categoriaDTO) jComboBox_categoria.getSelectedItem();

        // Verificar si categoriaSeleccionada es null
        if (categoriaSeleccionada != null) {
            // Obtener el ID y el nombre de la categoria seleccionada
            
             id_categoria =  categoriaSeleccionada.getCategoriaId().intValue();;
  //System.out.println("ID: " + id_categoria);
            // Mostrar los datos
           // System.out.println("ID: " + id_categoria);
           // System.out.println("Nombre: " + nombre);
       

        } else {
            System.out.println("No se ha seleccionado ninguna especialidad.");
        }
    
    }//GEN-LAST:event_jComboBox_categoriaItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmCrearNoticia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCrearNoticia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCrearNoticia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCrearNoticia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCrearNoticia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_guardar2;
    private javax.swing.JComboBox<categoriaDTO> jComboBox_categoria;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtFechaPublicada;
    private javax.swing.JTextArea txt_contenido;
    private javax.swing.JTextField txt_escritor;
    private javax.swing.JTextField txt_titulo;
    // End of variables declaration//GEN-END:variables
}
