package br.com.inkinvite.application.usecase;

import br.com.inkinvite.application.service.AutenticacaoService;
import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.domain.autenticacao.UsuarioInvalido;
import br.com.inkinvite.domain.autenticacao.UsuarioNaoEncontrado;

public class AutenticacaoUseCase extends UseCase {

    private final AutenticacaoService autenticacaoService;

    public AutenticacaoUseCase(AutenticacaoService autenticacaoService, LogService logService) {
        super(logService, "LoginUseCase");
        this.autenticacaoService = autenticacaoService;
    }

    public String recuperarLogin(String login, String senha) {
        start("Iniciando recuperacao de usuario " + login);
        try {
            String result = autenticacaoService.logar(login, senha);
            sucesso("Recuperacao do usuario de titulo " + login + " realizada com sucesso");
            return result;
        } catch (UsuarioNaoEncontrado e){
            erro("O usuario requisitado " + login + " nao pode ser encontrado com estes dados.", e);
            throw e;
        } catch (UsuarioInvalido e){
            erro("O usuario requisitado " + login + " possui dados invalidos.", e);
            throw e;
        } catch (Exception e) {
            erro("Ocorreu um erro ao tentar recuperar o usuario " + login, e);
            throw e;
        }
    }

    public String esqueciSenha(String email) {
        start("Iniciando recuperacao de usuario " + email);
        try {
            String result = autenticacaoService.esqueciSenha(email);
            sucesso("Recuperacao do usuario de titulo " + email + " realizada com sucesso");
            return result;
        } catch (UsuarioNaoEncontrado e){
            erro("O usuario requisitado " + email + " nao pode ser encontrado com estes dados.", e);
            throw e;
        } catch (UsuarioInvalido e){
            erro("O usuario requisitado " + email + " possui dados invalidos.", e);
            throw e;
        } catch (Exception e) {
            erro("Ocorreu um erro ao tentar recuperar o usuario " + email, e);
            throw e;
        }
    }
}
