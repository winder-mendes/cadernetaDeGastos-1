package com.cadernetadegastos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AtvLogin extends AppCompatActivity {

    private final String CONFIG = "config";
    EditText user;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        user = findViewById(R.id.user);
        password =findViewById(R.id.password);

        oUsuarioEstaLogado();
    }

    private void oUsuarioEstaLogado() {
        SharedPreferences pref = getSharedPreferences(CONFIG,0);
        long id = pref.getLong("logado",0);
        if(id > 0){
            mudarParaTelaHome();
        }
    }

    private void mudarParaTelaHome(){
        Intent home = new Intent(this,null);

        startActivity(home);
    }

    public void entrar(View v){
        String senha = password.getText().toString();
        String login = user.getText().toString();

        Usuario usuario = buscar(login,senha);
        if(usuario != null){
            SharedPreferences pref = getSharedPreferences(CONFIG,0);
            SharedPreferences.Editor editor = pref.edit();
            editor.putLong("login",usuario.getId());
            editor.commit();
            mudarParaTelaHome();
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
