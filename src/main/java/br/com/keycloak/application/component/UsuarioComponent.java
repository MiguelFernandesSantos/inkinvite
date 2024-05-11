package br.com.keycloak.application.component;

import br.com.keycloak.application.repo.UsuarioRepo;
import br.com.keycloak.application.service.LogService;
import br.com.keycloak.application.service.UsuarioService;
import br.com.keycloak.application.usecase.UsuarioUseCase;
import br.com.keycloak.domain.usuario.Usuario;

public class UsuarioComponent {
    final UsuarioUseCase useCase;

    public UsuarioComponent(UsuarioRepo usuarioRepo, UsuarioService usuarioService, LogService logService) {
        this.useCase = new UsuarioUseCase(usuarioRepo, usuarioService, logService);
    }

    public void criarUsuario(Usuario usuario) {
        useCase.criarUsuario(usuario);
    }
}
