package br.com.inkinvite.application.service;

public interface LogService {

    void start(String localizacao, String mensagem);

    void info(String localizacao, String mensagem);

    void sucesso(String localizacao, String mensagem);

    void erro(String localizacao, String mensagem, Throwable erro);

}
