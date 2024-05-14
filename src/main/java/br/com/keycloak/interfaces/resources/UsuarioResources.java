package br.com.keycloak.interfaces.resources;

import br.com.keycloak.application.component.UsuarioComponent;
import br.com.keycloak.application.repo.UsuarioRepo;
import br.com.keycloak.application.service.LogService;
import br.com.keycloak.application.service.UsuarioService;
import br.com.keycloak.infrastructure.dto.UsuarioDto;
import jakarta.enterprise.context.RequestScoped;
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


    @POST
    public Response criarUsuario(UsuarioDto usuario) {
        try {
            component.criarUsuario(usuario.paraDominio());
            return Response.ok().build();
        } catch (Exception e) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

}
