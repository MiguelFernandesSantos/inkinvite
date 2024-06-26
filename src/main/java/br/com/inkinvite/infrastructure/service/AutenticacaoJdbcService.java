package br.com.inkinvite.infrastructure.service;

import java.util.Collections;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.UserRepresentation;

import br.com.inkinvite.application.service.AutenticacaoService;
import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.domain.DominioException;
import br.com.inkinvite.domain.usuario.UsuarioInvalido;
import br.com.inkinvite.domain.usuario.UsuarioNaoEncontrado;
import br.com.inkinvite.infrastructure.security.KeycloakProvider;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;

@ApplicationScoped
public class AutenticacaoJdbcService implements AutenticacaoService {
    @Inject
    LogService logService;

    @Inject
    KeycloakProvider keycloakProvider;

    @Override
    public String logar(String login, String senha) {
        try {
            return validarLogin(login, senha);
        } catch (DominioException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void esqueciSenha(String credencialUsuario) {
        try (Keycloak keycloak = keycloakProvider.obterValidacaoAdmin()) {
            UserRepresentation user = keycloak.realm(keycloakProvider.getRealmName()).users().search(credencialUsuario).stream().findFirst().orElse(null);
            if (user == null) {
                throw new UsuarioNaoEncontrado();
            }
            keycloak.realm(keycloakProvider.getRealmName()).users().get(user.getId()).executeActionsEmail(Collections.singletonList("UPDATE_PASSWORD"));
        } catch (UsuarioNaoEncontrado e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String validarLogin(String login, String senha) {
        String token = null;
        try (Keycloak keycloak = keycloakProvider.obterClientKeycloakPorLogin(login, senha)) {
            AccessTokenResponse tokenResponse = keycloak.tokenManager().getAccessToken();
            token = tokenResponse.getToken();
        } catch (WebApplicationException keycloakError) {
            tratarErroHttpKeycloak(keycloakError);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return token;
    }

    private void tratarErroHttpKeycloak(WebApplicationException keycloakError) {
        if (keycloakError.getResponse().getStatus() == 401) {
            throw new UsuarioInvalido();
        } else if (keycloakError.getResponse().getStatus() == 404) {
            throw new UsuarioNaoEncontrado();
        } else {
            throw new RuntimeException(keycloakError);
        }
    }
}
