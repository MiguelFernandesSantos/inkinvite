package br.com.inkinvite.domain.obra;

import br.com.inkinvite.domain.DominioException;

public class NaoPermiteEditarObra extends DominioException {
    @Override
    public String toString() {
        return "O usuario informado não tem permissão de editar uma obra que ele não criou!!";
    }
}
