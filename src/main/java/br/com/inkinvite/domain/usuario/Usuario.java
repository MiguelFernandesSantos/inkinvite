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

    public String getPrimeiroNome() {
        return cabecalho.getPrimeiroNome();
    }

    public String getSegundoNome() {
        return cabecalho.getSegundoNome();
    }

    public String getLogin() {
        return cabecalho.getLogin();
    }

    public String getEmail() {
        return cabecalho.getEmail();
    }

    public String getSenha() {
        return cabecalho.getSenha();
    }

    public String getCriacao(String formato) {
        return cabecalho.getCriacao(formato);
    }

}
