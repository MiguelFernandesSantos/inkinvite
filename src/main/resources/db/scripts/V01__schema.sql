-- liquibase formatted sql
-- changeset inkinvite:V01__schema splitStatements:true endDelimiter:;

CREATE TABLE IF NOT EXISTS usuario (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    primeiro_nome VARCHAR(255) NOT NULL,
    segundo_nome VARCHAR(255) NOT NULL,
    login VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    data_criacao TIMESTAMP NOT NULL,
    CONSTRAINT uk_email UNIQUE (email),
    CONSTRAINT uk_login UNIQUE (login)
);

CREATE TABLE IF NOT EXISTS obra (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    autor BIGINT NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    descricao TEXT,
    status INT NOT NULL,
    data_criacao TIMESTAMP NOT NULL,
    CONSTRAINT fk_autor FOREIGN KEY (autor) REFERENCES usuario(id)
);
