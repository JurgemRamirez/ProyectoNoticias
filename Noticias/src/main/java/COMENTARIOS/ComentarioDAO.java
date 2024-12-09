package COMENTARIOS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ComentarioDAO {
    private Connection connection;


    public ComentarioDAO(Connection connection) {
        this.connection = connection;
    }

 
    public void insertarComentario(ComentarioDTO comentarioDTO) throws SQLException {
        String sql = "CALL SP_COMENTAR_NOTICIA(?, ?, ?)";  

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, comentarioDTO.getNewsId());
            stmt.setInt(2, comentarioDTO.getUserId());
            stmt.setString(3, comentarioDTO.getContenidoComentario());
            stmt.execute();
        }
    }
}
