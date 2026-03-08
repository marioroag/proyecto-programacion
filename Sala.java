import java.time.LocalTime;
import java.util.ArrayList;

public class Sala {
    private static int cantidadSalas = 1;

    private String id; // 1, 2, 3
    private int capacidad;
    private ArrayList<Proyeccion> proyecciones;

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

    /**
     * Agrega una nueva proyeccion a la sala
     * 
     * @param nuevaProyeccion Proyeccion a añadir
     */
    public void agregarProyeccion(Proyeccion nuevaProyeccion) {
        this.proyecciones.add(nuevaProyeccion);
    }

    // Es muy importante que en todo momento podamos saber si una sala está
    // disponible o no. Por eso, solicitamos que el sistema pueda comprobar
    // automáticamente, en función de la hora actual, si una sala está ocupada por
    // alguna proyección en curso.
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
