# CRUD-Docker
   
 A aplicação desenvolvida é um de crud javaweb com tomcat e o postgres.   
 Usamos como domínio apenas uma entidade pessoa.   
 É uma simples aplicação para o uso didático,sendo assim não foram abordados   
 conceitos de segurança e validação de dados por exemplo.   
 Focamos apenas em criar um container para aplicação java e um container para o     
 banco de dados da aplicação junto ao docker.    

 
Concebido para resolução de atividade da disciplina de POS   
Curso **Análise e Desenvolvimento de Sistemas**   
IFPB Campus Cajazerias   
Professor da disciplina Ricardo Job   

## Getting Started   

Antes de tudo obtenha o `Docker`    ![alt text](img/Whale.png "Docker")


[Docker Download](https://www.docker.com/community-edition)  
 
## Prerequisitos
* Java instalado
* Maven instalado
* Docker instalado
* IDE de sua preferência  

Mas como estamos usando o Docker para a implantação pode usar
apenas um Editor de texto como o [Sublime](https://www.sublimetext.com/3) ou [Notepad++](https://notepad-plus-plus.org/download/v7.5.1.html ) ...

![alt text](img/java.png "Java") 
![alt text](img/Maven.png "Maven") 
![alt text](img/Elephant.png "Posgtres") 
![alt text](img/sublime.png "Sublime") 
![alt text](img/notepad++.png "Notepad++") 




Se você gosta mais do estilo de deixar a IDE ajudar você a completar o código pode
usar o [Netbeans](https://netbeans.org/downloads/) ou o [Eclipse](http://www.eclipse.org/downloads/).   
![alt text](img/eclipse.png "Ecplise") 
![alt text](img/netbeans.png "Netbeans") 

## Criando o arquivo `Dockerfile` do banco de dados

Dentro do seu projeto crie uma diretório com o nome `postgres`, e dentro crie um
arquivo nomeado `Dockerfile`, juntamente com mais dois arquivos create.sql e insert.sql,falaremos deles
e do seu conteúdo logo mais.   
   
O arquivo Dockerfile teve ter o seguinte conteúdo:     

FROM postgres   
ENV POSTGRES_USER postgres   
ENV POSTGRES_PASSWORD 12345   
ENV POSTGRES_DB pos-cliente   
COPY create.sql /docker-entrypoint-initdb.d/    
COPY insert.sql /docker-entrypoint-initdb.d/   

Como percebemos no arquivo acima, estamos configurando o postgres   
indicando o user ,o password e o nome do banco que será criado para receber os dados   
da aplicação.   
Nas últimas duas linhas estamos informando ao docker, que após ele criar o banco de dados    
ele deve ler o conteúdo dos dois arquivos `create.sql` que vai criar a tabela e `insert.sql`    
que vai inserir no nossso banco pos-cliente.   

Sensacional não?   

## Conteúdo do arquivo create.sql

CREATE TABLE pessoa(    
  id  serial,   
  nome character varying(80) NOT NULL,    
  cpf character varying(14) NOT NULL,    
  PRIMARY KEY (id)    
); 


## Conteúdo do arquivo insert.sql 

INSERT INTO pessoa(nome, cpf) VALUES ('Kiko','123.132.121-31');    
INSERT INTO pessoa(nome, cpf) VALUES ('Chaves','123.132.121-31');    
INSERT INTO pessoa(nome, cpf) VALUES ('Chiquinha', '123.132.121-31');    
INSERT INTO pessoa(nome, cpf) VALUES ('Madruga', '123.132.121-31');    
INSERT INTO pessoa(nome, cpf) VALUES ('Florinda', '123.132.121-31');     


## Criar uma imagem

`docker build -t elefante/banco ./postgres`    
*`-t`: qual a tag que vamos atribuir a essa imagem*  
*`./postgres`: caminho  para o arquivo Dockerfile do postgres que está dentro da pasta postgres*  
*`elefante/banco`: nome da imagem  que atribuimos   
Depois que você executar o comando acima , caso você não tenha a imagem    
do postgres, o docker vai providenciar  para você automaticamente, claro    
isso acontece porque descrevemos assim no Dockerfile.
        
 
    
## Listar as imagens

`docker image ls`   
ou   
 `docker images`

## Executar o container


`docker run -p 5433:5432 -d --name elefante/banco`  
*`-p`: o bind entre a porta do host local com a porta do container*  
*`-d`: o container será executado em background* não obstruindo  o terminal  
*`--name`: o nome do container* 
*`banco` : nome da container 
  

Acima nós configuramos a porta do postgres para 5433    





## Criando o arquivo `Dockerfile`

```
FROM tomcat
COPY target/Aplicacao.war ${CATALINA_HOME}/webapps  
```   
`FROM` :  diz qual a imagem que precisamos   
`COPY` :  diz o caminho de onde copiar os arquivos .war para a implantação   
`${CATALINA_HOME}/webapps` :  lugar  onde vamos armazenar os gloriosos arquivos   

Este arquivo `Dockerfile`, deve obrigatoriamente estar dentro do diretório raiz do seu projeto.


Vale ressaltar que o nome `Aplicacao` foi o finalName que eu demos para a aplicação       
dentro do pom.xml.  
É por esse nome que vamos chamar o sistema no browser.   

```
<build>    
        <finalName>Aplicacao</finalName>    
</build>   
```

E claro dentro da pasta `WEB-INF` temos que ter uma outro diretório chamado `lib`   
que deve conter as bibliotecas `jstl.jar` e `standart.jar`, caso contrario teremos   
problemas ao carreagar o nosso sistema no browser.

## Criar uma imagem

`docker build -t minhaapp .`    
*`-t`: qual a tag que vamos atribuir a essa imagem*  
*`.`: caminho relativo (ou absoluto) para o arquivo Dockerfile*  

Depois que você executar o comando acima , caso você não tenha a imagem    
do tomcat, o docker vai providenciar  para você automaticamente, óbvio,   
isso acontece porque descrevemos essa ação no Dockerfile do projeto em questão.
        
   
FROM  **tomcat**   
COPY target/Aplicacao.war ${CATALINA_HOME}/webapps   
    
## Listar as imagens

`docker image ls`   
ou   
 `docker images`

## Executar o container
`docker run -p 8082:8080 -d --name app --link banco:host-banco minhaapp`
*`-p`: o bind entre a porta do host local com a porta do container*  
*`-d`: o container será executado em background* não obstruindo  o terminal  
*`--name`: o nome do container*  
*`--link`: para o docker vincular o banco do conteiner ao host-banco que referenciado no nosso projeto java no arquivo DbUtil.java*  
  
Agora vá até o browser a abra o seu projeto: [http://localhost:8082/Aplicacao](http://localhost:8081/Aplicacao.war/ )   

Acima nós configuramos a porta do tomcat para 8082 lembra?   
     
No meu caso como ainda estou usando o Docker Toolbox no windows abro a aplicação em [http://192.168.99.100:8082/Aplicacao.war/](http://192.168.99.100:8082/Aplicacao.war/ )

  
## Implantação usando  arquivo .sh

Para agilizar o processo de desenvolvimento vamos criar dois arquivos .sh: 
 
**run.sh**   

O arquivo **run.sh** deve conter o seguinte conteúdo:

-------------------------------------------------------------    
docker build -t elefante/banco ./postgres    
docker run -p 5433:5432 -d --name banco elefante/banco    

mvn clean package    
docker build -t ricardojob/pos-aula .    
docker run -p 8082:8080 -d --name app --link banco:host-banco ricardojob/pos-aula    

-------------------------------------------------------------    
**nonrun.sh**  

O arquivo **nonrun.sh** deve conter o seguinte conteúdo:    
-------------------------------------------------------------    
docker stop app    
docker kill app    
docker rm app    
docker rmi -f ricardojob/pos-aula    

docker stop banco    
docker kill banco    
docker rm banco    
docker rmi -f elefante/banco    

mvn clean    
-------------------------------------------------------------   


Assim uma vez que você já tenha as imagens e os containers criados,  
não é mais necessário digitar todas as vezes os comandos de criar a imagem do banco de dados,      
criar o container desse banco, e depois criar a imagem da aplicação web, criar o   
o container apos cada atualização de seu projeto.   
Simplesmente abra digite no docker: 
  
### Para iniciar:  

**sh run.sh**     

 Vai fazer tudo de uma só vez :    
* O docker vai criar a imagem do banco   
* O dockar vai criar o container desse banco e iniciar o mesmo    
* O maven vai criar o arquivo .war do projeto   
* Vai criar a imagem da aplicação   
* Por último criar e iniciar o container da aplicação  

### Para encerrar digite:  

**sh nonrun.sh**   

  Vai fazer tudo de uma só vez :  
* O docker vai parar o container da Aplicacao    
* O docker vai matar o container    
* Remover o container da aplicação
* Vai remover a imagem da aplicação do Docker  

* O docker vai parar o container do banco elefante    
* O docker vai matar o container   
* Remover o container do banco
* Remover a imagem do banco
 
* O maven vai limpar o projeto   


![alt text](img/sue.png "Java") 

## Listar os containers

`docker container ls`


## Listar os containers de ativos e inativos

`docker ps -a`


## Parar o container

`docker stop <container_id | container_name>`


## Documentação Docker
[Docker referências](https://docs.docker.com/reference/ )

## Construido com 

* [Java](http://www.dropwizard.io/1.0.2/docs/) - Lingugem de programação
* [Postgres](https://www.postgresql.org) - Banco de dados 
* [Maven](https://maven.apache.org/) - Gerenciador de dependencias
* [Tomcat](https://tomcat.apache.org/) - Servidor Web usado para a implantação do projeto
* [Docker](https://www.docker.com) - Gerenciador de containers onde podemos usar o container do Tomcat... 
* [NetBeans](https://netbeans.org/downloads/) - Usado para escrever o codigo fonte do projeto

## Controle de versão

Nós usamos o [Git](https://git-scm.com/) . 

## Autor

* Wellington Lins


## Agradecimentos

* Ao professor Ricardo Job 
* A colega Michelle Oliveira

## Here I can listen you call my name: 

wellingtonlins2013@gmail.com

#### Tell me your problems and doubts...