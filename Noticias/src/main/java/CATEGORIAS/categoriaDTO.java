
package CATEGORIAS;

public class categoriaDTO {
    private Long categoriaId;
    private String nombre;

    public categoriaDTO() {}


    public categoriaDTO(Long categoriaId, String nombre) {
        this.categoriaId = categoriaId;
        this.nombre = nombre;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
      @Override
    public String toString() {
        return nombre; // Devuelve el nombre de la especialidad para la representación en cadena
    }
}
