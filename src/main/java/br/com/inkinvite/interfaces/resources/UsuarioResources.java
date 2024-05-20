package br.com.inkinvite.interfaces.resources;

import br.com.inkinvite.application.service.UsuarioService;
import br.com.inkinvite.infrastructure.dto.UsuarioDto;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@RequestScoped
@Path("/api/usuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResources {

    @Inject
    UsuarioService usuarioService;

    @POST
    public Response criarUsuario(UsuarioDto usuario) {
        return usuarioService.criarUsuario(usuario);
    }
}