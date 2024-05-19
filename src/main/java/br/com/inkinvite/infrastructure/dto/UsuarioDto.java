package br.com.inkinvite.infrastructure.dto;

import br.com.inkinvite.domain.usuario.Usuario;

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
