package br.com.inkinvite.domain.obra;

import br.com.inkinvite.domain.DominioException;

public class CapituloNaoExiste extends DominioException {
    public CapituloNaoExiste() {
        super.mensagem = "Não foi encontrado um capítulo com essas informações";
    }
}
