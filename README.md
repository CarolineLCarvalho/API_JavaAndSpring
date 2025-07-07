📚 API RESTful com Java + Spring Boot
Este projeto é uma API 100% RESTful desenvolvida com Java e Spring Boot para uma avaliacao semestral da faculdade, utilizando o banco de dados em memória H2. A API conta com duas entidades que se relacionam por meio de um mapeamento OneToMany, e implementa as operações básicas de CRUD.

🚀 Tecnologias utilizadas
Java 21

Spring Boot

Spring Web

Spring Data JPA

Banco de dados H2

🧩 Estrutura da API
🔗 Entidades
Writer: representa um autor.

Book: representa um livro escrito por um autor.

Relacionamento:
Um Writer pode ter vários Books associados (OneToMany).

🌐 Endpoints RESTful
Writer
GET /writers – Lista todos os escritores

GET /writers/{id} – Busca um escritor por ID

POST /writers – Cadastra um novo escritor

PUT /writers/{id} – Atualiza um escritor existente

DELETE /writers/{id} – Remove um escritor

Book
GET /books – Lista todos os livros

GET /books/{id} – Busca um livro por ID

POST /books – Cadastra um novo livro (associado a um escritor)

PUT /books/{id} – Atualiza um livro

DELETE /books/{id} – Remove um livro
