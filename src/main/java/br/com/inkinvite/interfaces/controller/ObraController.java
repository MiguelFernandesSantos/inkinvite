package br.com.inkinvite.interfaces.controller;

import br.com.inkinvite.application.component.ObraComponent;
import br.com.inkinvite.application.repo.ObraRepo;
import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.domain.obra.Obra;
import br.com.inkinvite.infrastructure.dto.ObraDto;
import br.com.inkinvite.application.service.ObraService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@RequestScoped
@Path("/api/obra")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ObraController {

    final ObraComponent component;

    public ObraController(ObraRepo obraRepo, ObraService obraService, LogService logService) {
        this.component = new ObraComponent(obraRepo, obraService, logService);
    }

    @GET
    @Path("/mais-recentes")
    public Response obterObrasMaisRecentes(@QueryParam("ultimaObra") Integer ultimaObra) {
        try {
            List<Obra> obras = component.obterObrasMaisRecentes(ultimaObra);
            List<ObraDto> dtos = obras.stream().map(ObraDto::deDominio).toList();
            return Response.ok(dtos).build();
        } catch (Exception e) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
