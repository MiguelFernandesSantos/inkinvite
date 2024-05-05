package br.com.inkinvite.domain.obra;

import br.com.inkinvite.domain.DominioException;

public class ObraNaoExiste extends DominioException {
    public ObraNaoExiste() {
        super.mensagem = "Não foi encontrado uma obra com essas informações";
    }
}
