package br.com.inkinvite.infrastructure.repo.obra;

import br.com.inkinvite.domain.objetosDeValor.DataHora;
import br.com.inkinvite.domain.obra.Obra;
import br.com.inkinvite.domain.obra.StatusObra;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ObraFactory {

    public static List<Obra> mapearObras(ResultSet resultSet) throws SQLException, ParseException {
        List<Obra> obras = new ArrayList<>();
        while (resultSet.next()) obras.add(mapearObra(resultSet));
        return obras;
    }

    public static Obra mapearObra(ResultSet resultSet) throws SQLException, ParseException {
        return Obra.carregar(
                resultSet.getInt("numero"),
                resultSet.getInt("autor"),
                resultSet.getString("nome_autor"),
                resultSet.getString("titulo"),
                resultSet.getString("descricao"),
                StatusObra.fromId(resultSet.getInt("status")),
                DataHora.fromString(resultSet.getString("data_criacao"), "yyyy-MM-dd HH:mm:ss")
        );
    }

}
