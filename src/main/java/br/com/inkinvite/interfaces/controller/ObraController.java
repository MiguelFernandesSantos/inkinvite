package br.com.inkinvite.interfaces.controller;

import br.com.inkinvite.application.component.ObraComponent;
import br.com.inkinvite.application.repo.ObraRepo;
import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.application.service.StorageService;
import br.com.inkinvite.domain.obra.Obra;
import br.com.inkinvite.domain.obra.ObraNaoExiste;
import br.com.inkinvite.infrastructure.dto.ArquivoDto;
import br.com.inkinvite.infrastructure.dto.obra.CapitulosDto;
import br.com.inkinvite.infrastructure.dto.obra.ObraDto;
import br.com.inkinvite.application.service.ObraService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import java.util.List;

@RequestScoped
@Path("/api/obra")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ObraController {

    final ObraComponent component;

    public ObraController(ObraRepo obraRepo, ObraService obraService, StorageService storageService, LogService logService) {
        this.component = new ObraComponent(obraRepo, obraService, storageService, logService);
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

    @PUT
    @Path("{obra}/capitulo/{capitulo}/adicionar-arquivo")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response adicionarArquivoCapituloObra(@PathParam("obra") Integer obra, @PathParam("capitulo") Integer capitulo, @HeaderParam("mimeType") String mimeType, @MultipartForm ArquivoDto arquivo) {
        try {
            component.adicionarArquivoCapituloObra(obra, capitulo, arquivo.bytes, mimeType);
            return Response.ok().build();
        } catch (ObraNaoExiste e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
