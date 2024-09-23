package br.com.inkinvite.mock;

import br.com.inkinvite.application.component.ObraComponent;
import br.com.inkinvite.domain.DominioException;
import br.com.inkinvite.domain.obra.*;
import br.com.inkinvite.infrastructure.dto.obra.ObraCompletaDto;
import br.com.inkinvite.infrastructure.dto.obra.ObraDto;
import br.com.inkinvite.mock.mock.LogMockService;
import br.com.inkinvite.mock.mock.obra.ObraMockRepo;
import br.com.inkinvite.mock.mock.obra.ObraMockService;
import br.com.inkinvite.mock.mock.obra.StorageMockService;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class ObraTest {

    final ObraComponent component = new ObraComponent(
            new ObraMockRepo(),
            new ObraMockService(),
            new StorageMockService(),
            new LogMockService()
    );

    @Test
    void obterObraQueExiste() {
        Integer numeroObra = 200;
        ObraCompleta obra = component.obterObra(numeroObra);
        assertEquals(numeroObra.toString(), obra.getId());
    }

    @Test
    void obterObraNaoExistenteLancaExcecao() {
        Integer numeroObra = 404;
        ObraNaoExiste exception = assertThrows(
                ObraNaoExiste.class,
                () -> component.obterObra(numeroObra)
        );
        assertEquals(ObraNaoExiste.class, exception.getClass());
    }

    @Test
    void obterObraComErroGenerico() {
        Integer numeroObra = 500;
        Exception exception = assertThrows(
                Exception.class,
                () -> component.obterObra(numeroObra)
        );
        assertNotEquals(DominioException.class, exception.getClass());
    }

    @Test
    void deletarObraQueExiste() {
        Integer numeroObra = 200;
        String autor = "dono@gmail.com.br";
        assertDoesNotThrow(() -> component.deletarObra(numeroObra, autor));
    }

    @Test
    void deletarObraQueNaoExiste() {
        Integer numeroObra = 404;
        String autor = "dono@gmail.com.br";
        ObraNaoExiste exception = assertThrows(
                ObraNaoExiste.class,
                () -> component.deletarObra(numeroObra, autor)
        );
        assertEquals(ObraNaoExiste.class, exception.getClass());
    }

    @Test
    void deletarObraComErroGenerico() {
        Integer numeroObra = 500;
        String autor = "dono@gmail.com.br";
        Exception exception = assertThrows(
                Exception.class,
                () -> component.deletarObra(numeroObra, autor)
        );
        assertNotEquals(DominioException.class, exception.getClass());
    }

    @Test
    void deletarObraQueExisteMasNaoCriou() {
        Integer numeroObra = 200;
        String autor = "naoSouCriador@gmail.com.br";
        NaoPermiteEditarObra exception = assertThrows(
                NaoPermiteEditarObra.class,
                () -> component.deletarObra(numeroObra, autor)
        );
        assertEquals(NaoPermiteEditarObra.class, exception.getClass());
    }

    @Test
    void criarCapituloEmObraQueExiste() {
        Capitulo capitulo = new Capitulo();
        capitulo.setObra(200);
        assertDoesNotThrow(() -> component.novoCapitulo(capitulo));
    }

    @Test
    void criarCapituloEmObraQueNaoExiste() {
        Capitulo capitulo = new Capitulo();
        capitulo.setObra(404);
        ObraNaoExiste exception = assertThrows(
                ObraNaoExiste.class,
                () -> component.novoCapitulo(capitulo)
        );
        assertEquals(ObraNaoExiste.class, exception.getClass());
    }

    @Test
    void criarCapituloComErroGenerico() {
        Capitulo capitulo = new Capitulo();
        capitulo.setObra(500);
        Exception exception = assertThrows(
                Exception.class,
                () -> component.novoCapitulo(capitulo)
        );
        assertNotEquals(DominioException.class, exception.getClass());
    }

    @Test
    void obterObrasMaisRecentes() {
        Integer quantidade = 5;
        List<Obra> obras = component.obterObrasMaisRecentes(0);
        assertEquals(quantidade, obras.size());
    }

    @Test
    void obterObrasMaisRecentesComErroGenerico() {
        Integer quantidade = 500;
        Exception exception = assertThrows(
                Exception.class,
                () -> component.obterObrasMaisRecentes(500)
        );
        assertNotEquals(DominioException.class, exception.getClass());
    }

    @Test
    void editarObraQueExiste() {
        Integer numeroObra = 200;
        Obra obra = Obra.criar(1, "titulo", "descricao");
        String autor = "dono@gmail.com.br";
        assertDoesNotThrow(() -> component.editarObra(numeroObra, obra, autor));
    }

    @Test
    void editarObraQueNaoExiste() {
        Integer numeroObra = 404;
        Obra obra = Obra.criar(1, "titulo", "descricao");
        String autor = "dono@gmail.com.br";
        ObraNaoExiste exception = assertThrows(
                ObraNaoExiste.class,
                () -> component.editarObra(numeroObra, obra, autor)
        );
        assertEquals(ObraNaoExiste.class, exception.getClass());
    }

    @Test
    void editarObraComErroGenerico() {
        Integer numeroObra = 500;
        Obra obra = Obra.criar(1, "titulo", "descricao");
        String autor = "dono@gmail.com.br";
        Exception exception = assertThrows(
                Exception.class,
                () -> component.editarObra(numeroObra, obra, autor)
        );
        assertNotEquals(DominioException.class, exception.getClass());
    }

    @Test
    void editarObraQueExisteMaisNaoSouAutor() {
        Integer numeroObra = 200;
        String autor = "naoSouCriador@gmail.com.br";
        Obra obra = Obra.criar(1, "titulo", "descricao");
        NaoPermiteEditarObra exception = assertThrows(
                NaoPermiteEditarObra.class,
                () -> component.editarObra(numeroObra, obra, autor)
        );
        assertEquals(NaoPermiteEditarObra.class, exception.getClass());
    }

    @Test
    void obterStatusObraDeId() {
        StatusObra status = StatusObra.fromId(1);
        assertEquals(StatusObra.CRIADA, status);
    }

    @Test
    void obterCapituloDeObra() {
        Integer numeroObra = 200;
        Integer numeroCapitulo = 1;
        Capitulo capitulo = component.obterCapitulo(numeroObra, numeroCapitulo);
        assertEquals(numeroCapitulo.toString(), capitulo.getId());
    }

    @Test
    void obterCapiculoDeObraQueNaoExiste() {
        Integer numeroObra = 404;
        Integer numeroCapitulo = 1;
        ObraNaoExiste exception = assertThrows(
                ObraNaoExiste.class,
                () -> component.obterCapitulo(numeroObra, numeroCapitulo)
        );
        assertEquals(ObraNaoExiste.class, exception.getClass());
    }

    @Test
    void obterCapituloQueNaoExisteDeObra() {
        Integer numeroObra = 200;
        Integer numeroCapitulo = 404;
        CapituloNaoExiste exception = assertThrows(
                CapituloNaoExiste.class,
                () -> component.obterCapitulo(numeroObra, numeroCapitulo)
        );
        assertEquals(CapituloNaoExiste.class, exception.getClass());
    }

    @Test
    void obterCapituloComErroGenerico() {
        Integer numeroObra = 500;
        Integer numeroCapitulo = 1;
        Exception exception = assertThrows(
                Exception.class,
                () -> component.obterCapitulo(numeroObra, numeroCapitulo)
        );
        assertNotEquals(DominioException.class, exception.getClass());
    }

    @Test
    void obterObras() {
        Integer ultimaObra = 0;
        String pesquisa = "titulo";
        Integer limite = 5;
        List<Obra> obras = component.buscarObras(ultimaObra, pesquisa, limite);
        assertEquals(limite, obras.size());
    }

    @Test
    void buscarObrasComErroGenerico() {
        Integer ultimaObra = 500;
        String pesquisa = "erroGenerico";
        Integer limite = 5;
        Exception exception = assertThrows(
                Exception.class,
                () -> component.buscarObras(ultimaObra, pesquisa, limite)
        );
        assertNotEquals(DominioException.class, exception.getClass());
    }

    @Test
    void obterNenhumaObra() {
        Integer ultimaObra = 0;
        String pesquisa = "naoExiste";
        Integer limite = 0;
        List<Obra> obras = component.buscarObras(ultimaObra, pesquisa, limite);
        assertEquals(0, obras.size());
    }

    @Test
    void obraSimplesParaDominio() {
        Integer ultimaObra = 0;
        String pesquisa = "titulo";
        Integer limite = 5;
        List<Obra> obras = component.buscarObras(ultimaObra, pesquisa, limite);
        List<ObraDto> dtos = obras.stream().map(ObraDto::deDominio).toList();
        obras = dtos.stream().map(ObraDto::paraDominio).toList();
        assertEquals(limite, obras.size());
    }

    @Test
    void obraCompletadominioParaDto() {
        Integer numeroObra = 200;
        ObraCompleta obra = component.obterObra(numeroObra);
        ObraCompletaDto dto = ObraCompletaDto.deDominio(obra);
        assertEquals(obra.getId(), dto.numero.toString());
    }

    @Test
    void adicionarArquivoCapituloObra() {
        Integer numeroObra = 200;
        Integer numeroCapitulo = 1;
        byte[] bytes = "Hello world".getBytes();
        assertDoesNotThrow(() -> component.adicionarArquivoCapituloObra(numeroObra, numeroCapitulo, bytes, "png"));
    }

    @Test
    void adicionarArquivoCapituloObraEmCapituloQueNaoExiste() {
        Integer numeroObra = 200;
        Integer numeroCapitulo = 404;
        byte[] bytes = "Hello world".getBytes();
        CapituloNaoExiste exception = assertThrows(
                CapituloNaoExiste.class,
                () -> component.adicionarArquivoCapituloObra(numeroObra, numeroCapitulo, bytes, "png")
        );
        assertEquals(CapituloNaoExiste.class, exception.getClass());
    }

    @Test
    void adicionarArquivoCapituloObraComErroGenerico() {
        Integer numeroObra = 500;
        Integer numeroCapitulo = 1;
        byte[] bytes = "".getBytes();
        Exception exception = assertThrows(
                Exception.class,
                () -> component.adicionarArquivoCapituloObra(numeroObra, numeroCapitulo, bytes, "png")
        );
        assertNotEquals(DominioException.class, exception.getClass());
    }

    @Test
    void ordenarCapitulos() {
        Integer numeroObra = 200;
        ObraCompleta obra = component.obterObra(numeroObra);
        assertDoesNotThrow(() -> component.ordenarCapitulos(obra.numero, obra.obterCapitulos()));
    }

    @Test
    void ordenarCapitulosEmObraQueNaoExiste(){
        Integer numeroObra = 404;
        Capitulos capitulos = new Capitulos();
        ObraNaoExiste exception = assertThrows(
                ObraNaoExiste.class,
                () -> component.ordenarCapitulos(numeroObra, capitulos)
        );
        assertEquals(ObraNaoExiste.class, exception.getClass());
    }

    @Test
    void ordenarCapitulosComErroGenerico(){
        Integer numeroObra = 500;
        Capitulos capitulos = new Capitulos();
        Exception exception = assertThrows(
                Exception.class,
                () -> component.ordenarCapitulos(numeroObra, capitulos)
        );
        assertNotEquals(DominioException.class, exception.getClass());
    }

}
