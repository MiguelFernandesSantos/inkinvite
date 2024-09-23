package br.com.inkinvite.mock.mock.usuario;

import br.com.inkinvite.application.repo.UsuarioRepo;
import br.com.inkinvite.domain.usuario.Usuario;
import br.com.inkinvite.domain.usuario.UsuarioJaExiste;

import java.util.Objects;

public class UsuarioMockRepo implements UsuarioRepo {
    @Override
    public void salvar(Usuario cabecalhoUsuario) {
        if (Objects.equals(cabecalhoUsuario.getEmail(), "jaExiste@gmail.com")) {
            throw new UsuarioJaExiste();
        } else if (Objects.equals(cabecalhoUsuario.getEmail(), "erroGenerico@gmail.com")) {
            throw new RuntimeException();
        }
    }
}
