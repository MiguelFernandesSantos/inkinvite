package br.com.inkinvite.application.component;

import br.com.inkinvite.application.service.AutenticacaoService;
import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.application.usecase.AutenticacaoUseCase;

public class AutenticacaoComponent {
    final AutenticacaoUseCase useCase;

    public AutenticacaoComponent(AutenticacaoService authRepo, LogService logService) {
        this.useCase = new AutenticacaoUseCase(authRepo, logService);
    }

    public String recuperarLogin(String login, String senha) {
        return useCase.recuperarLogin(login, senha);
    }
}
