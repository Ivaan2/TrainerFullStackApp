# Usamos una imagen base de Java
FROM eclipse-temurin:17-jdk-alpine

# Establecemos el directorio de trabajo
WORKDIR /app

# Copiamos el archivo JAR al contenedor
COPY target/backend-0.0.1-SNAPSHOT.jar /app/backend-0.0.1-app.jar

# Exponemos el puerto en el que corre la aplicación
EXPOSE 8092

# Ejecutamos la aplicación
ENTRYPOINT ["java", "-jar", "/app/backend-0.0.1-app.jar"]
