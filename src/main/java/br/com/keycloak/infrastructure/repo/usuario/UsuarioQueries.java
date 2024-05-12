package br.com.keycloak.infrastructure.repo.usuario;

public class UsuarioQueries {
    protected final String QUERY_CRIAR_USUARIO = "INSERT INTO usuario (primeiro_nome, segundo_nome, login, email, senha, data_criacao) VALUES (?, ?, ?, ?, ?, ?)";
}
