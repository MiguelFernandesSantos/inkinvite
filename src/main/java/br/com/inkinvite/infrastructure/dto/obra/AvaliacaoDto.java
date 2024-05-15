package br.com.inkinvite.infrastructure.dto.obra;

import br.com.inkinvite.domain.objetosDeValor.Avaliacao;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.math.BigDecimal;

@RegisterForReflection
public class AvaliacaoDto {
    public BigDecimal avaliacao;

    public  Avaliacao paraDominio() {
        return Avaliacao.carregar(avaliacao);
    }

}
