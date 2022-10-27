- Setup Cassandra for development
  ```
  docker pull cassandra
  docker run --name my-cassandra -p 9042:9042 cassandra
  ```
  In another terminal:
  ```
  docker exec -it my-cassandra sh
  cqlsh
  create keyspace democloud with replication={'class':'SimpleStrategy', 'replication_factor':1} and durable_writes=true;
  ```
