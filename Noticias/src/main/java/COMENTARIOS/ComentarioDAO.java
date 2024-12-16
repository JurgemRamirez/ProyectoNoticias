package COMENTARIOS;

import NOTICIAS.NoticiaDTO;
import com.noticiero.noticias.CONEXION.conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ComentarioDAO {

     public void insertarComentario2(ComentarioDTO comentarioDTO) throws SQLException {
        String sql = "CALL SP_COMENTAR_NOTICIA(?, ?, ?)";  
                conexion conDB = new conexion();

        try (Connection conn = conDB.conectar();
                     CallableStatement stmt = conn.prepareCall(sql)) {
          //  stmt.registerOutParameter(1, OracleTypes.CURSOR);
          //  stmt.execute();

          stmt.setInt(1, comentarioDTO.getNewsId());
            stmt.setInt(2, comentarioDTO.getUserId());
            stmt.setString(3, comentarioDTO.getContenidoComentario());
            stmt.execute();

        }
        catch (SQLException ex) {
        // Manejo de errores
        JOptionPane.showMessageDialog(null, "Error al insertar la noticia: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
 
    public void insertarComentario(ComentarioDTO comentarioDTO) throws SQLException {
        String sql = "CALL SP_COMENTAR_NOTICIA(?, ?, ?)";  

      //  try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      //      stmt.setInt(1, comentarioDTO.getNewsId());
      //      stmt.setInt(2, comentarioDTO.getUserId());
      //      stmt.setString(3, comentarioDTO.getContenidoComentario());
      //      stmt.execute();
       // }
    }
}
