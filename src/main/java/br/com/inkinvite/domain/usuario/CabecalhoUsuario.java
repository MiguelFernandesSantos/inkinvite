package br.com.inkinvite.domain.usuario;

import br.com.inkinvite.domain.modelo.ObjetoDeValor;
import br.com.inkinvite.domain.objetosDeValor.DataHora;

public class CabecalhoUsuario extends ObjetoDeValor {
    private String primeiroNome;
    private String segundoNome;
    private String login;
    private String email;
    private String senha;
    private DataHora criacao;

    public static CabecalhoUsuario criar(String primeiroNome, String segundoNome, String login, String email, String senha) {
        CabecalhoUsuario cabecalhoUsuario = new CabecalhoUsuario();
        cabecalhoUsuario.primeiroNome = primeiroNome;
        cabecalhoUsuario.segundoNome = segundoNome;
        cabecalhoUsuario.login = login;
        cabecalhoUsuario.email = email;
        cabecalhoUsuario.senha = senha;
        cabecalhoUsuario.criacao = DataHora.agora();
        return cabecalhoUsuario;
    }

    public static CabecalhoUsuario carregar(String primeiroNome, String segundoNome, String login, String email) {
        CabecalhoUsuario cabecalhoUsuario = new CabecalhoUsuario();
        cabecalhoUsuario.primeiroNome = primeiroNome;
        cabecalhoUsuario.segundoNome = segundoNome;
        cabecalhoUsuario.login = login;
        cabecalhoUsuario.email = email;
        return cabecalhoUsuario;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public String getSegundoNome() {
        return segundoNome;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public DataHora getCriacao() {
        return criacao;
    }

    public String getCriacao(String formato) {
        return criacao.formatar(formato);
    }
}
