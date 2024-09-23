package br.com.inkinvite.mock.mock.obra;

import br.com.inkinvite.application.repo.ObraRepo;
import br.com.inkinvite.domain.objetosDeValor.DataHora;
import br.com.inkinvite.domain.obra.Capitulo;
import br.com.inkinvite.domain.obra.Obra;
import br.com.inkinvite.domain.obra.ObraCompleta;
import br.com.inkinvite.domain.obra.StatusObra;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ObraMockRepo implements ObraRepo {

    public ObraMockRepo() {
    }

    @Override
    public void salvar(Obra cabecalhoObra, String email) {

    }

    @Override
    public void editar(Integer numeroObra, Obra obra, String email) {
        if (numeroObra == 500) throw new RuntimeException("Erro genérico");
    }

    @Override
    public void deletar(Integer numeroObra) {
        if (numeroObra == 500) throw new RuntimeException("Erro genérico");
    }

    @Override
    public ObraCompleta buscarObra(Integer obra) {
        if (obra == 500) throw new RuntimeException("Erro genérico");
        ObraCompleta obraObtida = ObraCompleta.carregar(obra, 1, "autor", "meu titulo", "minha descricao", 1, null, DataHora.agora(), obterCapitulos(obra));
        return obraObtida;
    }

    private List<Capitulo> obterCapitulos(Integer obra) {
        List<Capitulo> capitulos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Capitulo capitulo = Capitulo.carregar(i, obra, "titulo", DataHora.agora(), i, null, null);
            capitulos.add(capitulo);
        }
        return capitulos;
    }

    @Override
    public List<Obra> buscarObras(Integer ultimaObra, String pesquisa, Integer limite) {
        if (Objects.equals(pesquisa, "erroGenerico")) throw new RuntimeException("Erro genérico");
        List<Obra> obras = new ArrayList<>();
        for (int i = 0; i < limite; i++) {
            Obra obra = Obra.carregar(i, 1, "autor", "titulo", "descricao", StatusObra.CRIADA, DataHora.agora());
            obras.add(obra);
        }
        return obras;
    }

    @Override
    public Capitulo buscarCapitulo(Integer obra, Integer numeroCapitulo) {
        if (obra == 500) throw new RuntimeException("Erro genérico");
        return Capitulo.carregar(numeroCapitulo, obra, "titulo", null, 1, null, null);
    }
}
