/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.noticiero.noticias.CONEXION;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author jurgemramirez
 */
public class conexion {
    
    
    private final String DRIVER = "oracle.jdbc.OracleDriver";
    private final String URL = "jdbc:oracle:thin:@//localhost:1521/XE";
    private final String USER = "PROYTECTOLGDB";
    private final String PASWORD = "Admin123";
    
    

    public Connection cadena;

    public conexion() {
         this.cadena = null;
    }

public Connection conectar() {
        try {
            Class.forName(DRIVER);
           // this.cadena = DriverManager.getConnection(URL, USER, PASWORD);
              this.cadena = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE", "PROYTECTOLGDB", "Admin123");

        System.out.println("Conexión establecida exitosamente.");

        } catch (ClassNotFoundException | SQLException e) {
        System.out.println("Error al conectar a la base de datos: " + e.getMessage());

            JOptionPane.showMessageDialog(null, e.getMessage());
            System.exit(0);
        }
        return this.cadena;

    }

    public void desconectar() {
        try {
            this.cadena.close();
        } catch (SQLException e) {
        System.out.println("Error al desconectar a la base de datos: " + e.getMessage());

            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    

    
}
