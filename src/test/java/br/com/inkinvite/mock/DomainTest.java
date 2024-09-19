package br.com.inkinvite.mock;

import br.com.inkinvite.domain.obra.Obra;
import br.com.inkinvite.domain.usuario.Usuario;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class DomainTest {

    @Test
    void criarObra() {
        Obra obra = Obra.criar(1, "titulo", "descricao");
        assertEquals(1, obra.getNumeroAutor());
    }

    @Test
    void carregarObra() {
        Obra obra = Obra.carregar(1, 1, "autor", "titulo", "descricao", null, null);
        assertEquals(1, obra.getNumeroAutor());
    }

    @Test
    void criarUsuario() {
        Usuario usuario = Usuario.criar("primeiroNome", "segundoNome", "login", "email", "senha", true);
        assertEquals("primeiroNome", usuario.getPrimeiroNome());
    }

    @Test
    void carregarUsuario() {
        Usuario usuario = Usuario.carregar("primeiroNome", "segundoNome", "login", "email", "senha");
        assertEquals("primeiroNome", usuario.getPrimeiroNome());
    }

}
