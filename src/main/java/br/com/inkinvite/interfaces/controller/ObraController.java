package br.com.inkinvite.interfaces.controller;

import br.com.inkinvite.application.component.ObraComponent;
import br.com.inkinvite.application.repo.ObraRepo;
import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.domain.obra.Obra;
import br.com.inkinvite.domain.obra.ObraNaoExiste;
import br.com.inkinvite.infrastructure.dto.obra.CapituloDto;
import br.com.inkinvite.infrastructure.dto.obra.CapitulosDto;
import br.com.inkinvite.infrastructure.dto.obra.ObraDto;
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

    @POST
    @Path("{obra}/novo-capitulo")
    public Response novoCapitulo(@PathParam("obra") Integer obra, CapituloDto capituloDto) {
        try {
            component.novoCapitulo(capituloDto.paraDominio());
            return Response.ok().build();
        } catch (ObraNaoExiste e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Path("{obra}/ordenar-capitulos")
    public Response ordenarCapitulos(@PathParam("obra") Integer obra, CapitulosDto capitulos) {
        try {
            component.ordenarCapitulos(obra, capitulos.paraDominio());
            return Response.ok().build();
        } catch (ObraNaoExiste e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
