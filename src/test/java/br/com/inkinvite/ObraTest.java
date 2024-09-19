package br.com.inkinvite;

import br.com.inkinvite.application.component.ObraComponent;
import br.com.inkinvite.domain.objetosDeValor.DataHora;
import br.com.inkinvite.domain.obra.*;
import br.com.inkinvite.domain.usuario.UsuarioNaoEncontrado;
import br.com.inkinvite.infrastructure.repo.obra.ObraJdbcRepo;
import br.com.inkinvite.infrastructure.service.LogServiceImpl;
import br.com.inkinvite.infrastructure.service.ObraJdbcService;
import br.com.inkinvite.infrastructure.service.StorageS3Service;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static br.com.inkinvite.infrastructure.repo.usuario.UsuarioQueries.QUERY_CRIAR_USUARIO;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class ObraTest {

    @Inject
    AgroalDataSource banco;

    final ObraComponent component;

    final String emailCriador = "usuario@test";

    public ObraTest(ObraJdbcRepo repo, ObraJdbcService obraJdbcService, StorageS3Service storageS3Service, LogServiceImpl logService) {
        component = new ObraComponent(
                repo,
                obraJdbcService,
                storageS3Service,
                logService
        );
    }

    @BeforeEach
    void reiniciarConfiguracao() throws SQLException {
        try (Connection conexao = banco.getConnection(); Statement statement = conexao.createStatement()) {
            statement.execute("DELETE FROM capitulo");
            statement.execute("DELETE FROM obra");
            statement.execute("DELETE FROM usuario");
            criarUsuarioTest(conexao);
        }
    }

    private void criarUsuarioTest(Connection conexao) throws SQLException {
        PreparedStatement preparedStatement = conexao.prepareStatement(QUERY_CRIAR_USUARIO);
        preparedStatement.setString(1, "Primeiro nome");
        preparedStatement.setString(2, "Ultimo nome");
        preparedStatement.setString(3, "meu login");
        preparedStatement.setString(4, emailCriador);
        preparedStatement.setString(5, DataHora.agora().formatar("yyyy-MM-dd HH:mm:ss"));
        preparedStatement.execute();
    }

    @Test
    void criarObraQueAutorExiste() {
        Obra obra = criarObraTest();
        assertDoesNotThrow(() -> component.criarObra(obra, emailCriador));
    }

    @Test
    void criarObraQueAutorNaoExiste() {
        Obra obra = criarObraTest();
        UsuarioNaoEncontrado exception = assertThrows(
                UsuarioNaoEncontrado.class,
                () -> component.criarObra(obra, "naoExiste@gmail.com")
        );
        assertEquals(UsuarioNaoEncontrado.class, exception.getClass());
    }

    @Test
    void deletarObraQueNaoExiste() {
        ObraNaoExiste exception = assertThrows(
                ObraNaoExiste.class,
                () -> component.deletarObra(1, emailCriador)
        );
        assertEquals(ObraNaoExiste.class, exception.getClass());
    }

    @Test
    void obterObraQueNaoExiste() {
        ObraNaoExiste exception = assertThrows(
                ObraNaoExiste.class,
                () -> component.obterObra(1)
        );
        assertEquals(ObraNaoExiste.class, exception.getClass());
    }

    @Test
    void obterObrasMaisRecentes() {
        criarObraQueAutorExiste();
        criarObraQueAutorExiste();
        List<Obra> obras = component.buscarObras(0, "", 5);
        assertEquals(2, obras.size());
    }

    @Test
    void obterCapiculoDeObraQueNaoExiste() {
        Integer numeroCapitulo = 1;
        CapituloNaoExiste exception = assertThrows(
                CapituloNaoExiste.class,
                () -> component.obterCapitulo(1, numeroCapitulo)
        );
        assertEquals(CapituloNaoExiste.class, exception.getClass());
    }


    private static Obra criarObraTest() {
        return Obra.criar(1, "titulo", "descricao");
    }

}
