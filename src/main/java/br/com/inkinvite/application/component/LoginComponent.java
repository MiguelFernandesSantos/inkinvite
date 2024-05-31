package br.com.inkinvite.application.component;

import br.com.inkinvite.application.repo.LoginRepo;
import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.application.usecase.LoginUseCase;
import br.com.inkinvite.domain.login.Login;

public class LoginComponent {
    final LoginUseCase useCase;

    public LoginComponent(LoginRepo loginRepo, LogService logService) {
        this.useCase = new LoginUseCase(loginRepo, logService);
    }

    public String recuperarLogin(Login login) {
        return useCase.recuperarLogin(login);
    }
}
