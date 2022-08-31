# Change Data Capture 
## (CDC)


---

## The worst bank ever
### v0.0.1 M1

![title](assets/img/money_transfer_process.png)

Note:
* TBD
* TBD
* TBD

---

## Database model

![title](assets/img/db-schema.png)

---

![title](assets/img/show-me-the-code.jpg)

---

## What we know so far

- dual writes is a problem
- Kafka does not support XA transactions
- `ChainedTransactionManager` is deprecated
- `TransactionalEventListener` is not a good option too...

Note:
*  two-phase commit protocol (2PC)
*

---

## Ok, so what now?
- avoid transactions? (CQRS, Saga, eventual consitency everywhere...)
- Change Data Capture

---

# Change Data Capture 

---

## Change Data Capture
### 101

---

## CDC Tools

- Debezium (Red Hat)
- Databus (LinkedIn)
- DBLog (Netflix)
- IBM Infosphere 
- Oracle GoldenGate
- Talend CDC
- DynamoDB*
- Bottled Water(unmaintained)


---

## MySQL
### binlog

> The binary log contains “events” that describe database changes such as table creation operations or changes to table data...

[The Binary Log documentation](https://dev.mysql.com/doc/refman/8.0/en/binary-log.html)

---


## PostgreSQL
### Write-Ahead Logging (WAL) & logical decoding feature 

> WAL's central concept is that changes to data files (where tables and indexes reside) must be written only after those changes have been logged, that is, after log records describing the changes have been flushed to permanent storage.

[Write-Ahead Logging (WAL) documentation](https://www.postgresql.org/docs/current/wal-intro.html)

> Logical decoding is the process of extracting all persistent changes to a database's tables into a coherent, easy to understand format  ... In PostgreSQL, logical decoding is implemented by decoding the contents of the write-ahead log...

[Logical Decoding Concepts documentation](https://www.postgresql.org/docs/current/logicaldecoding-explanation.html)

---

## MongoDB 
### opLog & Change Streams

---

## What is common?

---

### "Change Streams"
- solutions for data replication and streaming of changes
- PostgreSQL -> logical decoding (logical replication)
- MongoDB -> Change Streams

---


## Debezium


---

## Debezium demo


---

## Debezium(CDC) - problems

---

## 