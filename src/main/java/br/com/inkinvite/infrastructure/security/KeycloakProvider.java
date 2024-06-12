package br.com.inkinvite.infrastructure.security;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class KeycloakProvider {
    @Inject
    @ConfigProperty(name = "URL_ONLY")
    private String serverURL;

    @Inject
    @ConfigProperty(name = "REALM_NAME")
    private String realmName;

    @Inject
    @ConfigProperty(name = "CLIENT_ID")
    private String clientId;

    @Inject
    @ConfigProperty(name = "CLIENT_SECRET")
    private String clientSecret;

    @Inject
    @ConfigProperty(name = "KC_USERNAME")
    private String adminLogin;

    @Inject
    @ConfigProperty(name = "KC_PASSWORD")
    private String adminSenha;

    public Keycloak obterClientKeycloak() {
        return KeycloakBuilder.builder()
            .serverUrl(serverURL)
            .realm(realmName)
            .clientId(clientId)
            .clientSecret(clientSecret)
            .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
            .build();
    }

    public Keycloak obterClientKeycloakPorLogin(String login, String senha) {
        return KeycloakBuilder.builder()
                .serverUrl(serverURL)
                .realm(realmName)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .username(login)
                .password(senha)
                .grantType(OAuth2Constants.PASSWORD)
                .build();
    }

    public Keycloak obterValidacaoAdmin(String email) {
        return KeycloakBuilder.builder()
                .serverUrl(serverURL)
                .realm(realmName)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .username(adminLogin)
                .password(adminSenha)
                .grantType(OAuth2Constants.PASSWORD)
                .build();
    }

    public String getRealmName() {
        return realmName;
    }
}
