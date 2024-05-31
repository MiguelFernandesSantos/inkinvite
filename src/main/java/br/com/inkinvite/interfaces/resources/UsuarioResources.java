package br.com.inkinvite.interfaces.resources;

import br.com.inkinvite.application.component.UsuarioComponent;
import br.com.inkinvite.application.repo.UsuarioRepo;
import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.domain.usuario.UsuarioJaExiste;
import br.com.inkinvite.infrastructure.dto.UsuarioDto;
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

    public UsuarioResources(UsuarioRepo usuarioRepo, LogService logService) {
        this.component = new UsuarioComponent(usuarioRepo, logService);
    }

    @POST
    public Response criarUsuario(UsuarioDto usuario) {
        try {
            component.criarUsuario(usuario.paraDominio());
            return Response.ok().build();
        } catch (UsuarioJaExiste e) {
            Response response = Response.status(Response.Status.CONFLICT)
                                        .entity(e.toString())
                                        .type(MediaType.TEXT_PLAIN)
                                        .build();
            throw new WebApplicationException(response);
        } catch (Exception e) {
            throw new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

}
