package br.com.inkinvite.mock.mock.obra;

import br.com.inkinvite.application.service.ObraService;
import br.com.inkinvite.domain.obra.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ObraMockService implements ObraService {

    public ObraMockService() {
    }

    @Override
    public List<Obra> obterObrasMaisRecentes(Integer ultimaObra) {
        if (ultimaObra >= 500) throw new RuntimeException("Erro genérico");
        List<Obra> obras = new ArrayList<>();
        obras.add(new Obra());
        obras.add(new Obra());
        obras.add(new Obra());
        obras.add(new Obra());
        obras.add(new Obra());
        return obras;
    }

    @Override
    public void verificarExistencia(Integer obra) {
        if (obra == 404) {
            throw new ObraNaoExiste();
        }
    }

    @Override
    public void novoCapitulo(Capitulo capitulo) {
        if (capitulo.getObra() == 500) {
            throw new RuntimeException("Erro genérico");
        }
    }

    @Override
    public void ordenarCapitulos(Integer obra, Capitulos capitulos) {
        if (obra == 500) {
            throw new RuntimeException("Erro genérico");
        }
    }

    @Override
    public void salvarMimeTypeArquivoCapitulo(Integer obra, Integer capitulo, String mimeType) {

    }

    @Override
    public void verificarExistenciaCapitulo(Integer obra, Integer numeroCapitulo) {
        if (obra == 404) {
            throw new ObraNaoExiste();
        } else if (numeroCapitulo == 404) {
            throw new CapituloNaoExiste();
        }
    }

    @Override
    public void verificarCriadorObra(Integer numeroObra, String email) {
        if (Objects.equals(email, "naoSouCriador@gmail.com.br")) {
            throw new NaoPermiteEditarObra();
        }
    }
}
