package br.com.inkinvite.domain.modelo;

public abstract class Entidade {

    public Integer numero;

    public String getId() {
        return numero.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Entidade entidade = (Entidade) obj;
        return numero != null && numero.equals(entidade.numero);
    }

    @Override
    public int hashCode() {
        return numero != null ? numero.hashCode() : 0;
    }

}
