package br.com.inkinvite.domain.usuario;

import br.com.inkinvite.domain.modelo.Agregado;
import br.com.inkinvite.domain.objetosDeValor.DataHora;

public class Usuario extends Agregado {
    private String primeiroNome;
    private String segundoNome;
    private String login;
    private String email;
    private String senha;
    private Boolean eAutor;
    private DataHora criacao;

    public static Usuario criar(String primeiroNome, String segundoNome, String login, String email, String senha, Boolean eAutor) {
        Usuario usuario = new Usuario();
        usuario.primeiroNome = primeiroNome;
        usuario.segundoNome = segundoNome;
        usuario.login = login;
        usuario.email = email;
        usuario.senha = senha;
        usuario.eAutor = eAutor;
        usuario.criacao = DataHora.agora();
        return usuario;
    }

    public static Usuario carregar(String primeiroNome, String segundoNome, String login, String email, String senha) {
        Usuario usuario = new Usuario();
        usuario.primeiroNome = primeiroNome;
        usuario.segundoNome = segundoNome;
        usuario.login = login;
        usuario.email = email;
        usuario.senha = senha;
        usuario.criacao = DataHora.agora();
        return usuario;
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

    public Boolean getEAutor() {
        return eAutor;
    }

    public DataHora getCriacao() {
        return criacao;
    }

    public String getCriacao(String formato) {
        return criacao.formatar(formato);
    }
}
