import java.time.LocalTime;

public class Proyeccion {
    // [ Atributos ]
    private static int cantidadProyecciones = 1;

    private String codigoProyeccion;
    private Pelicula pelicula;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    // [ Constructores ]
    public Proyeccion(Pelicula pelicula, String horaInicio) {
        setCodigoProyeccion();
        this.pelicula = pelicula;
        setHoraInicio(horaInicio);
        setHoraFin(horaInicio);

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

    public void setHoraInicio(String horaInicio) {
        // Arregla el formato H:MM a HH:MM para que las funciones de LocalTime funcionen
        if (horaInicio.length() == 4) {
            horaInicio = "0" + horaInicio;
        }

        this.horaInicio = LocalTime.parse(horaInicio);
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaInicio.plusMinutes(pelicula.getDuracion());
    }

    // [ toString ]
    @Override
    public String toString() {
        return "Proyeccion: " + codigoProyeccion + " | " + pelicula.getTitulo()
                + " de "
                + horaInicio + " a " + horaFin;
    }

}
