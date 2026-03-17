public class Butaca {
    // [ Atributos ]
    private String id;
    private boolean ocupada;
    private Cliente cliente; // null si esta libre

    // [ Constructor ]
    public Butaca(String id) {
        this.id = id;
        this.ocupada = false;
        this.cliente = null;
    }

    // [ Getters y Setters ]
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // [ toString ]
    @Override
    public String toString() {
        String estado = "libre";
        if (ocupada) {
            estado = "ocupada";
        }
        return "Butaca: " + id + " está " + estado + " por " + cliente.getNombre() + ", dni: " + cliente.getDni();
    }

}
