package br.com.inkinvite.domain.autenticacao;

import br.com.inkinvite.domain.DominioException;

public class UsuarioNaoEncontrado extends DominioException {
    public UsuarioNaoEncontrado() {
        super.mensagem = "O usuário requisitado não pode ser encontrado. Tente novamente.";
    }
}
