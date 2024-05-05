package br.com.inkinvite.domain.obra;

import br.com.inkinvite.domain.modelo.Entidade;
import br.com.inkinvite.domain.objetosDeValor.DataHora;

import java.text.ParseException;

public class Comentario extends Entidade {

    private String conteudo;

    private String usuario;

    private Integer obra;

    private DataHora data;

    private Comentario() {
    }

    public static Comentario criar(String conteudo, String usuario, Integer obra) {
        Comentario comentario = new Comentario();
        comentario.conteudo = conteudo;
        comentario.usuario = usuario;
        comentario.obra = obra;
        comentario.data = DataHora.agora();
        return comentario;
    }

    public static Comentario carregar(Integer numero, String conteudo, String usuario, Integer obra, String data) throws ParseException {
        Comentario comentario = new Comentario();
        comentario.numero = numero;
        comentario.conteudo = conteudo;
        comentario.usuario = usuario;
        comentario.obra = obra;
        comentario.data = DataHora.fromString(data);
        return comentario;
    }

    public String getConteudo() {
        return conteudo;
    }

    public String getUsuario() {
        return usuario;
    }

    public DataHora getData() {
        return data;
    }
}
