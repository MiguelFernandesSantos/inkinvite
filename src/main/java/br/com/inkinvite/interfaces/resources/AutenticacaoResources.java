package br.com.inkinvite.interfaces.resources;

import br.com.inkinvite.application.component.AutenticacaoComponent;
import br.com.inkinvite.application.service.AutenticacaoService;
import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.domain.autenticacao.UsuarioNaoEncontrado;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@RequestScoped
@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AutenticacaoResources {

    final AutenticacaoComponent component;

    public AutenticacaoResources(AutenticacaoService authRepo, LogService logService) {
        this.component = new AutenticacaoComponent(authRepo, logService);
    }

    @POST
    @Path("/login")
    public Response login(String login, String senha) {
        try {
            String result = component.recuperarLogin(login, senha);
            return Response.status(Response.Status.OK).entity(result).type(MediaType.TEXT_PLAIN).build();
        } catch (UsuarioNaoEncontrado e) {
            Response response = Response.status(Response.Status.NOT_FOUND)
                                        .entity(e.toString())
                                        .type(MediaType.TEXT_PLAIN)
                                        .build();
            throw new WebApplicationException(response);
        } catch (Exception e) {
            throw new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}