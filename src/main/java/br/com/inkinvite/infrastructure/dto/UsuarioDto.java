package br.com.inkinvite.infrastructure.dto;

import br.com.inkinvite.domain.usuario.Usuario;

public class UsuarioDto {
    public CabecalhoUsuarioDto cabecalho;

    public Usuario paraDominio() {
        return Usuario.criar("Nome", "Sobrenome", "Nomesobrenome", "nome.sobrenome@email.com", "nome123");
    }

    public static UsuarioDto deDominio(Usuario usuario) {
        UsuarioDto dto = new UsuarioDto();
        dto.cabecalho = CabecalhoUsuarioDto.deDominio(usuario.getCabecalho());
        return dto;
    }

}
