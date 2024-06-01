package br.com.inkinvite.domain.autenticacao;

import br.com.inkinvite.domain.modelo.ObjetoDeValor;

public class CabecalhoLogin extends ObjetoDeValor {
    private String login;
    private String senha;

    public static CabecalhoLogin criar(String login, String senha) {
        CabecalhoLogin cabecalhoLogin = new CabecalhoLogin();
        cabecalhoLogin.login = login;
        cabecalhoLogin.senha = senha;
        return cabecalhoLogin;
    }

    public static CabecalhoLogin carregar(String login, String senha) {
        CabecalhoLogin cabecalhoUsuario = new CabecalhoLogin();
        cabecalhoUsuario.login = login;
        cabecalhoUsuario.senha = senha;
        return cabecalhoUsuario;
    }

    public String getLoginUsuario() {
        return login;
    }

    public String getSenhaUsuario() {
        return senha;
    }
}
