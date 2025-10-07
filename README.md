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

A **E-commerce API** é a espinha dorsal de uma loja virtual moderna. Este projeto implementa toda a lógica de negócio necessária para gerenciar produtos, clientes, carrinho de compras e o fluxo de pedidos. A arquitetura foi pensada para ser segura, escalável e capaz de traduzir requisitos complexos do mundo real em código funcional e de alta qualidade.

O objetivo foi criar um sistema que não apenas funcionasse, mas que fosse construído sobre uma base sólida de boas práticas de desenvolvimento, segurança e modelagem de dados relacional.

---

## ✨ Features

-   🔐 **Sistema de Autenticação Completo**:
    -   Registro de clientes e administradores com perfis distintos.
    -   Autenticação segura via **JWT (JSON Web Token)**.
    -   Senhas criptografadas com Bcrypt.

-   📦 **Gerenciamento de Produtos (Catálogo)**:
    -   CRUD completo para produtos, acessível apenas para administradores (`ADMIN`).
    -   Endpoints públicos para listagem e busca de produtos.

-   🛒 **Funcionalidade de Carrinho de Compras**:
    -   Adicionar produtos ao carrinho.
    -   Se o produto já existe no carrinho, a quantidade é incrementada.
    -   O carrinho é persistente e vinculado ao usuário logado.

-   🛍️ **Fluxo de Pedidos (Checkout)**:
    -   Criação de um novo pedido a partir dos itens do carrinho.
    -   **Atualização de estoque** em tempo real após a finalização da compra.
    -   O carrinho é esvaziado após o checkout.
    -   Acesso ao histórico de pedidos por usuário.

-   🛡️ **Controle de Acesso Baseado em Papéis (Roles)**:
    -   **Clientes (`USER`)**: Podem gerenciar seus dados, carrinho e pedidos.
    -   **Administradores (`ADMIN`)**: Têm acesso total ao gerenciamento de produtos.

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
-   Um cliente de API, como o **[Postman](https://www.postman.com/downloads/)** ou **[DBeaver](https://dbeaver.io/)**.

---

## 🚀 Como Executar

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
    - A propriedade `createDatabaseIfNotExist=true` irá criar o banco de dados `ecommerce_db` para você na primeira execução.

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

| Método | Endpoint             | Descrição                 | Acesso      |
|--------|----------------------|---------------------------|-------------|
| `POST` | `/auth/register`     | Registra um novo usuário. | Público     |
| `POST` | `/auth/login`        | Autentica um usuário e retorna um token JWT. | Público     |
</details>

<details>
<summary><strong>📦 Gerenciamento de Produtos</strong></summary>

| Método | Endpoint             | Descrição                 | Acesso      |
|--------|----------------------|---------------------------|-------------|
| `GET`  | `/products`          | Lista todos os produtos.  | Público     |
| `GET`  | `/products/{id}`     | Busca um produto por ID.  | Público     |
| `POST` | `/products`          | Cria um novo produto.     | `ADMIN`     |
| `PUT`  | `/products/{id}`     | Atualiza um produto.      | `ADMIN`     |
| `DELETE`| `/products/{id}`    | Deleta um produto.        | `ADMIN`     |
</details>

<details>
<summary><strong>🛒 Carrinho de Compras</strong></summary>

| Método | Endpoint             | Descrição                 | Acesso      |
|--------|----------------------|---------------------------|-------------|
| `POST` | `/cart/add`          | Adiciona um item ao carrinho do usuário logado. | `USER`      |
- **Corpo da Requisição (`/cart/add`):**
    ```json
    {
      "productId": 1,
      "quantity": 2
    }
    ```
- **Resposta de Sucesso:** Retorna o objeto do carrinho (`Cart`) atualizado.
</details>

<details>
<summary><strong>🛍️ Pedidos (Orders)</strong></summary>

| Método | Endpoint             | Descrição                 | Acesso      |
|--------|----------------------|---------------------------|-------------|
| `POST` | `/orders/checkout`   | Cria um pedido a partir do carrinho e o esvazia. | `USER`      |
| `GET`  | `/orders`            | Lista o histórico de pedidos do usuário logado. | `USER`      |
- **Resposta de Sucesso (`/orders/checkout`):** Retorna o objeto do pedido (`Order`) recém-criado.
</details>

---

## 🗂️ Modelagem de Dados

O Hibernate irá gerar o seguinte esquema de banco de dados com base nas entidades:

**Tabela `users`**
| Coluna | Tipo | Restrições |
|---|---|---|
| `id` | `BIGINT` | Chave Primária |
| `email` | `VARCHAR(255)` | Único, Não Nulo |
| `name` | `VARCHAR(255)` | |
| `password` | `VARCHAR(255)` | Não Nulo |
| `role` | `VARCHAR(255)` | `USER` ou `ADMIN` |

**Tabela `products`**
| Coluna | Tipo | Restrições |
|---|---|---|
| `id` | `BIGINT` | Chave Primária |
| `name` | `VARCHAR(255)` | Não Nulo |
| `description` | `VARCHAR(255)` | |
| `price` | `DECIMAL(19,2)` | Não Nulo |
| `stock` | `INTEGER` | Não Nulo |

**Tabela `carts`**
| Coluna | Tipo | Restrições |
|---|---|---|
| `id` | `BIGINT` | Chave Primária |
| `user_id` | `BIGINT` | Chave Estrangeira (users), Único, Não Nulo |

**Tabela `cart_items`**
| Coluna | Tipo | Restrições |
|---|---|---|
| `id` | `BIGINT` | Chave Primária |
| `quantity` | `INTEGER` | |
| `cart_id` | `BIGINT` | Chave Estrangeira (carts) |
| `product_id` | `BIGINT` | Chave Estrangeira (products) |

**Tabela `orders`**
| Coluna | Tipo | Restrições |
|---|---|---|
| `id` | `BIGINT` | Chave Primária |
| `order_date` | `DATETIME(6)` | |
| `total_price` | `DECIMAL(19,2)` | |
| `user_id` | `BIGINT` | Chave Estrangeira (users) |

**Tabela `order_items`**
| Coluna | Tipo | Restrições |
|---|---|---|
| `id` | `BIGINT` | Chave Primária |
| `price` | `DECIMAL(19,2)` | |
| `quantity` | `INTEGER` | |
| `order_id` | `BIGINT` | Chave Estrangeira (orders) |
| `product_id` | `BIGINT` | Chave Estrangeira (products) |

---

## 🏗️ Estrutura do Projeto

O projeto segue uma arquitetura em camadas para garantir a separação de responsabilidades:

* **`com.example.ecommerce`**
    * `└── config`
        * `├── JwtAuthenticationFilter.java` (Intercepta requisições para validar o token JWT)
        * `└── SecurityConfig.java` (Define as regras de segurança da API)
    * `└── controller`
        * `├── AuthController.java` (Endpoints para registro e login)
        * `└── ProductController.java` (Endpoints para o CRUD de produtos)
        * `└── OrderController.java` (Endpoints para os pedidos)
        * `└── CartController.java` (Endpoints para a criação do carrinho
    * `└── dto`
        * `├── AuthRequest.java` (Dados para a requisição de login)
        * `├── AuthResponse.java` (Resposta com o token JWT)
        * `└── RegisterRequest.java` (Dados para a requisição de registro)
        * `└── AddItemRequest.java`(Dados para a requisição de adicionar item)
    * `└── model`
        * `├── Product.java` (Entidade que representa a tabela `products`)
        * `├── Role.java` (Enum para os papéis de usuário: `USER`, `ADMIN`)
        * `└── User.java` (Entidade que representa a tabela `users`)
        * `└── Cart.java` (Entidade que representa a tebela `cart`)
        * `└── Order.java` (Entidade que representa a tebela `order`)
        * `└── OrderItem.java` (Entidade que representa a tebela `orderItem`)
        * `└── CartItem.java` (Entidade que representa a tebela `cartItem`)
    * `└── repository`
        * `├── ProductRepository.java` (Interface para acesso aos dados de produtos)
        * `└── UserRepository.java` (Interface para acesso aos dados de usuários)
        * `└── CartRepository.java` (Interface para acesso aos dados do carrihno)
        * `└── CartItemRepository.java` (Interface para acesso aos dados de para os itens do carrinho)
        * `└── OrderRepository.java` (Interface para acesso aos dados de pedido)
        * `└── OrderItemRepository.java` (Interface para acesso aos dados dos itens de pedido)
    * `└── service`
        * `├── AuthService.java` (Lógica de negócio para autenticação)
        * `├── JwtService.java` (Lógica para criar e validar tokens JWT)
        * `├── ProductService.java` (Lógica de negócio para o CRUD de produtos)
        * `└── UserDetailsServiceImpl.java` (Serviço para o Spring Security carregar os usuários)
        * `└── CartService.java` (Lógica de negócio para o funcionamento do carrinho)
        * `└── OrderService.java` (Lógica de negócio para o funcionamento dos pedidos)

---

## 👨‍💻 Autor

Desenvolvido por **Thiago André Neves Miranda**.

-   [![LinkedIn](https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/tanm-dev/)
-   [![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/ThiagoMiranda-m)

## 📄 Licença

Este projeto está sob a licença MIT.
     
