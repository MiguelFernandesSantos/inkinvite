package br.com.inkinvite.application.repo;

import br.com.inkinvite.domain.autenticacao.Autenticacao;

public interface AutenticacaoRepo {
    String logar(Autenticacao cabecalhoLogin);
}
