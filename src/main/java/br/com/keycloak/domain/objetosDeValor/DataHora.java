package br.com.keycloak.domain.objetosDeValor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.keycloak.domain.modelo.ObjetoDeValor;

public class DataHora extends ObjetoDeValor implements Comparable<DataHora> {
    private final Date valor;

    private DataHora(Date valor) {
        this.valor = valor;
    }

    public static DataHora agora() {
        return new DataHora(new Date());
    }

    public static DataHora fromString(String dataHoraStr) throws ParseException {
        return obterData(dataHoraStr, "dd/MM/yyyy HH:mm:ss");
    }

    public static DataHora fromString(String dataHoraStr, String formato) throws ParseException {
        return obterData(dataHoraStr, formato);
    }

    private static DataHora obterData(String dataHoraStr, String formato) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formato);
        Date valor = formatter.parse(dataHoraStr);
        return new DataHora(valor);
    }

    public Date getValor() {
        return valor;
    }

    public String formatar(String formato) {
        DateFormat formatador = new SimpleDateFormat(formato);
        return formatador.format(valor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataHora dataHora = (DataHora) o;
        return valor.equals(dataHora.valor);
    }

    @Override
    public int hashCode() {
        return valor.hashCode();
    }

    public boolean isAfter(DataHora dataHora) {
        return valor.after(dataHora.valor);
    }

    public boolean isBefore(DataHora dataHora) {
        return valor.before(dataHora.valor);
    }

    public boolean isEqual(DataHora dataHora) {
        return valor.equals(dataHora.valor);
    }

    public long diffInMilliseconds(DataHora dataHora) {
        return valor.getTime() - dataHora.valor.getTime();
    }

    @Override
    public int compareTo(DataHora dataHora) {
        return valor.compareTo(dataHora.valor);
    }

    @Override
    public String toString() {
        return formatar("dd/MM/yyyy HH:mm:ss");
    }
}
