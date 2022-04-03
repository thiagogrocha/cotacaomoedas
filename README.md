# Projeto que busca a cotação do Dólar por dia na base do Banco Central.

Para executar o sistema localmente siga os seguintes passos:

Acesse o diretório raiz do projeto microservice-a via prompt de comando e execute os seguintes comandos:

1 - ./mvnw clean install package

2 - docker build -f src/main/docker/Dockerfile.jvm -t quarkus/microservice-a-jvm .



Acesse o diretório raiz do projeto microservice-b via prompt de comando  e execute os seguintes comandos:

1 - ./mvnw clean install package

2 - docker build -f src/main/docker/Dockerfile.jvm -t quarkus/microservice-b-jvm .


Acesso o diretório docker  via prompt de comando e execute o comando:

docker-compose up -d

.

Os dois microservices possuem swagger-ui para manipulação dos rescursos, no entando basta acessar o microservice-b pelo endereço http://localhost:8082/swagger-ui/ .
