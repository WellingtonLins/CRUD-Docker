docker stop app
docker kill app
docker rm app
docker rmi -f ricardojob/pos-aula

docker stop banco
docker kill banco
docker rm banco
docker rmi -f ricardojob/banco

mvn clean