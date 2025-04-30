CREATE TABLE tarefas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descricao varchar(255),
    concluido TINYINT(1) DEFAULT 0
);