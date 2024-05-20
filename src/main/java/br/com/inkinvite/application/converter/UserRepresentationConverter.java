package br.com.inkinvite.application.converter;

import java.util.Collections;

import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import br.com.inkinvite.infrastructure.dto.UsuarioDto;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepresentationConverter {

    public UserRepresentation toUserRepresentation(UsuarioDto usuarioDto) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(usuarioDto.cabecalho.loginUsuario);
        user.setEmail(usuarioDto.cabecalho.emailUsuario);
        user.setFirstName(usuarioDto.cabecalho.primeiroNomeUsuario);
        user.setLastName(usuarioDto.cabecalho.segundoNomeUsuario);
        user.setCredentials(Collections.singletonList(createPasswordCredentials(usuarioDto.cabecalho.senhaUsuario)));
        user.setEnabled(true);
        return user;
    }

    private CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCreds = new CredentialRepresentation();
        passwordCreds.setType(CredentialRepresentation.PASSWORD);
        passwordCreds.setValue(password);
        passwordCreds.setTemporary(false);
        return passwordCreds;
    }
}
