package br.com.keycloak.infrastructure.repo.usuario;

public class UsuarioQueries {
    protected final String QUERY_CRIAR_USUARIO = "INSERT INTO usuario ("
            + "autor, titulo, descricao, status, data_criacao"
            + ") VALUES (?, ?, ?, ?, ?)";

}
