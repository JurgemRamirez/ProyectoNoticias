/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package USUARIOS;

import com.noticiero.noticias.CONEXION.conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author jurgemramirez
 */
public class usuarioDAO {
    
    // Crear un usuario
    public void createUsuario(String username, String email, String password, String role) throws SQLException {
        String sql = "{CALL SP_CREATE_USUARIO(?, ?, ?, ?)}";
         conexion conDB = new conexion();

        try (Connection conn = conDB.conectar();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, role);
            stmt.execute();
        }
    }
    
       // Leer todos los usuarios
    public List<usuarioDTO> readUsuarios() throws SQLException {
        String sql = "{CALL SP_READ_USUARIOS(?)}";
        conexion conDB = new conexion();

        List<usuarioDTO> usuarios = new ArrayList<>();
        try (Connection conn = conDB.conectar();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.execute();

            try (ResultSet rs = (ResultSet) stmt.getObject(1)) {
                while (rs.next()) {
                    usuarioDTO usuario = new usuarioDTO();
                    usuario.setId(rs.getLong("USER_ID"));
                    usuario.setUsername(rs.getString("USERNAME"));
                    usuario.setEmail(rs.getString("EMAIL"));
                    usuario.setRole(rs.getString("ROLE"));
                    usuarios.add(usuario);
                }
            }
        }
        return usuarios;
    }
    
     // Leer un usuario por ID
    public usuarioDTO readUsuarioById(Long id) throws SQLException {
        String sql = "{CALL SP_READ_USUARIO_BY_ID(?, ?)}";
        conexion conDB = new conexion();

        usuarioDTO usuario = null;
        try (Connection conn = conDB.conectar();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setLong(1, id);
            stmt.registerOutParameter(2, OracleTypes.CURSOR);
            stmt.execute();

            try (ResultSet rs = (ResultSet) stmt.getObject(2)) {
                if (rs.next()) {
                    usuario = new usuarioDTO();
                    usuario.setId(rs.getLong("USER_ID"));
                    usuario.setUsername(rs.getString("USERNAME"));
                    usuario.setEmail(rs.getString("EMAIL"));
                    usuario.setRole(rs.getString("ROLE"));
                }
            }
        }
        return usuario;
    }
    
      // Actualizar un usuario
    public void updateUsuario(Long id, String username, String email, String password, String role) throws SQLException {
        String sql = "{CALL SP_UPDATE_USUARIO(?, ?, ?, ?, ?)}";
        conexion conDB = new conexion();

        try (Connection conn = conDB.conectar();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setLong(1, id);
            stmt.setString(2, username);
            stmt.setString(3, email);
            stmt.setString(4, password);
            stmt.setString(5, role);
            stmt.execute();
        }
    }

    // Eliminar un usuario
    public void deleteUsuario(Long id) throws SQLException {
        String sql = "{CALL SP_DELETE_USUARIO(?)}";
        conexion conDB = new conexion();
        try (Connection conn = conDB.conectar();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setLong(1, id);
            stmt.execute();
        }
    }
    
}
