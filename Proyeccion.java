import java.time.LocalTime;
import java.util.ArrayList;

public class Proyeccion {
    // [ Atributos ]
    private static int cantidadProyecciones = 1;

    private Pelicula pelicula;
    private String codigoProyeccion;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private ArrayList<Cliente> clientes;

    // [ Constructores ]
    public Proyeccion(Pelicula pelicula, String horaInicio) {
        this.pelicula = pelicula;
        setCodigoProyeccion();
        setHoraInicio(horaInicio);
        setHoraFin(horaInicio);
        this.clientes = new ArrayList<>();

        cantidadProyecciones++;
    }

    // [ Metodos ]
    /**
     * Registra un cliente en una proyeccion
     * 
     * @param cliente Cliente
     */
    public void registrarCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    // region [ Getters y Setters ]
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

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }
    // endregion

    // [ toString ]
    @Override
    public String toString() {
        return "Proyeccion: " + codigoProyeccion + " | " + pelicula.getTitulo()
                + " de " + horaInicio + " a " + horaFin + "\n" + clientes;
    }

}
