package br.com.inkinvite.application.component;

import br.com.inkinvite.application.repo.UsuarioRepo;
import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.application.usecase.UsuarioUseCase;
import br.com.inkinvite.domain.usuario.Usuario;

public class UsuarioComponent {
    final UsuarioUseCase useCase;

    public UsuarioComponent(UsuarioRepo usuarioRepo,  LogService logService) {
        this.useCase = new UsuarioUseCase(usuarioRepo, logService);
    }

    public void criarUsuario(Usuario usuario) {
        useCase.criarUsuario(usuario);
    }
}
