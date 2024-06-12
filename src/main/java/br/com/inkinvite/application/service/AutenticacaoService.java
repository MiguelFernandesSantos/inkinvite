package br.com.inkinvite.application.service;

public interface AutenticacaoService {
    String logar(String login, String senha);

    void esqueciSenha(String email);
}
