package br.com.inkinvite.domain.obra;

import br.com.inkinvite.domain.modelo.ObjetoDeValor;
import br.com.inkinvite.domain.objetosDeValor.DataHora;

public class CabecalhoObra extends ObjetoDeValor {
    private Integer numeroAutor;
    private String nomeAutor;
    private String titulo;
    private String descricao;
    private StatusObra statusObra;
    private DataHora publicacao;

    public static CabecalhoObra criar(Integer numeroAutor, String titulo, String descricao) {
        CabecalhoObra cabecalhoObra = new CabecalhoObra();
        cabecalhoObra.numeroAutor = numeroAutor;
        cabecalhoObra.titulo = titulo;
        cabecalhoObra.descricao = descricao;
        cabecalhoObra.statusObra = StatusObra.CRIADA;
        cabecalhoObra.publicacao = DataHora.agora();
        return cabecalhoObra;
    }

    public static CabecalhoObra carregar(Integer numeroAutor, String nomeAutor, String titulo, String descricao, StatusObra statusObra, DataHora publicacao) {
        CabecalhoObra cabecalhoObra = new CabecalhoObra();
        cabecalhoObra.numeroAutor = numeroAutor;
        cabecalhoObra.nomeAutor = nomeAutor;
        cabecalhoObra.titulo = titulo;
        cabecalhoObra.descricao = descricao;
        cabecalhoObra.statusObra = statusObra;
        cabecalhoObra.publicacao = publicacao;
        return cabecalhoObra;
    }

    public Integer getNumeroAutor() {
        return numeroAutor;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getStatusObra() {
        return statusObra.id;
    }

    public DataHora getPublicacao() {
        return publicacao;
    }

    public String getPublicacao(String formato) {
        return publicacao.formatar(formato);
    }
}
