package br.com.inkinvite.infrastructure.repo.autenticacao;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;

import br.com.inkinvite.application.repo.AutenticacaoRepo;
import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.domain.autenticacao.Autenticacao;
import br.com.inkinvite.domain.autenticacao.UsuarioNaoEncontrado;
import br.com.inkinvite.domain.usuario.UsuarioJaExiste;
import br.com.inkinvite.infrastructure.security.KeycloakProvider;
import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class LoginJdbcRepo implements AutenticacaoRepo {

    @Inject
    AgroalDataSource banco;

    @Inject
    LogService logService;

    @Inject
    KeycloakProvider keycloakProvider;

    @Override
    public String logar(Autenticacao login) {
        try {
            return validarLogin(login);
        } catch (UsuarioNaoEncontrado e) {
            throw new UsuarioJaExiste();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String validarLogin(Autenticacao login) {
        try (Keycloak keycloak = keycloakProvider.obterClientKeycloakPorLogin(login.getLoginUsuario(), login.getSenhaUsuario())) {
            AccessTokenResponse tokenResponse = keycloak.tokenManager().getAccessToken();
            try {
                return tokenResponse.getToken();
            } catch (Exception e) {
                throw new UsuarioNaoEncontrado();
            }
        }
    }
}
