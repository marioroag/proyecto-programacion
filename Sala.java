import java.util.ArrayList;

public class Sala {
    private static int cantidadSalas = 1;

    private String id; // 1, 2, 3
    private int capacidad;
    private ArrayList<Proyeccion> proyecciones;

    public Sala(int capacidad, ArrayList<Proyeccion> proyecciones) {
        setId();
        this.capacidad = capacidad;
        this.proyecciones = proyecciones;
        cantidadSalas++;
    }

    public Sala(int capacidad) {
        setId();
        this.capacidad = capacidad;
        this.proyecciones = new ArrayList<>();
        cantidadSalas++;
    }

    public void agregarProyeccion(Proyeccion nuevaProyeccion) {
        this.proyecciones.add(nuevaProyeccion);
    }

    public String getId() {
        return id;
    }

    public void setId() {
        this.id = "SAL-" + cantidadSalas;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public ArrayList<Proyeccion> getProyecciones() {
        return proyecciones;
    }

    public void setProyecciones(ArrayList<Proyeccion> proyecciones) {
        this.proyecciones = proyecciones;
    }

    @Override
    public String toString() {
        String salaMostrar = "Sala: " + id + " | Tiene capacidad de " + capacidad
                + " personas, proyecta:\n";

        for (Proyeccion proyeccion : proyecciones) {
            salaMostrar += " " + proyeccion + "\n";
        }
        return salaMostrar;
    }

}
