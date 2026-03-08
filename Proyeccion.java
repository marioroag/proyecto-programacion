import java.time.LocalTime;

public class Proyeccion {
    // [ Atributos ]
    private static int cantidadProyecciones = 1;

    private String codigoProyeccion;
    private Pelicula pelicula;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    // [ Constructores ]
    public Proyeccion(Pelicula pelicula, String horaInicio, String horaFin) {
        setCodigoProyeccion();
        this.pelicula = pelicula;

        // Arregla el formato H:MM a HH:MM para que las funciones de LocalTime funcionen
        if (horaInicio.length() == 4) {
            horaInicio = "0" + horaInicio;
        }
        if (horaFin.length() == 4) {
            horaFin = "0" + horaFin;
        }

        this.horaInicio = LocalTime.parse(horaInicio);
        this.horaFin = LocalTime.parse(horaFin);

        cantidadProyecciones++;
    }

    // [ Getters y Setters ]
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

    // [ toString ]
    @Override
    public String toString() {
        return "Proyeccion: " + codigoProyeccion + " | " + pelicula.getTitulo()
                + " de "
                + horaInicio + " a " + horaFin;
    }

}
