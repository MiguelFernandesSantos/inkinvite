package br.com.inkinvite.interfaces.resources;

import java.util.Collections;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import br.com.inkinvite.application.component.UsuarioComponent;
import br.com.inkinvite.application.repo.UsuarioRepo;
import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.application.service.UsuarioService;
import br.com.inkinvite.infrastructure.dto.UsuarioDto;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@RequestScoped
@Path("/api/usuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResources {
    final UsuarioComponent component;

    public UsuarioResources(UsuarioRepo usuarioRepo, UsuarioService usuarioService, LogService logService) {
        this.component = new UsuarioComponent(usuarioRepo, usuarioService, logService);
    }

    @Inject
    KeycloakProvider keycloakProvider;

    @POST
    public Response criarUsuario(UsuarioDto usuario) {
        try {
            UsersResource usersResource = keycloakProvider.getKeycloakClient().realm(keycloakProvider.getRealmName()).users();
            UserRepresentation user = createUserRepresentation(usuario);
            Response response = usersResource.create(user);
            
            if (response.getStatus() == 201) {
                component.criarUsuario(usuario.paraDominio());
                return Response.status(Response.Status.CREATED).entity("User created successfully").build();
            } else {
                return Response.status(response.getStatus()).entity(response.getStatusInfo().toString()).build();
            }
        } catch (Exception e) {
            throw new WebApplicationException("Internal Server Error", e, Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    private UserRepresentation createUserRepresentation(UsuarioDto usuario) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(usuario.cabecalho.loginUsuario);
        user.setEmail(usuario.cabecalho.emailUsuario);
        user.setFirstName(usuario.cabecalho.primeiroNomeUsuario);
        user.setLastName(usuario.cabecalho.segundoNomeUsuario);
        user.setCredentials(Collections.singletonList(createPasswordCredentials(usuario.cabecalho.senhaUsuario)));
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

@Singleton
class KeycloakProvider {

    @Inject
    @ConfigProperty(name = "REALM")
    private String kcServerUrl;

    @Inject
    @ConfigProperty(name = "REALM_NAME")
    private String kcRealmName;

    @Inject
    @ConfigProperty(name = "CLIENT_ID")
    private String kcClientId;

    @Inject
    @ConfigProperty(name = "CLIENT_SECRET")
    private String kcClientSecret;

    public Keycloak getKeycloakClient() {
        return KeycloakBuilder.builder()
            .serverUrl(kcServerUrl)
            .realm(kcRealmName)
            .clientId(kcClientId)
            .clientSecret(kcClientSecret)
            .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
            .build();
    }

    public String getRealmName() {
        return kcRealmName;
    }
}
