package br.com.inkinvite.application.usecase;

import br.com.inkinvite.application.repo.LoginRepo;
import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.domain.login.Login;

public class LoginUseCase extends UseCase {

    private final LoginRepo loginRepo;

    public LoginUseCase(LoginRepo loginRepo, LogService logService) {
        super(logService, "LoginUseCase");
        this.loginRepo = loginRepo;
    }

    public void recuperarLogin(Login login) {
        start("Iniciando criacao da usuario de titulo " + login.getLoginUsuario());
        try {
            loginRepo.recuperar(login);
            sucesso("Criacao do usuario de titulo " + login.getLoginUsuario() + " realizada com sucesso");
        } catch (Exception e) {
            erro("Ocorreu um erro ao tentar criar a usuario de titulo " + login.getLoginUsuario(), e);
            throw e;
        }
    }
}
