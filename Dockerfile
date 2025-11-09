# ----------------------------------------------------------------------------------
# STAGE 1: BUILD (Compilación)
# Utiliza una imagen con Maven y JDK 21 para compilar el código.
# ----------------------------------------------------------------------------------
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copia los archivos de configuración de Maven (pom.xml)
# Esta etapa usa el cache de Docker si el pom.xml no ha cambiado.
COPY pom.xml .

# Descarga las dependencias (el goal "go-offline" asegura que la descarga sea explícita)
# El flag -B (batch mode) ayuda a la automatización sin interacción.
RUN mvn dependency:go-offline -B

# Copia el código fuente restante de la aplicación
COPY . .

# Ejecuta la compilación de la aplicación, creando el JAR ejecutable.
# clean y package son fases de ciclo de vida de Maven, no goals.
RUN mvn clean package -DskipTests

# ----------------------------------------------------------------------------------
# STAGE 2: RUN (Ejecución)
# Utiliza una imagen JRE 21 ligera para reducir el tamaño y el riesgo de seguridad (CVEs).
# ----------------------------------------------------------------------------------
FROM eclipse-temurin:21-jre-focal

# Define el puerto de la aplicación.
# Asumo 8080, pero ajústalo si tu aplicación usa 8081.
EXPOSE 8080

# Copia el archivo JAR compilado desde el Stage 1 al contenedor de ejecución.
# Se asume el nombre estándar de JAR generado por Spring Boot.
COPY --from=build /app/target/*.jar app.jar

# Comando de entrada para ejecutar la aplicación JAR.
# "exec java" es una convención de Docker para un manejo de señales más limpio.
ENTRYPOINT ["exec", "java", "-jar", "/app.jar"]