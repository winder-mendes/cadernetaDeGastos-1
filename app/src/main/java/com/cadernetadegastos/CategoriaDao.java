package com.cadernetadegastos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDao implements Dao<Categoria>{

    public static final String TABELA = "categoria";
    public static final String SQL_TABLE_CATEGORIA = "create table if not exists "+ TABELA +" ( " +
            " id integer primary key autoincrement, " +
            "descricao varchar(50) );";

    private final String[] CAMPOS = {"id, descricao"};
    private Conexao conexao;
    private SQLiteDatabase banco;

    public CategoriaDao(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    private ContentValues preencherValores(Categoria categoria){
        ContentValues values = new ContentValues();

        values.put("descricao",categoria.getDescricao());
        return values;
    }

    @Override
    public long insert(Categoria categoria) {
        ContentValues values = preencherValores(categoria);
        long id = banco.insert(TABELA,null,values);
        categoria.setId(id);
        return id;
    }

    @Override
    public long update(Categoria categoria) {
        ContentValues values = preencherValores(categoria);
        return banco.update(TABELA,values,"id = ?", new String[]{String.valueOf(categoria.getId())});
    }

    @Override
    public List<Categoria> list() {
        Cursor c = banco.query(TABELA,CAMPOS,null,null,null,null,null);
        List<Categoria> lista = new ArrayList<>();
        while (c.moveToNext()){
            Categoria categoria = new Categoria();
            categoria.setId(c.getLong(0));
            categoria.setDescricao(c.getString(1));
            lista.add(categoria);
        }
        return lista;
    }

    @Override
    public long remove(Categoria categoria) {
        return remove(categoria.getId());
    }

    @Override
    public long remove(long id) {
        return banco.delete(TABELA,"id = ?",new String[]{String.valueOf(id)});
    }

    @Override
    public Categoria get(long id) {
        Cursor c = banco.query(TABELA,CAMPOS,null,null,null,null,null);
        while (c.moveToNext()){
            long idCategoria = c.getLong(0);

            if(idCategoria == id){
                Categoria categoria = new Categoria();
                categoria.setId(idCategoria);
                categoria.setDescricao(c.getString(1));
                return categoria;
            }
        }
        return null;
    }
}
