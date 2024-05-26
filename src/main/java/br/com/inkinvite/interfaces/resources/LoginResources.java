package br.com.inkinvite.interfaces.resources;

import br.com.inkinvite.application.component.LoginComponent;
import br.com.inkinvite.application.repo.LoginRepo;
import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.infrastructure.dto.login.LoginDto;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
public class LoginResources {

    final LoginComponent component;

    public LoginResources(LoginRepo loginRepo,  LogService logService) {
        this.component = new LoginComponent(loginRepo,  logService);
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginDto login) {
        try {
            component.recuperarLogin(login.paraDominio());
            return Response.ok().build();
        }catch (Exception e) {
            throw new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}