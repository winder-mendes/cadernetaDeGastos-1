package com.cadernetadegastos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Conexao extends SQLiteOpenHelper{
    private static final String NAME = "banco.db";
    private static final int VERSION = 3;


    public Conexao(Context context){
        super(context,NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UsuarioDao.SQL_TABLE_USUARIO);
        db.execSQL(CategoriaDao.SQL_TABLE_CATEGORIA);
        db.execSQL(FornecedorDao.SQL_TABLE_FORNECEDOR);
        db.execSQL(LancamentoDao.SQL_TABLE_LANCAMENTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+ UsuarioDao.TABELA);
        db.execSQL("drop table if exists "+ CategoriaDao.TABELA);
        db.execSQL("drop table if exists "+ FornecedorDao.TABELA);
        db.execSQL("drop table if exists "+ LancamentoDao.TABELA);
        onCreate(db);
    }
}
