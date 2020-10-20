# VOTACAO-API

Api feita com spring boot e java 11.

- Disponível em: https://earg-votacao-api.herokuapp.com
- Swagger: https://earg-votacao-api.herokuapp.com/swagger-ui.html
- Github: https://github.com/emersongama/votacao-api
## Requisitos

- Java 11
- Maven 3+

## Executar API com o Docker

- Certifique-se de que sua máquina possui o docker instalado, caso não possua, ele pode ser baixado no seguinte endereço: https://www.docker.com/get-started;
- Utilizando o terminal, navegue para diretório contendo o arquivo docker-compose.yml:

```bash
cd votacao-api\src\main\docker
```

- Execute o compose para montar a stack no docker:

```bash
docker-compose up -d
```
- A api ficará disponível em: http://localhost:8081 e o banco em http://localhost:9090.