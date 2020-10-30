package com.cadernetadegastos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class FornecedorDao implements Dao<Fornecedor> {

    public static final String TABELA = "fornecedor";
    public static final String SQL_TABLE_FORNECEDOR = "create table if not exists "+ TABELA +" ( " +
            " id integer primary key autoincrement, " +
            "nome varchar(50), " +
            "telefone varchar(50), " +
            "email varchar(50), " +
            "uf varchar(50) );";

    private final String[] CAMPOS = {"id, nome, telefone, email, uf"};
    private Conexao conexao;
    private SQLiteDatabase banco;

    public FornecedorDao(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    private ContentValues preencherValores(Fornecedor fornecedor){
        ContentValues values = new ContentValues();

        values.put("nome",fornecedor.getNome());
        values.put("telefone",fornecedor.getTelefone());
        values.put("email",fornecedor.getEmail());
        values.put("uf",fornecedor.getUf());
        return values;
    }

    @Override
    public long insert(Fornecedor fornecedor) {
        ContentValues values = preencherValores(fornecedor);
        long id = banco.insert(TABELA,null,values);
        fornecedor.setId(id);
        return id;
    }

    @Override
    public long update(Fornecedor fornecedor) {
        ContentValues values = preencherValores(fornecedor);
        return banco.update(TABELA,values,"id = ?", new String[]{String.valueOf(fornecedor.getId())});
    }

    @Override
    public List<Fornecedor> list() {
        Cursor c = banco.query(TABELA,CAMPOS,null,null,null,null,null);
        List<Fornecedor> lista = new ArrayList<>();
        while (c.moveToNext()){
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setId(c.getLong(0));
            fornecedor.setNome(c.getString(1));
            fornecedor.setTelefone(c.getString(2));
            fornecedor.setEmail(c.getString(3));
            fornecedor.setUf(c.getString(4));
            lista.add(fornecedor);
        }
        return lista;
    }

    @Override
    public long remove(Fornecedor fornecedor) {
        return remove(fornecedor.getId());
    }

    @Override
    public long remove(long id) {
        return banco.delete(TABELA,"id = ?",new String[]{String.valueOf(id)});
    }

    @Override
    public Fornecedor get(long id) {
        Cursor c = banco.query(TABELA,CAMPOS,null,null,null,null,null);
        while (c.moveToNext()){
            long idFornecedor = c.getLong(0);

            if(idFornecedor == id){
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(idFornecedor);
                fornecedor.setNome(c.getString(1));
                fornecedor.setTelefone(c.getString(2));
                fornecedor.setEmail(c.getString(3));
                fornecedor.setUf(c.getString(4));
                return fornecedor;
            }
        }
        return null;
    }
}
