# VOTACAO-API

Api feita com spring boot e java 11.

- Disponível em: https://earg-votacao-api.herokuapp.com
- Swagger: https://earg-votacao-api.herokuapp.com/swagger-ui.html
- Github: https://github.com/emersongama/votacao-api

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