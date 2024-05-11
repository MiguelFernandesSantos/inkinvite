package br.com.keycloak.domain.usuario;

import java.util.List;

import br.com.inkinvite.domain.objetosDeValor.Avaliacao;
import br.com.inkinvite.domain.obra.Capitulo;

public class UsuarioCompleto extends Usuario {
    private Avaliacao avaliacao;
    private List<Capitulo> capitulos;

    private UsuarioCompleto() {
    }

    public static UsuarioCompleto carregar(String primeiroNomeUsuario, String segundoNomeUsuario, String loginUsuario, String emailUsuario) {
        UsuarioCompleto usuario = new UsuarioCompleto();
        usuario.cabecalho = CabecalhoUsuario.carregar(primeiroNomeUsuario, segundoNomeUsuario, loginUsuario, emailUsuario);
        return usuario;
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

