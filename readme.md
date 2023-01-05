# Contexto

Foi desenvolvido uma api rest simulando um drone de entregas.

<br>

## Técnologias usadas

- Java
- Springboot
- Mysql
- Junit

<br>

## Clonando o projeto

Copie e cole em seu terminal:

```
git@github.com:Arthur-Jr/User-List.git && cd User-List/
```

<br>

## Todos os comandos abaixo precisam ser executados na raiz do projeto!

<br>

## Criando JAR

```bash
mvn clean package -DskipTest
```

<br>

## Executando aplicação

### Com Docker:

Portas do docker:

- back: 8080:8080
- mongo: 3306:3306

<br>
Iniciando o app:

```bash
docker compose-up
```

A aplicação vai estar rodando no link http://localhost:8080/

- A aplicação pode demorar a rodar, no windows tem levado 7 min para o container do mysql funcionar, tentando descobrir o motivo ainda.
