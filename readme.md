# API para jogo da velha

### Tecnologias utilizadas
* Java 8
* Spring Boot 2.1.5
* H2

### Instalação
Antes de qualquer coisa tenha istalado o **Java 8** em sua máquina.

Em seguida baixe ou clone o projeto em um local de sua preferência e utilize o script
automatizado para gerar uma versão .jar ou executar a aplicação em modo de desenvolvimento.

Para executar o script utilize os comandos abaixo:

* Build: **./build.sh generate**
* Executar: **./build.sh execute**

Ao final do build um arquivo .jar será criado dentro da pasta **target**, caso queira
executá-lo utilize o comando:
* java -jar jogoDaVelha-0.0.1-SNAPSHOT.jar

Na primeira execução da aplicação será criado um arquivo chamado **database.mv.db** que servirá
como banco de dados para salvar as partidas e jogadas. Você pode acessá-lo pela URL
http://localhost:8888/h2 utilizando as credenciais especificadas dentro de **src/main/resources/application.properties**.