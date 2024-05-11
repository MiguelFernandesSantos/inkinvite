package br.com.keycloak.domain.objetosDeValor;

import java.math.BigDecimal;

import br.com.inkinvite.domain.modelo.ObjetoDeValor;

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
