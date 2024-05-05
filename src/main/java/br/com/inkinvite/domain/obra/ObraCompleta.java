package br.com.inkinvite.domain.obra;

import br.com.inkinvite.domain.modelo.Agregado;
import br.com.inkinvite.domain.objetosDeValor.Avaliacao;
import br.com.inkinvite.domain.objetosDeValor.DataHora;

import java.math.BigDecimal;
import java.util.List;

public class ObraCompleta extends Obra {
    private Avaliacao avaliacao;
    private List<Capitulo> capitulos;

    private ObraCompleta() {
    }


    public static ObraCompleta carregar(Integer numeroAutor, String autor, String titulo, String descricao, Integer statusObra, Avaliacao avaliacao, DataHora publicacao, List<Capitulo> capitulos) {
        ObraCompleta obraCompleta = new ObraCompleta();
        obraCompleta.cabecalho = CabecalhoObra.carregar(numeroAutor, autor, titulo, descricao, StatusObra.fromId(statusObra), publicacao);
        obraCompleta.avaliacao = avaliacao;
        obraCompleta.capitulos = capitulos;
        return obraCompleta;
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

    public BigDecimal getAvaliacao() {
        return avaliacao.getAvaliacao();
    }

    public DataHora getPublicacao() {
        return cabecalho.getPublicacao();
    }

    public List<Capitulo> getCapitulos() {
        return capitulos;
    }
}

