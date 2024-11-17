
package com.mycompany.proyectonoticias;

/**
 *
 * @author Tazita y mayeyita pw
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroUser extends JFrame {

    public RegistroUser() {
        setTitle("Registro de Usuario");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new GridLayout(5, 2, 10, 10)); 

      
        JLabel labelNombre = new JLabel("Nombre de Usuario:");
        JTextField campoNombre = new JTextField();

        JLabel labelCorreo = new JLabel("Correo Electrónico:");
        JTextField campoCorreo = new JTextField();

        JLabel labelContraseña = new JLabel("Contraseña:");
        JPasswordField campoContraseña = new JPasswordField();

        
        JButton botonRegistrar = new JButton("Registrar");
        JLabel resultado = new JLabel("");

        
        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String usuarioCorrecto = "admin";
                String correoCorrecto = "admin@gmail.com";
                String contrasenaCorrecta = "2024";

              
                String usuarioIngresado = campoNombre.getText();
                String correoIngresado = campoCorreo.getText();
                String contrasenaIngresada = new String(campoContraseña.getPassword());

                
                if (usuarioIngresado.equals(usuarioCorrecto) &&
                    correoIngresado.equals(correoCorrecto) &&
                    contrasenaIngresada.equals(contrasenaCorrecta)) {
                    resultado.setText("¡Registro exitoso!");
                    resultado.setForeground(Color.GREEN);
                } else {
                    resultado.setText("Datos incorrectos, intenta de nuevo.");
                    resultado.setForeground(Color.RED);
                }
            }
        });

       
        add(labelNombre);
        add(campoNombre);
        add(labelCorreo);
        add(campoCorreo);
        add(labelContraseña);
        add(campoContraseña);
        add(new JLabel("")); 
        add(botonRegistrar);
        add(new JLabel("")); 
        add(resultado);

        setVisible(true);
    }

    public static void main(String[] args) {
        new RegistroUser();
    }
}