import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Proyeccion {
    // [ Atributos ]
    private static int cantidadProyecciones = 1;

    private Pelicula pelicula;
    private String codigoProyeccion;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    private ArrayList<ArrayList<Butaca>> butacas;
    public static final int FILAS = 10;
    public static final int COLUMNAS = 20;

    private Sala sala;

    // [ Constructores ]
    public Proyeccion(Pelicula pelicula, String horaInicio, Sala sala) {
        this.pelicula = pelicula;
        setCodigoProyeccion();
        setHoraInicio(horaInicio);
        setHoraFin(horaInicio);
        this.sala = sala;
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
     * Busca una butaca a partir de su id, comprueba si existe, si esta ocupada, si
     * la proyeccion termino o si ese cliente ya esta registrado en esa proyeccion,
     * y si no hay errores asigna la butaca a ese cliente
     * 
     * @param idButaca
     * @param cliente
     * @return ArrayList<Boolean>, index 0: Asignado con exito, 1: No se encontró,
     *         2: Ocupada, 3: Proyeccion ha terminado, 4: Duplicado
     */
    public ArrayList<Boolean> asignarButaca(String idButaca, Cliente cliente) {
        ArrayList<Boolean> errores = new ArrayList<>(Arrays.asList(false, false, false, false, false));
        Butaca butacaActual;

        Butaca butaca = buscarButaca(idButaca);

        // Control de errores
        if (butaca == null) {
            errores.set(1, true); // No se encontró
        } else if (butaca.isOcupada()) {
            errores.set(2, true); // Ocupada
        } else if (horaFin.isBefore(LocalTime.now())) {
            errores.set(3, true); // Proyeccion ha terminado
        } else {
            // Busca duplicados
            for (int i = 0; i < FILAS; i++) {
                for (int j = 0; j < COLUMNAS; j++) {
                    butacaActual = butacas.get(i).get(j);

                    if (butacaActual.getCliente() != null &&
                            butacaActual.getCliente().getDni().equals(cliente.getDni())) {
                        errores.set(4, true); // Duplicado
                    }
                }
            }

            // Se asigna si no hay errores
            if (!errores.contains(true)) {
                errores.set(0, true);
                butaca.setCliente(cliente);
            }
        }

        return errores;
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
     * Mide la longuitud de un string sin contar con los carácteres típicos de loos
     * colores ASCII
     * 
     * @param string
     * @return Longuitud sin ASCII del String
     */
    public static int longuitudStringSinASCII(String string) {
        return string.replaceAll("(\\x9B|\\x1B\\[)[0-?]*[ -\\/]*[@-~]", "").length();
    }

    /**
     * Metodo sobrecomplicado por estetica que recorre la matriz de butacas y las
     * muestra de forma ordenada
     * 
     * @return Una representacion de butacas, [O] si libre, [X] si ocupada
     */
    public String mostrarButacas() {
        int anchoContenido = 0;
        String filaButacas = "";
        String espaciador = "";
        String resultado = "";
        String estado = "";
        Butaca butacaActual;

        final String GUIACOLUMNA = "     A    B    C    D    E    F    G    H    I    J    K    L    M    N    O    P    Q    R    S    T  ";

        final String RESET = "\u001B[0m"; // Resetea estilo
        final String BRIGHT_GREEN = "\u001B[92m"; // Color verde
        final String LIGHT_RED = "\u001B[38;2;255;120;120m"; // Color rojo

        // Genera la tabla de butacas a mostrar
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                // Guarda una a una las butacas de cada fila
                butacaActual = butacas.get(i).get(j);

                // Cambia el estado si la butaca está ocupada
                if (butacaActual.isOcupada()) {
                    estado = LIGHT_RED + "X" + RESET;
                } else {
                    estado = BRIGHT_GREEN + "0" + RESET;
                }

                // Va construyendo la fila
                filaButacas += " [" + estado + "] ";

                // Guardo el ancho de la sala despues de la primera fila completa
                if (i == 0 && j == COLUMNAS - 1) {
                    // +2 por ajuste contenido de el espacio de la enumeración
                    anchoContenido = longuitudStringSinASCII(filaButacas) + 3;

                    espaciador = "\n║ " + " ".repeat(anchoContenido) + " ║"; // Espaciador

                    resultado += "\n╔═" + "═".repeat(anchoContenido) + "═╗"; // Fila
                    resultado += "\n║ " + GUIACOLUMNA + " ║"; // Guia
                    resultado += espaciador;
                }

            }

            // Última fila tiene un espacio menos porque es 10 y tiene dos digitos
            if (i < FILAS - 1) {
                resultado += "\n║ " + (i + 1) + "  " + filaButacas + " ║";
            } else {
                resultado += "\n║ " + (i + 1) + " " + filaButacas + " ║";
            }
            filaButacas = "";

        }

        resultado += espaciador;
        resultado += "\n╚═" + "═".repeat(anchoContenido) + "═╝";

        return resultado;
    }

    /**
     * Cancela la asistencia de un cliente de una proyección liberando la butaca.
     * 
     * @param Butaca Butaca
     * @return ArrayList<Boolean>, index 0: Butaca no existe, 1 Ya esta libre, 2
     *         Proyección terminada
     */
    public ArrayList<Boolean> cancelarAsistencia(Butaca butaca) {
        ArrayList<Boolean> errores = new ArrayList<>(Arrays.asList(false, false, false));

        if (butaca == null) {
            errores.set(0, true); // Butaca no existe
        } else if (!butaca.isOcupada()) {
            errores.set(1, true); // Ya esta libre
        } else if (horaFin.isBefore(LocalTime.now())) {
            errores.set(2, true); // Proyección terminada
        } else {
            // Liberar butaca
            butaca.setOcupada(false);
            butaca.setCliente(null);
        }

        return errores;
    }

    /**
     * Devuelve un String organizado de todos los clientes que asisten a la
     * proyección
     * 
     * @return String
     */
    public String verListaClientes() {
        String resultado = "[ CLIENTES DE " + codigoProyeccion + " | " + contarAsistentes() + " asistentes ]";

        // Hay algun cliente?
        if (contarAsistentes() > 0) {
            for (int i = 0; i < FILAS; i++) {
                for (int j = 0; j < COLUMNAS; j++) {
                    Butaca butaca = butacas.get(i).get(j);

                    // Está ocupada?
                    if (butaca.isOcupada()) {
                        Cliente cliente = butaca.getCliente();

                        resultado += "\nButaca: " + butaca.getId() + " | " + cliente;
                    }
                }
            }

        } else {
            resultado += "\nEsta proyección no tiene clientes registrados.";
        }

        return resultado;
    }

    /**
     * Recorre el array buscando butacas ocupadas y las cuenta
     * 
     * @return int Numero de butacas ocupadas
     */
    public int contarAsistentes() {
        int asistentes = 0;
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (butacas.get(i).get(j).isOcupada()) {
                    asistentes++;
                }
            }
        }

        return asistentes;
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
        String resultado = "Proyección: " + codigoProyeccion + " | " + pelicula.getTitulo()
                + " de " + horaInicio + " a " + horaFin + ", ocupación: " + contarAsistentes() + "/"
                + sala.getCapacidad();

        return resultado;
    }

}
