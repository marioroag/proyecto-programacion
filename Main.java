public class Main {

    public static void main(String[] args) {
        Pelicula pelicula1 = new Pelicula("Avatar", 60, "+12", "Terror");
        Pelicula pelicula2 = new Pelicula("El precio del dinero", 120, "+18", "Accion");
        Pelicula pelicula3 = new Pelicula("Goodfellas", 160, "+16", "Comedia");
        // System.out.println(pelicula1);

        Proyeccion proyeccion1 = new Proyeccion(pelicula1, "14:30", "15:40");
        Proyeccion proyeccion2 = new Proyeccion(pelicula2, "16:30", "18:40");
        Proyeccion proyeccion3 = new Proyeccion(pelicula3, "10:30", "11:40");
        // System.out.println(proyeccion1);

        Sala sala1 = new Sala(100);
        sala1.agregarProyeccion(proyeccion1);
        sala1.agregarProyeccion(proyeccion2);
        sala1.agregarProyeccion(proyeccion3);

        // System.out.println(sala1);

        Cine cineStem = new Cine("Cine STEM", "Granada", "España");
        cineStem.agregarSala(sala1);
        System.out.println(cineStem);

        System.out.println(sala1.estaOcupada());

        System.out.println(cineStem.buscarSala("SAL-1"));

    }
}
