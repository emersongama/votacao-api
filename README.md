# votacao-api

Api feita com spring boot e java 11.

- Disponível em: https://earg-votacao-api.herokuapp.com
- Swagger: https://earg-votacao-api.herokuapp.com/swagger-ui.html

## Requisitos

- Java 11
- Maven 3+

## Ambiente DEV

- Crie uma database no **banco postgresql** com o nome **votacao** 
- Execute o comando de migration do flyway no terminal, para criar as tabelas e inserir os dados iniciais no banco, lembrando de alterar as credenciais de conexão:

```bash
mvn -Dspring_datasource_url=jdbc:postgresql://localhost:5432/votacao -Dspring_datasource_username=postgres -Dspring_datasource_password=post flyway:migrate
```
- Verifique as credenciais de conexão com o banco no arquivo application-dev.properties, e modifique-as caso necessário
- Execute a API:

```bash
mvn -Dspring.profiles.active=dev spring-boot:run
```