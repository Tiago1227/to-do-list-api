# 📝 To-Do List API

Esta é uma API RESTful para gerenciamento de tarefas, desenvolvida com **Spring Boot**. Ela permite a criação, atualização, exclusão e visualização de tarefas, além de gerenciar a autenticação de usuários com JWT.

## 🚀 Tecnologias utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Security (JWT)**
- **JPA (Hibernate)**
- **MySQL**
- **Lombok**

## ✅ Funcionalidades

- **Cadastro de Tarefas**: Permite ao usuário cadastrar tarefas com título, descrição e status de conclusão.
- **Listagem de Tarefas**: Exibe uma lista paginada de tarefas, com a possibilidade de aplicar filtros.
- **Atualização de Tarefas**: O usuário pode atualizar as informações de uma tarefa existente, como título, descrição e status de conclusão.
- **Exclusão de Tarefas**: Permite excluir uma tarefa do sistema.
- **Autenticação de Usuários**: Realiza a autenticação de usuários via JWT. Apenas o usuário autenticado pode realizar operações nas suas respectivas tarefas.

## ⚙️ Endpoints

### 1. **POST** `/auth/login`

- **Descrição**: Realiza o login do usuário e retorna um token JWT.
- **Body**:
  ```json
  {
    "login": "usuario_exemplo",
    "senha": "senha123"
  }
- **Resposta**:
  ```json
  {
    "token": "jwt_token_aqui"
  }

### 2. **POST** `/tarefas`

- **Descrição**: Cria uma nova tarefa.
- **Autenticação**: Requer token JWT.
- **Body**:
  ```json
  {
    "titulo": "Estudar",
    "descricao": "Revisar Java",
    "concluido": false
  }
- **Resposta**:
  ```json
  {
    "id": 1,
    "titulo": "Estudar",
    "descricao": "Revisar Java",
    "concluido": false
  }
### 3. **GET** `/tarefas`

- **Descrição**:  Lista as tarefas do usuário autenticado.
- **Autenticação**: Requer token JWT.
- **Resposta**:
  ```json
  {
    "id": 1,
    "titulo": "Estudar",
    "descricao": "Revisar Java",
    "concluido": false
  }
### 4. **GET** `/tarefas/{id}`

- **Descrição**:  Exibe os detalhes de uma tarefa específica.
- **Autenticação**: Requer token JWT.
- **Resposta**:
  ```json
  {
    "id": 1,
    "titulo": "Estudar",
    "descricao": "Revisar Java",
    "concluido": false
  }
### 5. **PUT** `/tarefas`

- **Descrição**: Atualiza as informações de uma tarefa existente.
- **Autenticação**: Requer token JWT.
- **Body**:
  ```json
  {
    "id": "1"
    "titulo": "Estudar",
    "descricao": "Revisar Java",
    "concluido": false
  }
- **Resposta**:
  ```json
  {
    "id": 1,
    "titulo": "Estudar",
    "descricao": "Revisar Java",
    "concluido": false
  }
### 6. **DELETE** `/tarefas/{id}`

- **Descrição**: Exclui uma tarefa.
- **Autenticação**: Requer token JWT.
  
