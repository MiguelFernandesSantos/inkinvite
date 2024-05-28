package br.com.inkinvite.infrastructure.dto;

import br.com.inkinvite.domain.usuario.CabecalhoUsuario;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class CabecalhoUsuarioDto {

    public String primeiroNome;
    public String segundoNome;
    public String login;
    public String email;
    public String senha;

    public CabecalhoUsuario paraDominio() {
        return CabecalhoUsuario.criar(primeiroNome, segundoNome, login, email, senha);
    }

    public static CabecalhoUsuarioDto deDominio(CabecalhoUsuario cabecalho) {
        CabecalhoUsuarioDto dto = new CabecalhoUsuarioDto();
        dto.primeiroNome = cabecalho.getPrimeiroNome();
        dto.segundoNome = cabecalho.getSegundoNome();
        dto.login = cabecalho.getLogin();
        dto.email = cabecalho.getEmail();
        dto.senha = cabecalho.getSenha();
        return dto;
    }
}
