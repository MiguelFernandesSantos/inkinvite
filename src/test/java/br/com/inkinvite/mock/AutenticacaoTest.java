package br.com.inkinvite.mock;

import br.com.inkinvite.application.component.AutenticacaoComponent;
import br.com.inkinvite.domain.DominioException;
import br.com.inkinvite.domain.usuario.UsuarioInvalido;
import br.com.inkinvite.domain.usuario.UsuarioNaoEncontrado;
import br.com.inkinvite.mock.mock.LogMockService;
import br.com.inkinvite.mock.mock.autenticacao.AutenticacaoMockService;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class AutenticacaoTest {

    final AutenticacaoComponent component = new AutenticacaoComponent(
            new AutenticacaoMockService(),
            new LogMockService()
    );

    @Test
    void autenticarUsuarioQueExiste() {
        String usuario = "emailQueExiste@gmail.com";
        String senha = "senha123";
        String token = component.recuperarLogin(usuario, senha);
        assertEquals("token" + usuario, token);
    }

    @Test
    void autenticarUsuarioQueNaoExiste() {
        String usuario = "naoExiste@gmail.com";
        String senha = "senha123";
        UsuarioNaoEncontrado exception = assertThrows(
                UsuarioNaoEncontrado.class,
                () -> component.recuperarLogin(usuario, senha)
        );
        assertEquals(UsuarioNaoEncontrado.class, exception.getClass());
    }

    @Test
    void autenticarComErroGenerico(){
        String usuario = "erroGenerico@gmail.com";
        String senha = "senha123";
        Exception exception = assertThrows(
                RuntimeException.class,
                () -> component.recuperarLogin(usuario, senha)
        );
        assertNotEquals(DominioException.class, exception.getClass());
    }

    @Test
    void autenticarUsuarioComSenhaIncorreta() {
        String usuario = "emailQueExiste@gmail.com";
        String senha = "senhaIncorreta";
        UsuarioInvalido exception = assertThrows(
                UsuarioInvalido.class,
                () -> component.recuperarLogin(usuario, senha)
        );
        assertEquals(UsuarioInvalido.class, exception.getClass());
    }

    @Test
    void esqueciSenhaUsuarioQueExiste() {
        String usuario = "emailQueExiste@gmail.com";
        assertDoesNotThrow(() -> component.esqueciSenha(usuario));
    }

    @Test
    void esqueciSenhaUsuarioQueNaoExiste() {
        String usuario = "naoExiste@gmail.com";
        UsuarioNaoEncontrado exception = assertThrows(
                UsuarioNaoEncontrado.class,
                () -> component.esqueciSenha(usuario)
        );
        assertEquals(UsuarioNaoEncontrado.class, exception.getClass());
    }

    @Test
    void esqueciSenhaComErroGenerico() {
        String usuario = "erroGenerico@gmail.com";
        Exception exception = assertThrows(
                RuntimeException.class,
                () -> component.esqueciSenha(usuario)
        );
        assertNotEquals(DominioException.class, exception.getClass());
    }

}