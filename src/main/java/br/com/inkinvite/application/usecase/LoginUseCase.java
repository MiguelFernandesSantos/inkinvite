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

    public String recuperarLogin(Login login) {
        start("Iniciando recuperacao de usuario " + login.getLoginUsuario());
        try {
            String result = loginRepo.recuperar(login);
            sucesso("Recuperacao do usuario de titulo " + login.getLoginUsuario() + " realizada com sucesso");
            return result;
        } catch (Exception e) {
            erro("Ocorreu um erro ao tentar recuperar o usuario " + login.getLoginUsuario(), e);
            throw e;
        }
    }
}
