package br.com.inkinvite.domain.obra;

public enum StatusObra {
    CRIADA(1),
    EM_ANDAMENTO(2),
    FINALIZADA(3),
    DESCONHECIDO(4);

    public final Integer id;

    StatusObra(Integer id) {
        this.id = id;
    }

    public static StatusObra fromId(Integer id) {
        return switch (id) {
            case 1 -> CRIADA;
            case 2 -> EM_ANDAMENTO;
            case 3 -> FINALIZADA;
            default -> DESCONHECIDO;
        };
    }
}
