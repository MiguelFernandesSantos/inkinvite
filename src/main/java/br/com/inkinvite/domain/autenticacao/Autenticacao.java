package br.com.inkinvite.domain.autenticacao;

import br.com.inkinvite.domain.modelo.Agregado;

public class Autenticacao extends Agregado {
    protected CabecalhoLogin cabecalho;

    public static Autenticacao criar(String login, String senha) {
        Autenticacao usuario = new Autenticacao();
        usuario.cabecalho = CabecalhoLogin.criar(login, senha);
        return usuario;
    }

    public static Autenticacao carregar(String login, String senha) {
        Autenticacao usuario = new Autenticacao();
        usuario.cabecalho = CabecalhoLogin.carregar(login, senha);
        return usuario;
    }

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
