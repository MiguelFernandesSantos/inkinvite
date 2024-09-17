package br.com.inkinvite.mock.obra;

import br.com.inkinvite.application.repo.ObraRepo;
import br.com.inkinvite.domain.obra.Capitulo;
import br.com.inkinvite.domain.obra.Obra;
import br.com.inkinvite.domain.obra.ObraCompleta;

import java.util.ArrayList;
import java.util.List;

public class ObraMockRepo implements ObraRepo {

    public ObraMockRepo() {
    }

    @Override
    public void salvar(Obra cabecalhoObra, String email) {

    }

    @Override
    public void editar(Integer numeroObra, Obra obra, String email) {

    }

    @Override
    public void deletar(Integer numeroObra) {
    }

    @Override
    public ObraCompleta buscarObra(Integer obra) {
        ObraCompleta obraObtida = new ObraCompleta();
        obraObtida.setNumero(obra);
        return obraObtida;
    }

    @Override
    public List<Obra> buscarObras(Integer ultimaObra, String pesquisa, Integer limite) {
        List<Obra> obras = new ArrayList<>();
        for (int i = 0; i < limite; i++) {
            Obra obra = new Obra();
            obra.setNumero(ultimaObra + i);
            obras.add(obra);
        }
        return obras;
    }

    @Override
    public Capitulo buscarCapitulo(Integer obra, Integer numeroCapitulo) {
        return Capitulo.carregar(numeroCapitulo, obra, "titulo", null, 1, null, null);
    }
}
