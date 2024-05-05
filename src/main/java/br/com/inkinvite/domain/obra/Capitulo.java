package br.com.inkinvite.domain.obra;

import br.com.inkinvite.domain.modelo.Entidade;
import br.com.inkinvite.domain.objetosDeValor.Avaliacao;

public class Capitulo extends Entidade {

    private Integer obra;
    private String titulo;
    private Avaliacao avaliacao;

    public static Capitulo criar(Integer obra, String titulo) {
        Capitulo capitulo = new Capitulo();
        capitulo.obra = obra;
        capitulo.titulo = titulo;
        return capitulo;
    }

    public static Capitulo carregar(Integer numero, Integer obra, String titulo, Avaliacao avaliacao) {
        Capitulo capitulo = new Capitulo();
        capitulo.numero = numero;
        capitulo.obra = obra;
        capitulo.titulo = titulo;
        capitulo.avaliacao = avaliacao;
        return capitulo;
    }

    public Integer getObra() {
        return obra;
    }

    public String getTitulo() {
        return titulo;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }
}
