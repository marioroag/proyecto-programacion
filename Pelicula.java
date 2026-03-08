public class Pelicula {
    private String codigo; // 1, 2, 3
    private String titulo;
    private int duracion; // minutos
    private String clasificacionEdad; // +12, +16
    private String genero;

    public Pelicula(String titulo, int duracion, String clasificacionEdad, String genero) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.clasificacionEdad = clasificacionEdad;
        this.genero = genero;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getClasificacionEdad() {
        return clasificacionEdad;
    }

    public void setClasificacionEdad(String clasificacionEdad) {
        this.clasificacionEdad = clasificacionEdad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return codigo + " | " + titulo + ", dura " + duracion + "m y está clasificada para personas "
                + clasificacionEdad + " | Generos: " + genero;
    }

}
