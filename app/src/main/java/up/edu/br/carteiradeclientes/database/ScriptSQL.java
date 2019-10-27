package up.edu.br.carteiradeclientes.database;

public class ScriptSQL {

    public static String getCreateTableCliente(){

        StringBuilder sql = new StringBuilder();

        sql.append(" CREATE TABLE IF NOT EXISTS cliente ( ");
        sql.append("    codigo INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append("    nome VARCHAR(50) NOT NULL DEFAULT(''), ");
        sql.append("    endereco VARCHAR(250) NOT NULL DEFAULT(''), ");
        sql.append("    email VARCHAR(200) NOT NULL DEFAULT(''), ");
        sql.append("    telefone VARCHAR(17) NOT NULL DEFAULT('') ); ");

        return sql.toString();
    }
}
