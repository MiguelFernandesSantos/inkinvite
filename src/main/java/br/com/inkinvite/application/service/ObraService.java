package br.com.inkinvite.application.service;

import br.com.inkinvite.domain.obra.Obra;

import java.util.List;

public interface ObraService {

    List<Obra> obterObrasMaisRecentes(Integer ultimaObra);
}
