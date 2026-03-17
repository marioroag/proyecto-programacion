import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Sala {
    // [ Atributos ]
    private static int cantidadSalas = 1;

    private String id;
    private int capacidad;
    private ArrayList<Proyeccion> proyecciones;

    // [ Constructores ]
    public Sala(int capacidad, ArrayList<Proyeccion> proyecciones) {
        setId();
        setCapacidad(capacidad);
        this.proyecciones = proyecciones;

        cantidadSalas++;
    }

    public Sala(int capacidad) {
        setId();
        setCapacidad(capacidad);
        this.proyecciones = new ArrayList<>();
        cantidadSalas++;
    }

    // [ Métodos ]
    /**
     * Comprueba los posibles errores y si no falla nada intenta registrar al
     * cliente en la proyeccion
     * 
     * @param cliente
     * @param proyeccion
     * @return
     */
    public ArrayList<Boolean> registrarClientePro(Cliente cliente, Proyeccion proyeccion) {
        ArrayList<Boolean> errores = new ArrayList<>(Arrays.asList(false, false, false, false));

        if (!proyecciones.contains(proyeccion)) {
            errores.set(0, true); // Proyeccion no registrada en la sala
        } else if (proyeccion.getClientes().contains(cliente)) {
            errores.set(1, true); // Cliente ya registrado
        } else if (!hayEspacioDisponible(proyeccion)) {
            errores.set(2, true); // No hay asientos libres
        } else if (proyeccion.getHoraFin().isBefore(LocalTime.now())) {
            errores.set(3, true); // Ya ha terminado la proyeccion
        } else {
            proyeccion.registrarCliente(cliente);
        }

        return errores;
    }

    /**
     * Devuelve true o false dependiendo de si hay espacio para la proyeccion
     * recibida
     * 
     * @param proyeccion
     * @return Boolean
     */
    public boolean hayEspacioDisponible(Proyeccion proyeccion) {
        return proyeccion.getClientes().size() < capacidad;
    }

    /**
     * Agrega una nueva proyeccion a la sala comprobando si no se solapa con alguna
     * ya existente
     * 
     * @param nuevaProyeccion Proyeccion a añadir
     * @return Número de error para posteriormente imprimir un mensaje customizado
     *         en el programa
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
     */
    public void cancelarProyeccion(String codigoProyeccion) {
        Proyeccion proyeccionEliminar = buscarProyeccion(codigoProyeccion);
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
     * @return Booleano
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
     * @return String
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

    public void setCapacidad(int capacidad) {
        if (capacidad > 0) {
            this.capacidad = capacidad;
        } else {
            this.capacidad = -1;
        }
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
