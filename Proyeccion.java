import java.time.LocalTime;

public class Proyeccion {
    private static int cantidadProyecciones = 1;

    private String codigoProyeccion;
    private Pelicula pelicula;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public Proyeccion(Pelicula pelicula, String horaInicio, String horaFin) {
        setCodigoProyeccion();
        this.pelicula = pelicula;
        this.horaInicio = LocalTime.parse(horaInicio);
        this.horaFin = LocalTime.parse(horaFin);

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

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    @Override
    public String toString() {
        return "Proyeccion: " + codigoProyeccion + " | " + pelicula.getTitulo()
                + " de "
                + horaInicio + " a " + horaFin;
    }

}
