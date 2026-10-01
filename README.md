######### Como Executar o Projeto
###### Teste - CadastraPedido 
# url: http://localhost:8080/rest/cadastrapedidos
# request: 
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

// Teste - Aprovar Pedido 
url: http://localhost:8080/rest/aprovapedidos/9001
curl --location --request PUT 'http://localhost:8080/rest/aprovapedidos/9001'


