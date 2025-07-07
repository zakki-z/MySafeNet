FROM cirrusci/flutter:latest

# Switch to root to handle permissions
USER root

# Create necessary directories and set up permissions
RUN mkdir -p /home/flutter/.pub-cache && \
    mkdir -p /home/flutter/.config && \
    groupadd -r flutter && \
    useradd -r -g flutter flutter && \
    chown -R flutter:flutter /home/flutter && \
    git config --global --add safe.directory /sdks/flutter && \
    chown -R flutter:flutter /sdks/flutter

WORKDIR /app

# Copy the project files
COPY --chown=flutter:flutter . .

# Switch to flutter user and set environment
USER flutter
ENV HOME=/home/flutter
ENV PUB_CACHE=/home/flutter/.pub-cache

# Disable analytics and get dependencies
RUN flutter config --no-analytics && \
    chmod -R 777 . && \
    flutter pub get && \
    flutter build web

# Use nginx to serve the web build
FROM nginx:alpine
COPY --from=0 /app/build/web /usr/share/nginx/html