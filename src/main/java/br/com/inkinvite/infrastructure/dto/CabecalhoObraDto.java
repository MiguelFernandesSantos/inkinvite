package br.com.inkinvite.infrastructure.dto;

import br.com.inkinvite.domain.obra.CabecalhoObra;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class CabecalhoObraDto {

    public Integer numeroAutor;
    public String nomeAutor;
    public String titulo;
    public String descricao;

    public CabecalhoObra paraDominio() {
        return CabecalhoObra.criar(numeroAutor, titulo, descricao);
    }

    public static CabecalhoObraDto deDominio(CabecalhoObra cabecalho) {
        CabecalhoObraDto dto = new CabecalhoObraDto();
        dto.numeroAutor = cabecalho.getNumeroAutor();
        dto.nomeAutor = cabecalho.getNomeAutor();
        dto.titulo = cabecalho.getTitulo();
        dto.descricao = cabecalho.getDescricao();
        return dto;
    }
}
