package br.com.inkinvite.infrastructure.repo.obra;

import br.com.inkinvite.domain.usuario.UsuarioNaoEncontrado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ObraDataBase extends ObraQueries {

    protected Integer obterIdentificadorAutor(String email, Connection conexao) throws SQLException {
        try (PreparedStatement statement = conexao.prepareStatement(OBTER_IDENTIFICADOR_AUTOR)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            boolean existe = resultSet.next();
            if (!existe) throw new UsuarioNaoEncontrado();
            Integer identificador = resultSet.getInt("identificador");
            resultSet.close();
            return identificador;
        }
    }

}
