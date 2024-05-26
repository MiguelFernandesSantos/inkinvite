package br.com.inkinvite.application.repo;

import br.com.inkinvite.domain.login.Login;

public interface LoginRepo {
    void recuperar(Login cabecalhoLogin);
}
