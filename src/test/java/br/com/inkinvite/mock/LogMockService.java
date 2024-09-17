package br.com.inkinvite.mock;

import br.com.inkinvite.application.service.LogService;

public class LogMockService implements LogService {

    public LogMockService() {
    }

    @Override
    public void start(String localizacao, String mensagem) {
        System.out.println("[" + localizacao + "] " + mensagem + "...");
    }

    @Override
    public void info(String localizacao, String mensagem) {
        System.out.println("[" + localizacao + "] " + mensagem);
    }

    @Override
    public void sucesso(String localizacao, String mensagem) {
        System.out.println("[" + localizacao + "] " + mensagem + "!!");
    }

    @Override
    public void erro(String localizacao, String mensagem, Throwable erro) {
        System.out.println("[" + localizacao + "] " + mensagem + "!! " + erro);
    }
}
