package br.com.inkinvite.application.usecase;

import br.com.inkinvite.application.repo.ObraRepo;
import br.com.inkinvite.application.service.LogService;
import br.com.inkinvite.application.service.StorageService;
import br.com.inkinvite.domain.obra.*;
import br.com.inkinvite.application.service.ObraService;

import java.util.List;

public class ObraUseCase extends UseCase {

    private final ObraRepo obraRepo;
    private final ObraService obraService;
    private final StorageService storageService;

    public ObraUseCase(ObraRepo obraRepo, ObraService obraService, StorageService storageService, LogService logService) {
        super(logService, "ObraUseCase");
        this.obraRepo = obraRepo;
        this.obraService = obraService;
        this.storageService = storageService;
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


    public ObraCompleta obterObra(Integer numeroObra) {
        start("Iniciando busca da obra de numero " + numeroObra);
        try {
            info("Verificando existencia da obra de numero " + numeroObra);
            obraService.verificarExistencia(numeroObra);
            info("Buscando a obra de numero " + numeroObra);
            ObraCompleta obra = obraRepo.buscarObra(numeroObra);
            sucesso("Busca da obra de numero " + obra + " realizada com sucesso");
            return obra;
        } catch (ObraNaoExiste e) {
            erro("A obra de numero " + numeroObra + " nao existe", e);
            throw e;
        } catch (Exception e) {
            erro("Ocorreu um erro ao tentar obter a obra de numero " + numeroObra, e);
            throw e;
        }
    }

    public void editarObra(Integer numeroObra, Obra obra) {
        start("Iniciando edicao da obra de titulo " + obra.getTitulo());
        try {
            info("Verificando existencia da obra de numero " + numeroObra);
            obraService.verificarExistencia(numeroObra);
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
            obraService.verificarExistencia(numeroObra);
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

    public void novoCapitulo(Capitulo capitulo) {
        start("Iniciando criacao de um novo capitulo de titulo " + capitulo.getTitulo() + " para a obra de numero " + capitulo.getObra());
        try {
            info("Verificando existencia da obra de numero " + capitulo.getObra());
            obraService.verificarExistencia(capitulo.getObra());
            info("Criando um novo capitulo de titulo " + capitulo.getTitulo());
            obraService.novoCapitulo(capitulo);
            sucesso("Criacao de um novo capitulo de titulo " + capitulo.getTitulo() + " para a obra de numero " + capitulo.getObra() + " realizada com sucesso");
        } catch (ObraNaoExiste e) {
            erro("A obra de numero " + capitulo.getObra() + " nao existe", e);
            throw e;
        } catch (Exception e) {
            erro("Ocorreu um erro ao tentar criar um novo capitulo de titulo " + capitulo.getTitulo() + " para a obra de numero " + capitulo.getObra(), e);
            throw e;
        }
    }

    public void ordenarCapitulos(Integer obra, Capitulos capitulos) {
        start("Iniciando ordenacao dos capitulos da obra de numero " + obra);
        try {
            info("Verificando existencia da obra de numero " + obra);
            obraService.verificarExistencia(obra);
            info("Ordenando os capitulos da obra de numero " + obra);
            obraService.ordenarCapitulos(obra, capitulos);
            sucesso("Ordenacao dos capitulos da obra de numero " + obra + " realizada com sucesso");
        } catch (ObraNaoExiste e) {
            erro("A obra de numero " + obra + " nao existe", e);
            throw e;
        } catch (Exception e) {
            erro("Ocorreu um erro ao tentar ordenar os capitulos da obra de numero " + obra, e);
            throw e;
        }
    }

    public List<Obra> buscarObras(Integer ultimaObra, String pesquisa, Integer limite) {
        start("Iniciando busca das " + limite + " obras a partir da obra de numero " + ultimaObra + " com a pesquisa " + pesquisa);
        try {
            List<Obra> obras = obraRepo.buscarObras(ultimaObra, pesquisa, limite);
            sucesso("Busca das " + limite + " obras a partir da obra de numero " + ultimaObra + " com a pesquisa " + pesquisa + " realizada com sucesso");
            return obras;
        } catch (Exception e) {
            erro("Ocorreu um erro ao tentar buscar as " + limite + " obras a partir da obra de numero " + ultimaObra + " com a pesquisa " + pesquisa, e);
            throw e;
        }
    }

    public void adicionarArquivoCapituloObra(Integer obra, Integer capitulo, byte[] bytes, String mimeType) {
        start("Iniciando adicao de um arquivo ao capitulo de numero " + capitulo + " da obra de numero " + obra);
        try {
            info("Verificando existencia da obra de numero " + obra);
            obraService.verificarExistenciaCapitulo(obra, capitulo);
            info("Adicionando um arquivo ao capitulo de numero " + capitulo + " da obra de numero " + obra);
            storageService.adicionarArquivoCapituloObra(obra, capitulo, bytes, mimeType);
            info("Salvando mimetype do arquivo no capitulo de numero " + capitulo + " da obra de numero " + obra);
            obraService.salvarMimeTypeArquivoCapitulo(obra, capitulo, mimeType);
            sucesso("Adicao de um arquivo ao capitulo de numero " + capitulo + " da obra de numero " + obra + " realizada com sucesso");
        } catch (CapituloNaoExiste e) {
            erro("O capitulo de numero " + capitulo + " da obra de numero " + obra + " nao existe", e);
            throw e;
        } catch (Exception e) {
            erro("Ocorreu um erro ao tentar adicionar um arquivo ao capitulo de numero " + capitulo + " da obra de numero " + obra, e);
            throw e;
        }
    }

    public Capitulo obterCapitulo(Integer obra, Integer numeroCapitulo) {
        start("Iniciando busca do capitulo de numero " + numeroCapitulo + " da obra de numero " + obra);
        try {
            info("Verificando existencia da obra de numero " + obra);
            obraService.verificarExistenciaCapitulo(obra, numeroCapitulo);
            info("Buscando o capitulo de numero " + numeroCapitulo + " da obra de numero " + obra);
            Capitulo capitulo = obraRepo.buscarCapitulo(obra, numeroCapitulo);
            buscarArquivo(obra, numeroCapitulo, capitulo);
            sucesso("Busca do capitulo de numero " + numeroCapitulo + " da obra de numero " + obra + " realizada com sucesso");
            return capitulo;
        } catch (CapituloNaoExiste e) {
            erro("O capitulo de numero " + numeroCapitulo + " da obra de numero " + obra + " nao existe", e);
            throw e;
        } catch (Exception e) {
            erro("Ocorreu um erro ao tentar buscar o capitulo de numero " + numeroCapitulo + " da obra de numero " + obra, e);
            throw e;
        }
    }

    private void buscarArquivo(Integer obra, Integer numeroCapitulo, Capitulo capitulo) {
        if (capitulo.possuiMimeType()) {
            info("Buscando arquivo do capitulo de numero " + numeroCapitulo + " da obra de numero " + obra);
            byte[] bytes = storageService.buscarArquivoCapituloObra(obra, numeroCapitulo, capitulo.getMimeType());
            capitulo.adicionarByteArquivos(bytes);
            info("Arquivo do capitulo de numero " + numeroCapitulo + " da obra de numero " + obra + " encontrado");
        } else {
            info("O capitulo de numero " + numeroCapitulo + " da obra de numero " + obra + " nao possui arquivo");
        }
    }
}
