# Gestor de Pistas Deportivas

Este proyecto implementa una aplicación Java para gestionar instalaciones deportivas, incluyendo usuarios, pistas, materiales y reservas. Se utiliza una base de datos MySQL para el almacenamiento de datos, con acceso a través del driver JDBC.

## Características

-Gestión de jugadores: Alta, modificación, listado y eliminación de jugadores.

-Gestión de pistas: Creación, asociación de materiales, y listado de pistas disponibles/no disponibles.

-Gestión de materiales: Asignación y validación según tipo de pista.

-Gestión de reservas: Realización de reservas individuales y a través de bonos con descuentos automáticos.

-Base de datos MySQL: Esquema relacional que soporta las operaciones de la aplicación.

-Patrones de diseño: Uso de DAO y DTO para separar la lógica de negocio del acceso a datos.

-Configuración modular: Uso de archivos config.properties y sql.properties para facilitar la configuración.


## Requisitos

Software necesario

-Java 8 o superior

-MySQL 8.0 o superior

-IDE compatible con Java (por ejemplo, IntelliJ IDEA, Eclipse)

-Driver JDBC para MySQL (disponible en MySQL Connector/J)

  
## Configuración de la base de datos

1. Crear una base de datos en MySQL siguiendo el esquema proporcionado en database/schema.sql.

2. Configurar el archivo config.properties con las credenciales y parámetros de conexión:
   
-db.url=jdbc:mysql://oraclepr.uco.es:3306/f62esreb

-db.username=[f62esreb]

-db.password=[pw-gm2-24]

3.  Importar los datos iniciales si es necesario (opcional): database/data.sql.


## Instalación

1. Clonar el repositorio:

-git clone https://github.com/f62esreb/PW

-cd Practica2

2. Compilar y empaquetar el proyecto:
mvn clean package

3. Ejecutar el programa desde el archivo JAR generado:
java -jar target/Practica2.jar



## Uso

1. Gestión de usuarios:
Alta de usuarios, modificación y consulta mediante la interfaz de texto.

2. Gestión de pistas y materiales:
Crear pistas, asociar materiales y consultar pistas disponibles.

3. Gestión de reservas:
Realizar reservas individuales o a través de bonos.




