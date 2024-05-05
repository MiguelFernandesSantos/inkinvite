package br.com.inkinvite.application.usecase;

import br.com.inkinvite.application.service.LogService;

public class UseCase {

    private final LogService logService;

    private final String nomeUseCase;

    public UseCase(LogService logService, String nomeUseCase) {
        this.logService = logService;
        this.nomeUseCase = nomeUseCase;
    }

    public void start(String mensagem) {
        logService.start(nomeUseCase, mensagem);
    }

    public void info(String mensagem) {
        logService.info(nomeUseCase, mensagem);
    }

    public void sucesso(String mensagem) {
        logService.sucesso(nomeUseCase, mensagem);
    }

    public void erro(String mensagem) {
        logService.start(nomeUseCase, mensagem);
    }

    public void erro(String mensagem, Throwable erro) {
        logService.erro(nomeUseCase, mensagem, erro);
    }
}
