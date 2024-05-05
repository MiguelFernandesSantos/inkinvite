package br.com.inkinvite.infrastructure.dto;

import br.com.inkinvite.domain.obra.Obra;

public class ObraDto {
    public Integer numero;
    public CabecalhoObraDto cabecalho;

    public Obra paraDominio() {
        return Obra.criar(cabecalho.numeroAutor, cabecalho.titulo, cabecalho.descricao);
    }

    public static ObraDto deDominio(Obra obra) {
        ObraDto dto = new ObraDto();
        dto.numero = Integer.parseInt(obra.getId());
        dto.cabecalho = CabecalhoObraDto.deDominio(obra.getCabecalho());
        return dto;
    }

}
