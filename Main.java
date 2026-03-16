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

        // region [ Creacion de objetos para testing ]
        Pelicula pelicula1 = new Pelicula("Avatar", 60, "+12", "Terror");
        Pelicula pelicula2 = new Pelicula("El precio del poder", 120, "+18", "Accion");
        Pelicula pelicula3 = new Pelicula("Goodfellas", 160, "+16", "Comedia");

        // System.out.println(pelicula1);
        Sala sala1 = new Sala(100);
        Sala sala2 = new Sala(150);

        Proyeccion proyeccion1 = new Proyeccion(pelicula1, "14:30", sala1);
        Proyeccion proyeccion2 = new Proyeccion(pelicula2, "16:30", sala1);
        Proyeccion proyeccion3 = new Proyeccion(pelicula3, "9:30", sala2);
        // System.out.println(proyeccion1);

        sala1.agregarProyeccion(proyeccion1);
        sala1.agregarProyeccion(proyeccion2);
        // System.out.println(sala1);

        sala2.agregarProyeccion(proyeccion3);

        Cine cineStem = new Cine("Cine STEM", "Granada", "España");
        cineStem.agregarSala(sala1);
        cineStem.agregarSala(sala2);

        cineStem.agregarPelicula(pelicula1);
        cineStem.agregarPelicula(pelicula2);
        cineStem.agregarPelicula(pelicula3);

        Cliente cliente1 = new Cliente("Mario", "1235567N", 21, "Premium", 1);
        Cliente cliente2 = new Cliente("Lola", "5463367N", 19, "Normal", 2);
        Cliente cliente3 = new Cliente("Mento", "4343655N", 15, "Normal", 3);

        cineStem.registrarCliente(cliente1);
        cineStem.registrarCliente(cliente2);
        cineStem.registrarCliente(cliente3);

        // System.out.println(cliente1);

        // endregion

        // region [ Declaraciones ]
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

        // region [ Menú ]
        do {
            opcionMenuPrincipal = leerOpcionMenu(scanner, opcionesMenu, "[ " + cineStem.getNombre() + " ]");

            if (opcionMenuPrincipal == 1) { // Gestionar Cine
                do {
                    opcionMenuCine = leerOpcionMenu(scanner, opcionesCines, "[ GESTIÓN CINE ]");

                    if (opcionMenuCine == 1) { // Ver detalles cine
                        System.out.println(cineStem);
                    } else if (opcionMenuCine == 2) { // Volver al menú principal
                        System.out.println("Volviendo al menú principal..");
                    }

                } while (opcionMenuCine != opcionesCines.size());

            } else if (opcionMenuPrincipal == 2) { // Gestionar Salas
                do {
                    opcionMenuSalas = leerOpcionMenu(scanner, opcionesSalas, "[ GESTIÓN SALAS ]");

                    if (opcionMenuSalas == 1) { // Listar salas de un cine
                        System.out.println("[ SALAS ]\n" + cineStem.mostrarSalas());

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
                    opcionMenuPeliculas = leerOpcionMenu(scanner, opcionesPeliculas, "[ GESTIÓN PELICULAS ]");

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
                    opcionMenuClientes = leerOpcionMenu(scanner, opcionesClientes, "[ GESTIÓN CLIENTES ]");

                    if (opcionMenuClientes == 1) { // Registrar cliente en una proyección
                        Cliente clienteAniadir = cineStem
                                .buscarClientePorDni(leerTexto(scanner, "DNI del cliente a registrar: "));

                        System.out.println(clienteAniadir);
                        if (clienteAniadir != null) {
                            registrarClienteEnPro(scanner, cineStem, clienteAniadir);

                        } else {
                            imprimirError("No se a encontrado un cliente con ese DNI, vuelva a intentarlo.");
                        }
                    } else if (opcionMenuClientes == 2) { // Cancelar asistencia

                    } else if (opcionMenuClientes == 3) { // Ver lista de clientes en una proyección

                    } else if (opcionMenuClientes == 4) { // Ver número total de asistentes

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
        // endregion

        scanner.close();

    }

    // ===================
    // == [ FUNCIONES ] ==
    // ===================

    // region [ Cine ]

    public static void registrarNuevoCliente(Scanner scanner, Cine cine) {
        imprimirMensaje("Introduzca los datos del cliente");
        Cliente clienteRegistrar = new Cliente(
                leerTexto(scanner, "Nombre: "),
                leerTexto(scanner, "DNI: "),
                leerIntPositivo(scanner, "Edad: "),
                leerTexto(scanner, "Tipo de entrada: "),
                leerIntPositivo(scanner, "Número de entrada: "));

        cine.registrarCliente(clienteRegistrar);
    }

    public static void registrarClienteEnPro(Scanner scanner, Cine cine, Cliente clienteRegistrar) {
        // Busca proyeccion
        Proyeccion proyeccionRegistrar = cine.buscarProyeccion(
                leerTexto(scanner, "Codigo de la proyección en la que registrar al cliente: "));

        // Se encontro una proyección?
        if (proyeccionRegistrar != null) {
            Sala salaAniadir = proyeccionRegistrar.getSala();

            ArrayList<Boolean> arrayErrores = salaAniadir.registrarClientePro(clienteRegistrar,
                    proyeccionRegistrar);

            // Control de errores
            if (arrayErrores.get(0)) {
                imprimirError("Proyeccion no registrada en ninguna sala.");
            } else if (arrayErrores.get(1)) {
                imprimirError("Cliente ya registrado en la proyeccion.");
            } else if (arrayErrores.get(2)) {
                imprimirError("No hay asientos libres en la sala.");
            } else if (arrayErrores.get(3)) {
                imprimirError("Ya ha terminado la proyeccion.");
            } else {
                imprimirMensaje("Cliente registrado a la proyeccion con exito!");
            }

        } else {
            imprimirError("No se ha encontrado esa proyeccion, vuelva a intentarlo.");
        }
    }

    /**
     * Busca una pelicula y muestra todas sus proyecciones programadas
     * 
     * @param scanner
     * @param cine
     * @return String
     */
    public static void mostrarProyeccionesPelicula(Scanner scanner, Cine cine) {
        String idPelicula = leerTexto(scanner, "ID de la pelicula a buscar: ");
        Pelicula peliculaBuscar = cine.buscarPelicula(idPelicula);

        if (peliculaBuscar != null) {
            imprimirMensaje("[ Proyecciones de " + peliculaBuscar.getTitulo() + " ]\n"
                    + cine.mostrarProyeccionesPelicula(peliculaBuscar));

        } else {
            imprimirError("No se encontro una película con ese ID.");
        }
    }

    /**
     * Busca y muestra los detalles de una pelicula
     * 
     * @param scanner
     * @param cine
     * @return String
     */
    public static void verDetallesPelicula(Scanner scanner, Cine cine) {
        Pelicula peliculaMostrar = cine.buscarPelicula(leerTexto(scanner, "ID de la pelicula: "));

        if (peliculaMostrar != null) {
            imprimirMensaje("" + peliculaMostrar);
        } else {
            imprimirError("No se encontro una pelicula con ese ID, vuelva a intentarlo.");
        }
    }

    /**
     * Pregunta por el codigo de una sala, la busca si y muestra si esta disponible
     * en el momento de la ejecucion
     * 
     * @param scanner Scanner
     * @param cine    Cine
     * @return String
     */
    public static void mostrarDispoSala(Scanner scanner, Cine cine) {
        String idSalaComprobar = leerTexto(scanner, "ID de la sala a consultar: ");
        Sala salaComprobar = cine.buscarSala(idSalaComprobar);

        if (salaComprobar != null) {
            imprimirMensaje("La sala " + idSalaComprobar + " está "
                    + salaComprobar.mostrarDisponibilidad("libre", "ocupada") + ".");
        } else {
            imprimirError("No se ha encontrado una sala con ese ID, vuelva a intentarlo.");
        }
    }

    /**
     * Programa una nueva proyeccion, consulta sala, pelicula y hora
     * Valida que la sala y la pelicula existan y que la hora sea correcta y no
     * solape con una proyeccion ya programada
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
                            "Hora de inicio de la proyeccion (Formato: HH:MM): ");

                    // Formato valido para LocalTime?
                    try {
                        Proyeccion proyeccionAniadir = new Proyeccion(peliculaAniadir, proHoraInicio, salaAniadirPro);

                        // Se solapa con alguna existente?
                        if (salaAniadirPro.agregarProyeccion(proyeccionAniadir)) {
                            imprimirMensaje(peliculaAniadir.getTitulo()
                                    + " programada con exito programada de "
                                    + proyeccionAniadir.getHoraInicio() + " a "
                                    + proyeccionAniadir.getHoraFin() + "!");
                        } else {
                            imprimirError(
                                    "El horario se solapa con una proyeccion ya programada, vuelva a intentarlo.");
                        }

                    } catch (DateTimeParseException e) {
                        imprimirError("Hora introducida incorrecta. Usa formato HH:MM");

                    }

                } else {
                    imprimirError("No se ha encontrado una pelicula con ese ID, vuelva a intentarlo.");
                }
            } else {
                imprimirError("No se ha encontrado una sala con ese ID, vuelva a intentarlo.");
            }
        } else {
            imprimirError("El cine no tiene peliculas todavia, Añade peliculas para poder programarlas.");
        }
    }

    /**
     * Solicita datos para encontrar la proyeccion a cancelar y la cancela
     * 
     * @param scanner
     * @param cine
     * @return String de feedback
     */
    public static void cancelarProyeccion(Scanner scanner, Cine cine) {
        String idSalaAniadirPro = leerTexto(scanner,
                "ID de la sala en la que cancelar una proyección: ");
        Sala salaAniadirPro = cine.buscarSala(idSalaAniadirPro);

        // Se encontro una sala?
        if (salaAniadirPro != null) {
            String idProyeccion = leerTexto(scanner, "ID de la proyeccion a cancelar: ");
            Proyeccion proyeccionCanelar = salaAniadirPro.buscarProyeccion(idProyeccion);

            if (salaAniadirPro.buscarProyeccion(idProyeccion) != null) {
                if (LocalTime.now().isBefore(proyeccionCanelar.getHoraFin())) {
                    salaAniadirPro.cancelarProyeccion(idProyeccion);
                    imprimirMensaje("Proyección cancelada con exito!");
                } else {
                    imprimirError("No es posible cancelar una proyeccion ya finalizada");
                }
            } else {
                imprimirError("No se ha encontrado una proyeccion con ese ID en esta sala, vuelva a intentarlo.");
            }

        } else {
            imprimirError("No se ha encontrado una sala con ese ID, vuelva a intentarlo.");
        }
    }
    // endregion

    // region [ Basicas ]
    public static void imprimirPrompt(String mensaje) {
        System.out.print(mensaje);
    }

    public static void imprimirMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public static void imprimirError(String mensaje) {
        System.err.println(mensaje);
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
                imprimirError("No puede estar vacío");
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
                imprimirError("Introduce un número entero válido");
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

    public static void mostrarMenu(ArrayList<String> opciones, String cabecera) {
        imprimirMensaje("\n--- " + cabecera + " ---");
        for (int i = 0; i < opciones.size(); i++) {
            imprimirMensaje((i + 1) + ". " + opciones.get(i));
        }
    }

    public static int leerOpcionMenu(Scanner scanner, ArrayList<String> menu, String cabecera) {
        int opcion;
        mostrarMenu(menu, cabecera);
        do {
            opcion = leerInt(scanner, ">> Opción: ");
            if (!validarOpcion(opcion, menu.size())) {
                imprimirError("Opción fuera de rango");
            }
        } while (!validarOpcion(opcion, menu.size()));

        return opcion;
    }
    // endregion
}
