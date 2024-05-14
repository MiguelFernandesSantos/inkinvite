package br.com.keycloak.infrastructure.dto;

import br.com.keycloak.domain.usuario.CabecalhoUsuario;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class CabecalhoUsuarioDto {

    public String primeiroNomeUsuario;
    public String segundoNomeUsuario;
    public String loginUsuario;
    public String emailUsuario;
    public String senhaUsuario;

    public CabecalhoUsuario paraDominio() {
        return CabecalhoUsuario.criar(primeiroNomeUsuario, segundoNomeUsuario, loginUsuario, emailUsuario, senhaUsuario);
    }

    public static CabecalhoUsuarioDto deDominio(CabecalhoUsuario cabecalho) {
        CabecalhoUsuarioDto dto = new CabecalhoUsuarioDto();
        dto.primeiroNomeUsuario = cabecalho.getPrimeiroNomeUsuario();
        dto.segundoNomeUsuario = cabecalho.getSegundoNomeUsuario();
        dto.loginUsuario = cabecalho.getLoginUsuario();
        dto.emailUsuario = cabecalho.getEmailUsuario();
        dto.senhaUsuario = cabecalho.getSenhaUsuario();
        return dto;
    }
}
