package br.com.inkinvite.application.usecase;

import br.com.inkinvite.application.repo.UsuarioRepo;
import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.domain.usuario.Usuario;
import br.com.inkinvite.domain.usuario.UsuarioJaExiste;

public class UsuarioUseCase extends UseCase {

    private final UsuarioRepo usuarioRepo;

    public UsuarioUseCase(UsuarioRepo usuarioRepo, LogService logService) {
        super(logService, "UsuarioUseCase");
        this.usuarioRepo = usuarioRepo;
    }

    public void criarUsuario(Usuario usuario) {
        start("Iniciando criacao da usuario de titulo " + usuario.getEmail());
        try {
            usuarioRepo.salvar(usuario);
            sucesso("Criacao do usuario de titulo " + usuario.getEmail() + " realizada com sucesso");
        } catch (UsuarioJaExiste e){
            erro("O usuario requisitado " + usuario.getEmail() + " nao pode ser criado com estes dados.", e);
            throw e;
        } catch (Exception e) {
            erro("Ocorreu um erro ao tentar criar a usuario de titulo " + usuario.getEmail(), e);
            throw e;
        }
    }
}
