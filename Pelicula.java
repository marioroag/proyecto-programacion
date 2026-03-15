public class Pelicula {
    // [ Atributos ]
    private static int cantidadPeliculas = 1;

    private String codigo;
    private String titulo;
    private int duracion;
    private String clasificacionEdad;
    private String genero; // todo: ArrayList de generos validos

    // [ Constructores ]
    public Pelicula(String titulo, int duracion, String clasificacionEdad, String genero) {
        setCodigo();
        this.titulo = titulo;
        this.duracion = duracion;
        this.clasificacionEdad = clasificacionEdad;
        this.genero = genero;

        cantidadPeliculas++;
    }

    // [ Getters y Setters ]
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo() {
        this.codigo = "PEL-" + cantidadPeliculas;
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

    // [ toString ]
    @Override
    public String toString() {
        return "Pelicula: " + codigo + " | " + titulo + ", dura " + duracion + "m y está clasificada para personas "
                + clasificacionEdad + " | Generos: " + genero;
    }

}
