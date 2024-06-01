package br.com.inkinvite.infrastructure.dto.login;

import br.com.inkinvite.domain.autenticacao.CabecalhoLogin;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class CabecalhoLoginDto {

    public String login;
    public String senha;

    public CabecalhoLogin paraDominio() {
        return CabecalhoLogin.carregar(login, senha);
    }

    public static CabecalhoLoginDto deDominio(CabecalhoLogin cabecalho) {
        CabecalhoLoginDto dto = new CabecalhoLoginDto();
        dto.login = cabecalho.getLoginUsuario();
        dto.senha = cabecalho.getSenhaUsuario();
        return dto;
    }
}
