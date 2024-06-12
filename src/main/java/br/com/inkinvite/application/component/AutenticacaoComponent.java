package br.com.inkinvite.application.component;

import br.com.inkinvite.application.service.AutenticacaoService;
import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.application.usecase.AutenticacaoUseCase;

public class AutenticacaoComponent {
    final AutenticacaoUseCase useCase;

    public AutenticacaoComponent(AutenticacaoService autenticacaoService, LogService logService) {
        this.useCase = new AutenticacaoUseCase(autenticacaoService, logService);
    }

    public String recuperarLogin(String login, String senha) {
        return useCase.recuperarLogin(login, senha);
    }

    public void esqueciSenha(String email) {
        useCase.esqueciSenha(email);
    }
}
