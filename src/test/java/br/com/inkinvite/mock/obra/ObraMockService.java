package br.com.inkinvite.mock.obra;

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

    }

    @Override
    public void ordenarCapitulos(Integer obra, Capitulos capitulos) {

    }

    @Override
    public void salvarMimeTypeArquivoCapitulo(Integer obra, Integer capitulo, String mimeType) {

    }

    @Override
    public void verificarExistenciaCapitulo(Integer obra, Integer numeroCapitulo) {

    }

    @Override
    public void verificarCriadorObra(Integer numeroObra, String email) {
        if (Objects.equals(email, "naoSouCriador@gmail.com.br")) {
            throw new NaoPermiteEditarObra();
        }
    }
}
