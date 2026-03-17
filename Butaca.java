public class Butaca {
    private String id;
    private boolean ocupada;
    private Cliente cliente; // null si esta libre

    public Butaca(String id) {
        this.id = id;
        this.ocupada = false;
        this.cliente = null;
    }

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

    @Override
    public String toString() {
        return id;
    }

}
