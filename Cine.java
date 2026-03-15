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

    // [ Constructores ]
    public Cine(String nombre, String ciudad, String pais) {
        setCodigo();
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pais = pais;
        this.salas = new ArrayList<>();
        this.peliculas = new ArrayList<>();

        cantidadCines++;
    }

    // [ Metodos ]
    /**
     * Muestra los datos del cine con los datos de sus salas y sus proyecciones
     * 
     * @return
     */
    public String mostrarCineCompleto() {
        String cineMostrar = "[ " + nombre + " " + codigo + " ]\n" + pais + ", " + ciudad + "\n[ Salas ]";

        for (Sala sala : salas) {
            cineMostrar += "\n" + sala;
        }

        return cineMostrar;
    }

    /**
     * Muestra las peliculas del cine
     * 
     * @return
     */
    public String mostarPeliculas() {
        String peliculasMostrar = "[ Peliculas Disponibles ]";

        for (Pelicula pelicula : peliculas) {
            peliculasMostrar += "\n" + pelicula.getCodigo() + " | " + pelicula.getTitulo();
        }

        return peliculasMostrar;
    }

    /**
     * Agrega una nueva sala al cine
     * 
     * @param nuevaSala Sala
     */
    public void agregarPelicula(Pelicula nuevaPelicula) {
        this.peliculas.add(nuevaPelicula);
    }

    /**
     * Busca una pelicula dentro de la sala usando su codigo
     * 
     * @param codigoProyeccion
     * @return
     */
    public Pelicula buscarPelicula(String codigoPelicula) {
        Pelicula peliculaBuscar = null;

        for (Pelicula pelicula : peliculas) {
            if (codigoPelicula.equals(pelicula.getCodigo())) {
                peliculaBuscar = pelicula;
            }
        }

        return peliculaBuscar;
    }

    /**
     * Agrega una nueva sala al cine
     * 
     * @param nuevaSala Sala
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
            if (idSala.equals(sala.getId())) {
                salaBuscar = sala;
            }
        }

        return salaBuscar;
    }

    /**
     * Muestra todas las salas del cine
     * 
     * @return String
     */
    public String mostrarSalas() {
        String mensajeSalas = "";

        for (Sala sala : salas) {
            mensajeSalas += sala;
        }

        return mensajeSalas;
    }

    // [ Getters y Setters ]

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
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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

    // [ toString ]
    @Override
    public String toString() {
        String cineMostrar = "[ " + nombre + ", " + codigo + " ]\n" + pais + ", " + ciudad + ", " + salas.size()
                + " salas.";

        return cineMostrar;
    }
}