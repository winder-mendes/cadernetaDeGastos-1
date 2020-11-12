package com.cadernetadegastos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

public class Conexao extends SQLiteOpenHelper{
    private static final String NAME = "banco.db";
    private static final int VERSION = 3;
    private Context context;

    public Conexao(Context context){
        super(context,NAME,null,VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UsuarioDao.SQL_TABLE_USUARIO);
        db.execSQL(CategoriaDao.SQL_TABLE_CATEGORIA);
        db.execSQL(FornecedorDao.SQL_TABLE_FORNECEDOR);
        db.execSQL(LancamentoDao.SQL_TABLE_LANCAMENTO);


    }

    public static void incerirDadosIniciais(Context context){
        File db = context.getDatabasePath(NAME);
        if(!db.exists()){
            new Conexao(context);
            dadosIniciais(context);
        }
    }

    private static void dadosIniciais(Context context){
        UsuarioDao usuarioDao = new UsuarioDao(context);
        CategoriaDao categoriaDao = new CategoriaDao(context);
        FornecedorDao fornecedorDao = new FornecedorDao(context);
        LancamentoDao lancamentoDao = new LancamentoDao(context);


        Usuario usuario = new Usuario();
        usuario.setLogin("test");
        usuario.setSenha("test");
        usuario.setRenda(2000);
        usuario.setFone("0000-0000");
        usuario.setEmail("email@email");
        usuarioDao.insert(usuario);

        Categoria semCategoria = new Categoria();
        semCategoria.setDescricao("Sem Categoria");
        categoriaDao.insert(semCategoria);

        Categoria trasporte = new Categoria();
        trasporte.setDescricao("Trasporte");
        categoriaDao.insert(trasporte);

        Categoria alimentacao = new Categoria();
        trasporte.setDescricao("Alimentação");
        categoriaDao.insert(alimentacao);

        Fornecedor semFornecedor = new Fornecedor();
        semFornecedor.setNome("Sem Fornecedor");
        fornecedorDao.insert(semFornecedor);

        Fornecedor posto = new Fornecedor();
        posto.setNome("Posto");
        fornecedorDao.insert(posto);

        Fornecedor superMercado = new Fornecedor();
        superMercado.setNome("Super Mercado");
        fornecedorDao.insert(superMercado);

        Lancamento cachorroQuente = new Lancamento();
        cachorroQuente.setDescricao("Cachorro Quente");
        cachorroQuente.setData(new Date());
        cachorroQuente.setTipo("gasto");
        cachorroQuente.setValor(10.0F);
        cachorroQuente.setUsuario(usuario);
        cachorroQuente.setCategoria(alimentacao);
        cachorroQuente.setFornecedor(superMercado);
        lancamentoDao.insert(cachorroQuente);

        Calendar c = Calendar.getInstance();
        c.set(2022,8,23);

        Lancamento combustivel = new Lancamento();
        combustivel.setDescricao("Cachorro Quente");
        combustivel.setData(c.getTime());
        combustivel.setTipo("gasto");
        combustivel.setValor(100.0F);
        combustivel.setUsuario(usuario);
        combustivel.setCategoria(trasporte);
        combustivel.setFornecedor(posto);
        lancamentoDao.insert(combustivel);
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
