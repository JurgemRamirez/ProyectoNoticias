/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NOTICIAS;

import java.sql.Date;

/**
 *
 * @author jurgemramirez
 */
public class NoticiaDTO {
    private String titulo;
    private String contenido;
    private String escritor;
    private Date fechaPublicada;
    private int categoriaId;

    // Constructor
    public NoticiaDTO(String titulo, String contenido, String escritor, Date fechaPublicada, int categoriaId) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.escritor = escritor;
        this.fechaPublicada = fechaPublicada;
        this.categoriaId = categoriaId;
    }

    // Getters y Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getEscritor() {
        return escritor;
    }

    public void setEscritor(String escritor) {
        this.escritor = escritor;
    }

    public Date getFechaPublicada() {
        return fechaPublicada;
    }

    public void setFechaPublicada(Date fechaPublicada) {
        this.fechaPublicada = fechaPublicada;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }
}

