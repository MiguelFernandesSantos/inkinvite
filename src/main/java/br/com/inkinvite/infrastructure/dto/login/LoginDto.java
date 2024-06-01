package br.com.inkinvite.infrastructure.dto.login;

import br.com.inkinvite.domain.autenticacao.Autenticacao;

public class LoginDto {
    public CabecalhoLoginDto cabecalho;

    public Autenticacao paraDominio() {
        return Autenticacao.criar(cabecalho.login, cabecalho.senha);
    }

    public static LoginDto deDominio(Autenticacao login) {
        LoginDto dto = new LoginDto();
        dto.cabecalho = CabecalhoLoginDto.deDominio(login.getCabecalho());
        return dto;
    }

}
