package br.com.inkinvite.domain.login;

import br.com.inkinvite.domain.modelo.Agregado;

public class Login extends Agregado {
    protected CabecalhoLogin cabecalho;

    public static Login criar(String login, String senha) {
        Login usuario = new Login();
        usuario.cabecalho = CabecalhoLogin.criar(login, senha);
        return usuario;
    }

    public static Login carregar(String login, String senha) {
        Login usuario = new Login();
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
