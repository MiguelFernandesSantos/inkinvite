package br.com.inkinvite.application.component;

import br.com.inkinvite.application.repo.AutenticacaoRepo;
import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.application.usecase.AutenticacaoUseCase;
import br.com.inkinvite.domain.autenticacao.Autenticacao;

public class AutenticacaoComponent {
    final AutenticacaoUseCase useCase;

    public AutenticacaoComponent(AutenticacaoRepo authRepo, LogService logService) {
        this.useCase = new AutenticacaoUseCase(authRepo, logService);
    }

    public String recuperarLogin(Autenticacao login) {
        return useCase.recuperarLogin(login);
    }
}
