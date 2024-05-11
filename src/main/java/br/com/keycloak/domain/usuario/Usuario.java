package br.com.keycloak.domain.usuario;

import br.com.inkinvite.domain.modelo.Agregado;

public class Usuario extends Agregado {
    protected CabecalhoUsuario cabecalho;

    public static Usuario criar(String primeiroNomeUsuario, String segundoNomeUsuario, String loginUsuario, String emailUsuario, String senhaUsuario) {
        Usuario usuario = new Usuario();
        usuario.cabecalho = CabecalhoUsuario.criar(primeiroNomeUsuario, segundoNomeUsuario, loginUsuario, emailUsuario, senhaUsuario);
        return usuario;
    }

    public static Usuario carregar(String primeiroNomeUsuario, String segundoNomeUsuario, String loginUsuario, String emailUsuario, String senhaUsuario) {
        Usuario usuario = new Usuario();
        usuario.cabecalho = CabecalhoUsuario.carregar(primeiroNomeUsuario, segundoNomeUsuario, loginUsuario, emailUsuario);
        return usuario;
    }

    public CabecalhoUsuario getCabecalho() {
        return cabecalho;
    }

    public String getPrimeiroNomeUsuario() {
        return cabecalho.getPrimeiroNomeUsuario();
    }

    public String getSegundoNomeUsuario() {
        return cabecalho.getSegundoNomeUsuario();
    }

    public String getLoginUsuario() {
        return cabecalho.getLoginUsuario();
    }

    public String getEmailUsuario() {
        return cabecalho.getEmailUsuario();
    }

    public String getSenhaUsuario() {
        return cabecalho.getSenhaUsuario();
    }

}
