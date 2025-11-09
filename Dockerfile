# ----------------------------------------------------------------------------------
# STAGE 1: BUILD (Compilación del código fuente en un contenedor con Maven y JDK 21)
# ----------------------------------------------------------------------------------
FROM maven:3.9-eclipse-temurin-21 AS build
# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app
# Copia los archivos de configuración de Maven (pom.xml) y las dependencias
COPY pom.xml .
# Intenta descargar dependencias primero para aprovechar el cache de Docker
RUN mvn dependency:go-offline -B
# Copia el resto del código fuente
COPY . .
# Ejecuta la compilación de la aplicación, creando el JAR final
RUN mvn clean package -DskipTests

# ----------------------------------------------------------------------------------
# STAGE 2: RUN (Creación de la imagen ligera de ejecución con JRE 21)
# ----------------------------------------------------------------------------------
# Usa una imagen base más ligera (JRE) para la ejecución final
FROM eclipse-temurin:21-jre-focal

# Puerto de la aplicación (cambia de 8081 a 8080 si esa es tu configuración por defecto)
EXPOSE 8080

# Copia el archivo JAR compilado desde la etapa 'build' al contenedor de ejecución
COPY --from=build /app/target/*.jar app.jar

# Comando de entrada simple
ENTRYPOINT ["java", "-jar", "/app.jar"]