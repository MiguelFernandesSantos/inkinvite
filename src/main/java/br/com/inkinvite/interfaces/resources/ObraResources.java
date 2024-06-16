package br.com.inkinvite.interfaces.resources;

import br.com.inkinvite.application.component.ObraComponent;
import br.com.inkinvite.application.repo.ObraRepo;
import br.com.inkinvite.application.service.StorageService;
import br.com.inkinvite.domain.obra.*;
import br.com.inkinvite.infrastructure.dto.obra.CapituloDto;
import br.com.inkinvite.infrastructure.dto.obra.ObraCompletaDto;
import br.com.inkinvite.infrastructure.dto.obra.ObraDto;
import br.com.inkinvite.application.service.ObraService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import br.com.inkinvite.application.service.LogService;

import java.util.List;

@RequestScoped
@Path("/api/obra")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ObraResources {

    final ObraComponent component;

    public ObraResources(ObraRepo obraRepo, ObraService obraService, StorageService storageService, LogService logService) {
        this.component = new ObraComponent(obraRepo, obraService, storageService, logService);
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

    @POST
    public Response buscarObras(@QueryParam("ultimaObra") Integer ultimaObra, @QueryParam("pesquisa") String pesquisa, @QueryParam("limite") Integer limite) {
        try {
            List<Obra> obras = component.buscarObras(ultimaObra, pesquisa, limite);
            List<ObraDto> dtos = obras.stream().map(ObraDto::deDominio).toList();
            return Response.ok(dtos).build();
        } catch (Exception e) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("/{numero}")
    public Response obterObra(@PathParam("numero") Integer numero) {
        try {
            ObraCompleta obra = component.obterObra(numero);
            ObraCompletaDto dto = ObraCompletaDto.deDominio(obra);
            return Response.ok(dto).build();
        } catch (ObraNaoExiste e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
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
        } catch (ObraNaoExiste e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
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
        } catch (ObraNaoExiste e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
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

    @GET
    @Path("{obra}/capitulo/{capitulo}")
    public Response obterCapitulo(@PathParam("obra") Integer obra, @PathParam("capitulo") Integer numeroCapitulo) {
        try {
            Capitulo capitulo = component.obterCapitulo(obra, numeroCapitulo);
            CapituloDto capituloDto = CapituloDto.deDominio(capitulo);
            return Response.ok(capituloDto).build();
        } catch (CapituloNaoExiste e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

}
