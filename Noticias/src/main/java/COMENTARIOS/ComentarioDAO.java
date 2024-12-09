package COMENTARIOS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ComentarioDAO {
    private Connection connection;

    // Constructor que recibe la conexión
    public ComentarioDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para insertar un comentario en la base de datos
    public void insertarComentario(ComentarioDTO comentarioDTO) throws SQLException {
        String sql = "CALL SP_COMENTAR_NOTICIA(?, ?, ?)";  // Llamamos al procedimiento almacenado

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, comentarioDTO.getNewsId());
            stmt.setInt(2, comentarioDTO.getUserId());
            stmt.setString(3, comentarioDTO.getContenidoComentario());
            stmt.execute();
        }
    }
}
