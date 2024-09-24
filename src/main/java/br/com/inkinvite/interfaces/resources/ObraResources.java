package br.com.inkinvite.interfaces.resources;

import java.util.List;

import br.com.inkinvite.domain.usuario.UsuarioNaoEncontrado;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.openapi.annotations.Operation;

import br.com.inkinvite.application.component.ObraComponent;
import br.com.inkinvite.application.repo.ObraRepo;
import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.application.service.ObraService;
import br.com.inkinvite.application.service.StorageService;
import br.com.inkinvite.domain.obra.Capitulo;
import br.com.inkinvite.domain.obra.CapituloNaoExiste;
import br.com.inkinvite.domain.obra.NaoPermiteEditarObra;
import br.com.inkinvite.domain.obra.Obra;
import br.com.inkinvite.domain.obra.ObraCompleta;
import br.com.inkinvite.domain.obra.ObraNaoExiste;
import br.com.inkinvite.infrastructure.dto.obra.CapituloDto;
import br.com.inkinvite.infrastructure.dto.obra.ObraCompletaDto;
import br.com.inkinvite.infrastructure.dto.obra.ObraDto;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@RequestScoped
@Path("/api/obra")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class ObraResources {

    @Inject
    @Claim("email")
    String email;

    final ObraComponent component;

    public ObraResources(ObraRepo obraRepo, ObraService obraService, StorageService storageService, LogService logService) {
        this.component = new ObraComponent(obraRepo, obraService, storageService, logService);
    }


    @POST
    @Operation(summary = "Cria uma nova obra", description = "Grava no banco de dados o cabecalho da obra.")
    @RolesAllowed("autor")
    public Response criarObra(ObraDto obra) {
        try {
            component.criarObra(obra.paraDominio(), email);
            return Response.ok().build();
        } catch (UsuarioNaoEncontrado e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    @Path("/paginado")
    @Operation(summary = "Busca de forma resumida as obras que existem", description = "Busca no banco de dados as obras que existem e estão de acordo com os parametros.")
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
    @Operation(summary = "Busca uma obra especifica", description = "Busca no banco de dados a obra de numero passada no PathParam junto de seus capitulos.")
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
    @Operation(summary = "Edita uma obra especifica", description = "Altera no banco de dados o cabecalho da obra passada como PathParam.")
    @RolesAllowed("autor")
    public Response editarObra(@PathParam("numero") Integer numeroObra, ObraDto obra) {
        try {
            component.editarObra(numeroObra, obra.paraDominio(), email);
            return Response.ok().build();
        } catch (ObraNaoExiste e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        } catch (NaoPermiteEditarObra e) {
            throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
        } catch (Exception e) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @DELETE
    @Path("/{numero}")
    @Operation(summary = "Deleta uma obra especifica", description = "Deleta do banco de dados a obra passada como PathParam junto de seus capitulos.")
    @RolesAllowed("autor")
    public Response deletarObra(@PathParam("numero") Integer numeroObra) {
        try {
            component.deletarObra(numeroObra, email);
            return Response.ok().build();
        } catch (ObraNaoExiste e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        } catch (NaoPermiteEditarObra e) {
            throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
        } catch (Exception e) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    @Path("{obra}/novo-capitulo")
    @Operation(summary = "Adiciona um novo capitulo em uma obra", description = "Adiciona no banco de dados um novo capitulo para a obra informada.")
    @RolesAllowed("autor")
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
    @Operation(summary = "Obtem o capitulo de uma obra", description = "Busca no banco de dados o capitulo de numero passado no PathParam da obra passada no PathParam junto do arquivo relacionado salvo no storage.")
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
