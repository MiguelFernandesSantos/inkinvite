package br.com.inkinvite.infrastructure.repo.login;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;

import br.com.inkinvite.application.repo.LoginRepo;
import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.domain.login.Login;
import br.com.inkinvite.infrastructure.security.KeycloakProvider;
import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class LoginJdbcRepo implements LoginRepo {

    @Inject
    AgroalDataSource banco;

    @Inject
    LogService logService;

    @Inject
    KeycloakProvider keycloakProvider;

    @Override
    @Transactional
    public String recuperar(Login login) {
        try {
            return validarLogin(login);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String validarLogin(Login login) {
        Keycloak keycloak = keycloakProvider.obterClientKeycloakPorLogin(login.getLoginUsuario(), login.getSenhaUsuario());
        AccessTokenResponse tokenResponse = keycloak.tokenManager().getAccessToken();
        return tokenResponse.getToken();
    }
}
