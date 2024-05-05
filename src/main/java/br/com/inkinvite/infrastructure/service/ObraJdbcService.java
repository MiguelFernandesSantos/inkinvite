package br.com.inkinvite.infrastructure.service;

import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.application.service.ObraService;
import br.com.inkinvite.domain.obra.Obra;
import br.com.inkinvite.infrastructure.repo.obra.ObraQueries;
import io.agroal.api.AgroalDataSource;
import jakarta.inject.Inject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import static br.com.inkinvite.infrastructure.repo.MySqlConnection.obterStatement;
import static br.com.inkinvite.infrastructure.repo.obra.ObraFactory.mapearObras;

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
            return mapearObras(resultSet);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
