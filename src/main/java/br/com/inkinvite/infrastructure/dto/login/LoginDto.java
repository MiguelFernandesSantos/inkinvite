package br.com.inkinvite.infrastructure.dto.login;

import br.com.inkinvite.domain.login.Login;

public class LoginDto {
    public CabecalhoLoginDto cabecalho;

    public Login paraDominio() {
        return Login.criar(cabecalho.login, cabecalho.senha);
    }

    public static LoginDto deDominio(Login login) {
        LoginDto dto = new LoginDto();
        dto.cabecalho = CabecalhoLoginDto.deDominio(login.getCabecalho());
        return dto;
    }

}
