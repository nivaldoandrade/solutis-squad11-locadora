# Desafio - Locadora

Este é um projeto de backend para uma locadora de automóveis, desenvolvido utilizando Spring Boot e MySQL. O sistema oferece uma API completa para gerenciar o cadastro de clientes, veículos, aluguéis e muito mais. A aplicação permite que os usuários se registrem, escolham veículos para alugar, e efetivem reservas, enquanto fornece uma interface robusta para a administração dos dados de locadora. A API oferece as
seguintes funcionalidades:

1.  **Cadastro de Clientes:** Permite o registro de novos clientes, incluindo validação de e-mail e dados pessoais.

2. **Gestão de Veículos:** Adiciona, lista e detalha veículos disponíveis para aluguel, com suporte a categorias e acessórios.

3. **Aluguel de Veículos:** Seleciona veículos, reserva e confirma aluguéis, e gerencia as reservas dos clientes.

4. **Confirmação de Cadastro:** Envia e valida links de confirmação por e-mail para ativação de contas.

## Pré-requisitos

- **Java 21**: Certifique-se de ter o JDK 21 instalado.
- **MySQL**: O banco de dados MySQL deve estar em execução na porta padrão 3306.

## Configuração do Banco de Dados

O projeto usa um arquivo de configuração para conectar ao banco de dados. O arquivo `application.properties` está localizado na
pasta `resources` e contém as seguintes propriedades:

```text
spring.application.name=Locadora  
  
spring.datasource.url=jdbc:mysql://localhost:3306/locadora_squad11?createDatabaseIfNotExist=true&serverTimezone=America/Sao_Paulo  
spring.datasource.username=${USERNAME_DB:root}  
spring.datasource.password=${PASSWORD_DB:root}  
  
#STORAGE  
storage.temp-dir=temp  
storage.local.upload-dir=uploads  
spring.servlet.multipart.max-file-size=50MB  
spring.servlet.multipart.max-request-size=50MB  
  
# Criar automaticamente as tabela de acordo as entidades  
spring.jpa.hibernate.ddl-auto=create  
spring.jpa.defer-datasource-initialization=true  
spring.sql.init.mode=always
```
Você pode ajustar estas configurações de acordo com o seu ambiente, se necessário. No entanto, é importante manter os nomes
das propriedades e a estrutura do arquivo conforme o exemplo acima, para garantir a correta
conexão com o banco de dados.

## Rodando Localmente com Docker Compose

### 1. Clone o Repositório

Clone o repositório para o seu ambiente local:

```bash
git clone https://github.com/nivaldoandrade/solutis-squad11-locadora
cd solutis-squad11-locadora
```
### 2. Iniciar o Docker Compose

No terminal, navegue até o diretório onde está o arquivo docker-compose.yml e execute o comando:

```bash
docker-compose up
```
Esse comando irá iniciar todos os serviços definidos no seu arquivo docker-compose.yml, incluindo a aplicação e o banco de dados MySQL. A aplicação estará acessível em http://localhost:8080/api/ e o banco de dados MySQL estará disponível na porta padrão 3306.

### 3. Parar o Docker Compose

Para parar os serviços e remover os contêineres, execute:

```bash
docker-compose down
```
Esse comando irá parar todos os contêineres e remover as redes criadas pelo Docker Compose, liberando os recursos do sistema.

## Rodando Localmente sem Docker Compose

### 1. Clone o Repositório

Clone o repositório para o seu ambiente local:

```bash
git clone https://github.com/nivaldoandrade/solutis-squad11-locadora
cd solutis-squad11-locadora
```

### 2. Configurar o Banco de Dados

Você pode usar Docker para iniciar um contêiner MySQL com o seguinte comando:

```bash
docker run --name some-mysql -p3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql
```
Ou, se preferir, certifique-se de que o MySQL está rodando na sua máquina na porta padrão 3306.


### 3. Compilar e Executar o Sistema

1. Compile o projeto:

```bash
mvn clean install
```
2. Execute o sistema: Navegue até o diretório onde o JAR foi gerado e execute:

```bash
java -jar target/locadora-0.0.1-SNAPSHOT.jar
```

Ou, se você estiver usando uma IDE, execute a classe principal LocadoraApplication a partir do diretório src/main/java.

## **Documentação da API**

Para visualizar a documentação interativa da API, você pode usar o Swagger. O Swagger fornece uma interface gráfica onde você pode explorar e testar as endpoints da API.

Após iniciar os serviços, a documentação do Swagger estará disponível em:
```bash
http://localhost:8080/swagger-ui/index.html
```
Navegue até este URL no seu navegador para acessar a interface do Swagger, onde você poderá visualizar e interagir com a documentação da API.