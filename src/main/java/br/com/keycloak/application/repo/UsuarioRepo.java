package br.com.keycloak.application.repo;

import br.com.keycloak.domain.usuario.Usuario;

public interface UsuarioRepo {
    void salvar(Usuario cabecalhoUsuario);
}
