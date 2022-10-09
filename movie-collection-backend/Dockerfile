FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

# docker build -t hristomanov/mc-fe:v1 -f Dockerfile .
# docker run -p 8081:8081 -d --name mc-be hristomanov/mc-be:v1
# docker login
# docker push hristomanov/mc-fe:v1
# docker-compose -f docker-compose.yml up
# docker-compose -up -d
# docker rm mc-be
# docker rmi hristomanov/mc-be:v1 -f (-f for force remove when container using him)

# docker exec -it movie-collection-backend-db-1 mysql -uroot -p
# show databases;
# use movie-collection
# show tables;