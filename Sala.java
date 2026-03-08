import java.time.LocalTime;
import java.util.ArrayList;

public class Sala {
    // [ Atributos ]
    private static int cantidadSalas = 1;

    private String id; // 1, 2, 3
    private int capacidad;
    private ArrayList<Proyeccion> proyecciones;

    // [ Constructores ]
    public Sala(int capacidad, ArrayList<Proyeccion> proyecciones) {
        setId();
        this.capacidad = capacidad;
        this.proyecciones = proyecciones;
        cantidadSalas++;
    }

    public Sala(int capacidad) {
        setId();
        this.capacidad = capacidad;
        this.proyecciones = new ArrayList<>();
        cantidadSalas++;
    }

    // [ Métodos ]
    /**
     * Agrega una nueva proyeccion a la sala
     * 
     * @param nuevaProyeccion Proyeccion a añadir
     */
    public void agregarProyeccion(Proyeccion nuevaProyeccion) {
        this.proyecciones.add(nuevaProyeccion);
    }

    /**
     * Devuelve un booleano que indica si la sala esta ocupada (true) o no (false)
     * en el momento de ejecución del método usando LocalTime
     * 
     * @return booleano
     */
    public boolean estaOcupada() {
        boolean salaOcupada = false;
        LocalTime horaActual = LocalTime.now();

        for (Proyeccion proyeccion : proyecciones) {
            if (horaActual.isAfter(proyeccion.getHoraInicio()) &&
                    horaActual.isBefore(proyeccion.getHoraFin())) {
                salaOcupada = true;
            }
        }

        return salaOcupada;
    }

    // [ Getters y Setters ]
    public String getId() {
        return id;
    }

    public void setId() {
        this.id = "SAL-" + cantidadSalas;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public ArrayList<Proyeccion> getProyecciones() {
        return proyecciones;
    }

    public void setProyecciones(ArrayList<Proyeccion> proyecciones) {
        this.proyecciones = proyecciones;
    }

    // [ toString ]
    @Override
    public String toString() {
        String salaMostrar = "Sala: " + id + " | Tiene capacidad de " + capacidad
                + " personas, proyecta:\n";

        for (Proyeccion proyeccion : proyecciones) {
            salaMostrar += " " + proyeccion + "\n";
        }

        return salaMostrar;
    }

}
