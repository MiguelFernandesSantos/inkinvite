package br.com.inkinvite.domain.obra;

import br.com.inkinvite.domain.modelo.Agregado;
import br.com.inkinvite.domain.objetosDeValor.DataHora;

public class Obra extends Agregado {
    protected CabecalhoObra cabecalho;

    public static Obra criar(Integer numeroAutor, String titulo, String descricao) {
        Obra obraCompleta = new Obra();
        obraCompleta.cabecalho = CabecalhoObra.criar(numeroAutor, titulo, descricao);
        return obraCompleta;
    }

    public static Obra carregar(Integer numeroObra, Integer numeroAutor, String nomeAutor, String titulo, String descricao, StatusObra statusObra, DataHora publicacao) {
        Obra obra = new Obra();
        obra.numero = numeroObra;
        obra.cabecalho = CabecalhoObra.carregar(numeroAutor, nomeAutor, titulo, descricao, statusObra, publicacao);
        return obra;
    }

    public CabecalhoObra getCabecalho() {
        return cabecalho;
    }

    public Integer getNumeroAutor() {
        return cabecalho.getNumeroAutor();
    }

    public String getNomeAutor() {
        return cabecalho.getNomeAutor();
    }

    public String getTitulo() {
        return cabecalho.getTitulo();
    }

    public String getDescricao() {
        return cabecalho.getDescricao();
    }

    public Integer getStatusObra() {
        return cabecalho.getStatusObra();
    }

    public DataHora getPublicacao() {
        return cabecalho.getPublicacao();
    }

    public String getPublicacao(String formato) {
        return cabecalho.getPublicacao(formato);
    }

}
