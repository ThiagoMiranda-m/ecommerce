<div align="center">

<h1 align="center">🛒 E-commerce API</h1>

<p align="center">
  <strong>O back-end completo para uma plataforma de E-commerce, construído com Java e Spring Boot.</strong>
</p>

<p align="center">
  <a href="#-sobre-o-projeto">Sobre</a> •
  <a href="#-features">Features</a> •
  <a href="#-tecnologias">Tecnologias</a> •
  <a href="#-pré-requisitos">Pré-requisitos</a> •
  <a href="#-como-executar">Execução</a> •
  <a href="#-documentação-da-api">API Endpoints</a> •
  <a href="#-modelagem-de-dados">Modelagem</a>
</p>

<p align="center">
    <img src="https://img.shields.io/badge/Java-21-blue?logo=java&logoColor=white" alt="Java 21">
    <img src="https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?logo=spring-boot&logoColor=white" alt="Spring Boot 3.x">
    <img src="https://img.shields.io/badge/Spring_Security-6.x-blue?logo=spring-security&logoColor=white" alt="Spring Security 6.x">
    <img src="https://img.shields.io/badge/JPA_/_Hibernate-Data-orange?logo=hibernate&logoColor=white" alt="JPA / Hibernate">
    <img src="https://img.shields.io/badge/JWT-Authentication-purple?logo=json-web-tokens&logoColor=white" alt="JWT">
    <img src="https://img.shields.io/badge/MySQL-Database-blue?logo=mysql&logoColor=white" alt="MySQL">
</p>

</div>

---

## 🎯 Sobre o Projeto

A **E-commerce API** é a espinha dorsal de uma loja virtual moderna. Este projeto implementa toda a lógica de negócio necessária para gerenciar produtos, clientes, e o fluxo de autenticação. A arquitetura foi pensada para ser segura, escalável e capaz de traduzir requisitos complexos do mundo real em código funcional e de alta qualidade.

O objetivo principal foi criar um sistema que não apenas funcionasse, mas que fosse construído sobre uma base sólida de boas práticas de desenvolvimento, segurança e modelagem de dados relacional.

---

## ✨ Features

-   🔐 **Sistema de Autenticação Completo**:
    -   Registro de clientes e administradores com perfis distintos.
    -   Autenticação segura via **JWT (JSON Web Token)**, garantindo que apenas usuários logados possam acessar rotas protegidas.
    -   Senhas criptografadas com Bcrypt para armazenamento seguro.

-   📦 **Gerenciamento de Produtos (Catálogo)**:
    -   CRUD completo para produtos, acessível apenas para administradores (`ADMIN`).
    -   Endpoints públicos para listagem e busca de produtos, permitindo que qualquer visitante visualize o catálogo.

-   🛡️ **Controle de Acesso Baseado em Papéis (Roles)**:
    -   **Clientes (`USER`)**: Podem gerenciar seus dados e, futuramente, carrinhos e pedidos.
    -   **Administradores (`ADMIN`)**: Têm acesso total ao gerenciamento de produtos.

-   ✔️ **Validação de Dados**: Utiliza o `jakarta.validation` para garantir que os dados recebidos pela API (ex: no registro de um usuário) sejam válidos e completos.

---

## 🛠️ Tecnologias

O projeto foi construído utilizando as seguintes tecnologias:

| Tecnologia         | Descrição                                         |
| ------------------ | --------------------------------------------------- |
| **Java 21** | Versão LTS do Java, garantindo robustez e performance.                  |
| **Spring Boot** | Framework principal para agilizar o desenvolvimento.      |
| **Spring Security**| Implementação da autenticação e autorização por roles.      |
| **Spring Data JPA**| Camada de persistência para abstrair o acesso ao banco.                    |
| **Hibernate** | Implementação ORM para mapear objetos Java a tabelas.               |
| **MySQL** | Banco de dados relacional escolhido pela sua popularidade e confiabilidade.               |
| **Maven** | Gerenciamento de dependências e build do projeto.   |
| **JWT** | Geração e validação de tokens para stateless authentication.          |
| **Lombok** | Redução de código boilerplate em models e DTOs.     |

---

## 📋 Pré-requisitos

Antes de começar, você precisará ter as seguintes ferramentas instaladas em sua máquina:
-   [**JDK 21**](https://www.oracle.com/java/technologies/downloads/#java21) ou superior.
-   [**Apache Maven**](https://maven.apache.org/download.cgi) 3.5 ou superior.
-   [**MySQL Server**](https://dev.mysql.com/downloads/mysql/) instalado e em execução.
-   Uma IDE de sua preferência (ex: **IntelliJ IDEA**, **VS Code**).
-   Um cliente de API, como o **[Postman](https://www.postman.com/downloads/)** ou **[DBeaver](https://dbeaver.io/)** para testar os endpoints e visualizar o banco.

---

## 🚀 Como Executar

Siga os passos abaixo para executar o projeto localmente.

1.  **Clone o repositório:**
    ```bash
    git clone <url-do-seu-repositorio-ecommerce>
    cd ecommerce
    ```

2.  **Configuração do Banco de Dados:**
    -   Abra o arquivo `src/main/resources/application.properties`.
    -   Altere as propriedades `spring.datasource.username` e `spring.datasource.password` com suas credenciais do MySQL.
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db?createDatabaseIfNotExist=true
    spring.datasource.username=seu_usuario_mysql
    spring.datasource.password=sua_senha_mysql
    ```
    - A propriedade `createDatabaseIfNotExist=true` irá criar o banco de dados `ecommerce_db` para você na primeira execução, caso ele não exista.

3.  **Execute a aplicação:**
    ```bash
    ./mvnw spring-boot:run
    ```

A API estará disponível em `http://localhost:8080`.

---

## 📖 Documentação da API

A seguir estão detalhados os endpoints disponíveis na API.

**URL Base**: `http://localhost:8080`

<details>
<summary><strong>🔑 Autenticação de Usuários</strong></summary>

#### 1. Registrar um Novo Usuário
-   **Método**: `POST`
-   **Endpoint**: `/auth/register`
-   **Descrição**: Cria um novo usuário (`USER` ou `ADMIN`).
-   **Corpo da Requisição (`JSON`):**
    ```json
    {
      "name": "Nome do Usuário",
      "email": "usuario@email.com",
      "password": "senha_forte",
      "role": "USER"
    }
    ```
-   **Resposta de Sucesso (200 OK):** Retorna o token JWT para o usuário recém-criado.
    ```json
    {
      "token": "eyJhbGciOiJIUzI1NiJ9..."
    }
    ```

#### 2. Autenticar um Usuário (Login)
-   **Método**: `POST`
-   **Endpoint**: `/auth/login`
-   **Descrição**: Autentica um usuário e retorna um token JWT.
-   **Corpo da Requisição (`JSON`):**
    ```json
    {
      "email": "usuario@email.com",
      "password": "senha_forte"
    }
    ```
-   **Resposta de Sucesso (200 OK):**
    ```json
    {
      "token": "eyJhbGciOiJIUzI1NiJ9..."
    }
    ```
</details>

<details>
<summary><strong>📦 Gerenciamento de Produtos</strong></summary>

#### 3. Listar Todos os Produtos
-   **Método**: `GET`
-   **Endpoint**: `/products`
-   **Acesso**: Público.
-   **Resposta de Sucesso (200 OK):** Retorna uma lista com todos os produtos.

#### 4. Buscar um Produto por ID
-   **Método**: `GET`
-   **Endpoint**: `/products/{id}`
-   **Acesso**: Público.
-   **Resposta de Sucesso (200 OK):** Retorna o produto correspondente ao `id`.

#### 5. Criar um Novo Produto
-   **Método**: `POST`
-   **Endpoint**: `/products`
-   **Acesso**: Requer autenticação de `ADMIN`.
-   **Header**: `Authorization: Bearer <seu_token_jwt>`
-   **Corpo da Requisição (`JSON`):**
    ```json
    {
        "name": "Notebook Gamer",
        "description": "Notebook com placa de vídeo dedicada.",
        "price": 8999.90,
        "stock": 25
    }
    ```

#### 6. Atualizar um Produto
-   **Método**: `PUT`
-   **Endpoint**: `/products/{id}`
-   **Acesso**: Requer autenticação de `ADMIN`.
-   **Header**: `Authorization: Bearer <seu_token_jwt>`
-   **Corpo da Requisição (`JSON`):** Mesmo formato da criação.

#### 7. Deletar um Produto
-   **Método**: `DELETE`
-   **Endpoint**: `/products/{id}`
-   **Acesso**: Requer autenticação de `ADMIN`.
-   **Header**: `Authorization: Bearer <seu_token_jwt>`
-   **Resposta de Sucesso (204 No Content):** Corpo da resposta vazio.

</details>

---

## 🗂️ Modelagem de Dados

O Hibernate irá gerar o seguinte esquema de banco de dados com base nas entidades:

**Tabela `users`**
| Coluna | Tipo | Restrições |
|---|---|---|
| `id` | `BIGINT` | Chave Primária, Auto-incremento |
| `name` | `VARCHAR(255)` | |
| `email` | `VARCHAR(255)` | Único, Não Nulo |
| `password` | `VARCHAR(255)` | Não Nulo |
| `role` | `VARCHAR(255)` | `USER` ou `ADMIN` |

**Tabela `products`**
| Coluna | Tipo | Restrições |
|---|---|---|
| `id` | `BIGINT` | Chave Primária, Auto-incremento |
| `name` | `VARCHAR(255)` | Não Nulo |
| `description` | `VARCHAR(255)` | |
| `price` | `DECIMAL(19,2)` | Não Nulo |
| `stock` | `INTEGER` | Não Nulo |

---

## 🏗️ Estrutura do Projeto

O projeto segue uma arquitetura em camadas para garantir a separação de responsabilidades e a manutenibilidade do código:

* **`com.example.ecommerce`**
    * `└── config`
        * `├── JwtAuthenticationFilter.java` (Intercepta requisições para validar o token JWT)
        * `└── SecurityConfig.java` (Define as regras de segurança da API)
    * `└── controller`
        * `├── AuthController.java` (Endpoints para registro e login)
        * `└── ProductController.java` (Endpoints para o CRUD de produtos)
    * `└── dto`
        * `├── AuthRequest.java` (Dados para a requisição de login)
        * `├── AuthResponse.java` (Resposta com o token JWT)
        * `└── RegisterRequest.java` (Dados para a requisição de registro)
    * `└── model`
        * `├── Product.java` (Entidade que representa a tabela `products`)
        * `├── Role.java` (Enum para os papéis de usuário: `USER`, `ADMIN`)
        * `└── User.java` (Entidade que representa a tabela `users`)
    * `└── repository`
        * `├── ProductRepository.java` (Interface para acesso aos dados de produtos)
        * `└── UserRepository.java` (Interface para acesso aos dados de usuários)
    * `└── service`
        * `├── AuthService.java` (Lógica de negócio para autenticação)
        * `├── JwtService.java` (Lógica para criar e validar tokens JWT)
        * `├── ProductService.java` (Lógica de negócio para o CRUD de produtos)
        * `└── UserDetailsServiceImpl.java` (Serviço para o Spring Security carregar os usuários)
