/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.noticiero.noticias;

import com.noticiero.noticias.CONEXION.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jurgemramirez
 */
public class Noticias {

    public static void main(String[] args) throws SQLException {
// clase conexion
        conexion con = new conexion();
        Connection connection = null;
        
        try {
            // Conectar a la base de datos
            connection = con.conectar();
          //  System.out.println("Conexión exitosa.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Asegurarse de desconectar la base de datos
            if (connection != null) {
                con.desconectar();
                System.out.println("Conexión cerrada.");
            }
        }
   }
      
   
}
