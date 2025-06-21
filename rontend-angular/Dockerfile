# Stage 1: Build the Angular app
FROM node:20 as build

WORKDIR /app
COPY . .
RUN npm install -g @angular/cli && npm install && ng build --configuration production

# Stage 2: Serve it with Nginx
FROM nginx:alpine
COPY --from=build /app/dist/<your-project-name> /usr/share/nginx/html
EXPOSE 80
