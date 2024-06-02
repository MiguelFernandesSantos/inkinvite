package br.com.inkinvite.domain.autenticacao;

import br.com.inkinvite.domain.modelo.Agregado;

public class Autenticacao extends Agregado {
    protected CabecalhoLogin cabecalho;

    public CabecalhoLogin getCabecalho() {
        return cabecalho;
    }

    public String getLoginUsuario() {
        return cabecalho.getLoginUsuario();
    }

    public String getSenhaUsuario() {
        return cabecalho.getSenhaUsuario();
    }

}
