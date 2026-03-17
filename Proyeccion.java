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

    private ArrayList<ArrayList<Butaca>> butacas;
    final private int FILAS = 10;
    final private int COLUMNAS = 20;

    private Sala sala;

    // [ Constructores ]
    public Proyeccion(Pelicula pelicula, String horaInicio, Sala sala) {
        this.pelicula = pelicula;
        setCodigoProyeccion();
        setHoraInicio(horaInicio);
        setHoraFin(horaInicio);
        this.sala = sala;
        this.clientes = new ArrayList<>();
        this.butacas = new ArrayList<>();

        inicializarButacas();

        cantidadProyecciones++;
    }

    // [ Metodos ]
    /**
     * Recorre el array de butacas y busca la butaca con el id proporcionado
     * 
     * @param idButaca
     * @return Butaca encontrada
     */
    public Butaca buscarButaca(String idButaca) {
        // Buscar la butaca
        Butaca butaca = null;

        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (butacas.get(i).get(j).getId().equalsIgnoreCase(idButaca)) {
                    butaca = butacas.get(i).get(j);
                }
            }
        }

        return butaca;
    }

    /**
     * Inicializa la matriz de butacas, crea un ArrayList y lo añade a la matriz,
     * despues lo rellena, asi hasta rellenarlo todo
     */
    public void inicializarButacas() {
        for (int i = 0; i < FILAS; i++) {
            // Añade nueva fila
            butacas.add(new ArrayList<Butaca>());

            for (int j = 0; j < COLUMNAS; j++) {
                // Transorma la posición a letra
                char letra = ((char) ('A' + j));

                // Inicializa cada celda
                butacas.get(i).add(new Butaca("" + (i + 1) + letra));

            }
        }
    }

    /**
     * Metodo sobrecomplicado por estetica que recorre la matriz de butacas y las
     * muestra de forma ordenada
     */
    public String mostrarButacas() {
        int anchoContenido = 0;
        String filaButacas = "";
        String espaciador = "";
        String resultado = "";

        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                // Guarda una a una las butacas de cada fila
                Butaca butacaActual = butacas.get(i).get(j);
                String estado = "O";

                // Cambia el estado si la butaca está ocupada
                if (butacaActual.isOcupada()) {
                    estado = "X";
                }
                filaButacas += " [" + estado + "] ";

                // Guardo el ancho de la sala despues de la primera fila completa
                if (i == 0 && j == COLUMNAS - 1) {
                    anchoContenido = filaButacas.length();

                    espaciador = "\n║ " + " ".repeat(anchoContenido) + " ║"; // Espaciador

                    resultado += "\n╔═" + "═".repeat(anchoContenido) + "═╗"; // Fila
                    resultado += espaciador;
                }

            }

            resultado += "\n║ " + filaButacas + " ║";
            filaButacas = "";

        }

        resultado += espaciador;
        resultado += "\n╚═" + "═".repeat(anchoContenido) + "═╝";

        return resultado;
    }

    /**
     * Registra un cliente en una proyeccion
     * 
     * @param cliente Cliente
     */
    public void registrarCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    /**
     * Elimina un cliente de una proyeccion
     * 
     * @param cliente Cliente
     */
    public void cancelarAsistencia(Cliente cliente) {
        this.clientes.remove(cliente);
    }

    /**
     * Devuelve un String de todos los clientes que asisten a la proyección
     * 
     * @return String
     */
    public String verListaClientes() {
        String resultado = "[ CLIENTES DE " + codigoProyeccion + " ]";

        // Hay algun cliente?
        if (clientes.size() > 0) {
            for (Cliente cliente : clientes) {
                resultado += "\nDNI: " + cliente.getDni() + " | " + cliente.getNombre() + " de " + cliente.getEdad()
                        + " años tiene la entrada " + cliente.getTipoEntrada()
                        + " Nº" + cliente.getNumEntrada() + ".";
            }

        } else {
            resultado += "\nEstá proyección todavía no tiene clientes registrados.";
        }

        return resultado;
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

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
    // endregion

    // [ toString ]
    @Override
    public String toString() {
        String resultado = "Proyeccion: " + codigoProyeccion + " | " + pelicula.getTitulo()
                + " de " + horaInicio + " a " + horaFin;

        for (Cliente cliente : clientes) {
            resultado += "\n  Cliente: " + cliente;
        }

        return resultado;
    }

}
