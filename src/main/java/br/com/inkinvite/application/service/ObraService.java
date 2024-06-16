package br.com.inkinvite.application.service;

import br.com.inkinvite.domain.obra.Capitulo;
import br.com.inkinvite.domain.obra.Capitulos;
import br.com.inkinvite.domain.obra.Obra;

import java.util.List;

public interface ObraService {

    List<Obra> obterObrasMaisRecentes(Integer ultimaObra);

    void verificarExistencia(Integer obra);

    void novoCapitulo(Capitulo capitulo);

    void ordenarCapitulos(Integer obra, Capitulos capitulos);

    void salvarMimeTypeArquivoCapitulo(Integer obra, Integer capitulo, String mimeType);

    void verificarExistenciaCapitulo(Integer obra, Integer numeroCapitulo);
}
