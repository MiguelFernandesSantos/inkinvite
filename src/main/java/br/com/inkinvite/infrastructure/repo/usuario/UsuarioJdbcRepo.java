package br.com.inkinvite.infrastructure.repo.usuario;

import static br.com.inkinvite.infrastructure.repo.MySqlConnection.obterStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.inkinvite.application.repo.UsuarioRepo;
import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.domain.usuario.Usuario;
import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UsuarioJdbcRepo extends UsuarioQueries implements UsuarioRepo {

    @Inject
    AgroalDataSource banco;

    @Inject
    LogService logService;

    @Override
    @Transactional
    public void salvar(Usuario usuario) {
        try (Connection conexao = banco.getConnection(); PreparedStatement statement = obterStatement(conexao, QUERY_CRIAR_USUARIO)) {
            statement.setString(1, usuario.getPrimeiroNomeUsuario());
            statement.setString(2, usuario.getSegundoNomeUsuario());
            statement.setString(3, usuario.getLoginUsuario());
            statement.setString(4, usuario.getEmailUsuario());
            statement.setString(5, usuario.getSenhaUsuario());
            statement.setString(6, usuario.getCriacao("yyyy-MM-dd HH:mm:ss"));
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
