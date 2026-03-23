import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // ==================
        // ==== [ MAIN ] ====
        // ==================

        // region [ DECLARACIONES ]
        Cine cineStem = FakerCine.generarCineFake(); // Generación de datos de un cine falso

        // Cliente para teste (siempre tiene dni 123 asi que es más facil testear)
        Cliente clientePrueba = new Cliente("Mario", "123", 21, "Premium", 0);
        cineStem.registrarCliente(clientePrueba);

        Scanner scanner = new Scanner(System.in);

        int opcionMenuPrincipal, opcionMenuCine, opcionMenuSalas, opcionMenuPeliculas, opcionMenuClientes;

        ArrayList<String> opcionesMenu = new ArrayList<>(
                Arrays.asList("Gestionar Cine", "Gestionar Salas", "Gestionar Películas",
                        "Gestionar Clientes", "Salir"));

        ArrayList<String> opcionesCines = new ArrayList<>(
                Arrays.asList("Ver detalles del cine", "Volver al menú principal"));

        ArrayList<String> opcionesSalas = new ArrayList<>(
                Arrays.asList("Listar salas de un cine", "Consultar disponibilidad de una sala",
                        "Programar película en una sala", "Cancelar proyección", "Volver al menú principal"));

        ArrayList<String> opcionesPeliculas = new ArrayList<>(
                Arrays.asList("Listar películas", "Ver detalles de una pelicula",
                        "Ver proyecciones de una película", "Volver al menú principal"));

        ArrayList<String> opcionesClientes = new ArrayList<>(
                Arrays.asList("Registrar cliente en una proyección", "Cancelar asistencia",
                        "Ver lista de clientes en una proyección", "Ver número total de asistentes",
                        "Registrar un cliente nuevo", "Volver al menú principal"));
        // endregion

        // [ MENÚ ]
        do {
            opcionMenuPrincipal = leerOpcionMenu(scanner, opcionesMenu, "-═ [ " + cineStem.getNombre() + " ] ═-");

            if (opcionMenuPrincipal == 1) { // Gestionar Cine
                do {
                    opcionMenuCine = leerOpcionMenu(scanner, opcionesCines, "-═ [ GESTIÓN CINE ] ═-");

                    if (opcionMenuCine == 1) { // Ver detalles cine
                        System.out.println(cineStem);
                    } else if (opcionMenuCine == 2) { // Volver al menú principal
                        System.out.println("Volviendo al menú principal..");
                    }

                } while (opcionMenuCine != opcionesCines.size());

            } else if (opcionMenuPrincipal == 2) { // Gestionar Salas
                do {
                    opcionMenuSalas = leerOpcionMenu(scanner, opcionesSalas, "-═ [ GESTIÓN SALAS ] ═-");

                    if (opcionMenuSalas == 1) { // Listar salas de un cine
                        System.out.print(cineStem.mostrarSalas());

                    } else if (opcionMenuSalas == 2) { // Consultar disponibilidad de una sala
                        mostrarDispoSala(scanner, cineStem);

                    } else if (opcionMenuSalas == 3) { // Programar proyeccion en una sala
                        programarProyeccion(scanner, cineStem);

                    } else if (opcionMenuSalas == 4) { // Cancelar proyección
                        cancelarProyeccion(scanner, cineStem);

                    } else if (opcionMenuSalas == 5) { // Volver al menú principal
                        System.out.println("Volviendo al menú principal..");
                    }

                } while (opcionMenuSalas != opcionesSalas.size());

            } else if (opcionMenuPrincipal == 3) { // Gestionar Películas
                do {
                    opcionMenuPeliculas = leerOpcionMenu(scanner, opcionesPeliculas, "-═ [ GESTIÓN PELICULAS ] ═-");

                    if (opcionMenuPeliculas == 1) { // Listar películas
                        System.out.println(cineStem.mostarPeliculas());

                    } else if (opcionMenuPeliculas == 2) { // Ver detalles de una pelicula
                        verDetallesPelicula(scanner, cineStem);

                    } else if (opcionMenuPeliculas == 3) { // Ver proyecciones de una película
                        mostrarProyeccionesPelicula(scanner, cineStem);

                    } else if (opcionMenuPeliculas == 4) { // Volver al menú principal
                        System.out.println("Volviendo al menú principal..");
                    }

                } while (opcionMenuPeliculas != opcionesPeliculas.size());

            } else if (opcionMenuPrincipal == 4) { // Gestionar Clientes
                do {
                    opcionMenuClientes = leerOpcionMenu(scanner, opcionesClientes, "-═ [ GESTIÓN CLIENTES ] ═-");

                    if (opcionMenuClientes == 1) { // Registrar cliente en una proyección
                        registrarClienteEnPro(scanner, cineStem);

                    } else if (opcionMenuClientes == 2) { // Cancelar asistencia
                        cancelarAsistencia(scanner, cineStem);

                    } else if (opcionMenuClientes == 3) { // Ver lista de clientes en una proyección
                        verListaAsistentes(scanner, cineStem);

                    } else if (opcionMenuClientes == 4) { // Ver número total de asistentes
                        verTotalAsistentes(scanner, cineStem);

                    } else if (opcionMenuClientes == 5) { // Registrar un cliente nuevo
                        registrarNuevoCliente(scanner, cineStem);

                    } else if (opcionMenuClientes == 6) { // Volver al menú principal
                        System.out.println("Volviendo al menú principal..");

                    }

                } while (opcionMenuClientes != opcionesClientes.size());

            } else if (opcionMenuPrincipal == 5) { // Salir
                System.out.println("Saliendo del programa..");
            }

        } while (opcionMenuPrincipal != opcionesMenu.size());

        scanner.close();

    }

    // ===================
    // == [ FUNCIONES ] ==
    // ===================

    // region [ CINE ]
    /**
     * Busca una proyeccion y si la encuentra imprime los clientes reistrados en
     * ella.
     * 
     * @param scanner
     * @param cine
     */
    public static void verListaAsistentes(Scanner scanner, Cine cine) {
        // Busca la proyeccion
        Proyeccion proyeccionVerClientes = cine.buscarProyeccion(
                leerTexto(scanner, "Codigo de la proyección de la cual ver los asistentes: "));

        // Muestra la lista de clientes ( si hay )
        if (proyeccionVerClientes != null) {
            imprimirInfo(proyeccionVerClientes.verListaClientes());

        } else {
            imprimirError("No se ha encontrado una proyección con ese código.");
        }
    }

    /**
     * Busca una proyeccion y muestra su total de asistentes.
     * 
     * @param scanner
     * @param cine
     */
    public static void verTotalAsistentes(Scanner scanner, Cine cine) {
        // Busca la proyección
        Proyeccion proyeccionVerAsistentes = cine.buscarProyeccion(
                leerTexto(scanner, "Codigo de la proyección de la cual ver los asistentes: "));

        // Muestra la cantidad de asistentes ( si hay )
        if (proyeccionVerAsistentes != null) {
            imprimirInfo("La proyeccion " + proyeccionVerAsistentes.getCodigoProyeccion() + " tiene "
                    + proyeccionVerAsistentes.contarAsistentes() + " asistentes.");

        } else {
            imprimirError("No se ha encontrado una proyeccion con ese código.");
        }
    }

    /**
     * Busca y elimina a un cliente de una proyeccion.
     * 
     * @param scanner
     * @param cine
     */
    public static void cancelarAsistencia(Scanner scanner, Cine cine) {
        Proyeccion proyeccion = cine.buscarProyeccion(
                leerTexto(scanner, "Código de la proyección: "));

        if (proyeccion != null) {
            imprimirInfo(proyeccion.mostrarButacas());

            String idButaca = leerTexto(scanner, "ID de la butaca a cancelar (ej: 5A, 10B): ");
            Butaca butacaCliente = proyeccion.buscarButaca(idButaca);

            ArrayList<Boolean> errores = proyeccion.cancelarAsistencia(butacaCliente);

            if (errores.get(0)) {
                imprimirError("La butaca no existe.");
            } else if (errores.get(1)) {
                imprimirError("La butaca ya está libre.");
            } else if (errores.get(2)) {
                imprimirError("La proyección ha finalizado.");
            } else {
                imprimirExito("Asistencia cancelada con éxito.");
            }

        } else {
            imprimirError("No se encontró una proyección con ese código.");
        }
    }

    /**
     * Registra un nuevo cliente y lo añade al cine.
     * 
     * @param scanner
     * @param cine
     */
    public static void registrarNuevoCliente(Scanner scanner, Cine cine) {
        // Pide datos
        imprimirInfo("Introduzca los datos del cliente");
        Cliente clienteRegistrar = new Cliente(
                leerTexto(scanner, "Nombre: "),
                leerTexto(scanner, "DNI: "),
                leerIntPositivo(scanner, "Edad: "),
                leerTexto(scanner, "Tipo de entrada: "),
                leerIntPositivo(scanner, "Número de entrada: "));

        // Lo registra si no está duplicado
        if (cine.registrarCliente(clienteRegistrar)) {
            imprimirExito("Cliente registrado con éxito!");
        } else {
            imprimirError("Cliente con DNI: " + clienteRegistrar.getDni() + " ya está registrado.");
        }

    }

    public static Cliente obtenerCliente(Scanner scanner, Cine cine) {
        Cliente cliente = cine.buscarClientePorDni(leerTexto(scanner, "DNI del cliente a registrar: "));

        if (cliente == null) {
            imprimirError("No se a encontrado un cliente con ese DNI.");
        }

        return cliente;
    }

    public static Proyeccion obtenerProyeccion(Scanner scanner, Cine cine) {
        Proyeccion proyeccion = cine.buscarProyeccion(leerTexto(scanner, "Código de la proyección: "));

        if (proyeccion == null) {
            imprimirError("No se ha encontrado una proyección con ese ID.");
        }

        return proyeccion;
    }

    public static void gestionarReservaButaca(Scanner scanner, Proyeccion proyeccion, Cliente cliente) {
        ArrayList<Boolean> errores;
        String idButaca;

        do {
            imprimirInfo(proyeccion.mostrarButacas());
            imprimirPrompt("Introduce (SALIR) para cancelar el registro.\n");
            idButaca = leerTexto(scanner, "Elige una butaca (ej: 7A): ");

            // Intenta asignarla
            errores = proyeccion.asignarButaca(idButaca, cliente);

            if (!idButaca.equalsIgnoreCase("SALIR")) {
                imprimirInfo("Volviendo..");
            }

        } while (errores.get(0) != true && !idButaca.equalsIgnoreCase("SALIR"));
    }

    /**
     * Pide un cliente, id y butaca y reserva su asistencia si es posible.
     * 
     * @param scanner
     * @param cine
     */
    public static void registrarClienteEnProRefactorizado(Scanner scanner, Cine cine) {
        // Busca cine
        Cliente clienteRegistrar = obtenerCliente(scanner, cine);

        // Se encontro el cliente?
        if (clienteRegistrar != null) {
            Proyeccion proyeccionRegistrar = obtenerProyeccion(scanner, cine);

            // Se encontro la proyección?
            if (proyeccionRegistrar != null) {

                gestionarReservaButaca(scanner, proyeccionRegistrar, clienteRegistrar);

            }
        }
    }

    /**
     * Pide un cliente, id y butaca y reserva su asistencia si es posible.
     * 
     * @param scanner
     * @param cine
     */
    public static void registrarClienteEnPro(Scanner scanner, Cine cine) {
        // Busca cine
        Cliente clienteRegistrar = cine.buscarClientePorDni(leerTexto(scanner, "DNI del cliente a registrar: "));

        // Se encontro el cliente?
        if (clienteRegistrar != null) {
            Proyeccion proyeccionRegistrar = cine.buscarProyeccion(
                    leerTexto(scanner, "Código de la proyección: "));

            // Se encontro la proyección?
            if (proyeccionRegistrar != null) {

                ArrayList<Boolean> errores;
                String idButaca;
                do {
                    imprimirInfo(proyeccionRegistrar.mostrarButacas());
                    imprimirPrompt("Introduce (SALIR) para cancelar el registro.\n");
                    idButaca = leerTexto(scanner, "Elige una butaca (ej: 7A): ");

                    // Intenta asignarla
                    errores = proyeccionRegistrar.asignarButaca(idButaca, clienteRegistrar);

                    if (!idButaca.equalsIgnoreCase("SALIR")) {
                        if (errores.get(0)) {
                            imprimirExito("Cliente registrado correctamente en la butaca " + idButaca.toUpperCase());
                        } else if (errores.get(1)) {
                            imprimirError("La butaca no existe.");
                        } else if (errores.get(2)) {
                            imprimirError("La butaca esta ocupada.");
                        } else if (errores.get(3)) {
                            imprimirError("La proyección ya ha terminado.");
                        } else if (errores.get(4)) {
                            imprimirError("El cliente ya tiene una butaca en esta proyección.");
                        }
                    }

                } while (errores.get(0) != true && !idButaca.equalsIgnoreCase("SALIR"));

            } else {
                imprimirError("No se ha encontrado esa proyección.");
            }

        } else {
            imprimirError("No se a encontrado un cliente con ese DNI.");
        }
    }

    /**
     * Busca una pelicula y muestra todas sus proyecciones programadas.
     * 
     * @param scanner
     * @param cine
     * @return String
     */
    public static void mostrarProyeccionesPelicula(Scanner scanner, Cine cine) {
        // Busca la pelicula
        String idPelicula = leerTexto(scanner, "ID de la pelicula a buscar: ");
        Pelicula peliculaBuscar = cine.buscarPelicula(idPelicula);

        // Muestra sus proyecciones
        if (peliculaBuscar != null) {
            imprimirInfo("[ Proyecciones de " + peliculaBuscar.getTitulo() + " ]\n"
                    + cine.mostrarProyeccionesPelicula(peliculaBuscar));

        } else {
            imprimirError("No se encontro una película con ese ID.");
        }
    }

    /**
     * Busca y muestra los detalles de una pelicula.
     * 
     * @param scanner
     * @param cine
     * @return String
     */
    public static void verDetallesPelicula(Scanner scanner, Cine cine) {
        // Busca pelicula
        Pelicula peliculaMostrar = cine.buscarPelicula(leerTexto(scanner, "ID de la pelicula: "));

        // Muestra sus detalles
        if (peliculaMostrar != null) {
            imprimirInfo("" + peliculaMostrar);
        } else {
            imprimirError("No se ha encontrado una pelicula con ese ID.");
        }
    }

    /**
     * Pregunta por el codigo de una sala, la busca si y muestra si esta disponible
     * en el momento de la ejecucion.
     * 
     * @param scanner Scanner
     * @param cine    Cine
     * @return String
     */
    public static void mostrarDispoSala(Scanner scanner, Cine cine) {
        // Busca la sala
        String idSalaComprobar = leerTexto(scanner, "ID de la sala a consultar: ");
        Sala salaComprobar = cine.buscarSala(idSalaComprobar);

        // Muesra su disponibilidad si a encuentra
        if (salaComprobar != null) {
            imprimirInfo("La sala " + idSalaComprobar + " está "
                    + salaComprobar.mostrarDisponibilidad("libre", "ocupada") + ".");
        } else {
            imprimirError("No se ha encontrado una sala con ese ID.");
        }
    }

    /**
     * Programa una nueva proyeccion, consulta sala, pelicula y hora
     * Valida que la sala y la pelicula existan y que la hora sea correcta y no
     * solape con una proyeccion ya programada.
     * 
     * @param scanner
     * @param cine
     * @return String de feedback
     */
    public static void programarProyeccion(Scanner scanner, Cine cine) {
        // El cine tiene peliculas que programar?
        if (cine.getPeliculas().size() > 0) {
            String idSalaAniadirPro = leerTexto(scanner,
                    "ID de la sala en la que añadir una proyección: ");
            Sala salaAniadirPro = cine.buscarSala(idSalaAniadirPro);

            // Se encontro una sala?
            if (salaAniadirPro != null) {
                String idPeliculaAniadir = leerTexto(scanner, "ID de la pelicula a proyectar: ");
                Pelicula peliculaAniadir = cine.buscarPelicula(idPeliculaAniadir);

                // Se encontro una pelicula?
                if (peliculaAniadir != null) {
                    String proHoraInicio = leerTexto(scanner,
                            "Hora de inicio de la proyección (Formato: HH:MM): ");

                    // Formato valido para LocalTime?
                    try {
                        Proyeccion proyeccionAniadir = new Proyeccion(peliculaAniadir, proHoraInicio, salaAniadirPro);

                        // Se solapa con alguna existente?
                        if (salaAniadirPro.agregarProyeccion(proyeccionAniadir)) {
                            imprimirExito(peliculaAniadir.getTitulo()
                                    + " programada con éxito programada de "
                                    + proyeccionAniadir.getHoraInicio() + " a "
                                    + proyeccionAniadir.getHoraFin() + "!");
                        } else {
                            imprimirError(
                                    "El horario se solapa con una proyeccion ya programada.");
                        }

                    } catch (DateTimeParseException e) {
                        imprimirError("Hora introducida incorrecta. Usa formato HH:MM.");

                    }

                } else {
                    imprimirError("No se ha encontrado una pelicula con ese ID.");
                }
            } else {
                imprimirError("No se ha encontrado una sala con ese ID.");
            }
        } else {
            imprimirError("El cine no tiene peliculas todavía, Añade peliculas para poder programarlas.");
        }
    }

    /**
     * Solicita el id de la proyeccion a cancelar y la cancela.
     * 
     * @param scanner
     * @param cine
     * @return String de feedback
     */
    public static void cancelarProyeccion(Scanner scanner, Cine cine) {
        // Busca la proyeccion
        Proyeccion proyeccionCanelar = cine
                .buscarProyeccion(leerTexto(scanner, "ID de la proyección a cancelar: "));

        // Se encontro una proyeccion?
        if (proyeccionCanelar != null) {
            // No ha terminado?
            if (LocalTime.now().isBefore(proyeccionCanelar.getHoraFin())) {
                Sala salaProyecion = proyeccionCanelar.getSala();
                salaProyecion.cancelarProyeccion(proyeccionCanelar);
                imprimirExito("Proyección cancelada con éxito!");
            } else {
                imprimirError("No es posible cancelar una proyección ya finalizada.");
            }
        } else {
            imprimirError("No se ha encontrado una proyección con ese ID.");
        }
    }
    // endregion

    // region [ BASICAS ]
    public static void imprimirPrompt(String mensaje) {
        System.out.print(mensaje);
    }

    public static void imprimirInfo(String mensaje) {
        System.out.println(mensaje);
    }

    public static void imprimirExito(String mensaje) {
        final String RESET = "\u001B[0m"; // Resetea estilo
        final String BRIGHT_GREEN = "\u001B[92m"; // Color verde

        System.out.println(BRIGHT_GREEN + mensaje + RESET);
    }

    public static void imprimirError(String mensaje) {
        final String RESET = "\u001B[0m"; // Resetea estilo
        final String LIGHT_RED = "\u001B[38;2;255;120;120m"; // Color rojo

        System.err.println(LIGHT_RED + mensaje + RESET);
    }

    public static boolean validarOpcion(int opcionValor, int max) {
        return opcionValor >= 1 && opcionValor <= max;
    }

    public static String leerTexto(Scanner scanner, String mensaje) {
        String texto;
        do {
            imprimirPrompt(mensaje);
            texto = scanner.nextLine().trim();
            if (texto.isEmpty()) {
                imprimirError("No puede estar vacío.");
            }
        } while (texto.isEmpty());

        return texto;
    }

    public static int leerInt(Scanner scanner, String mensaje) {
        int num = -1;
        boolean intValido = false;

        do {
            imprimirPrompt(mensaje);
            if (scanner.hasNextInt()) {
                num = scanner.nextInt();
                scanner.nextLine(); // limpiar buffer
                intValido = true;
            } else {
                imprimirError("Introduce un número entero válido.");
                scanner.nextLine(); // limpiar buffer
            }
        } while (!intValido);

        return num;
    }

    public static int leerIntPositivo(Scanner scanner, String mensaje) {
        int num;
        do {
            num = leerInt(scanner, mensaje);
            if (num <= 0) {
                imprimirError("Debe ser mayor que 0");
            }
        } while (num <= 0);

        return num;
    }

    public static String mostrarMenu(ArrayList<String> opciones, String cabecera) {
        int mayorLongOpcion = 0;

        // Estilos
        final String BRIGHT_WHITE = "\u001B[97m"; // Blanco brillante
        final String BOLD = "\u001B[1m"; // Aplica negrita
        final String RESET = "\u001B[0m"; // Resetea para que no se quede el resto del texto con ese estilo

        // Encuentra la opcion mas larga
        for (String opcion : opciones) {
            if (opcion.length() > mayorLongOpcion) {
                mayorLongOpcion = opcion.length();
            }
        }

        // " 1. " = ajuste +4
        mayorLongOpcion += 4;

        // Se queda con el mas largo entre cabecera y mayor opcion
        int anchoContenido = Math.max(mayorLongOpcion, cabecera.length());

        // Ajuste por espacio inicial al alinear opciones
        int anchoTotal = anchoContenido + 1;

        // Cabecera centrada
        String titulo = " " + BRIGHT_WHITE + BOLD + centrarTexto(cabecera, anchoContenido) + RESET;

        // Cabecera decorada
        String menu = "\n╔" + "═".repeat(anchoTotal) + "╗";
        menu += "\n║" + titulo + "║";
        menu += "\n╠" + "═".repeat(anchoTotal) + "╣";

        // Opciones alineadas
        for (int i = 0; i < opciones.size(); i++) {
            String opcion = (i + 1) + ". " + opciones.get(i);
            menu += "\n║ " + rellenarDerecha(opcion, anchoContenido) + "║";
        }

        // Linea final
        menu += "\n╚" + "═".repeat(anchoTotal) + "╝";

        return menu;
    }

    public static String centrarTexto(String texto, int ancho) {
        int espacios = ancho - texto.length();
        int izquierda = espacios / 2;
        int derecha = espacios - izquierda;

        return " ".repeat(izquierda) + texto + " ".repeat(derecha);
    }

    public static String rellenarDerecha(String texto, int ancho) {
        return texto + " ".repeat(ancho - texto.length());
    }

    public static int leerOpcionMenu(Scanner scanner, ArrayList<String> menu, String cabecera) {
        int opcion;
        imprimirInfo(mostrarMenu(menu, cabecera));
        do {
            opcion = leerInt(scanner, ">> Opción: ");
            if (!validarOpcion(opcion, menu.size())) {
                imprimirError("Opción fuera de rango.");
            }
        } while (!validarOpcion(opcion, menu.size()));

        return opcion;
    }
    // endregion
}
