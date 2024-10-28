# Gestor de Reservas Deportivas

Este proyecto es una aplicación en Java para gestionar reservas de pistas deportivas. Los usuarios pueden reservar pistas para diferentes tipos de actividades (reservas infantiles, familiares y para adultos), y el sistema permite gestionar usuarios, materiales y bonos de reserva. El programa implementa patrones de diseño como el patrón **Factoría** para la creación de reservas.

## Tabla de Contenidos
- [Características](#características)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Requisitos Previos](#requisitos-previos)
- [Instalación](#instalación)
- [Uso](#uso)
- [Ejemplos de Código](#ejemplos-de-código)
- [Contribuciones](#contribuciones)
- [Licencia](#licencia)

## Características

- **Gestión de Usuarios**: permite registrar y buscar usuarios para realizar reservas.
- **Gestión de Pistas y Material**: permite añadir, modificar y consultar la disponibilidad de las pistas y los materiales asociados.
- **Gestión de Reservas**: permite realizar reservas en diferentes modalidades (infantil, familiar y adultos) y aplicar descuentos según antigüedad.
- **Patrón de Diseño Factoría**: utilizado para la creación de los diferentes tipos de reservas.
  
## Estructura del Proyecto

- **`Jugador`**: Clase que representa a un usuario en el sistema, contiene atributos como nombre, correo electrónico y fecha de registro.
- **`Material`**: Clase que representa el material disponible para los usuarios (pelotas, aros, etc.).
- **`Pista`**: Clase que representa una pista deportiva. Cada pista tiene un nombre, tipo y capacidad máxima de jugadores.
- **`Reserva`**: Clase abstracta base para las reservas. Define los atributos generales, como usuario, fecha, duración y precio.
- **`ReservaAdultos`, `ReservaInfantil`, `ReservaFamiliar`**: Subclases de `Reserva` que representan los diferentes tipos de reservas, especializadas en función de los participantes.
- **`ReservaFactory`**: Clase que implementa el patrón Factoría para la creación de los diferentes tipos de reservas.
- **`GestorUsuarios`**: Clase encargada de gestionar el registro, búsqueda y almacenamiento de usuarios.
- **`GestorPistas`**: Clase encargada de gestionar la disponibilidad de pistas y la asignación de materiales.
- **`GestorReservas`**: Clase encargada de gestionar el proceso de reserva, desde la creación hasta la modificación y cancelación de reservas.
- **`Main`**: Clase principal donde se inicializan los gestores y se permite la interacción con el sistema.

## Requisitos Previos

Para ejecutar este proyecto, necesitas tener instalado:
- **Java 8** o superior
- **Maven** (opcional, si deseas compilar el proyecto con Maven)

## Instalación

1. Clona este repositorio:
    ```bash
    git clone https://github.com/usuario/gestor-de-reservas.git
    cd gestor-de-reservas
    ```
2. Compila el proyecto:
    ```bash
    javac -d bin src/**/*.java
    ```
3. Ejecuta el programa:
    ```bash
    java -cp bin Main
    ```

## Uso

Al iniciar el programa desde `Main`, se pueden realizar las siguientes acciones:
1. **Registrar usuarios**: Añade un nuevo usuario a la lista de usuarios registrados.
2. **Consultar disponibilidad de pistas**: Verifica la disponibilidad de una pista en función de su nombre y tipo.
3. **Crear reservas**: Crea una reserva para un usuario, seleccionando el tipo de reserva, pista, fecha y duración.
4. **Modificar o cancelar reservas**: Permite cambiar la fecha de una reserva o cancelarla.
5. **Consultar materiales disponibles**: Muestra los materiales disponibles para las diferentes pistas.

### Ejemplos de Código

Ejemplo de cómo hacer una reserva individual:
```java
GestorUsuarios gestorUsuarios = new GestorUsuarios();
GestorPistas gestorPistas = new GestorPistas();
GestorReservas gestorReservas = new GestorReservas(gestorUsuarios, gestorPistas);

// Crear un usuario
Jugador usuario = new Jugador("nombre@example.com", "Nombre Usuario");
gestorUsuarios.registrarUsuario(usuario);

// Crear una pista
Pista pista = new Pista("Pista 1", TamanoPista.TRES_VS_TRES, 6);
gestorPistas.agregarPista(pista);

// Hacer una reserva
LocalDateTime fechaHora = LocalDateTime.now().plusDays(2);
gestorReservas.hacerReservaIndividual("nombre@example.com", fechaHora, 60, "Pista 1", 4, TipoReserva.FAMILIAR);
