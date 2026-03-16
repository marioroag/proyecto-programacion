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

        Proyeccion proyeccion1 = new Proyeccion(pelicula1, "14:30");
        Proyeccion proyeccion2 = new Proyeccion(pelicula2, "16:30");
        Proyeccion proyeccion3 = new Proyeccion(pelicula3, "9:30");
        // System.out.println(proyeccion1);

        Sala sala1 = new Sala(100);
        sala1.agregarProyeccion(proyeccion1);
        sala1.agregarProyeccion(proyeccion2);
        // System.out.println(sala1);

        Sala sala2 = new Sala(100);
        sala2.agregarProyeccion(proyeccion1);
        sala2.agregarProyeccion(proyeccion3);

        Cine cineStem = new Cine("Cine STEM", "Granada", "España");
        cineStem.agregarSala(sala1);
        cineStem.agregarSala(sala2);

        cineStem.agregarPelicula(pelicula1);
        cineStem.agregarPelicula(pelicula2);
        cineStem.agregarPelicula(pelicula3);

        Cliente cliente1 = new Cliente("Mario", "1235567N", 21, "Premium", 1);
        // System.out.println(cliente1);

        // endregion

        // region [ Declaraciones ]
        Scanner scanner = new Scanner(System.in);

        int opcionMenuPrincipal, opcionMenuCine, opcionMenuSalas, opcionMenuPeliculas;

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
        // endregion

        // region [ Menú ]
        do {
            opcionMenuPrincipal = leerOpcionMenu(scanner, opcionesMenu, cineStem.getNombre());

            if (opcionMenuPrincipal == 1) { // Gestionar Cine
                do {
                    opcionMenuCine = leerOpcionMenu(scanner, opcionesCines, "GESTIÓN CINE");

                    if (opcionMenuCine == 1) { // Ver detalles cine
                        System.out.println(cineStem);
                    } else if (opcionMenuCine == 2) { // Volver al menú principal
                        System.out.println("Volviendo al menú principal..");
                    }

                } while (opcionMenuCine != opcionesCines.size());

            } else if (opcionMenuPrincipal == 2) { // Gestionar Salas
                do {
                    opcionMenuSalas = leerOpcionMenu(scanner, opcionesSalas, "GESTIÓN SALAS");

                    if (opcionMenuSalas == 1) { // Listar salas de un cine
                        System.out.println("[ SALAS ]\n" + cineStem.mostrarSalas());

                    } else if (opcionMenuSalas == 2) { // Consultar disponibilidad de una sala
                        System.out.println(mostrarDispoSala(scanner, cineStem));

                    } else if (opcionMenuSalas == 3) { // Programar proyeccion en una sala
                        System.out.println(programarProyeccion(scanner, cineStem));

                    } else if (opcionMenuSalas == 4) { // Cancelar proyección
                        cancelarProyeccion(scanner, cineStem);

                    } else if (opcionMenuSalas == 5) { // Volver al menú principal
                        System.out.println("Volviendo al menú principal..");
                    }

                } while (opcionMenuSalas != opcionesSalas.size());

            } else if (opcionMenuPrincipal == 3) { // Gestionar Películas
                do {
                    opcionMenuPeliculas = leerOpcionMenu(scanner, opcionesPeliculas, "GESTIÓN PELICULAS");

                    if (opcionMenuPeliculas == 1) { // Listar películas
                        System.out.println(cineStem.mostarPeliculas());

                    } else if (opcionMenuPeliculas == 2) { // Ver detalles de una pelicula
                        verDetallesPelicula(scanner, cineStem);

                    } else if (opcionMenuPeliculas == 3) { // Ver proyecciones de una película
                        System.out.println(verProyeccionesPelicula(scanner, cineStem));

                    } else if (opcionMenuPeliculas == 4) { // Volver al menú principal
                        System.out.println("Volviendo al menú principal..");
                    }

                } while (opcionMenuPeliculas != opcionesPeliculas.size());

            } else if (opcionMenuPrincipal == 4) { // Gestionar Clientes

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
    /**
     * Busca una pelicula y muestra todas sus proyecciones programadas
     * 
     * @param scanner
     * @param cine
     * @return String
     */
    public static String verProyeccionesPelicula(Scanner scanner, Cine cine) {
        String feedback = "";

        String idPelicula = leerTexto(scanner, "ID de la pelicula a buscar: ");
        Pelicula peliculaBuscar = cine.buscarPelicula(idPelicula);

        if (peliculaBuscar != null) {
            feedback = "[ Proyecciones de " + peliculaBuscar.getTitulo() + " ]\n"
                    + cine.mostrarProyeccionesPelicula(peliculaBuscar);
        } else {
            feedback = "No se encontro una película con ese ID.";
        }

        return feedback;
    }

    /**
     * Busca y muestra los detalles de una pelicula
     * 
     * @param scanner
     * @param cine
     * @return String
     */
    public static String verDetallesPelicula(Scanner scanner, Cine cine) {
        String feedback = "";
        Pelicula peliculaMostrar = cine.buscarPelicula(leerTexto(scanner, "ID de la pelicula: "));

        if (peliculaMostrar != null) {
            feedback += peliculaMostrar;
        } else {
            feedback = "No se encontro una pelicula con ese ID, vuelva a intentarlo.";
        }

        return feedback;
    }

    /**
     * Pregunta por el codigo de una sala, la busca si y muestra si esta disponible
     * en el momento de la ejecucion
     * 
     * @param scanner Scanner
     * @param cine    Cine
     * @return String
     */
    public static String mostrarDispoSala(Scanner scanner, Cine cine) {
        String feedback = "";
        String idSalaComprobar = leerTexto(scanner, "ID de la sala a consultar: ");
        Sala salaComprobar = cine.buscarSala(idSalaComprobar);

        if (salaComprobar != null) {
            feedback = "La sala " + idSalaComprobar + " está "
                    + salaComprobar.mostrarDisponibilidad("libre", "ocupada") + ".";
        } else {
            feedback = "No se ha encontrado una sala con ese ID, vuelva a intentarlo.";
        }

        return feedback;
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
    public static String programarProyeccion(Scanner scanner, Cine cine) {
        String feedback = "";

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
                        Proyeccion proyeccionAniadir = new Proyeccion(peliculaAniadir, proHoraInicio);

                        // Se solapa con alguna existente?
                        if (salaAniadirPro.agregarProyeccion(proyeccionAniadir)) {
                            feedback = peliculaAniadir.getTitulo()
                                    + " programada con exito programada para las "
                                    + proyeccionAniadir.getHoraInicio() + " a "
                                    + proyeccionAniadir.getHoraFin() + "!";
                        } else {
                            feedback = "El horario se solapa con una proyeccion ya programada, vuelva a intentarlo.";
                        }

                    } catch (DateTimeParseException e) {
                        feedback = "Hora introducida incorrecta. Usa formato HH:MM";

                    } catch (IllegalArgumentException e) {

                        feedback = "La hora de fin debe ser posterior a la de inicio.";

                    }

                } else {
                    feedback = "No se ha encontrado una pelicula con ese ID, vuelva a intentarlo.";
                }
            } else {
                feedback = "No se ha encontrado una sala con ese ID, vuelva a intentarlo.";
            }
        } else {
            feedback = "El cine no tiene peliculas todavia, Añade peliculas para poder programarlas.";
        }

        return feedback;
    }

    /**
     * Solicita datos para encontrar la proyeccion a cancelar y la cancela
     * 
     * @param scanner
     * @param cine
     * @return String de feedback
     */
    public static String cancelarProyeccion(Scanner scanner, Cine cine) {
        String feedback = "";

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
                    feedback = "Proyección cancelada con exito!";
                } else {
                    feedback = "No es posible cancelar una proyeccion ya finalizada";
                }
            } else {
                feedback = "No se ha encontrado una proyeccion con ese ID en esta sala, vuelva a intentarlo.";
            }

        } else {
            feedback = "No se ha encontrado una sala con ese ID, vuelva a intentarlo.";
        }

        return feedback;
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
                imprimirMensaje("No puede estar vacío");
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
                imprimirMensaje("Introduce un número entero válido");
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
                imprimirMensaje("Debe ser mayor que 0");
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
        do {
            mostrarMenu(menu, cabecera);
            opcion = leerInt(scanner, "Opción: ");
            if (!validarOpcion(opcion, menu.size())) {
                imprimirMensaje("Opción fuera de rango");
            }
        } while (!validarOpcion(opcion, menu.size()));

        return opcion;
    }
    // endregion
}
