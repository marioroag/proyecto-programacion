import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Pelicula pelicula1 = new Pelicula("Avatar", 60, "+12", "Terror");
        Pelicula pelicula2 = new Pelicula("El precio del dinero", 120, "+18", "Accion");
        Pelicula pelicula3 = new Pelicula("Goodfellas", 160, "+16", "Comedia");
        // System.out.println(pelicula1);

        Proyeccion proyeccion1 = new Proyeccion(pelicula1, "14:30", "15:40");
        Proyeccion proyeccion2 = new Proyeccion(pelicula2, "16:30", "18:40");
        Proyeccion proyeccion3 = new Proyeccion(pelicula3, "10:30", "11:40");
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
        System.out.println(cineStem);

        Scanner scanner = new Scanner(System.in);

        ArrayList<String> opcionesMenu = new ArrayList<>(
                Arrays.asList("Salir", "Informacion del cine", "Ver salas de cine",
                        "Comprobar disponibilidad de sala", "Programar pelicula", "Cancelar proyeccion"));

        int opcionMenu;

        do {
            opcionMenu = leerOpcionMenu(scanner, opcionesMenu);
            if (opcionMenu == 0) { // Salir
                cineStem.setCiudad("Granada");
                cineStem.setPais("Japon");
                cineStem.setNombre("STEMOS");
            } else if (opcionMenu == 1) { // Informacion del cine
                System.out.println(cineStem);
            } else if (opcionMenu == 2) { // Mostrar salas
                System.out.println(cineStem.mostrarSalas());
            } else if (opcionMenu == 3) { // Comprobar disponibilidad de la sala
                System.out.println(sala1.estaOcupada());
            } else if (opcionMenu == 4) { // Programar pelicula
                System.out.println("No se agrega por solapacion con 10:30, 11:40");
                Proyeccion proyeccion4 = new Proyeccion(pelicula3, "9:00", "11:00");
                if (sala1.agregarProyeccion(proyeccion4) == 1) {
                    System.out.println("La pelicula a programar se solapa con otra película existente.");
                } else {
                    System.out.println("Pelicula añadida correctamente.");
                }

                System.out.println("Se cambia la hora para que no solapen");
                proyeccion4.setHoraFin("10:00");
                if (sala1.agregarProyeccion(proyeccion4) == 1) {
                    System.out.println("La pelicula a programar se solapa con otra película existente.");
                } else {
                    System.out.println("Pelicula añadida correctamente.");
                }
            } else if (opcionMenu == 5) { // Cancelar proyeccion
                sala1.cancelarProyeccion("PRO-2");
            }
        } while (opcionMenu != 0);

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
        return opcionValor >= 0 && opcionValor <= max;
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

    public static void mostrarMenu(ArrayList<String> opciones) {
        imprimirMensaje("\n--- MENÚ ---");
        for (int i = 0; i < opciones.size(); i++) {
            imprimirMensaje(i + ". " + opciones.get(i));
        }
    }

    public static int leerOpcionMenu(Scanner scanner, ArrayList<String> menu) {
        int opcion;
        do {
            mostrarMenu(menu);
            opcion = leerInt(scanner, "Opción: ");
            if (!validarOpcion(opcion, menu.size() - 1)) {
                imprimirMensaje("Opción fuera de rango");
            }
        } while (!validarOpcion(opcion, menu.size() - 1));

        return opcion;
    }
}
