package br.com.inkinvite.infrastructure.repo.usuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import br.com.inkinvite.domain.usuario.Usuario;

public class UsuarioFactory {

    public static List<Usuario> mapearUsuarios(ResultSet resultSet) throws SQLException, ParseException {
        List<Usuario> usuarios = new ArrayList<>();
        while (resultSet.next()) usuarios.add(mapearUsuario(resultSet));
        return usuarios;
    }

    public static Usuario mapearUsuario(ResultSet resultSet) throws SQLException, ParseException {
        return Usuario.carregar(
            resultSet.getString("primeiro_nome"),
            resultSet.getString("segundo_nome"),
            resultSet.getString("login"),
            resultSet.getString("email"),
            resultSet.getString("senha")
        );
    }

}
