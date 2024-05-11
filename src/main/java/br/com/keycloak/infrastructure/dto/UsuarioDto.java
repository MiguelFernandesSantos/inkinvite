package br.com.keycloak.infrastructure.dto;

import br.com.keycloak.domain.usuario.Usuario;

public class UsuarioDto {
    public CabecalhoUsuarioDto cabecalho;

    public Usuario paraDominio() {
        return Usuario.criar(cabecalho.primeiroNomeUsuario, cabecalho.segundoNomeUsuario, cabecalho.loginUsuario, cabecalho.emailUsuario, cabecalho.senhaUsuario);
    }

    public static UsuarioDto deDominio(Usuario usuario) {
        UsuarioDto dto = new UsuarioDto();
        dto.cabecalho = CabecalhoUsuarioDto.deDominio(usuario.getCabecalho());
        return dto;
    }

}
