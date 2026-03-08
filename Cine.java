import java.util.ArrayList;

public class Cine {
    private static int cantidadCines = 1;
    private String codigo; // C-1
    private String nombre;
    private String ciudad;
    private String pais; // ES, FR, US
    private ArrayList<Sala> salas;

    public Cine(String nombre, String ciudad, String pais) {
        setCodigo();
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pais = pais;
        this.salas = new ArrayList<>();

        cantidadCines++;
    }

    public void agregarSala(Sala nuevaSala) {
        this.salas.add(nuevaSala);
    }

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

    @Override
    public String toString() {
        String cineMostrar = "[ " + nombre + " " + codigo + " ]\n" + pais + ", " + ciudad + "\n[ Salas ]\n";

        for (Sala sala : salas) {
            cineMostrar += sala + "\n";
        }

        return cineMostrar;
    }
}