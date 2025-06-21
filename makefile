# Check if docker-compose is available, otherwise use docker compose
DOCKER_COMPOSE := $(shell command -v docker-compose 2> /dev/null || echo "docker compose")
COMPOSE_FILE = dockercomposer.yml
ENV_FILE = .env

# Default target
.PHONY: help
help:
	@echo "Available commands:"
	@echo "  make start              Start all containers"
	@echo "  make stop               Stop all containers"
	@echo "  make restart            Restart all containers"
	@echo "  make build              Build all containers"
	@echo "  make logs               Show logs from all containers"
	@echo "  make logs-mercure       Show logs from Mercure container"
	@echo "  make ps                 Show running containers"
	@echo "  make clean              Remove all containers, networks, and volumes"
	@echo "  make generate-keys      Generate secure keys for Mercure"
	@echo "  make setup-mercure      Create .env file with generated Mercure keys"

# Start all containers
.PHONY: start
start:
	$(DOCKER_COMPOSE) -f $(COMPOSE_FILE) up -d

# Stop all containers
.PHONY: stop
stop:
	$(DOCKER_COMPOSE) -f $(COMPOSE_FILE) down

# Restart all containers
.PHONY: restart
restart: stop start

# Build all containers
.PHONY: build
build:
	$(DOCKER_COMPOSE) -f $(COMPOSE_FILE) build

# Show logs
.PHONY: logs
logs:
	$(DOCKER_COMPOSE) -f $(COMPOSE_FILE) logs -f

# Show Mercure logs specifically
.PHONY: logs-mercure
logs-mercure:
	$(DOCKER_COMPOSE) -f $(COMPOSE_FILE) logs -f mercure

# Show running containers
.PHONY: ps
ps:
	$(DOCKER_COMPOSE) -f $(COMPOSE_FILE) ps

# Remove all containers, networks, and volumes
.PHONY: clean
clean:
	$(DOCKER_COMPOSE) -f $(COMPOSE_FILE) down -v --remove-orphans

# Generate secure keys for Mercure
.PHONY: generate-keys
generate-keys:
	@echo "Generating secure keys for Mercure..."
	@echo "MERCURE_PUBLISHER_JWT_KEY=$$(openssl rand -hex 32)" > $(ENV_FILE)
	@echo "MERCURE_SUBSCRIBER_JWT_KEY=$$(openssl rand -hex 32)" >> $(ENV_FILE)
	@echo "Keys generated and saved to $(ENV_FILE)"

# Setup Mercure with generated keys
.PHONY: setup-mercure
setup-mercure: generate-keys
	@echo "MERCURE_EXTRA_DIRECTIVES=cors_origins *" >> $(ENV_FILE)
	@echo "SERVER_NAME=:80" >> $(ENV_FILE)
	@echo "Mercure configuration saved to $(ENV_FILE)"
	@echo "You can now update your docker-compose.yml to use these environment variables"