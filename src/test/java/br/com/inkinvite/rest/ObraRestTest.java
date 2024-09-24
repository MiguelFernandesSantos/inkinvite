package br.com.inkinvite.rest;

import br.com.inkinvite.infrastructure.dto.obra.AvaliacaoDto;
import br.com.inkinvite.infrastructure.dto.obra.CabecalhoObraDto;
import br.com.inkinvite.infrastructure.dto.obra.CapituloDto;
import br.com.inkinvite.infrastructure.dto.obra.ObraDto;
import br.com.inkinvite.interfaces.resources.ObraResources;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestHTTPEndpoint(ObraResources.class)
public class ObraRestTest {

    @Test
    @TestSecurity(authorizationEnabled = false)
    void buscarObrasComPermissao() {
        given()
                .contentType("application/json")
                .queryParam("ultimaObra", 0)
                .queryParam("pesquisa", "")
                .queryParam("limite", 10)
                .when().post("paginado")
                .then()
                .statusCode(200);
    }

    @Test
    void buscarObrasSemPermissao() {
        given()
                .contentType("application/json")
                .queryParam("ultimaObra", 0)
                .queryParam("pesquisa", "")
                .queryParam("limite", 10)
                .when().post("paginado")
                .then()
                .statusCode(401);
    }

    @Test
    @TestSecurity(authorizationEnabled = false)
    void criarObraAutorNaoExiste() {
        given()
                .contentType("application/json")
                .body(instanciaObra())
                .when().post()
                .then()
                .statusCode(404);
    }

    @Test
    @TestSecurity(authorizationEnabled = false)
    void obterObraQueNaoExiste() {
        given()
                .contentType("application/json")
                .pathParam("numero", 1)
                .when().get("{numero}")
                .then()
                .statusCode(404);
    }

    @Test
    @TestSecurity(authorizationEnabled = false)
    void editarObraQueNaoExiste() {
        given()
                .contentType("application/json")
                .pathParam("numero", 1)
                .body(instanciaObra())
                .when().put("{numero}")
                .then()
                .statusCode(404);
    }

    @Test
    @TestSecurity(authorizationEnabled = false)
    void deletarObraQueNaoExiste() {
        given()
                .contentType("application/json")
                .pathParam("numero", 1)
                .when().delete("{numero}")
                .then()
                .statusCode(404);
    }

    @Test
    @TestSecurity(authorizationEnabled = false)
    void novoCapituloEmObraQueNaoExiste() {
        given()
                .contentType("application/json")
                .pathParam("obra", 1)
                .body(instanciaCapitulo())
                .when().post("{obra}/novo-capitulo")
                .then()
                .statusCode(404);
    }

    @Test
    @TestSecurity(authorizationEnabled = false)
    void obterCapituloQueNaoExiste() {
        given()
                .contentType("application/json")
                .pathParam("obra", 1)
                .pathParam("capitulo", 1)
                .when().get("{obra}/capitulo/{capitulo}")
                .then()
                .statusCode(404);
    }

    ObraDto instanciaObra() {
        ObraDto obra = new ObraDto();
        CabecalhoObraDto dto = new CabecalhoObraDto();
        dto.numeroAutor = 1;
        dto.nomeAutor = "Autor";
        dto.titulo = "Obra de teste";
        dto.descricao = "Obra de teste";
        obra.cabecalho = dto;
        return obra;
    }

    CapituloDto instanciaCapitulo() {
        CapituloDto capitulo = new CapituloDto();
        capitulo.numero = 1;
        capitulo.obra = 1;
        capitulo.titulo = "Capitulo de teste";
        capitulo.dataCriacao = "2021-01-01";
        capitulo.numeroOrdinal = 1;
        AvaliacaoDto avaliacaoDto = new AvaliacaoDto();
        avaliacaoDto.avaliacao = BigDecimal.TEN;
        capitulo.avaliacao = avaliacaoDto;
        return capitulo;
    }

}
