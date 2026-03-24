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
        this.numEntrada = numEntrada;
    }

    // [ Metodos ]
    /**
     * Comprueba la longuitud, y contenido de un string DNI para verificar que se ha
     * introducido correctamente
     * 
     * @param dniComprobar
     * @return true si 9 digito, 8 numeros y 1 letra
     */
    public boolean verificarDNI(String dniComprobar) {
        boolean valido = true;

        int longDNI = dniComprobar.length();
        final int LONGUITUDREQUERIDA = 9;

        // Tiene 9 digitos y el último es una letra?
        if (longDNI == LONGUITUDREQUERIDA && Character.isLetter(dniComprobar.charAt(LONGUITUDREQUERIDA - 1))) {
            // Comprueba primeras 8 letras
            for (int i = 0; i < LONGUITUDREQUERIDA - 1 && valido; i++) {
                if (!Character.isDigit(dniComprobar.charAt(i))) {
                    valido = false;
                }
            }
        } else {
            valido = false;
        }

        return valido;
    }

    // region [ Getters y Setters ]
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if (verificarDNI(dni)) {
            this.dni = dni.toUpperCase();
        } else {
            this.dni = "";
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
        if (!tipoEntrada.isBlank() && tipoEntrada != null) {
            this.tipoEntrada = tipoEntrada;
        } else {
            this.tipoEntrada = null;
        }
    }

    public int getNumEntrada() {
        return numEntrada;
    }

    public void setNumEntrada(int numEntrada) {
        if (numEntrada >= 0) {
            this.numEntrada = numEntrada;
        } else {
            this.numEntrada = -1;
        }
    }
    // endregion

    // [ toString ]
    @Override
    public String toString() {
        return "DNI: " + dni + " | " + nombre + " de " + edad + " años | Entrada: " + tipoEntrada
                + " Nº" + numEntrada;
    }

}
