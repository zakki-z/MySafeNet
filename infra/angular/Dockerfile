# Build stage
FROM node:18-alpine AS builder

WORKDIR /app

# Copy package files
COPY package*.json ./
RUN npm ci

# Copy source code
COPY . .

# Build the application
RUN npm run build --prod

# Runtime stage with nginx
FROM nginx:alpine

# Copy built app to nginx
COPY --from=builder /app/dist/admin-dashboard /usr/share/nginx/html/

# Copy custom nginx config
COPY ../../infra/angular/nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]