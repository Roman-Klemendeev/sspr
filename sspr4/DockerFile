FROM openjdk:11-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем исходники сервера и клиента
COPY src/Server.java /app/Server.java
COPY src/Client.java /app/Client.java

# Компилируем сервер и клиент
RUN javac /app/Server.java
RUN javac /app/Client.java

# Запускаем сервер и клиент одновременно
CMD ["sh", "-c", "java -cp /app Server & java -cp /app Client"]