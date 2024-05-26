package br.com.inkinvite.domain.usuario;

import br.com.inkinvite.domain.DominioException;

public class UsuarioJaExiste extends DominioException {
    public UsuarioJaExiste() {
        super.mensagem = "O usuário requisitado não pode ser criado com estes dados. Tente novamente.";
    }
}
