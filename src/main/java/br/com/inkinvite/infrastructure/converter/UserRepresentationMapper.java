package br.com.inkinvite.infrastructure.converter;

import java.util.Collections;

import br.com.inkinvite.domain.usuario.Usuario;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

public class UserRepresentationMapper {

    public static  UserRepresentation toUserRepresentation(Usuario usuario) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(usuario.getLoginUsuario());
        user.setEmail(usuario.getEmailUsuario());
        user.setFirstName(usuario.getPrimeiroNomeUsuario());
        user.setLastName(usuario.getSegundoNomeUsuario());
        user.setCredentials(Collections.singletonList(createPasswordCredentials(usuario.getSenhaUsuario())));
        user.setEnabled(true);
        return user;
    }

    private static  CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCreds = new CredentialRepresentation();
        passwordCreds.setType(CredentialRepresentation.PASSWORD);
        passwordCreds.setValue(password);
        passwordCreds.setTemporary(false);
        return passwordCreds;
    }
}
