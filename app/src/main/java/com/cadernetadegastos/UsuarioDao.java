package com.cadernetadegastos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDao implements Dao<Usuario> {

    public static final String TABELA = "usuario";

    public static final String SQL_TABLE_USUARIO = "create table if not exists "+ TABELA +" ( " +
            " id integer primary key autoincrement, " +
            "login varchar(50), " +
            "senha varchar(50), " +
            "email varchar(50), " +
            "fone varchar(50), " +
            "renda varchar(50) );";

    private final String[] CAMPOS = {"id, login, senha, email, fone, renda"};
    private Conexao conexao;
    private SQLiteDatabase banco;

    public UsuarioDao(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    private ContentValues preencherValores(Usuario usuario){
        ContentValues values = new ContentValues();

        values.put("login",usuario.getLogin());
        values.put("senha",usuario.getSenha());
        values.put("email",usuario.getEmail());
        values.put("fone",usuario.getFone());
        values.put("renda",usuario.getRenda());

        return values;
    }

    @Override
    public long insert(Usuario usuario) {
        ContentValues values = preencherValores(usuario);
        long id = banco.insert(TABELA,null,values);
        usuario.setId(id);
        return id;
    }

    @Override
    public long update(Usuario usuario) {
        ContentValues values = preencherValores(usuario);
        return banco.update(TABELA,values,"id = ?", new String[]{String.valueOf(usuario.getId())});
    }

    @Override
    public List<Usuario> list() {
        Cursor c = banco.query(TABELA,CAMPOS,null,null,null,null,null);
        List<Usuario> lista = new ArrayList<>();
        while (c.moveToNext()){
            Usuario usuario = new Usuario();
            usuario.setId(c.getLong(0));
            usuario.setLogin(c.getString(1));
            usuario.setSenha(c.getString(2));
            usuario.setEmail(c.getString(3));
            usuario.setFone(c.getString(4));
            usuario.setRenda(c.getFloat(5));
            lista.add(usuario);
        }
        return lista;
    }

    @Override
    public long remove(Usuario usuario) {
        return remove(usuario.getId());
    }

    @Override
    public long remove(long id) {
        return banco.delete(TABELA,"id = ?",new String[]{String.valueOf(id)});
    }

    @Override
    public Usuario get(long id) {
        Cursor c = banco.query(TABELA,CAMPOS,null,null,null,null,null);
        while (c.moveToNext()){
            long idUsuario = c.getLong(0);

            if(idUsuario == id){
                Usuario usuario = new Usuario();
                usuario.setId(idUsuario);
                usuario.setLogin(c.getString(1));
                usuario.setSenha(c.getString(2));
                usuario.setEmail(c.getString(3));
                usuario.setFone(c.getString(4));
                usuario.setRenda(c.getFloat(5));
                return usuario;
            }
        }
        return null;
    }
}
