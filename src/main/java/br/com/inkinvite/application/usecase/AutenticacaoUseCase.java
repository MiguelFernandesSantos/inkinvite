package br.com.inkinvite.application.usecase;

import br.com.inkinvite.application.repo.AutenticacaoRepo;
import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.domain.autenticacao.Autenticacao;
import br.com.inkinvite.domain.autenticacao.UsuarioNaoEncontrado;

public class AutenticacaoUseCase extends UseCase {

    private final AutenticacaoRepo authRepo;

    public AutenticacaoUseCase(AutenticacaoRepo authRepo, LogService logService) {
        super(logService, "LoginUseCase");
        this.authRepo = authRepo;
    }

    public String recuperarLogin(Autenticacao login) {
        start("Iniciando recuperacao de usuario " + login.getLoginUsuario());
        try {
            String result = authRepo.logar(login);
            sucesso("Recuperacao do usuario de titulo " + login.getLoginUsuario() + " realizada com sucesso");
            return result;
        } catch (UsuarioNaoEncontrado e){
            erro("O usuario requisitado " + login.getLoginUsuario() + " nao pode ser encontrado com estes dados.", e);
            throw e;
        } catch (Exception e) {
            erro("Ocorreu um erro ao tentar recuperar o usuario " + login.getLoginUsuario(), e);
            throw e;
        }
    }
}
