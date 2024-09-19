package br.com.inkinvite.infrastructure.repo.usuario;

public class UsuarioQueries {
     public final static String QUERY_CRIAR_USUARIO = "INSERT INTO usuario (primeiro_nome, segundo_nome, login, email, data_criacao) VALUES (?, ?, ?, ?, ?)";
}
