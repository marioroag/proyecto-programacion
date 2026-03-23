import java.util.ArrayList;

public class Cine {
    // [ Atributos ]
    private static int cantidadCines = 1;

    private String codigo;
    private String nombre;
    private String ciudad;
    private String pais;
    private ArrayList<Sala> salas;
    private ArrayList<Pelicula> peliculas;
    private ArrayList<Cliente> clientes;

    // [ Constructores ]
    public Cine(String nombre, String ciudad, String pais) {
        setCodigo();
        setNombre(nombre);
        setCiudad(ciudad);
        setPais(pais);
        this.salas = new ArrayList<>();
        this.peliculas = new ArrayList<>();
        this.clientes = new ArrayList<>();

        cantidadCines++;
    }

    // [ Metodos ]

    /**
     * Recorre el array de clientes buscando el dni recibido y lo devuelve si lo
     * encuentra,
     * si no, {@code null}
     * 
     * @param dniCliente
     * @return Cliente encontrado
     */
    public Cliente buscarClientePorDni(String dniCliente) {
        Cliente clienteEncontrado = null;

        for (Cliente cliente : clientes) {
            if (cliente.getDni().equalsIgnoreCase(dniCliente)) {
                clienteEncontrado = cliente;
            }
        }

        return clienteEncontrado;
    }

    /**
     * Registra a un cliente si no esta duplicado
     * 
     * @param nuevoCliente
     * @return boolean {@code true} si no esta duplicado
     */
    public boolean registrarCliente(Cliente nuevoCliente) {
        boolean duplicado = false;

        for (Cliente cliente : clientes) {
            if (cliente == nuevoCliente) {
                duplicado = true;
            }
        }

        if (!duplicado) {
            this.clientes.add(nuevoCliente);
        }

        return duplicado;
    }

    /**
     * Muestra los datos del cine con los datos de sus salas y sus proyecciones
     * 
     * @return String con todos los datos del Cine
     */
    public String mostrarCineCompleto() {
        String cineMostrar = "[ " + nombre + " " + codigo + " ]\n" + pais + ", " + ciudad + "\n[ Salas ]";

        for (Sala sala : salas) {
            cineMostrar += "\n" + sala;
        }

        return cineMostrar;
    }

    /**
     * Devuelve las peliculas del cine en un String presentable
     * Si no hay pelicula devuelve "Todavía no hay peliculas en este cine."
     * 
     * @return String organizado de peliculas
     */
    public String mostarPeliculas() {
        String peliculasMostrar = "[ Peliculas Disponibles ]";

        if (peliculas.size() > 0) {
            for (Pelicula pelicula : peliculas) {
                peliculasMostrar += "\n" + pelicula.getCodigo() + " | " + pelicula.getTitulo();
            }
        } else {
            peliculasMostrar += "\nTodavía no hay peliculas en este cine.";
        }

        return peliculasMostrar;
    }

    /**
     * Busca una proyeccion en todas las salas del cine y la devuelve
     * 
     * @param codigoPelicula
     * @return Proyeccion encontrada
     */
    public Proyeccion buscarProyeccion(String codigoProyeccion) {
        Proyeccion proyeccionEncontrada = null;

        for (Sala sala : salas) {
            for (Proyeccion proyeccion : sala.getProyecciones()) {
                if (proyeccion.getCodigoProyeccion().equalsIgnoreCase(codigoProyeccion)) {
                    proyeccionEncontrada = proyeccion;
                }
            }
        }

        return proyeccionEncontrada;
    }

    /**
     * Encuentra las proyecciones de una pelicula recorriendo cada sala y cada
     * proyeccion buscando la pelicula que recibe
     * 
     * @param pelicula Pelicula a buscar
     * @return Las proyecciones actuales de la pelicula
     */
    public String mostrarProyeccionesPelicula(Pelicula pelicula) {
        String resultado = "";
        boolean encontrada = false;

        for (Sala sala : salas) {
            for (Proyeccion proyeccion : sala.getProyecciones()) {

                if (proyeccion.getPelicula().equals(pelicula)) {
                    resultado += "Sala: " + sala.getId() + " | "
                            + proyeccion.getHoraInicio() + " - "
                            + proyeccion.getHoraFin() + "\n";

                    encontrada = true;
                }
            }
        }

        if (!encontrada) {
            resultado = "No hay proyecciones programadas para esta película.";
        }

        return resultado;
    }

    /**
     * Agrega una nueva sala al cine si no está antes en el mismo
     * 
     * @param nuevaSala Sala a añadir
     * @return boolean {@code true} si la pelicula no estaba ya en la pelicula
     */
    public boolean agregarPelicula(Pelicula nuevaPelicula) {
        boolean exito = false;

        if (!peliculas.contains(nuevaPelicula)) {
            this.peliculas.add(nuevaPelicula);
            exito = true;
        }

        return exito;
    }

    /**
     * Busca una pelicula dentro de la sala usando su codigo
     * 
     * @param codigoProyeccion Codigo de la pelicula a buscar
     * @return Pelicula encontrada
     */
    public Pelicula buscarPelicula(String codigoPelicula) {
        Pelicula peliculaBuscar = null;

        for (Pelicula pelicula : peliculas) {
            if (codigoPelicula.equalsIgnoreCase(pelicula.getCodigo())) {
                peliculaBuscar = pelicula;
            }
        }

        return peliculaBuscar;
    }

    /**
     * Agrega una nueva sala al cine
     * 
     * @param nuevaSala Sala a añadir
     */
    public void agregarSala(Sala nuevaSala) {
        this.salas.add(nuevaSala);
    }

    /**
     * Busca una sala dentro del cine usando su ID
     * 
     * @param idSala ID a buscar
     * @return Sala encontrada, null si no se encontró
     */
    public Sala buscarSala(String idSala) {
        Sala salaBuscar = null;

        for (Sala sala : salas) {
            if (idSala.equalsIgnoreCase(sala.getId())) {
                salaBuscar = sala;
            }
        }

        return salaBuscar;
    }

    /**
     * Muestra todas las salas del cine
     * 
     * @return String con todas las salas del cine
     */
    public String mostrarSalas() {
        String mensajeSalas = "[ SALAS ]";

        for (Sala sala : salas) {
            mensajeSalas += "\n" + sala;
        }

        return mensajeSalas;
    }

    // region [ Getters y Setters ]
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo() {
        this.codigo = "C-" + cantidadCines;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (!nombre.isBlank() && nombre != null) {
            this.nombre = nombre;
        } else {
            this.nombre = null;
        }
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        if (!ciudad.isBlank() && ciudad != null) {
            this.ciudad = ciudad;
        } else {
            this.ciudad = null;
        }
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        if (!pais.isBlank() && pais != null) {
            this.pais = pais;
        } else {
            this.pais = null;
        }
    }

    public ArrayList<Sala> getSalas() {
        return salas;
    }

    public void setSalas(ArrayList<Sala> salas) {
        this.salas = salas;
    }

    public ArrayList<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(ArrayList<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }
    // endregion

    // [ toString ]
    @Override
    public String toString() {
        String cineMostrar = "[ " + nombre + ", " + codigo + " ]\n" + pais + ", " + ciudad + ", " + salas.size()
                + " salas.";

        return cineMostrar;
    }

}