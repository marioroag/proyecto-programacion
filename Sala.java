import java.time.LocalTime;
import java.util.ArrayList;

public class Sala {
    // [ Atributos ]
    private static int cantidadSalas = 1;

    private String id;
    private int capacidad;
    private ArrayList<Proyeccion> proyecciones;

    // [ Constructores ]
    public Sala(ArrayList<Proyeccion> proyecciones) {
        setId();
        this.capacidad = capacidadMaxSala();
        this.proyecciones = proyecciones;

        cantidadSalas++;
    }

    public Sala() {
        setId();
        this.capacidad = capacidadMaxSala();
        this.proyecciones = new ArrayList<>();
        cantidadSalas++;
    }

    // [ Métodos ]
    /**
     * Devuelve la capacidad de la sala multiplicando las filas por las columnas
     * 
     * @return int max capacidad sala
     */
    public int capacidadMaxSala() {
        return Proyeccion.FILAS * Proyeccion.COLUMNAS;
    }

    /**
     * Devuelve true o false dependiendo de si hay espacio para la proyeccion
     * recibida
     * 
     * @param proyeccion
     * @return boolean {@code true} si hay menos asistentes que capacidad
     */
    public boolean hayEspacioDisponible(Proyeccion proyeccion) {
        boolean hayEspacio = false;
        if (proyeccion.contarAsistentes() < capacidad) {
            hayEspacio = true;
        }
        return hayEspacio;
    }

    /**
     * Agrega una nueva proyeccion a la sala comprobando si no se solapa con alguna
     * ya existente
     * 
     * @param nuevaProyeccion Proyeccion a añadir
     * @return boolean {@code true} si la nueva proyeccion no solapa con ninguna
     */
    public boolean agregarProyeccion(Proyeccion nuevaProyeccion) {
        boolean exito = true;

        for (Proyeccion proyeccion : proyecciones) {
            if (nuevaProyeccion.getHoraInicio().isBefore(proyeccion.getHoraFin()) &&
                    nuevaProyeccion.getHoraFin().isAfter(proyeccion.getHoraInicio())) {
                exito = false;
            }
        }

        if (exito) {
            proyecciones.add(nuevaProyeccion);
        }

        return exito;
    }

    /**
     * Recibe el codigo de una proyeccion, la busca en la sala usando
     * buscarProyeccion() y la elimina de la lista de proyecciones
     * 
     * @param codigoProyeccion Codigo de la proyeccion a buscar
     * @return boolean {@code true} si se encuentra la proteccion y si elimina
     */
    public boolean cancelarProyeccion(String codigoProyeccion) {
        boolean exito = false;
        Proyeccion proyeccionEliminar = buscarProyeccion(codigoProyeccion);

        if (proyeccionEliminar != null) {
            proyecciones.remove(proyeccionEliminar);
            exito = true;
        }

        return exito;
    }

    /**
     * Recibe una proyeccion y la elimina de la lista de proyecciones
     */
    public void cancelarProyeccion(Proyeccion proyeccionEliminar) {
        proyecciones.remove(proyeccionEliminar);
    }

    /**
     * Busca una proyeccion dentro de la sala usando su codigo
     * 
     * @param codigoProyeccion Codigo de la proyeccion a buscar
     * @return Proyeccion encontrada
     */
    public Proyeccion buscarProyeccion(String codigoProyeccion) {
        Proyeccion proyeccionBuscar = null;

        for (Proyeccion proyeccion : proyecciones) {
            if (codigoProyeccion.equalsIgnoreCase(proyeccion.getCodigoProyeccion())) {
                proyeccionBuscar = proyeccion;
            }
        }

        return proyeccionBuscar;
    }

    /**
     * Devuelve un booleano que indica si la sala esta ocupada (true) o no (false)
     * en el momento de ejecución del método usando LocalTime
     * 
     * @return boolean {@code true} si se esta proyectando ahora mismo en la sala
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

    /**
     * Permite customizar la visibilidad de la disponibilidad de una sala
     * 
     * @param libre   Como se mostrara si esta libre
     * @param ocupada Como se mostrara si esta ocupada
     * @return String customizado
     */
    public String mostrarDisponibilidad(String libre, String ocupada) {
        String disponibilidad = libre;

        if (estaOcupada()) {
            disponibilidad = ocupada;
        }

        return disponibilidad;
    }

    // region [ Getters y Setters ]
    public String getId() {
        return id;
    }

    public void setId() {
        this.id = "SAL-" + cantidadSalas;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public ArrayList<Proyeccion> getProyecciones() {
        return proyecciones;
    }

    public void setProyecciones(ArrayList<Proyeccion> proyecciones) {
        this.proyecciones = proyecciones;
    }
    // endregion

    // [ toString ]
    @Override
    public String toString() {

        String salaMostrar = mostrarDisponibilidad("LIBRE", "OCUPADA") + ": " + id + " | Tiene capacidad de "
                + capacidad
                + " personas:\n";

        for (Proyeccion proyeccion : proyecciones) {
            salaMostrar += " " + proyeccion + "\n";
        }

        return salaMostrar;
    }

}
