package br.com.keycloak.application.usecase;

import br.com.keycloak.application.repo.UsuarioRepo;
import br.com.keycloak.application.service.LogService;
import br.com.keycloak.application.service.UsuarioService;
import br.com.keycloak.domain.usuario.Usuario;

public class UsuarioUseCase extends UseCase {

    private final UsuarioRepo usuarioRepo;

    public UsuarioUseCase(UsuarioRepo usuarioRepo, UsuarioService usuarioService, LogService logService) {
        super(logService, "UsuarioUseCase");
        this.usuarioRepo = usuarioRepo;
    }

    public void criarUsuario(Usuario usuario) {
        start("Iniciando criacao da usuario de titulo " + usuario.getLoginUsuario());
        try {
            usuarioRepo.salvar(usuario);
            sucesso("Criacao da usuario de titulo " + usuario.getLoginUsuario() + " realizada com sucesso");
        } catch (Exception e) {
            erro("Ocorreu um erro ao tentar criar a usuario de titulo " + usuario.getLoginUsuario(), e);
            throw e;
        }
    }
}
