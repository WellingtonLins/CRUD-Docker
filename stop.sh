docker stop app
docker kill app
docker rm app
docker rmi -f ricardojob/pos-aula:3

docker stop banco
docker kill banco
docker rm banco
docker rmi -f ricardojob/banco

mvn clean