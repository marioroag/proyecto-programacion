import java.util.*;

public class FakerCine {
    // Faker cortesia de ChatGPT
    private static final Random random = new Random();

    private static final String[] nombres = {
            "Mario", "Lola", "Carlos", "Ana", "Lucia", "Pedro", "Sergio", "Elena",
            "David", "Marta", "Javier", "Claudia", "Raul", "Andrea"
    };

    private static final String[] apellidos = {
            "Gomez", "Perez", "Lopez", "Martinez", "Sanchez", "Ramirez"
    };

    private static final String[] peliculas = {
            "Avatar", "Interstellar", "Matrix", "Inception", "Titanic",
            "Joker", "Gladiator", "The Batman", "Dune", "Oppenheimer"
    };

    private static final String[] generos = {
            "Accion", "Comedia", "Drama", "Terror", "Ciencia Ficcion"
    };

    private static final String[] clasificaciones = {
            "+7", "+12", "+16", "+18"
    };

    // =========================
    // 🎬 MÉTODO PRINCIPAL
    // =========================
    public static Cine generarCineFake() {

        Cine cine = new Cine("Cine Faker PRO", "Granada", "España");

        // 🎥 Películas
        ArrayList<Pelicula> pelis = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Pelicula p = generarPelicula();
            pelis.add(p);
            cine.agregarPelicula(p);
        }

        // 👤 Clientes
        ArrayList<Cliente> clientes = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Cliente c = generarCliente(i + 1);
            clientes.add(c);
            cine.registrarCliente(c);
        }

        // 🏟️ Salas + Proyecciones
        for (int i = 0; i < 4; i++) {

            Sala sala = new Sala(80 + random.nextInt(120));

            int horaActual = 10;

            while (horaActual < 22) {

                Pelicula peli = pelis.get(random.nextInt(pelis.size()));

                int duracionMin = peli.getDuracion();
                int duracionHoras = (int) Math.ceil(duracionMin / 60.0);

                // evitar pasar de medianoche
                if (horaActual + duracionHoras >= 24)
                    break;

                String horaInicio = String.format("%02d:%02d",
                        horaActual,
                        random.nextBoolean() ? 0 : 30);

                Proyeccion proy = new Proyeccion(peli, horaInicio, sala);

                if (sala.agregarProyeccion(proy)) {

                    llenarProyeccion(proy, clientes);

                    // siguiente con margen
                    horaActual += duracionHoras + 1;

                } else {
                    horaActual++;
                }
            }

            cine.agregarSala(sala);
        }

        return cine;
    }

    // =========================
    // 🎟️ LLENAR PROYECCIÓN
    // =========================
    private static void llenarProyeccion(Proyeccion proy, ArrayList<Cliente> clientes) {

        int filas = 10;
        int columnas = 20;

        ArrayList<String> idsButacas = new ArrayList<>();

        // 🧠 Generar todas las butacas reales (como tú las creas)
        for (int i = 1; i <= filas; i++) {
            for (int j = 0; j < columnas; j++) {
                char letra = (char) ('A' + j);
                idsButacas.add(i + "" + letra);
            }
        }

        // 🔀 Mezclar
        Collections.shuffle(idsButacas);

        int total = idsButacas.size();

        // 🎟️ ocupación entre 30% y 70%
        int ocupacion = (int) (total * (0.3 + Math.random() * 0.4));

        for (int i = 0; i < ocupacion; i++) {

            String idButaca = idsButacas.get(i);
            Cliente cliente = clientes.get((int) (Math.random() * clientes.size()));

            proy.asignarButaca(idButaca, cliente);
        }
    }

    // =========================
    // 🎬 PELÍCULA
    // =========================
    private static Pelicula generarPelicula() {
        return new Pelicula(
                peliculas[random.nextInt(peliculas.length)],
                80 + random.nextInt(100),
                clasificaciones[random.nextInt(clasificaciones.length)],
                generos[random.nextInt(generos.length)]);
    }

    // =========================
    // 👤 CLIENTE
    // =========================
    private static Cliente generarCliente(int id) {
        return new Cliente(
                nombreCompleto(),
                generarDNI(),
                14 + random.nextInt(50),
                random.nextBoolean() ? "Premium" : "Normal",
                id);
    }

    private static String nombreCompleto() {
        return nombres[random.nextInt(nombres.length)] + " " +
                apellidos[random.nextInt(apellidos.length)];
    }

    private static String generarDNI() {
        int numero = 10000000 + random.nextInt(90000000);
        char letra = (char) ('A' + random.nextInt(26));
        return numero + "" + letra;
    }
}