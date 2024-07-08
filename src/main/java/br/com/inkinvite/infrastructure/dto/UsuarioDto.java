package br.com.inkinvite.infrastructure.dto;

import br.com.inkinvite.domain.usuario.Usuario;

public class UsuarioDto {
    public CabecalhoUsuarioDto cabecalho;

    public Usuario paraDominio() {
        return Usuario.criar(cabecalho.primeiroNome, cabecalho.segundoNome, cabecalho.login, cabecalho.email, cabecalho.senha, cabecalho.eAutor);
    }

}
