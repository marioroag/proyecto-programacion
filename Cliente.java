public class Cliente {
    // [ Atributos ]
    private String nombre;
    private String dni;
    private int edad;
    private String tipoEntrada;
    private int numEntrada;

    // [ Constructor ]
    public Cliente(String nombre, String dni, int edad, String tipoEntrada, int numEntrada) {
        this.nombre = nombre;
        this.dni = dni;
        this.edad = edad;
        this.tipoEntrada = tipoEntrada;
        this.numEntrada = numEntrada;
    }

    // [ Getters y Setters ]
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTipoEntrada() {
        return tipoEntrada;
    }

    public void setTipoEntrada(String tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }

    public int getNumEntrada() {
        return numEntrada;
    }

    public void setNumEntrada(int numEntrada) {
        this.numEntrada = numEntrada;
    }

    // [ toString ]
    @Override
    public String toString() {
        return "Cliente [nombre=" + nombre + ", dni=" + dni + ", edad=" + edad + ", tipoEntrada=" + tipoEntrada
                + ", numEntrada=" + numEntrada + "]";
    }

}
