package br.com.inkinvite.infrastructure.repo.obra;

import br.com.inkinvite.application.repo.ObraRepo;
import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.domain.obra.Capitulo;
import br.com.inkinvite.domain.obra.Obra;
import br.com.inkinvite.domain.obra.ObraCompleta;
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
import static br.com.inkinvite.infrastructure.repo.obra.ObraFactory.mapearObra;
import static br.com.inkinvite.infrastructure.repo.obra.ObraFactory.mapearObras;

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
    public ObraCompleta buscarObra(Integer obra) {
        try (Connection conexao = banco.getConnection(); PreparedStatement statementCabecalho = obterStatement(conexao, QUERY_OBTER_OBRA_ESPECIFICA); PreparedStatement statementCapitulos = obterStatement(conexao, QUERY_OBTER_CAPITULOS_OBRA)) {
            statementCabecalho.setInt(1, obra);
            ResultSet cabecalho = statementCabecalho.executeQuery();
            statementCapitulos.setInt(1, obra);
            ResultSet capitulos = statementCapitulos.executeQuery();
            ObraCompleta obraCompleta = mapearObra(cabecalho, capitulos);
            cabecalho.close();
            capitulos.close();
            return obraCompleta;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Obra> buscarObras(Integer ultimaObra, String pesquisa, Integer limite) {
        try (Connection conexao = banco.getConnection(); PreparedStatement statement = obterStatement(conexao, QUERY_BUSCAR_OBRAS_PAGINADO)) {
            statement.setInt(1, ultimaObra);
            statement.setString(2, obterLikePesquisa(pesquisa));
            statement.setString(3, obterLikePesquisa(pesquisa));
            statement.setInt(4, limite == null ? 12 : limite);
            ResultSet resultSet = statement.executeQuery();
            List<Obra> obras = mapearObras(resultSet);
            resultSet.close();
            return obras;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Capitulo buscarCapitulo(Integer obra, Integer numeroCapitulo) {
        try (Connection conexao = banco.getConnection(); PreparedStatement statement = obterStatement(conexao, QUERY_OBTER_CAPITULO_OBRA_ESPECIFICA)) {
            statement.setInt(1, obra);
            statement.setInt(2, numeroCapitulo);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Capitulo capitulo = ObraFactory.mapearCapitulo(resultSet);
            resultSet.close();
            return capitulo;
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
        try (Connection conexao = banco.getConnection(); PreparedStatement statementObras = obterStatement(conexao, QUERY_DELETAR_OBRA); PreparedStatement statementCapitulos = obterStatement(conexao, QUERY_DELETAR_CAPITULOS)) {
            deletarConteudoObra(numeroObra, statementCapitulos);
            deletarConteudoObra(numeroObra, statementObras);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void deletarConteudoObra(Integer numeroObra, PreparedStatement statementObras) throws SQLException {
        statementObras.setInt(1, numeroObra);
        statementObras.execute();
    }

    private static String obterLikePesquisa(String pesquisa) {
        return "%" + pesquisa + "%";
    }
}
