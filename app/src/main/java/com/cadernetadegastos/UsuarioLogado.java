package com.cadernetadegastos;

import android.content.Context;
import android.content.SharedPreferences;

public class UsuarioLogado {
    private final String CONFIG = "config";
    private final String KEY = "logado";
    private Context context;
    public UsuarioLogado(Context context){
        this.context = context;
    }

    public void logar(long id){
        SharedPreferences pref = context.getSharedPreferences(CONFIG,0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong("logado",id);
        editor.commit();
    }

    public void logar(Usuario usuario){
        logar(usuario.getId());
    }

    public Long logadoId(){
        SharedPreferences pref = context.getSharedPreferences(CONFIG, 0);
        long id = pref.getLong(KEY, 0);
        return id == 0 ? null : id;
    }

    public Usuario logado(){
        UsuarioDao usuarioDao = new UsuarioDao(context);
        Long id = logadoId();
        return id == null ? null : usuarioDao.get(id);
    }

    public void logout(){
        SharedPreferences pref = context.getSharedPreferences(CONFIG,0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong("logado",0);
        editor.commit();
    }
}
