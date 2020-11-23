package com.cadernetadegastos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LancamentoDao implements Dao<Lancamento>{

    public static final String TABELA = "lancamento";
    public static final String SQL_TABLE_LANCAMENTO = "create table if not exists "+ TABELA +" ( " +
            " id integer primary key autoincrement, " +
            " tipo varchar(50), " +
            " data varchar(50), " +
            " valor float, " +
            " descricao varchar(50), " +
            " id_usuario integer, " +
            " id_categoria integer, " +
            " id_fornecedor integer, " +
            "foreign key(id_usuario)  references "+ UsuarioDao.TABELA + "(id)," +
            "foreign key(id_categoria)  references "+ CategoriaDao.TABELA + "(id)," +
            "foreign key(id_fornecedor)  references "+ FornecedorDao.TABELA + "(id) );";

    private final String[] CAMPOS = {"id, tipo, data, valor, descricao, id_usuario, id_categoria, id_fornecedor"};
    private Conexao conexao;
    private SQLiteDatabase banco;
    private Context context;

    public LancamentoDao(Context context){
        this.context = context;
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    private ContentValues preencherValores(Lancamento lancamento){
        ContentValues values = new ContentValues();

        values.put("tipo",lancamento.getTipo());
        if(lancamento.getData() != null) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String data = df.format(lancamento.getData());
            Log.i("DAO","data inserida " + data);
            values.put("data", data);
        }
        values.put("valor",lancamento.getValor());
        values.put("descricao",lancamento.getDescricao());
        if(lancamento.getUsuario() != null)
            values.put("id_usuario",lancamento.getUsuario().getId());
        if(lancamento.getCategoria() != null)
            values.put("id_categoria",lancamento.getCategoria().getId());
        if(lancamento.getFornecedor() != null)
            values.put("id_fornecedor",lancamento.getFornecedor().getId());

        return values;
    }

    @Override
    public long insert(Lancamento lancamento) {
        ContentValues values = preencherValores(lancamento);
        long id = banco.insert(TABELA,null,values);
        lancamento.setId(id);
        return id;
    }

    @Override
    public long update(Lancamento lancamento) {
        ContentValues values = preencherValores(lancamento);
        return banco.update(TABELA,values,"id = ?", new String[]{String.valueOf(lancamento.getId())});
    }

    public List<Lancamento> list(Usuario usuario) {
        Cursor c = banco.query(TABELA, CAMPOS, "id_usuario = " + usuario.getId(), null, null, null, null);
        List<Lancamento> lista = new ArrayList<>();
        while (c.moveToNext()) {
            Lancamento lancamento = new Lancamento();
            lancamento.setId(c.getLong(0));
            lancamento.setTipo(c.getString(1));

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
                String data = c.getString(2);
                Log.i("DAO","data " + data);
                lancamento.setData(df.parse(data));
            } catch (ParseException e) {
                lancamento.setData(null);
                e.printStackTrace();
            }
            lancamento.setValor(c.getFloat(3));
            lancamento.setDescricao(c.getString(4));
            c.getLong(5);
            c.getLong(6);
            c.getLong(7);
            lancamento.setUsuario(new UsuarioDao(context).get(c.getLong(5)));
            lancamento.setCategoria(new CategoriaDao(context).get(c.getLong(6)));
            lancamento.setFornecedor(new FornecedorDao(context).get(c.getLong(7)));

            lista.add(lancamento);
        }
        return lista;
    }


    @Override
    public List<Lancamento> list() {
        Cursor c = banco.query(TABELA, CAMPOS, null, null, null, null, null);
        List<Lancamento> lista = new ArrayList<>();
        while (c.moveToNext()) {
            Lancamento lancamento = new Lancamento();
            lancamento.setId(c.getLong(0));
            lancamento.setTipo(c.getString(1));

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
                String data = c.getString(2);
                if(data != null){
                    lancamento.setData(df.parse(data));
                }else{
                    lancamento.setData(null);
                }

            } catch (ParseException e) {
                lancamento.setData(null);
                e.printStackTrace();
            }
            lancamento.setValor(c.getFloat(3));
            lancamento.setDescricao(c.getString(4));
            c.getLong(5);
            c.getLong(6);
            c.getLong(7);
            lancamento.setUsuario(new UsuarioDao(context).get(c.getLong(5)));
            lancamento.setCategoria(new CategoriaDao(context).get(c.getLong(6)));
            lancamento.setFornecedor(new FornecedorDao(context).get(c.getLong(7)));

            lista.add(lancamento);
        }
            return lista;
    }

    @Override
    public long remove(Lancamento lancamento) {
        return remove(lancamento.getId());
    }

    @Override
    public long remove(long id) {
        return banco.delete(TABELA,"id = ?",new String[]{String.valueOf(id)});
    }

    @Override
    public Lancamento get(long id) {
        for (Lancamento lancamento : list()){
            if(lancamento.getId() == id) return lancamento;
        }
        return null;
    }
}
