###### Como Executar o Projeto
##### Inicializando o Container
##### Dentro do diretório do projeto por exemplo: cd C:\projects\coderestapi
####  Verifique se o arquivo docker-compose.yml está nessa pasta.
####  Execute o comando: docker compose up --build -d
####  Avalie se o container está ok com o comando: docker ps
| CONTAINER   | IMAGE              | STATUS | PORTS       |
| ----------- | ------------------ | ------ | ----------- |
| coderestapi | coderestapi:latest | Up     | 8080:8080   |
| mongodb     | mongo:latest       | Up     | 27017:27017 |
*************************************************************************
#### Teste - ConsultaPedido 
#### url: http://localhost:8080/rest/consultapedidos
```json
curl --location 'http://localhost:8080/rest/consultapedidos'
```
#### Teste - CadastraPedido 
#### url: http://localhost:8080/rest/cadastrapedidos
#### request: 
```json
 {
    "id": 9006,
    "idPartner": 2001,
    "itemList": [],
    "totalValue": 50.00,
    "status": "PENDING",
    "createDate": "2026-09-30",
    "lastUpdateDate": "2026-09-30T21:30:00"
}
```
```json
curl --location 'http://localhost:8080/rest/cadastrapedidos' \
--header 'Content-Type: application/json' \
--data '{
    "id": 9001,
    "idPartner": 2001,
    "itemList": [],
    "totalValue": 300.00,
    "status": "PENDING",
    "createDate": "2026-09-30",
    "lastUpdateDate": "2026-09-30T21:30:00"
}'
```
#### Teste - AtualizaPedido 
#### url: http://localhost:8080/rest/atualizapedidos/9006
#### request:
```json
{
  "id": 8888,
  "customerId": 9999,
  "items": [],
  "amount": 200.00,
  "status": "APPROVED",
  "orderDate": "2026-09-30",
  "createdAt": "2026-09-30T23:00:00"
}
```
```json
curl --location --request PUT 'http://localhost:8080/rest/atualizapedidos/9006?Content-Type=application%2Fjson&Accept=application%2Fjson' \
--header 'Content-Type: application/json' \
--data '{
  "id": 8888,
  "customerId": 9999,
  "items": [],
  "amount": 200.00,
  "status": "APPROVED",
  "orderDate": "2026-09-30",
  "createdAt": "2026-09-30T23:00:00"
}'
```
#### Teste - CancelaPedido 
#### url: http://localhost:8080/rest/cancelapedidos/7777?Accept=application/json
#### request:
```json
{
  "id": 7777,
  "customerId": 2001,
  "amount": 300.00,
  "status": "CANCELLED"
}
```
```json
curl --location --request DELETE 'http://localhost:8080/rest/cancelapedidos/7777?Accept=application%2Fjson' \
--header 'Content-Type: application/json' \
--data '{
  "id": 7777,
  "customerId": 2001,
  "amount": 300.00,
  "status": "CANCELLED"
}'
```
#### Teste - AprovaPedido 
#### url: http://localhost:8080/rest/cadastrapedidos](http://localhost:8080/rest/aprovapedidos/9001
#### request: 
```json
{
  "id": 8888,
  "customerId": 9999,
  "items": [],
  "amount": 200.00,
  "status": "APPROVED",
  "orderDate": "2026-09-30",
  "createdAt": "2026-09-30T23:00:00"
}'
```


