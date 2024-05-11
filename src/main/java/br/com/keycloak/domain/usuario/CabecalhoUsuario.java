package br.com.keycloak.domain.usuario;

import br.com.keycloak.domain.modelo.ObjetoDeValor;
import br.com.keycloak.domain.objetosDeValor.DataHora;

public class CabecalhoUsuario extends ObjetoDeValor {
    private String primeiroNomeUsuario;
    private String segundoNomeUsuario;
    private String loginUsuario;
    private String emailUsuario;
    private String senhaUsuario;
    private DataHora criacao;

    public static CabecalhoUsuario criar(String primeiroNomeUsuario, String segundoNomeUsuario, String loginUsuario, String emailUsuario, String senhaUsuario) {
        CabecalhoUsuario cabecalhoUsuario = new CabecalhoUsuario();
        cabecalhoUsuario.primeiroNomeUsuario = primeiroNomeUsuario;
        cabecalhoUsuario.segundoNomeUsuario = segundoNomeUsuario;
        cabecalhoUsuario.loginUsuario = loginUsuario;
        cabecalhoUsuario.emailUsuario = emailUsuario;
        cabecalhoUsuario.senhaUsuario = senhaUsuario;
        cabecalhoUsuario.criacao = DataHora.agora();
        return cabecalhoUsuario;
    }

    public static CabecalhoUsuario carregar(String primeiroNomeUsuario, String segundoNomeUsuario, String loginUsuario, String emailUsuario) {
        CabecalhoUsuario cabecalhoUsuario = new CabecalhoUsuario();
        cabecalhoUsuario.primeiroNomeUsuario = primeiroNomeUsuario;
        cabecalhoUsuario.segundoNomeUsuario = segundoNomeUsuario;
        cabecalhoUsuario.loginUsuario = loginUsuario;
        cabecalhoUsuario.emailUsuario = emailUsuario;
        return cabecalhoUsuario;
    }

    public String getPrimeiroNomeUsuario() {
        return primeiroNomeUsuario;
    }

    public String getSegundoNomeUsuario() {
        return segundoNomeUsuario;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public DataHora getCriacao() {
        return criacao;
    }
}
