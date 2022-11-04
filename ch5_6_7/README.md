
```
docker run -p 3306:3306 --name mysql-server -e MYSQL_ROOT_PASSWORD=abcd1234 mysql
```
```
docker exec -it mysql-server sh
mysql -u root -h localhost -p

```