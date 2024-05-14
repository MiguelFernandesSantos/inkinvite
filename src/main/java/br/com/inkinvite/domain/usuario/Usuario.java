package br.com.inkinvite.domain.usuario;

import br.com.inkinvite.domain.modelo.Agregado;

public class Usuario extends Agregado {
    protected CabecalhoUsuario cabecalho;

    public static Usuario criar(String primeiroNome, String segundoNome, String login, String email, String senha) {
        Usuario usuario = new Usuario();
        usuario.cabecalho = CabecalhoUsuario.criar(primeiroNome, segundoNome, login, email, senha);
        return usuario;
    }

    public static Usuario carregar(String primeiroNome, String segundoNome, String login, String email, String senha) {
        Usuario usuario = new Usuario();
        usuario.cabecalho = CabecalhoUsuario.carregar(primeiroNome, segundoNome, login, email);
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

    public String getCriacao(String formato) {
        return cabecalho.getCriacao(formato);
    }

}
