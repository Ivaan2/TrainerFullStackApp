# Usamos una imagen base de Java
FROM eclipse-temurin:17-jre-alpine

# Establecemos el directorio de trabajo
WORKDIR /app

# Copiamos el archivo JAR al contenedor
COPY backend-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto en el que corre la aplicación
EXPOSE 8092

# Ejecutamos la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]