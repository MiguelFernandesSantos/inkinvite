package br.com.inkinvite.domain.objetosDeValor.documento;

import br.com.inkinvite.domain.modelo.ObjetoDeValor;

public abstract class DocumentoIdentificacao extends ObjetoDeValor {
    public abstract String getNumero();

    public abstract String formatar();

    @Override
    public String toString() {
        return formatar();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentoIdentificacao that = (DocumentoIdentificacao) o;
        return getNumero().equals(that.getNumero());
    }

    @Override
    public int hashCode() {
        return getNumero().hashCode();
    }
}
