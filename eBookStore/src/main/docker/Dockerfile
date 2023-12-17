# 使用 openjdk 作为基本镜像
FROM openjdk:17

#在容器的根目录中 创建一个叫app的文件夹
RUN mkdir /app

#把容器的工作目录换成app，所以运行docker exec -it 容器名 /bin/bash 进入容器的时候，所在位置是app
WORKDIR /app

#把当前所在路径中的jar文件复制到容器的app的文件夹里面
COPY ./eBookStore-0.0.1-SNAPSHOT.jar /app

# 设置端口
EXPOSE 8080

# 初始化运行容器时，运行程序
ENTRYPOINT ["java","-jar","/app/eBookStore-0.0.1-SNAPSHOT.jar"]
