package br.com.inkinvite.application.usecase;

import br.com.inkinvite.application.repo.UsuarioRepo;
import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.domain.usuario.Usuario;

public class UsuarioUseCase extends UseCase {

    private final UsuarioRepo usuarioRepo;

    public UsuarioUseCase(UsuarioRepo usuarioRepo, LogService logService) {
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
