package br.com.inkinvite.infrastructure.repo.usuario;

import static br.com.inkinvite.infrastructure.converter.UserRepresentationMapper.toUserRepresentation;
import static br.com.inkinvite.infrastructure.repo.MySqlConnection.obterStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.inkinvite.application.repo.UsuarioRepo;
import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.domain.usuario.Usuario;
import br.com.inkinvite.infrastructure.security.KeycloakProvider;
import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;

@ApplicationScoped
public class UsuarioJdbcRepo extends UsuarioQueries implements UsuarioRepo {

    @Inject
    AgroalDataSource banco;

    @Inject
    LogService logService;

    @Inject
    KeycloakProvider keycloakProvider;

    @Override
    @Transactional
    public void salvar(Usuario usuario) {
        try {
            salvarKeyClock(usuario);
            salvarNoBanco(usuario);
        } catch (Exception e) {
            throw new RuntimeException("Error creating user", e);
        }
    }

    private void salvarKeyClock(Usuario usuario) {
        UsersResource usersResource = keycloakProvider.getKeycloakClient().realm(keycloakProvider.getRealmName()).users();
        UserRepresentation user = toUserRepresentation(usuario);
        Response response = usersResource.create(user);
        if (response.getStatus() >= 200 && response.getStatus() <= 299) throw new RuntimeException();
    }

    private void salvarNoBanco(Usuario usuario) {
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
