package br.com.inkinvite.infrastructure.repo.usuario;

import static br.com.inkinvite.infrastructure.converter.UserRepresentationMapper.paraUserRepresentation;
import static br.com.inkinvite.infrastructure.repo.MySqlConnection.obterStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;

import br.com.inkinvite.application.repo.UsuarioRepo;
import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.domain.usuario.Usuario;
import br.com.inkinvite.domain.usuario.UsuarioJaExiste;
import br.com.inkinvite.infrastructure.security.KeycloakProvider;
import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

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
        } catch (UsuarioJaExiste e) {
            throw new UsuarioJaExiste();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void salvarKeyClock(Usuario usuario) {
        try (Keycloak keycloak = keycloakProvider.obterClientKeycloak()) {
            UsersResource usersResource = keycloak.realm(keycloakProvider.getRealmName()).users();
            UserRepresentation user = paraUserRepresentation(usuario);
            try (Response response = usersResource.create(user)) {
                if (response.getStatus() < 200 || response.getStatus() >= 300) {
                    throw new UsuarioJaExiste();
                }
            }
        }
    }

    private void salvarNoBanco(Usuario usuario) {
        try (Connection conexao = banco.getConnection(); PreparedStatement statement = obterStatement(conexao, QUERY_CRIAR_USUARIO)) {
            statement.setString(1, usuario.getPrimeiroNome());
            statement.setString(2, usuario.getSegundoNome());
            statement.setString(3, usuario.getLogin());
            statement.setString(4, usuario.getEmail());
            statement.setString(5, usuario.getSenha());
            statement.setString(6, usuario.getCriacao("yyyy-MM-dd HH:mm:ss"));
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
