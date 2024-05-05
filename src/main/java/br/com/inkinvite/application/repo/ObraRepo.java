package br.com.inkinvite.application.repo;

import br.com.inkinvite.domain.obra.Obra;

import java.util.List;

public interface ObraRepo {
    void salvar(Obra cabecalhoObra);

    void verificarExistencia(Integer numero);

    void editar(Integer numeroObra, Obra obra);

    void deletar(Integer numeroObra);

}
