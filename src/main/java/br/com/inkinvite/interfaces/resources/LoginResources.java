package br.com.inkinvite.interfaces.resources;

import br.com.inkinvite.application.component.LoginComponent;
import br.com.inkinvite.application.repo.LoginRepo;
import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.infrastructure.dto.login.LoginDto;
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
public class LoginResources {

    final LoginComponent component;

    public LoginResources(LoginRepo loginRepo, LogService logService) {
        this.component = new LoginComponent(loginRepo, logService);
    }

    @POST
    @Path("/login")
    public Response login(LoginDto login) {
        try {
            String result = component.recuperarLogin(login.paraDominio());
            return Response.status(Response.Status.CONFLICT).entity(result).type(MediaType.TEXT_PLAIN).build();
        }catch (Exception e) {
            throw new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}