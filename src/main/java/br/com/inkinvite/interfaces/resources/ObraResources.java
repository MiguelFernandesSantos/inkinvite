package br.com.inkinvite.interfaces.resources;

import br.com.inkinvite.application.component.ObraComponent;
import br.com.inkinvite.application.repo.ObraRepo;
import br.com.inkinvite.infrastructure.dto.ObraDto;
import br.com.inkinvite.application.service.ObraService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import br.com.inkinvite.application.service.LogService;

@RequestScoped
@Path("/api/obra")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ObraResources {

    final ObraComponent component;

    public ObraResources(ObraRepo obraRepo, ObraService obraService, LogService logService) {
        this.component = new ObraComponent(obraRepo, obraService, logService);
    }


    @POST
    public Response criarObra(ObraDto obra) {
        try {
            component.criarObra(obra.paraDominio());
            return Response.ok().build();
        } catch (Exception e) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("/{numero}")
    public Response obterObra(@PathParam("numero") Integer numero) {
        try {
            // TODO obter obra
            return Response.ok().build();
        } catch (Exception e) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Path("/{numero}")
    public Response editarObra(@PathParam("numero") Integer numeroObra, ObraDto obra) {
        try {
            component.editarObra(numeroObra, obra.paraDominio());
            return Response.ok().build();
        } catch (Exception e) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @DELETE
    @Path("/{numero}")
    public Response deletarObra(@PathParam("numero") Integer numeroObra) {
        try {
            component.deletarObra(numeroObra);
            return Response.ok().build();
        } catch (Exception e) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

}
