package up.edu.br.carteiradeclientes.dominio.repositorios;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import up.edu.br.carteiradeclientes.dominio.entidades.Cliente;

public class ClienteRepositorio {

    private SQLiteDatabase conexao;

    public ClienteRepositorio(SQLiteDatabase conexao){
        this.conexao = conexao;
    }

    public void inserir(Cliente cliente){

        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", cliente.getNome());
        contentValues.put("endereco", cliente.getEndereco());
        contentValues.put("email", cliente.getEmail());
        contentValues.put("telefone", cliente.getTelefone());

        conexao.insertOrThrow("cliente", null, contentValues);
    }


    public void excluir(int codigo){
        String[] parametros = new String[1];
        parametros[0] = String.valueOf(codigo);

        conexao.delete("cliente","codigo = ?", parametros);
    }


    public void alterar(Cliente cliente){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", cliente.getNome());
        contentValues.put("endereco", cliente.getEndereco());
        contentValues.put("email", cliente.getEmail());
        contentValues.put("telefone", cliente.getTelefone());

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(cliente.getCodigo());

        conexao.update("cliente", contentValues, "codigo = ?", parametros);
    }


    public List<Cliente> listarClientes(){

        List<Cliente> clientes = new ArrayList<Cliente>();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT codigo, nome, endereco, email, telefone FROM cliente");

        Cursor cursor = conexao.rawQuery(sql.toString(), null);

        if (cursor.getCount() > 0){

            cursor.moveToFirst();
            do {

                Cliente cliente = new Cliente();

                cliente.setCodigo(cursor.getInt(cursor.getColumnIndexOrThrow("codigo")));
                cliente.setNome(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
                cliente.setEndereco(cursor.getString(cursor.getColumnIndexOrThrow("endereco")));
                cliente.setEmail(cursor.getString(cursor.getColumnIndexOrThrow("email")));
                cliente.setTelefone(cursor.getString(cursor.getColumnIndexOrThrow("telefone")));

                clientes.add(cliente);

            } while (cursor.moveToNext());
        }
        return clientes;
    }


    public Cliente buscarCliente(int codigo){

        Cliente cliente = new Cliente();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT codigo, nome, endereco, email, telefone FROM cliente WHERE codigo=?");

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(codigo);

        Cursor cursor = conexao.rawQuery(sql.toString(), parametros);

        if (cursor.getCount() > 0){
            cliente.setCodigo(cursor.getInt(cursor.getColumnIndexOrThrow("codigo")));
            cliente.setNome(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
            cliente.setEndereco(cursor.getString(cursor.getColumnIndexOrThrow("endereco")));
            cliente.setEmail(cursor.getString(cursor.getColumnIndexOrThrow("email")));
            cliente.setTelefone(cursor.getString(cursor.getColumnIndexOrThrow("telefone")));

            return cliente;
        }

        return null;
    }
}
