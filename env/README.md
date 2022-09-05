# Launch 

```
docker-compose up

```

# Clering the environment

```
docker-compose down -v --remove-orphans
docker volume prune

```

# Links
- kafka UI http://localhost:9001/
- Debezium UI http://localhost:9001/
- pgadmin4 http://localhost:5050/
- schema registry http://localhost:8000/#/ (or use Kafka UI with buit-in schema browser)