package COMENTARIOS;

import java.sql.Date;

public class Comentario {
    private int comentarioId;
    private int newsId;
    private int userId;
    private String contenidoComentario;
    private Date fechaComentada;

    // Getters y Setters
    public int getComentarioId() {
        return comentarioId;
    }

    public void setComentarioId(int comentarioId) {
        this.comentarioId = comentarioId;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContenidoComentario() {
        return contenidoComentario;
    }

    public void setContenidoComentario(String contenidoComentario) {
        this.contenidoComentario = contenidoComentario;
    }

    public Date getFechaComentada() {
        return fechaComentada;
    }

    public void setFechaComentada(Date fechaComentada) {
        this.fechaComentada = fechaComentada;
    }
}
