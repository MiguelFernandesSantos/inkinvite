package br.com.inkinvite.infrastructure.repo.obra;

import br.com.inkinvite.application.repo.ObraRepo;
import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.domain.DominioException;
import br.com.inkinvite.domain.obra.Obra;
import br.com.inkinvite.domain.obra.ObraNaoExiste;
import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static br.com.inkinvite.infrastructure.repo.MySqlConnection.obterStatement;

@ApplicationScoped
public class ObraJdbcRepo extends ObraQueries implements ObraRepo {

    @Inject
    AgroalDataSource banco;

    @Inject
    LogService logService;

    @Override
    @Transactional
    public void salvar(Obra obra) {
        try (Connection conexao = banco.getConnection(); PreparedStatement statement = obterStatement(conexao, QUERY_CRIAR_OBRA)) {
            statement.setInt(1, obra.getNumeroAutor());
            statement.setString(2, obra.getTitulo());
            statement.setString(3, obra.getDescricao());
            statement.setInt(4, obra.getStatusObra());
            statement.setString(5, obra.getPublicacao("yyyy-MM-dd HH:mm:ss"));
            statement.execute();
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
    public void editar(Integer numeroObra, Obra obra) {
        try (Connection conexao = banco.getConnection(); PreparedStatement statement = obterStatement(conexao, QUERY_EDITAR_OBRA)) {
            statement.setInt(1, obra.getNumeroAutor());
            statement.setString(2, obra.getTitulo());
            statement.setString(3, obra.getDescricao());
            statement.setInt(4, obra.getStatusObra());
            statement.setInt(5, numeroObra);
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void deletar(Integer numeroObra) {
        try (Connection conexao = banco.getConnection(); PreparedStatement statement = obterStatement(conexao, QUERY_DELETAR_OBRA)) {
            statement.setInt(1, numeroObra);
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
