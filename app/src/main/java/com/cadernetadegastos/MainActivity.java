package com.cadernetadegastos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //codigo recebido, celso

    //vamos tentar fazer isso certo

    //codigo gabriel

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String senha = "";
        String login = "";

        Usuario usuario = buscar(login,senha);
        if(usuario != null){
            SharedPreferences pref = getSharedPreferences("conf",0);
            SharedPreferences.Editor editor = pref.edit();
            editor.putLong("login",usuario.getId());

        }else{
            Toast.makeText(this,"invalido",Toast.LENGTH_SHORT).show();
        }

    }

    private Usuario buscar(String login,String senha){
        UsuarioDao usuarioDao = new UsuarioDao(this);

        List<Usuario> usuarios = usuarioDao.list();

        for(Usuario usuario : usuarios){
            if(usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)){
                return usuario;
            }
        }
        return  null;
    }
}