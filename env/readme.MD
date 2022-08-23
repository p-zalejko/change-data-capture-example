# Registering a connector

```
curl -i -X POST \
   -H "Content-Type:application/json" \
   -d \
'{
  "name": "shipments-connector",  
  "config": {
    "connector.class": "io.debezium.connector.postgresql.PostgresConnector", 
    "plugin.name": "pgoutput",
    "database.hostname": "postgres", 
    "database.port": "5432", 
    "database.user": "demo", 
    "database.password": "demo", 
    "database.dbname" : "demo_db", 
    "database.server.name": "postgres", 
    "table.include.list": "public.account" 
  }
}' \
 'http://localhost:8083/connectors'



```