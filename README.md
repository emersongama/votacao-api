# votacao-api

Foobar is a Python library for dealing with word pluralization.

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