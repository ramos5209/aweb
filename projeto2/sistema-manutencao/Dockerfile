# Etapa 1: Build da aplicação com Maven
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copia pom.xml e baixa dependências
COPY pom.xml .
RUN mvn -B dependency:go-offline

# Copia todo o código do projeto
COPY . .
RUN mvn -B clean package -DskipTests

# Etapa 2: Imagem final para rodar Spring Boot
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copia o JAR gerado no build
COPY --from=build /app/target/*.jar app.jar

# Expõe porta (Render injeta a variável PORT)
EXPOSE 8080

# Inicia a aplicação
ENTRYPOINT ["java","-jar","app.jar"]
