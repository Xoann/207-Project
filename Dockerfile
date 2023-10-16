# 使用官方的 Java 运行时作为父镜像
FROM openjdk:17-jdk-slim

# 工作目录设置
WORKDIR /app

# JAR 文件的路径。由于我们使用的是 Spring Boot Maven 插件，构建的 JAR 文件通常位于 target/ 目录中
ARG JAR_FILE=target/GPTChatHelper-0.0.1-SNAPSHOT.jar

# 将 JAR 文件复制到容器的 /app 目录
COPY ${JAR_FILE} app.jar

# 暴露端口，假设 Spring Boot 应用默认运行在 8080 端口
EXPOSE 8080

# 设置容器的默认命令
ENTRYPOINT ["java", "-jar", "app.jar"]
