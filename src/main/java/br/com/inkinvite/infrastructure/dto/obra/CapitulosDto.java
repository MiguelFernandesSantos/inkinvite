package br.com.inkinvite.infrastructure.dto.obra;

import br.com.inkinvite.domain.obra.Capitulo;
import br.com.inkinvite.domain.obra.Capitulos;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RegisterForReflection
public class CapitulosDto {

    public List<CapituloDto> capitulos;

    public Capitulos paraDominio() throws ParseException {
        List<Capitulo> capitulos = converterCapitulos();
        return Capitulos.carregar(capitulos);
    }

    private List<Capitulo> converterCapitulos() throws ParseException {
        List<Capitulo> capitulos = new ArrayList<>();
        for (CapituloDto capitulo : this.capitulos) {
            Capitulo paraDominio = capitulo.paraDominio();
            capitulos.add(paraDominio);
        }
        return capitulos;
    }
}
