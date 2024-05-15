package br.com.inkinvite.infrastructure.repo.obra;

import br.com.inkinvite.domain.objetosDeValor.DataHora;
import br.com.inkinvite.domain.obra.Capitulo;
import br.com.inkinvite.domain.obra.Obra;
import br.com.inkinvite.domain.obra.ObraCompleta;
import br.com.inkinvite.domain.obra.StatusObra;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ObraFactory {

    public static ObraCompleta mapearObra(ResultSet resultoCabecalho, ResultSet resultCapitulos) throws SQLException, ParseException {
        List<Capitulo> capitulos = mapearCapitulos(resultCapitulos);
        resultoCabecalho.next();
        return ObraCompleta.carregar(
                resultoCabecalho.getInt("numero"),
                resultoCabecalho.getInt("autor"),
                resultoCabecalho.getString("nome_autor"),
                resultoCabecalho.getString("titulo"),
                resultoCabecalho.getString("descricao"),
                resultoCabecalho.getInt("status"),
                null, // TODO AVALIAÇÃO
                DataHora.fromString(resultoCabecalho.getString("data_criacao"), "yyyy-MM-dd HH:mm:ss"),
                capitulos
        );
    }

    private static List<Capitulo> mapearCapitulos(ResultSet resultCapitulos) throws SQLException, ParseException {
        List<Capitulo> capitulos = new ArrayList<>();
        while (resultCapitulos.next()) {
            capitulos.add(mapearCapitulo(resultCapitulos));
        }
        return capitulos;
    }

    private static Capitulo mapearCapitulo(ResultSet resultCapitulos) throws SQLException, ParseException {
        return Capitulo.carregar(
                resultCapitulos.getInt("numero"),
                resultCapitulos.getInt("obra"),
                resultCapitulos.getString("titulo"),
                DataHora.fromString(resultCapitulos.getString("data_criacao"), "yyyy-MM-dd HH:mm:ss"),
                resultCapitulos.getInt("numero_ordinal"),
                null // TODO AVALIAÇÃO
        );
    }

    public static List<Obra> mapearObras(ResultSet resultSet) throws SQLException, ParseException {
        List<Obra> obras = new ArrayList<>();
        while (resultSet.next()) obras.add(mapearObraResumida(resultSet));
        return obras;
    }

    public static Obra mapearObraResumida(ResultSet resultSet) throws SQLException, ParseException {
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
