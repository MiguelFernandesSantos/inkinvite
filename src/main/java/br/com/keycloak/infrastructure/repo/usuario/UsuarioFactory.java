package br.com.keycloak.infrastructure.repo.usuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import br.com.keycloak.domain.usuario.Usuario;

public class UsuarioFactory {

    public static List<Usuario> mapearObras(ResultSet resultSet) throws SQLException, ParseException {
        List<Usuario> obras = new ArrayList<>();
        while (resultSet.next()) obras.add(mapearObra(resultSet));
        return obras;
    }

    public static Usuario mapearObra(ResultSet resultSet) throws SQLException, ParseException {
        return Usuario.carregar(
            resultSet.getString("primeiroNomeUsuario"),
            resultSet.getString("segundoNomeUsuario"),
            resultSet.getString("loginUsuario"),
            resultSet.getString("emailUsuario"),
            resultSet.getString("senhaUsuario")
        );
    }

}
