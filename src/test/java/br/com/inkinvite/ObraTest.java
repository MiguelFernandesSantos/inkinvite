package br.com.inkinvite;

import br.com.inkinvite.application.component.ObraComponent;
import br.com.inkinvite.domain.obra.*;
import br.com.inkinvite.mock.LogMockService;
import br.com.inkinvite.mock.obra.ObraMockRepo;
import br.com.inkinvite.mock.obra.ObraMockService;
import br.com.inkinvite.mock.obra.StorageMockService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ObraTest {

    final ObraComponent component = new ObraComponent(
            new ObraMockRepo(),
            new ObraMockService(),
            new StorageMockService(),
            new LogMockService()
    );

    @Test
    public void testObterObraQueExiste() {
        Integer numeroObra = 200;
        ObraCompleta obra = component.obterObra(numeroObra);
        assertEquals(numeroObra.toString(), obra.getId());
    }

    @Test
    public void testObterObraNaoExistenteLancaExcecao() {
        Integer numeroObra = 404;
        ObraNaoExiste exception = assertThrows(
                ObraNaoExiste.class,
                () -> component.obterObra(numeroObra)
        );
        assertEquals(ObraNaoExiste.class, exception.getClass());
    }

    @Test
    public void deletarObraQueExiste() {
        Integer numeroObra = 200;
        String autor = "dono@gmail.com.br";
        assertDoesNotThrow(() -> component.deletarObra(numeroObra, autor));
    }

    @Test
    public void deletarObraQueNaoExiste() {
        Integer numeroObra = 404;
        String autor = "dono@gmail.com.br";
        ObraNaoExiste exception = assertThrows(
                ObraNaoExiste.class,
                () -> component.deletarObra(numeroObra, autor)
        );
        assertEquals(ObraNaoExiste.class, exception.getClass());
    }

    @Test
    public void deletarObraQueExisteMasNaoCriou() {
        Integer numeroObra = 200;
        String autor = "naoSouCriador@gmail.com.br";
        NaoPermiteEditarObra exception = assertThrows(
                NaoPermiteEditarObra.class,
                () -> component.deletarObra(numeroObra, autor)
        );
        assertEquals(NaoPermiteEditarObra.class, exception.getClass());
    }

    @Test
    public void criarCapituloEmObraQueExiste() {
        Capitulo capitulo = new Capitulo();
        capitulo.setObra(200);
        assertDoesNotThrow(() -> component.novoCapitulo(capitulo));
    }

    @Test
    public void criarCapituloEmObraQueNaoExiste() {
        Capitulo capitulo = new Capitulo();
        capitulo.setObra(404);
        ObraNaoExiste exception = assertThrows(
                ObraNaoExiste.class,
                () -> component.novoCapitulo(capitulo)
        );
        assertEquals(ObraNaoExiste.class, exception.getClass());
    }

    @Test
    public void obterObrasMaisRecentes() {
        Integer quantidade = 5;
        List<Obra> obras = component.obterObrasMaisRecentes(0);
        assertEquals(quantidade, obras.size());
    }

    @Test
    public void editarObraQueExiste() {
        Integer numeroObra = 200;
        Obra obra = Obra.criar(1, "titulo", "descricao");
        String autor = "dono@gmail.com.br";
        assertDoesNotThrow(() -> component.editarObra(numeroObra, obra, autor));
    }

    @Test
    public void  editarObraQueNaoExiste(){
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
    public void editarObraQueExisteMaisNaoSouAutor() {
        Integer numeroObra = 200;
        String autor = "naoSouCriador@gmail.com.br";
        Obra obra = Obra.criar(1, "titulo", "descricao");
        NaoPermiteEditarObra exception = assertThrows(
                NaoPermiteEditarObra.class,
                () -> component.editarObra(numeroObra, obra, autor)
        );
        assertEquals(NaoPermiteEditarObra.class, exception.getClass());
    }
}
