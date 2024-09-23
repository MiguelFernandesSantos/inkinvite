package br.com.inkinvite.domain.obra;

import br.com.inkinvite.domain.objetosDeValor.Avaliacao;
import br.com.inkinvite.domain.objetosDeValor.DataHora;

import java.math.BigDecimal;
import java.util.List;

public class ObraCompleta extends Obra {
    private Avaliacao avaliacao;
    private Capitulos capitulos;

    public ObraCompleta() {
    }

    public static ObraCompleta carregar(Integer numeroObra, Integer numeroAutor, String autor, String titulo, String descricao, Integer statusObra, Avaliacao avaliacao, DataHora publicacao, List<Capitulo> capitulos) {
        ObraCompleta obraCompleta = new ObraCompleta();
        obraCompleta.numero = numeroObra;
        obraCompleta.cabecalho = CabecalhoObra.carregar(numeroAutor, autor, titulo, descricao, StatusObra.fromId(statusObra), publicacao);
        obraCompleta.avaliacao = avaliacao;
        obraCompleta.capitulos = Capitulos.carregar(capitulos);
        return obraCompleta;
    }

    public BigDecimal getAvaliacao() {
        return avaliacao != null ? avaliacao.obterValorAvaliacao() : null;
    }

    public List<Capitulo> obterListaCapitulos() {
        return capitulos.obterCapitulos();
    }

    public Capitulos obterCapitulos() {
        return capitulos;
    }


}

