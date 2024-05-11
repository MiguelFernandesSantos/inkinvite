package br.com.keycloak.infrastructure.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlConnection {
    public static PreparedStatement obterStatement(Connection conexao, String query) throws SQLException {
        return conexao.prepareStatement(query);
    }

    public static Statement obterStatement(Connection conexao) throws SQLException {
        return conexao.createStatement();
    }
}
