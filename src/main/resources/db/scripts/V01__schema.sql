-- liquibase formatted sql
-- changeset inkinvite:V01__schema splitStatements:true endDelimiter:;

CREATE TABLE IF NOT EXISTS obra (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    autor BIGINT NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    descricao TEXT,
    status INT NOT NULL,
    data_criacao TIMESTAMP NOT NULL,
);