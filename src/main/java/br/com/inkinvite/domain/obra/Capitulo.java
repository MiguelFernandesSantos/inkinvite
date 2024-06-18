package br.com.inkinvite.domain.obra;

import br.com.inkinvite.domain.modelo.Entidade;
import br.com.inkinvite.domain.objetosDeValor.Avaliacao;
import br.com.inkinvite.domain.objetosDeValor.DataHora;

import java.math.BigDecimal;

public class Capitulo extends Entidade {
    private Integer obra;
    private String titulo;
    private DataHora dataCriacao;
    private Integer numeroOrdinal;
    private Avaliacao avaliacao;
    private String mimeType;
    private byte[] arquivo;

    public static Capitulo criar( Integer obra, String titulo) {
        Capitulo capitulo = new Capitulo();
        capitulo.obra = obra;
        capitulo.titulo = titulo;
        capitulo.dataCriacao = DataHora.agora();
        return capitulo;
    }

    public static Capitulo carregar(Integer numero, Integer obra, String titulo, DataHora dataCriacao, Integer numeroOrdinal, Avaliacao avaliacao, String mimeType) {
        Capitulo capitulo = new Capitulo();
        capitulo.numero = numero;
        capitulo.obra = obra;
        capitulo.titulo = titulo;
        capitulo.dataCriacao = dataCriacao;
        capitulo.numeroOrdinal = numeroOrdinal;
        capitulo.avaliacao = avaliacao;
        capitulo.mimeType = mimeType;
        return capitulo;
    }

    public Integer getObra() {
        return obra;
    }

    public String getTitulo() {
        return titulo;
    }

    public BigDecimal getAvaliacao() {
        return avaliacao != null ? avaliacao.obterValorAvaliacao() : null;
    }

    public Integer getNumeroOrdinal() {
        return numeroOrdinal;
    }

    public DataHora getDataCriacao() {
        return dataCriacao;
    }

    public Boolean possuiMimeType() {
        return mimeType != null;
    }

    public String getMimeType() {
        return mimeType;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void adicionarByteArquivos(byte[] bytes) {
        this.arquivo = bytes;
    }
}
