# Cine STEM

Aplicación de consola en Java para gestionar un cine: salas, películas, proyecciones y reservas de butacas. Todo funciona desde un menú interactivo en la terminal, con un mapa de butacas en color.

Proyecto de la asignatura de Programación (2.º trimestre, 1.º DAW) en STEM Granada.

## Funcionalidades

**Salas**
- Listar las salas del cine y consultar si están libres u ocupadas.
- Programar una película en una sala, comprobando que no se solape con otras proyecciones.
- Cancelar proyecciones.

**Películas**
- Listar el catálogo y ver los detalles de cada película (duración, género, clasificación por edad).
- Ver todas las proyecciones de una película.

**Clientes y reservas**
- Registrar clientes, validando el formato del DNI (8 números y una letra) y evitando duplicados.
- Reservar una butaca concreta (por ejemplo `7A`) en una proyección sobre un mapa visual de la sala.
- Cancelar la asistencia y liberar la butaca.
- Ver la lista de asistentes y el total de una proyección.

Las reservas y cancelaciones se bloquean si la proyección ya ha terminado.

Al arrancar se genera un cine con películas, clientes y salas de prueba para poder usarlo directamente.

## Mapa de butacas

Cada sala tiene 10 filas y 20 columnas. Las butacas libres se muestran en verde (`0`) y las ocupadas en rojo (`X`):

```
      A    B    C    D    E    F    G    H  ...
 1   [0]  [0]  [X]  [0]  [0]  [X]  [0]  [0] ...
 2   [0]  [0]  [0]  [0]  [X]  [0]  [0]  [0] ...
 3   [X]  [X]  [0]  [0]  [0]  [0]  [0]  [0] ...
 ...
```

## Estructura

| Clase | Responsabilidad |
|---|---|
| `Main` | Menús, lectura y validación de la entrada del usuario |
| `Cine` | Agrupa salas, películas y clientes, y ofrece las búsquedas |
| `Sala` | Gestiona sus proyecciones y su disponibilidad |
| `Proyeccion` | Película + sala + horario, con la matriz de butacas |
| `Butaca` | Estado (libre/ocupada) y cliente asignado |
| `Pelicula` | Datos de la película |
| `Cliente` | Datos del cliente y validación del DNI |
| `FakerCine` | Generador de datos de prueba |

## Tecnologías

- **Java** (sin dependencias externas)
- Programación orientada a objetos: clases, encapsulación y relaciones entre objetos
- Colecciones (`ArrayList`), fechas y horas con `java.time`
- Códigos ANSI para los colores de la terminal

## Cómo ejecutarlo

Requisitos: **JDK 11 o superior**.

```bash
javac *.java
java Main
```

Para que se vean bien los colores y los bordes, usa una terminal con soporte UTF-8 y ANSI (la terminal de macOS, Linux o Windows Terminal).

Hay un cliente de prueba ya registrado con DNI `12345678N`.

## Autor

**Mario Rodríguez Aguado** · [GitHub](https://github.com/marioroag)
