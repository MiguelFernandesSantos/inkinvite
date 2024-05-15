package br.com.inkinvite.infrastructure.dto.obra;

import br.com.inkinvite.domain.obra.Capitulo;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.text.ParseException;

@RegisterForReflection
public class CapituloDto {
    public Integer numero;
    public Integer obra;
    public String titulo;
    public String dataCriacao;
    public Integer numeroOrdinal;
    public AvaliacaoDto avaliacao;

    public Capitulo paraDominio() throws ParseException {
        return Capitulo.carregar(
                numero,
                obra,
                titulo,
                null,
                numeroOrdinal,
                avaliacao.paraDominio()
        );
    }

}
