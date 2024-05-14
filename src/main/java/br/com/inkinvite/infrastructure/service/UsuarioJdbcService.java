package br.com.inkinvite.infrastructure.service;

import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.application.service.UsuarioService;
import br.com.inkinvite.infrastructure.repo.usuario.UsuarioQueries;
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
