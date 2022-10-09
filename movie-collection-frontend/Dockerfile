# Choose the Image which has Node installed already
FROM node:alpine

# install simple http server for serving static content
RUN npm install -g http-server

# make the 'app' folder the current working directory
WORKDIR /app

# copy both 'package.json' and 'package-lock.json' (if available)
COPY package*.json ./

# install project dependencies
RUN npm install

# copy project files and folders to the current working directory (i.e. 'app' folder)
COPY . .

# build app for production with minification
RUN npm run build

EXPOSE 8080
CMD [ "http-server", "dist" ]

# docker build -t hristomanov/mc-fe:v1 -f Dockerfile .
# docker run -it -p 8080:8080 -d --name mc-fe hristomanov/mc-fe:v1
# docker login
# docker push hristomanov/mc-fe:v1
# docker rm mc-fe
# docker rmi hristomanov/mc-fe:v1 -f (-f for force remove when container using him)
