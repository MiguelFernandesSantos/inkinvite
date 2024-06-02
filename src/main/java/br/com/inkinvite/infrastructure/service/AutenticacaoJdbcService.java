package br.com.inkinvite.infrastructure.service;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;

import br.com.inkinvite.application.service.AutenticacaoService;
import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.domain.autenticacao.UsuarioInvalido;
import br.com.inkinvite.domain.autenticacao.UsuarioNaoEncontrado;
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
        } catch (UsuarioNaoEncontrado e) {
            throw new UsuarioNaoEncontrado();
        } catch (UsuarioInvalido e) {
            throw new UsuarioInvalido();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String validarLogin(String login, String senha) {
        try (Keycloak keycloak = keycloakProvider.obterClientKeycloakPorLogin(login, senha)) {
            AccessTokenResponse tokenResponse = keycloak.tokenManager().getAccessToken();
            return tokenResponse.getToken();
        } catch (WebApplicationException keycloakError) {
            if (keycloakError.getResponse().getStatus() == 401) {
                throw new UsuarioInvalido();
            } else if (keycloakError.getResponse().getStatus() == 404) {
                throw new UsuarioNaoEncontrado();
            } else {
                throw new RuntimeException(keycloakError);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
