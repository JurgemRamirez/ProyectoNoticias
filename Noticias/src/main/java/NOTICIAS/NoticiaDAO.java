/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NOTICIAS;

import com.noticiero.noticias.CONEXION.conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author jurgemramirez
 */
public class NoticiaDAO {
    
     public void insertarNoticia(NoticiaDTO noticia) throws SQLException {
        String sql = "{ call insertar_noticia(?, ?, ?, ?, ?) }";
                conexion conDB = new conexion();

        try (Connection conn = conDB.conectar();
                     CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.execute();

            stmt.setString(1, noticia.getTitulo());
            stmt.setString(2, noticia.getContenido());
            stmt.setString(3, noticia.getEscritor());
            stmt.setDate(4, new java.sql.Date(noticia.getFechaPublicada().getTime()));
            stmt.setInt(5, noticia.getCategoriaId());
            stmt.execute();
        }
    }
     
      public void actualizarNoticia(Noticia noticia) throws SQLException {
        String sql = "{ call actualizar_noticia(?, ?, ?, ?, ?, ?) }";
        conexion conDB = new conexion();

         try (Connection conn = conDB.conectar();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, noticia.getNewsId());
            stmt.setString(2, noticia.getTitulo());
            stmt.setString(3, noticia.getContenido());
            stmt.setString(4, noticia.getEscritor());
            stmt.setDate(5, new java.sql.Date(noticia.getFechaPublicada().getTime()));
            stmt.setInt(6, noticia.getCategoriaId());
            stmt.execute();
        }
    }
      
        public void eliminarNoticia(int newsId) throws SQLException {
        String sql = "{ call eliminar_noticia(?) }";
            conexion conDB = new conexion();

        try (Connection conn = conDB.conectar();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, newsId);
            stmt.execute();
        }
    }
        
         public Noticia consultarNoticia(int newsId) throws SQLException {
        String sql = "{ call consultar_noticia(?, ?) }";
                    conexion conDB = new conexion();

          try (Connection conn = conDB.conectar();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, newsId);
            stmt.registerOutParameter(2, OracleTypes.CURSOR);
            stmt.execute();

            try (ResultSet rs = (ResultSet) stmt.getObject(2)) {
                if (rs.next()) {
                    Noticia noticia = new Noticia();
                    noticia.setNewsId(rs.getInt("NEWS_ID"));
                    noticia.setTitulo(rs.getString("TITULO"));
                    noticia.setContenido(rs.getString("CONTENIDO"));
                    noticia.setEscritor(rs.getString("ESCRITOR"));
                    noticia.setFechaPublicada(rs.getDate("FECHA_PUBLICADA"));
                    noticia.setCategoriaId(rs.getInt("CATEGORIA_ID"));
                    return noticia;
                }
            }
        }
        return null;
    }
         
            public List<Noticia> listarNoticias() throws SQLException {
        String sql = "{ call listar_noticias(?) }";
         conexion conDB = new conexion();

        List<Noticia> noticias = new ArrayList<>();
     try (Connection conn = conDB.conectar();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.execute();

            try (ResultSet rs = (ResultSet) stmt.getObject(1)) {
                while (rs.next()) {
                    Noticia noticia = new Noticia();
                    noticia.setNewsId(rs.getInt("NEWS_ID"));
                    noticia.setTitulo(rs.getString("TITULO"));
                    noticia.setContenido(rs.getString("CONTENIDO"));
                    noticia.setEscritor(rs.getString("ESCRITOR"));
                    noticia.setFechaPublicada(rs.getDate("FECHA_PUBLICADA"));
                    noticia.setCategoriaId(rs.getInt("CATEGORIA_ID"));
                    noticias.add(noticia);
                }
            }
        }
        return noticias;
    }

    
}
