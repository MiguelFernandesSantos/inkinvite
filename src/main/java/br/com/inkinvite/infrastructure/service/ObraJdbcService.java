package br.com.inkinvite.infrastructure.service;

import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.application.service.ObraService;
import br.com.inkinvite.domain.DominioException;
import br.com.inkinvite.domain.objetosDeValor.DataHora;
import br.com.inkinvite.domain.obra.Capitulo;
import br.com.inkinvite.domain.obra.Capitulos;
import br.com.inkinvite.domain.obra.Obra;
import br.com.inkinvite.domain.obra.ObraNaoExiste;
import br.com.inkinvite.infrastructure.repo.obra.ObraQueries;
import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static br.com.inkinvite.infrastructure.repo.MySqlConnection.obterStatement;
import static br.com.inkinvite.infrastructure.repo.obra.ObraFactory.mapearObras;

@ApplicationScoped
public class ObraJdbcService extends ObraQueries implements ObraService {
    @Inject
    AgroalDataSource banco;

    @Inject
    LogService logService;

    @Override
    public List<Obra> obterObrasMaisRecentes(Integer ultimaObra) {
        try (Connection connection = banco.getConnection(); PreparedStatement statement = obterStatement(connection, QUERY_OBTER_OBRAS_MAIS_RECENTES)) {
            statement.setObject(1, ultimaObra);
            ResultSet resultSet = statement.executeQuery();
            List<Obra> obras = mapearObras(resultSet);
            resultSet.close();
            return obras;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void verificarExistencia(Integer numero) {
        try (Connection conexao = banco.getConnection(); PreparedStatement statement = obterStatement(conexao, QUERY_VERIFICAR_EXISTENCIA_OBRA)) {
            statement.setInt(1, numero);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Integer quantidade = resultSet.getInt("quantidade");
            resultSet.close();
            if (quantidade == 0) {
                throw new ObraNaoExiste();
            }
        } catch (DominioException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void novoCapitulo(Capitulo capitulo) {
        try (Connection conexao = banco.getConnection(); PreparedStatement statement = obterStatement(conexao, QUERY_INSERIR_NOVO_CAPITULO)) {
            statement.setInt(1, capitulo.getObra());
            statement.setString(2, capitulo.getTitulo());
            statement.setString(3, DataHora.agora().formatar("yyyy-MM-dd HH:mm:ss"));
            statement.setInt(4, capitulo.getObra());
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void ordenarCapitulos(Integer obra, Capitulos capitulos) {
        try (Connection conexao = banco.getConnection(); PreparedStatement statementOrdem = obterStatement(conexao, QUERY_ATUALIZAR_ORDINAL_OBRA)) {
            carregarBatch(capitulos, statementOrdem);
            statementOrdem.executeBatch();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void salvarMimeTypeArquivoCapitulo(Integer obra, Integer capitulo, String mimeType) {
        try (Connection conexao = banco.getConnection(); PreparedStatement statement = obterStatement(conexao, QUERY_ALTERAR_MIMETYPE_CAPITULO)) {
            statement.setString(1, mimeType);
            statement.setInt(2, obra);
            statement.setInt(3, capitulo);
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void carregarBatch(Capitulos capitulos, PreparedStatement statement) throws SQLException {
        for (Capitulo capitulo : capitulos.obterCapitulos()) {
            statement.setInt(1, capitulo.getNumeroOrdinal());
            statement.setInt(2, Integer.parseInt(capitulo.getId()));
            statement.addBatch();
        }
    }

}
