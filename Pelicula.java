public class Pelicula {
    // [ Atributos ]
    private static int cantidadPeliculas = 1;

    private String codigo;
    private String titulo;
    private int duracion;
    private String clasificacionEdad;
    private String genero;

    // [ Constructores ]
    public Pelicula(String titulo, int duracion, String clasificacionEdad, String genero) {
        setCodigo();
        setTitulo(titulo);
        setDuracion(duracion);
        setClasificacionEdad(clasificacionEdad);
        setGenero(genero);

        cantidadPeliculas++;
    }

    // region [ Getters y Setters ]
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
        if (!titulo.isBlank() && titulo != null) {
            this.titulo = titulo;
        } else {
            this.titulo = null;
        }
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        if (duracion > 0) {
            this.duracion = duracion;
        } else {
            this.duracion = -1;
        }
    }

    public String getClasificacionEdad() {
        return clasificacionEdad;
    }

    public void setClasificacionEdad(String clasificacionEdad) {
        if (!clasificacionEdad.isBlank() && clasificacionEdad != null) {
            this.clasificacionEdad = clasificacionEdad;
        } else {
            this.clasificacionEdad = null;
        }
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        if (!genero.isBlank() && genero != null) {
            this.genero = genero;
        } else {
            this.genero = null;
        }
    }
    // endregion

    // [ toString ]
    @Override
    public String toString() {
        return "Pelicula: " + codigo + " | " + titulo + ", dura " + duracion + "m y está clasificada para personas "
                + clasificacionEdad + " | Género: " + genero;
    }

}
