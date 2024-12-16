package COMENTARIOS;

public class ComentarioDTO {
private int newsId;
private int userId;
private String contenidoComentario;


    public ComentarioDTO(int newsId, int userId, String contenidoComentario) {
        this.newsId = newsId;
        this.userId = userId;
        this.contenidoComentario = contenidoComentario;
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
}

