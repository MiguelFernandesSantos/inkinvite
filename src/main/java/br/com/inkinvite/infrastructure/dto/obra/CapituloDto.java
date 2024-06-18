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
    public byte[] arquivo;

    public static CapituloDto deDominio(Capitulo capitulo) {
        CapituloDto dto = new CapituloDto();
        dto.numero = Integer.parseInt(capitulo.getId());
        dto.obra = capitulo.getObra();
        dto.titulo = capitulo.getTitulo();
        dto.dataCriacao = capitulo.getDataCriacao().toString();
        dto.numeroOrdinal = capitulo.getNumeroOrdinal();
        dto.avaliacao = capitulo.getAvaliacao() != null ? AvaliacaoDto.deDominio(capitulo.getAvaliacao()) : null;
        dto.arquivo = capitulo.getArquivo();
        return dto;
    }

    public Capitulo paraDominio() throws ParseException {
        return Capitulo.carregar(
                numero,
                obra,
                titulo,
                null,
                numeroOrdinal,
                avaliacao.paraDominio(),
                null
        );
    }

}
