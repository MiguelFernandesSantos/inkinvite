package br.com.inkinvite.application.service;

import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;

import br.com.inkinvite.application.component.UsuarioComponent;
import br.com.inkinvite.application.converter.UserRepresentationConverter;
import br.com.inkinvite.infrastructure.dto.UsuarioDto;
import br.com.inkinvite.security.KeycloakProvider;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class UsuarioService {

    @Inject
    UsuarioComponent component;

    @Inject
    KeycloakProvider keycloakProvider;

    @Inject
    UserRepresentationConverter userRepresentationConverter;

    public Response criarUsuario(UsuarioDto usuario) {
        try {
            UsersResource usersResource = keycloakProvider.getKeycloakClient().realm(keycloakProvider.getRealmName()).users();
            UserRepresentation user = userRepresentationConverter.toUserRepresentation(usuario);
            Response response = usersResource.create(user);
            
            if (response.getStatus() == 201) {
                component.criarUsuario(usuario.paraDominio());
                return Response.status(Response.Status.CREATED).entity("User created successfully").build();
            } else {
                return Response.status(response.getStatus()).entity(response.getStatusInfo().toString()).build();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error creating user", e);
        }
    }
}
