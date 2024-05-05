package br.com.inkinvite.domain.objetosDeValor;

import br.com.inkinvite.domain.modelo.ObjetoDeValor;

import java.math.BigDecimal;

public class Avaliacao extends ObjetoDeValor {
    private BigDecimal avaliacao;

    private Avaliacao() {
    }

    public static Avaliacao carregar(BigDecimal avaliacao) {
        Avaliacao avaliacaoObj = new Avaliacao();
        avaliacaoObj.avaliacao = avaliacao;
        return avaliacaoObj;
    }

    public BigDecimal getAvaliacao() {
        return avaliacao;
    }
}
