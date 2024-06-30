package br.com.inkinvite.infrastructure.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class CabecalhoUsuarioDto {

    public String primeiroNome;
    public String segundoNome;
    public String login;
    public String email;
    public String senha;

}
