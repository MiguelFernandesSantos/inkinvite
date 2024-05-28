package br.com.inkinvite.infrastructure.converter;

import java.util.Collections;

import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import br.com.inkinvite.domain.usuario.Usuario;

public class UserRepresentationMapper {

    public static  UserRepresentation paraUserRepresentation(Usuario usuario) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(usuario.getLogin());
        user.setEmail(usuario.getEmail());
        user.setFirstName(usuario.getPrimeiroNome());
        user.setLastName(usuario.getSegundoNome());
        user.setCredentials(Collections.singletonList(criarCredenciais(usuario.getSenha())));
        user.setEnabled(true);
        return user;
    }

    private static  CredentialRepresentation criarCredenciais(String password) {
        CredentialRepresentation passwordCreds = new CredentialRepresentation();
        passwordCreds.setType(CredentialRepresentation.PASSWORD);
        passwordCreds.setValue(password);
        passwordCreds.setTemporary(false);
        return passwordCreds;
    }
}
