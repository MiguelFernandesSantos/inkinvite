package br.com.inkinvite.mock.mock.autenticacao;

import br.com.inkinvite.application.service.AutenticacaoService;
import br.com.inkinvite.domain.usuario.UsuarioInvalido;
import br.com.inkinvite.domain.usuario.UsuarioNaoEncontrado;

import java.util.Objects;

public class AutenticacaoMockService implements AutenticacaoService {

    public AutenticacaoMockService() {
    }

    @Override
    public String logar(String login, String senha) {
        validarUsuario(login, senha);
        return "token" + login;
    }


    @Override
    public void esqueciSenha(String credencialUsuario) {
        validarUsuario(credencialUsuario, null);
    }

    private static void validarUsuario(String login, String senha) {
        if (Objects.equals(login, "naoExiste@gmail.com")) {
            throw new UsuarioNaoEncontrado();
        } else if (Objects.equals(login, "erroGenerico@gmail.com")) {
            throw new RuntimeException();
        } else if (Objects.equals(senha, "senhaIncorreta")) {
            throw new UsuarioInvalido();
        }
    }
}
