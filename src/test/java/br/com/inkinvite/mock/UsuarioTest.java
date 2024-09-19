package br.com.inkinvite.mock;

import br.com.inkinvite.application.component.UsuarioComponent;
import br.com.inkinvite.domain.usuario.Usuario;
import br.com.inkinvite.domain.usuario.UsuarioJaExiste;
import br.com.inkinvite.mock.mock.LogMockService;
import br.com.inkinvite.mock.mock.usuario.UsuarioMockRepo;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class UsuarioTest {

    final UsuarioComponent component = new UsuarioComponent(
            new UsuarioMockRepo(),
            new LogMockService()
    );

    @Test
    void salvarUsuarioQueNaoExiste() {
        String email = "NaoExiste@gmail.com";
        Usuario usuario = criarUsuarioMock(email);
        assertDoesNotThrow(() -> component.criarUsuario(usuario));
    }

    @Test
    void salvarUsuarioQueExiste() {
        String email = "jaExiste@gmail.com";
        Usuario usuario = criarUsuarioMock(email);
        UsuarioJaExiste exception = assertThrows(
                UsuarioJaExiste.class,
                () -> component.criarUsuario(usuario)
        );
        assertEquals(UsuarioJaExiste.class, exception.getClass());
    }

    Usuario criarUsuarioMock(String email) {
        return Usuario.criar("Primeiro", "Ultimo", "login", email, "senha123", false);
    }

}
