public class Cliente {
    // [ Atributos ]
    private String nombre;
    private String dni;
    private int edad;
    private String tipoEntrada;
    private int numEntrada;

    // [ Constructor ]
    public Cliente(String nombre, String dni, int edad, String tipoEntrada, int numEntrada) {
        setNombre(nombre);
        setDni(dni);
        setEdad(edad);
        this.tipoEntrada = tipoEntrada;
        // Todavia no se si generare esto aleatoriamente o viene dado
        this.numEntrada = numEntrada;
    }

    // [ Getters y Setters ]
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (!nombre.isBlank() && nombre != null) {
            this.nombre = nombre;
        } else {
            this.nombre = "invalid";
        }
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if (!dni.isBlank() && dni != null) {
            this.dni = dni;
        } else {
            this.dni = "invalid";
        }
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad >= 0) {
            this.edad = edad;
        } else {
            this.edad = -1;
        }
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
