# Clering the environment

```
docker stop $ (docker ps -a -q)
docker rm $ (docker ps -a -q)
docker volume prune

docker-compose down -v --remove-orphans

```

# Registering a connector

```

curl -i -X POST \
   -H "Content-Type:application/json" \
   -d \
'{
  "name": "demo-pg-connector",  
  "config": {
    "connector.class": "io.debezium.connector.postgresql.PostgresConnector", 
    "plugin.name": "pgoutput",
    "database.hostname": "postgres", 
    "database.port": "5432", 
    "database.user": "demo", 
    "database.password": "demo", 
    "database.dbname" : "demo_db", 
    "database.server.name": "postgres", 
    "table.include.list": "public.account_owner,public.account,public.account_history" 
  }
}' \
 'http://localhost:8083/connectors'


```