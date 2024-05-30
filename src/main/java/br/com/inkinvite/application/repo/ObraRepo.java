package br.com.inkinvite.application.repo;

import br.com.inkinvite.domain.obra.Obra;
import br.com.inkinvite.domain.obra.ObraCompleta;

import java.util.List;

public interface ObraRepo {
    void salvar(Obra cabecalhoObra);

    void editar(Integer numeroObra, Obra obra);

    void deletar(Integer numeroObra);

    ObraCompleta buscarObra(Integer obra);

    List<Obra> buscarObras(Integer ultimaObra, String pesquisa, Integer limite);
}
