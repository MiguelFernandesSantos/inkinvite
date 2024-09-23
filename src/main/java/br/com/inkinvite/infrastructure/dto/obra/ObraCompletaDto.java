package br.com.inkinvite.infrastructure.dto.obra;

import br.com.inkinvite.domain.obra.ObraCompleta;

public class ObraCompletaDto extends ObraDto {
    public AvaliacaoDto avaliacao;
    public CapitulosDto capitulos;

    public static ObraCompletaDto deDominio(ObraCompleta obra) {
        ObraCompletaDto dto = new ObraCompletaDto();
        dto.numero = Integer.parseInt(obra.getId());
        dto.cabecalho = CabecalhoObraDto.deDominio(obra.getCabecalho());
        dto.avaliacao = obra.getAvaliacao() != null ? AvaliacaoDto.deDominio(obra.getAvaliacao()) : null;
        dto.capitulos = CapitulosDto.deDominio(obra.obterListaCapitulos());
        return dto;
    }
}
