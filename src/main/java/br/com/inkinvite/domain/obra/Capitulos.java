package br.com.inkinvite.domain.obra;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Capitulos {

    private Map<Integer, Capitulo> capitulosPorOrdinal;

    public static Capitulos carregar(List<Capitulo> capitulos) {
        Capitulos capitulo = new Capitulos();
        capitulo.capitulosPorOrdinal = capitulos.stream().collect(Collectors.toMap(Capitulo::getNumeroOrdinal, Function.identity()));
        return capitulo;
    }

    public Capitulo getCapitulo(Integer ordinal) {
        return capitulosPorOrdinal.get(ordinal);
    }

    public List<Capitulo> obterCapitulos() {
        return capitulosPorOrdinal.values().stream().toList();
    }

}
