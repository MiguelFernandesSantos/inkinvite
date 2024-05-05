package br.com.inkinvite.application.usecase;

import br.com.inkinvite.application.repo.ObraRepo;
import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.domain.obra.Obra;
import br.com.inkinvite.application.service.ObraService;
import br.com.inkinvite.domain.obra.ObraNaoExiste;

import java.util.List;

public class ObraUseCase extends UseCase {

    private final ObraRepo obraRepo;
    private final ObraService obraService;

    public ObraUseCase(ObraRepo obraRepo, ObraService obraService, LogService logService) {
        super(logService, "ObraUseCase");
        this.obraRepo = obraRepo;
        this.obraService = obraService;
    }

    public void criarObra(Obra obra) {
        start("Iniciando criacao da obra de titulo " + obra.getTitulo());
        try {
            obraRepo.salvar(obra);
            sucesso("Criacao da obra de titulo " + obra.getTitulo() + " realizada com sucesso");
        } catch (Exception e) {
            erro("Ocorreu um erro ao tentar criar a obra de titulo " + obra.getTitulo(), e);
            throw e;
        }
    }

    public void editarObra(Integer numeroObra, Obra obra) {
        start("Iniciando edicao da obra de titulo " + obra.getTitulo());
        try {
            info("Verificando existencia da obra de numero " + numeroObra);
            obraRepo.verificarExistencia(numeroObra);
            // TODO verificar se é o autor da obra [NECESSARIO JWT]
            info("Editando a obra de titulo " + obra.getTitulo());
            obraRepo.editar(numeroObra, obra);
            sucesso("Edicao da obra de titulo " + obra.getTitulo() + " realizada com sucesso");
        } catch (ObraNaoExiste e) {
            erro("A obra de numero " + numeroObra + " nao existe", e);
            throw e;
        } catch (Exception e) {
            erro("Ocorreu um erro ao tentar editar a obra de titulo " + obra.getTitulo(), e);
            throw e;
        }
    }

    public void deletarObra(Integer numeroObra) {
        start("Iniciando exclusao da obra de numero " + numeroObra);
        try {
            info("Verificando existencia da obra de numero " + numeroObra);
            obraRepo.verificarExistencia(numeroObra);
            // TODO verificar se é o autor da obra [NECESSARIO JWT]
            info("Excluindo a obra de numero " + numeroObra);
            obraRepo.deletar(numeroObra);
            sucesso("Exclusao da obra de numero " + numeroObra + " realizada com sucesso");
        } catch (ObraNaoExiste e) {
            erro("A obra de numero " + numeroObra + " nao existe", e);
            throw e;
        } catch (Exception e) {
            erro("Ocorreu um erro ao tentar excluir a obra de numero " + numeroObra, e);
            throw e;
        }
    }

    public List<Obra> obterObrasMaisRecentes(Integer ultimaObra) {
        start("Iniciando busca das obras mais recentes a partir da obra de numero " + ultimaObra);
        try {
            info("Buscando obras mais recentes");
            List<Obra> obras = obraService.obterObrasMaisRecentes(ultimaObra);
            sucesso("Busca das obras mais recentes realizada com sucesso");
            return obras;
        } catch (Exception e) {
            erro("Ocorreu um erro ao tentar buscar as obras mais recentes", e);
            throw e;
        }
    }
}
