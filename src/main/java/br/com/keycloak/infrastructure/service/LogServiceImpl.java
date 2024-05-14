package br.com.keycloak.infrastructure.service;

import br.com.keycloak.application.service.LogService;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LogServiceImpl implements LogService {
    @Override
    public void start(String localizacao, String mensagem) {
        Log.info("[" + localizacao + "] " + mensagem + "...");
    }

    @Override
    public void info(String localizacao, String mensagem) {
        Log.info("[" + localizacao + "] " + mensagem);
    }

    @Override
    public void sucesso(String localizacao, String mensagem) {
        Log.info("[" + localizacao + "] " + mensagem + "!");
    }

    @Override
    public void erro(String localizacao, String mensagem, Throwable erro) {
        Log.error("[" + localizacao + "] " + mensagem + "!!", erro);
    }
}
