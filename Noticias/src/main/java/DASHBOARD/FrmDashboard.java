/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package DASHBOARD;

import com.noticiero.noticias.CONEXION.conexion;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author jurgemramirez
 */
public class FrmDashboard extends javax.swing.JFrame {
 
    private JPanel panelNoticias; // Panel principal que contendrá todos los paneles de noticias

    /**
     * Creates new form FrmDashboard
     */
    public FrmDashboard() {
        initComponents();
          // Configuración del JFrame
      //  setTitle("Noticias");
       // setSize(1200, 600);
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Panel principal para las noticias
        panelNoticias = new JPanel();
        panelNoticias.setLocation(-32485, -32596);
        panelNoticias.setLayout(new GridLayout(0, 3, 10, 10)); // Grid con 3 columnas
        add(new JScrollPane(panelNoticias), BorderLayout.CENTER);
        
        // Cargar las noticias al iniciar la ventana
        cargarNoticias();

        setVisible(true);
    }
    
    
     // Método para cargar las noticias desde la base de datos
    private void cargarNoticias() {
        //Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
            conexion conDB = new conexion();
              String sql = "{ call listar_noticias(?) }";
         Connection conn = conDB.conectar();
     try{
            // Conectar a la base de datos Oracle (ajusta los parámetros de conexión)
          //  conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "usuario", "contraseña");
                      stmt = conn.prepareCall("{ call listar_noticias(?) }");
 

            
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.execute();
            
            // Obtener el cursor
            rs = (ResultSet) stmt.getObject(1);
            
            // Limpiar el panel antes de cargar nuevas noticias
            panelNoticias.removeAll();
            
            // Iterar sobre el resultado y agregar un panel para cada noticia
            while (rs.next()) {
                String titulo = rs.getString("titulo");
                String escritor = rs.getString("escritor");
                Date fecha = rs.getDate("fecha_Publicada");
                String Contenido = rs.getString("Contenido");

                // System.out.println("fecha: " + fecha);

                // Crear un panel para cada noticia
                JPanel panelNoticia = new JPanel();
                panelNoticia.setLayout(new BorderLayout());
                panelNoticia.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                
                // Crear etiquetas para el título y escritor
                JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
                lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
                
                JLabel lblEscritor = new JLabel("Escritor: " + escritor, SwingConstants.CENTER);
                lblEscritor.setFont(new Font("Arial", Font.ITALIC, 12));
                
                JLabel lblFecha = new JLabel("Fecha: " + fecha, SwingConstants.CENTER);
                  lblFecha.setFont(new Font("Arial", Font.ITALIC, 12));
                
                // Crear un botón para ver más detalles
                JButton btnVerMas = new JButton("Ver Más");
                btnVerMas.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        mostrarDetallesNoticia(titulo, escritor,fecha,Contenido);
                    }
                });
                
                    // Crear un panel secundario para los textos
                JPanel panelTexto = new JPanel();
                panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.Y_AXIS)); // Alineación vertical compacta
         // Reducir márgenes de cada JLabel para disminuir espacio
                    lblTitulo.setBorder(BorderFactory.createEmptyBorder(90, 0, 2, 0));
                    lblEscritor.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));
                    lblFecha.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));

                    // Agregar las etiquetas al panel con menor espacio entre ellas
                    panelTexto.add(lblTitulo);
                    panelTexto.add(lblEscritor);
                    panelTexto.add(lblFecha);
                
                // Agregar los componentes al panel
                panelNoticia.add(panelTexto, BorderLayout.CENTER);

                panelNoticia.add(btnVerMas, BorderLayout.SOUTH);
                
                // Agregar el panel de noticia al panel principal
                panelNoticias.add(panelNoticia);
            }
            
            // Actualizar la interfaz
            panelNoticias.revalidate();
            panelNoticias.repaint();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Cerrar conexiones
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
       // Método para mostrar los detalles de la noticia
    private void mostrarDetallesNoticia(String titulo, String escritor, Date fecha,String Contenido ) {
        // Crear un cuadro de diálogo con más detalles
        JOptionPane.showMessageDialog(this, "Detalles de la noticia:\n\n" +
                                            "Título: " + titulo + "\n" +
                                            "Escritor: " + escritor + "\n" +
                                            "Fecha: " + fecha+ "\n\n" +
                                             "Contenido: " + Contenido,
                                            "Detalles de la Noticia", JOptionPane.INFORMATION_MESSAGE);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        buscar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel2.setText("Ultimas Noticias");

        jPanel1.setBackground(new java.awt.Color(0, 102, 255));

        jLabel1.setBackground(new java.awt.Color(0, 51, 204));
        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Noticias");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        buscar.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        buscar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        buscar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton1.setBackground(new java.awt.Color(51, 204, 0));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Buscar");

        jButton2.setBackground(new java.awt.Color(255, 204, 0));
        jButton2.setText("Iniciar");

        jButton3.setBackground(new java.awt.Color(0, 204, 204));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Registrarse");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 456, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 982, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jMenu1.setText("Noticias");

        jMenuItem2.setText("Registrar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Categorias");
        jMenu2.setToolTipText("");

        jMenuItem3.setText("Registrar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Consultar");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        jMenu6.setText("Usuarios");

        jMenuItem10.setText("Registrar");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem10);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(527, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
 
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
   
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
    
    }//GEN-LAST:event_jMenuItem10ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField buscar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
