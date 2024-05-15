package br.com.inkinvite.infrastructure.repo.obra;

public class ObraQueries {

    protected final String QUERY_CRIAR_OBRA = "INSERT INTO obra ("
            + "autor, titulo, descricao, status, data_criacao"
            + ") VALUES (?, ?, ?, ?, ?)";

    protected final String QUERY_VERIFICAR_EXISTENCIA_OBRA =
            "SELECT "
                    + "COUNT(*) AS quantidade "
                    + "FROM obra WHERE id = ?";

    protected final String QUERY_EDITAR_OBRA =
            "UPDATE obra SET "
                    + "autor = ?, titulo = ?, descricao = ?, status = ? "
                    + "WHERE id = ?";

    protected final String QUERY_DELETAR_OBRA =
            "DELETE FROM obra WHERE id = ?";

    protected final String QUERY_DELETAR_CAPITULOS =
            "DELETE FROM capitulo WHERE obra = ?";

    protected final String QUERY_OBTER_OBRAS_MAIS_RECENTES =
            "SELECT  "
                    + "obra.id AS numero, "
                    + "autor, "
                    + "usuario.primeiro_nome AS nome_autor, "
                    + "titulo, "
                    + "descricao, "
                    + "status, "
                    + "obra.data_criacao "
                    + "FROM obra"
                    + "INNER JOIN usuario ON usuario.id = obra.autor "
                    + "WHERE obra.id < IFNULL(?, (SELECT MAX(sub.id) + 1 FROM obra AS sub)) "
                    + "ORDER BY data_criacao DESC ";

    protected final String QUERY_INSERIR_NOVO_CAPITULO =
            "INSERT INTO capitulo ( "
                    + "obra, "
                    + "titulo, "
                    + "data_criacao, "
                    + "numero_ordinal"
                    + ") VALUES (?, ?, ?,  "
                    + "(SELECT IFNULL(MAX(sub.numero_ordinal), 0) + 1 FROM capitulo sub WHERE sub.obra = ?)) ";

    protected final String QUERY_ATUALIZAR_ORDINAL_OBRA =
            "UPDATE capitulo "
                    + "SET numero_ordinal = ? "
                    + "WHERE id = ? ";

}
