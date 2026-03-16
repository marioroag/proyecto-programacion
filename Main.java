import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // [ Creacion de objetos para testing ]
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

        // [ Declaraciones ]
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

        // [ Menú ]
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
                        String idSalaComprobar = leerTexto(scanner, "ID de la sala a consultar: ");
                        Sala salaComprobar = cineStem.buscarSala(idSalaComprobar);

                        if (salaComprobar != null) {
                            System.out.println("La sala " + idSalaComprobar + " está "
                                    + salaComprobar.mostrarDisponibilidad("libre", "ocupada") + ".");
                        } else {
                            System.out.println("No se ha encontrado una sala con ese ID, vuelva a intentarlo.");
                        }

                    } else if (opcionMenuSalas == 3) { // Programar proyeccion en una sala
                        // con otra proyección ya programada en la misma sala.

                        // El cine tiene peliculas que programar?
                        if (cineStem.getPeliculas().size() > 0) {
                            String idSalaAniadirPro = leerTexto(scanner,
                                    "ID de la sala en la que añadir una proyección: ");
                            Sala salaAniadirPro = cineStem.buscarSala(idSalaAniadirPro);

                            // Se encontro una sala?
                            if (salaAniadirPro != null) {
                                String idPeliculaAniadir = leerTexto(scanner, "ID de la pelicula a proyectar: ");
                                Pelicula peliculaAniadir = cineStem.buscarPelicula(idPeliculaAniadir);

                                // Se encontro una pelicula?
                                if (peliculaAniadir != null) {
                                    String proHoraInicio = leerTexto(scanner,
                                            "Hora de inicio de la proyeccion (Formato: HH:MM): ");

                                    // Formato valido para LocalTime?
                                    try {
                                        Proyeccion proyeccionAniadir = new Proyeccion(peliculaAniadir, proHoraInicio);

                                        // Se solapa con alguna existente?
                                        if (salaAniadirPro.agregarProyeccion(proyeccionAniadir)) {
                                            System.out.println(
                                                    peliculaAniadir.getTitulo()
                                                            + " programada con exito programada para las "
                                                            + proyeccionAniadir.getHoraInicio() + " a "
                                                            + proyeccionAniadir.getHoraFin() + "!");
                                        } else {
                                            System.out.println(
                                                    "El horario se solapa con una proyeccion ya programada, vuelva a intentarlo.");
                                        }

                                    } catch (DateTimeParseException e) {
                                        System.out.println("Hora introducida incorrecta. Usa formato HH:MM");

                                    } catch (IllegalArgumentException e) {

                                        System.out.println("La hora de fin debe ser posterior a la de inicio.");

                                    }

                                } else {
                                    System.out.println(
                                            "No se ha encontrado una pelicula con ese ID, vuelva a intentarlo.");
                                }
                            } else {
                                System.out.println("No se ha encontrado una sala con ese ID, vuelva a intentarlo.");
                            }
                        } else {
                            System.out.println(
                                    "El cine no tiene peliculas todavia, Añade peliculas para poder programarlas.");
                        }

                    } else if (opcionMenuSalas == 4) { // Cancelar proyección
                        String idSalaAniadirPro = leerTexto(scanner,
                                "ID de la sala en la que cancelar una proyección: ");
                        Sala salaAniadirPro = cineStem.buscarSala(idSalaAniadirPro);

                        // Se encontro una sala?
                        if (salaAniadirPro != null) {
                            String idProyeccion = leerTexto(scanner, "ID de la proyeccion a cancelar: ");
                            Proyeccion proyeccionCanelar = salaAniadirPro.buscarProyeccion(idProyeccion);

                            if (salaAniadirPro.buscarProyeccion(idProyeccion) != null) {
                                if (LocalTime.now().isBefore(proyeccionCanelar.getHoraFin())) {
                                    salaAniadirPro.cancelarProyeccion(idProyeccion);
                                    System.out.println("Proyección cancelada con exito!");
                                } else {
                                    System.out.println("No es posible cancelar una proyeccion ya finalizada");
                                }
                            } else {
                                System.out.println(
                                        "No se ha encontrado una proyeccion con ese ID en esta sala, vuelva a intentarlo.");
                            }

                        } else {
                            System.out.println("No se ha encontrado una sala con ese ID, vuelva a intentarlo.");
                        }
                    } else if (opcionMenuSalas == 5) { // Volver al menú principal
                        System.out.println("Volviendo al menú principal..");
                    }

                } while (opcionMenuSalas != opcionesSalas.size());

            } else if (opcionMenuPrincipal == 3) { // Gestionar Películas
                do {
                    opcionMenuPeliculas = leerOpcionMenu(scanner, opcionesPeliculas, "GESTIÓN PELICULAS");

                    if (opcionMenuPeliculas == 1) { // Listar películas
                        if (cineStem.getPeliculas().size() > 0) {
                            System.out.println(cineStem.mostarPeliculas());
                        } else {
                            System.out.println("No hay peliculas que mostrar todavía.");
                        }

                    } else if (opcionMenuPeliculas == 2) { // Ver detalles de una pelicula
                        Pelicula peliculaMostrar = cineStem.buscarPelicula(leerTexto(scanner, "ID de la pelicula: "));

                        if (peliculaMostrar != null) {
                            System.out.println(peliculaMostrar);
                        } else {
                            System.out.println("No se encontro una pelicula con ese ID, vuelva a intentarlo.");
                        }

                    } else if (opcionMenuPeliculas == 3) { // Ver proyecciones de una película
                        String idPelicula = leerTexto(scanner, "ID de la pelicula a buscar: ");
                        Pelicula peliculaBuscar = cineStem.buscarPelicula(idPelicula);

                        if (peliculaBuscar != null) {
                            System.out.println(cineStem.mostrarProyeccionesPelicula(peliculaBuscar));
                        } else {
                            System.out.println("No se encontro una película con ese ID.");
                        }

                    } else if (opcionMenuPeliculas == 4) { // Volver al menú principal
                        System.out.println("Volviendo al menú principal..");
                    }

                } while (opcionMenuPeliculas != opcionesPeliculas.size());

            } else if (opcionMenuPrincipal == 4) { // Gestionar Clientes
            } else if (opcionMenuPrincipal == 5) { // Salir
                System.out.println("Saliendo del programa..");
            }
        } while (opcionMenuPrincipal != opcionesMenu.size());

        scanner.close();

    }

    // =================
    // = [ FUNCIONES ] =
    // =================
    static void imprimirPrompt(String mensaje) {
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
}
