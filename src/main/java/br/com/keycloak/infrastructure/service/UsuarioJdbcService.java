package br.com.keycloak.infrastructure.service;

import br.com.keycloak.application.service.LogService;
import br.com.keycloak.application.service.UsuarioService;
import br.com.keycloak.infrastructure.repo.usuario.UsuarioQueries;
import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UsuarioJdbcService extends UsuarioQueries implements UsuarioService {
    @Inject
    AgroalDataSource banco;

    @Inject
    LogService logService;


}
