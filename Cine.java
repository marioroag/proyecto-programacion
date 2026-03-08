import java.util.ArrayList;

public class Cine {
    // [ Atributos ]
    private static int cantidadCines = 1;
    private String codigo;
    private String nombre;
    private String ciudad;
    private String pais;
    private ArrayList<Sala> salas;

    // [ Constructores ]
    public Cine(String nombre, String ciudad, String pais) {
        setCodigo();
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pais = pais;
        this.salas = new ArrayList<>();

        cantidadCines++;
    }

    // [ Metodos ]
    public void agregarSala(Sala nuevaSala) {
        this.salas.add(nuevaSala);
    }

    /**
     * Busca una sala dentro del cine usando su id
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

    // [ toString ]
    @Override
    public String toString() {
        String cineMostrar = "[ " + nombre + " " + codigo + " ]\n" + pais + ", " + ciudad + "\n[ Salas ]";

        for (Sala sala : salas) {
            cineMostrar += "\n" + sala;
        }

        return cineMostrar;
    }
}