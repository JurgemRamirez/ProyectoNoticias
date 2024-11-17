
package CATEGORIAS;

import com.noticiero.noticias.CONEXION.conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

public class categoriaDAO {

    
    public void createCategoria(String nombre) throws SQLException {
        String sql = "{CALL SP_CREATE_CATEGORIA(?)}"; 
        conexion conDB = new conexion();

        try (Connection conn = conDB.conectar();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, nombre);
            stmt.execute();
        }
    }

    public List<categoriaDTO> readCategorias() throws SQLException {
        String sql = "{CALL SP_READ_CATEGORIAS(?)}"; 
        conexion conDB = new conexion();

        List<categoriaDTO> categorias = new ArrayList<>();
        try (Connection conn = conDB.conectar();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.execute();

            try (ResultSet rs = (ResultSet) stmt.getObject(1)) {
                while (rs.next()) {
                    categoriaDTO categoria = new categoriaDTO();
                    categoria.setCategoriaId(rs.getLong("CATEGORIA_ID"));
                    categoria.setNombre(rs.getString("NOMBRE"));
                    categorias.add(categoria);
                }
            }
        }
        return categorias;
    }


    public categoriaDTO readCategoriaById(Long id) throws SQLException {
        String sql = "{CALL SP_READ_CATEGORIA_BY_ID(?, ?)}"; 
        conexion conDB = new conexion();

        categoriaDTO categoria = null;
        try (Connection conn = conDB.conectar();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setLong(1, id);
            stmt.registerOutParameter(2, OracleTypes.CURSOR);
            stmt.execute();

            try (ResultSet rs = (ResultSet) stmt.getObject(2)) {
                if (rs.next()) {
                    categoria = new categoriaDTO();
                    categoria.setCategoriaId(rs.getLong("CATEGORIA_ID"));
                    categoria.setNombre(rs.getString("NOMBRE"));
                }
            }
        }
        return categoria;
    }

  
    public void updateCategoria(Long id, String nombre) throws SQLException {
        String sql = "{CALL SP_UPDATE_CATEGORIA(?, ?)}"; 
        conexion conDB = new conexion();

        try (Connection conn = conDB.conectar();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setLong(1, id);
            stmt.setString(2, nombre);
            stmt.execute();
        }
    }

  
    public void deleteCategoria(Long id) throws SQLException {
        String sql = "{CALL SP_DELETE_CATEGORIA(?)}"; 
        conexion conDB = new conexion();

        try (Connection conn = conDB.conectar();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setLong(1, id);
            stmt.execute();
        }
    }
}