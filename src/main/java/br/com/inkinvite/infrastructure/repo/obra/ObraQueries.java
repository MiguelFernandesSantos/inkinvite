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
                    + "autor = ?, titulo = ?, descricao = ?, status = ?"
                    + "WHERE id = ?";

    protected final String QUERY_DELETAR_OBRA =
            "DELETE FROM obra WHERE id = ?";

    protected final String QUERY_OBTER_OBRAS_MAIS_RECENTES =
            "SELECT  "
                    + "id AS numero, "
                    + "autor, "
                    + "'' AS nome_autor, " // TODO obter nome do autor
                    + "titulo, "
                    + "descricao, "
                    + "status, "
                    + "data_criacao "
                    + "FROM obra  "
                    + "WHERE id < IFNULL(?, (SELECT MAX(sub.id) + 1 FROM obra AS sub)) "
                    + "ORDER BY data_criacao DESC ";

}
