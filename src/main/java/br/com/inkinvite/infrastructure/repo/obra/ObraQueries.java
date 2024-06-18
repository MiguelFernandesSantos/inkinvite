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
                    + "FROM obra "
                    + "INNER JOIN usuario ON usuario.id = obra.autor "
                    + "WHERE obra.id < IFNULL(?, (SELECT MAX(sub.id) + 1 FROM obra AS sub)) "
                    + "ORDER BY data_criacao "
                    + "ASC LIMIT 10";

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

    protected final String QUERY_OBTER_OBRA_ESPECIFICA =
            "SELECT  "
                    + "obra.id AS numero, "
                    + "autor, "
                    + "usuario.primeiro_nome AS nome_autor, "
                    + "titulo, "
                    + "descricao, "
                    + "status, "
                    + "obra.data_criacao "
                    + "FROM obra "
                    + "INNER JOIN usuario ON usuario.id = obra.autor "
                    + "WHERE obra.id = ? ";

    protected final String QUERY_OBTER_CAPITULOS_OBRA =
            "SELECT  "
                    + "id AS numero, "
                    + "obra, "
                    + "titulo, "
                    + "data_criacao, "
                    + "numero_ordinal, "
                    + "mimetype "
                    + "FROM capitulo "
                    + "WHERE obra = ? ";

    protected final String QUERY_BUSCAR_OBRAS_PAGINADO =
            "SELECT   "
                    + "obra.id AS numero,  "
                    + "autor,  "
                    + "usuario.primeiro_nome AS nome_autor,  "
                    + "titulo,  "
                    + "descricao,  "
                    + "status,  "
                    + "obra.data_criacao  "
                    + "FROM obra  "
                    + "INNER JOIN usuario ON usuario.id = obra.autor  "
                    + "WHERE obra.id > IFNULL(?, (SELECT MIN(sub.id) - 1 FROM obra AS sub)) "
                    + "AND (descricao LIKE ? OR titulo LIKE ?) "
                    + "ORDER BY obra.id "
                    + "LIMIT ? ";

    protected final String QUERY_ALTERAR_MIMETYPE_CAPITULO =
            "UPDATE capitulo "
                    + "SET mimetype = ? "
                    + "WHERE obra = ? AND id = ? ";

    protected final String QUERY_VERIFICAR_EXISTENCIA_CAPITULO =
            "SELECT "
                    + "COUNT(*) AS quantidade "
                    + "FROM capitulo WHERE id = ? AND obra = ?";

    protected final String QUERY_OBTER_CAPITULO_OBRA_ESPECIFICA =
            "SELECT "
                    + "id AS numero, "
                    + "obra, "
                    + "titulo, "
                    + "data_criacao, "
                    + "numero_ordinal,  "
                    + "mimetype "
                    + "FROM capitulo WHERE id = ? AND obra = ?";
}

