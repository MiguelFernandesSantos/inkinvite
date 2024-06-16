package br.com.inkinvite.application.component;

import br.com.inkinvite.application.repo.ObraRepo;
import br.com.inkinvite.application.service.StorageService;
import br.com.inkinvite.application.usecase.ObraUseCase;
import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.domain.obra.Capitulo;
import br.com.inkinvite.domain.obra.Capitulos;
import br.com.inkinvite.domain.obra.Obra;
import br.com.inkinvite.application.service.ObraService;
import br.com.inkinvite.domain.obra.ObraCompleta;

import java.util.List;

public class ObraComponent {
    final ObraUseCase useCase;

    public ObraComponent(ObraRepo obraRepo, ObraService obraService, StorageService storageService, LogService logService) {
        this.useCase = new ObraUseCase(obraRepo, obraService, storageService, logService);
    }

    public void criarObra(Obra obra) {
        useCase.criarObra(obra);
    }

    public void editarObra(Integer numeroObra, Obra obra) {
        useCase.editarObra(numeroObra, obra);
    }

    public void deletarObra(Integer numeroObra) {
        useCase.deletarObra(numeroObra);
    }

    public List<Obra> obterObrasMaisRecentes(Integer ultimaObra) {
        return useCase.obterObrasMaisRecentes(ultimaObra);
    }

    public void novoCapitulo(Capitulo capitulo) {
        useCase.novoCapitulo(capitulo);
    }

    public void ordenarCapitulos(Integer obra, Capitulos capitulos) {
        useCase.ordenarCapitulos(obra, capitulos);
    }

    public ObraCompleta obterObra(Integer numero) {
        return useCase.obterObra(numero);
    }

    public List<Obra> buscarObras(Integer ultimaObra, String pesquisa, Integer limite) {
        return useCase.buscarObras(ultimaObra, pesquisa, limite);
    }

    public void adicionarArquivoCapituloObra(Integer obra, Integer capitulo, byte[] bytes,String mimeType ) {
        useCase.adicionarArquivoCapituloObra(obra, capitulo, bytes, mimeType);
    }
}
