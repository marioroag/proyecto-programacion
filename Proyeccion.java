public class Proyeccion {
    private static int cantidadProyecciones = 1;

    private String codigoProyeccion;
    private Pelicula pelicula;
    private String horaInicio;
    private String horaFin;

    public Proyeccion(Pelicula pelicula, String horaInicio, String horaFin) {
        setCodigoProyeccion();
        this.pelicula = pelicula;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;

        cantidadProyecciones++;
    }

    public String getCodigoProyeccion() {
        return codigoProyeccion;
    }

    public void setCodigoProyeccion() {
        this.codigoProyeccion = "PRO-" + cantidadProyecciones;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    @Override
    public String toString() {
        return "Proyeccion: " + codigoProyeccion + " | " + pelicula.getTitulo()
                + " de "
                + horaInicio + " a " + horaFin;
    }

}
