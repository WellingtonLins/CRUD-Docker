docker build -t ricardojob/banco ./postgres
docker run -p 5433:5432 -d --name banco ricardojob/banco

mvn clean package
docker build -t ricardojob/pos-aula:3 .
docker run -p 8082:8080 -d --name app --link banco:host-banco ricardojob/pos-aula:3