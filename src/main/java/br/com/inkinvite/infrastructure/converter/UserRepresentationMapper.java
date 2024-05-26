package br.com.inkinvite.infrastructure.converter;

import java.util.Collections;

import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import br.com.inkinvite.domain.usuario.Usuario;

public class UserRepresentationMapper {

    public static  UserRepresentation paraUserRepresentation(Usuario usuario) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(usuario.getLoginUsuario());
        user.setEmail(usuario.getEmailUsuario());
        user.setFirstName(usuario.getPrimeiroNomeUsuario());
        user.setLastName(usuario.getSegundoNomeUsuario());
        user.setCredentials(Collections.singletonList(criarCredenciais(usuario.getSenhaUsuario())));
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
