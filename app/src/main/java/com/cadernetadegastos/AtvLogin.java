package com.cadernetadegastos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AtvLogin extends AppCompatActivity {

    private UsuarioLogado usuarioLogado;
    private EditText user;
    private EditText password;
    private CheckBox boxLembrarLogin;

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Conexao.incerirDadosIniciais(this);

        usuarioLogado = new UsuarioLogado(this);

        user = findViewById(R.id.user);
        password =findViewById(R.id.password);
        boxLembrarLogin = findViewById(R.id.boxLembrarLogin);

        pref = this.getSharedPreferences("config",0);
        boxLembrarLogin.setChecked(pref.getBoolean("lembrarLogin",false));

        if(boxLembrarLogin.isChecked()){
            oUsuarioEstaLogado();
        }
    }

    private void oUsuarioEstaLogado() {
        Long id = usuarioLogado.logadoId();
        if(id != null){
            mudarParaTelaHome();
        }
    }

    private void mudarParaTelaHome(){
        Intent home = new Intent(this,AtvHome.class);

        startActivity(home);
    }

    public void entrar(View v){
        String senha = password.getText().toString();
        String login = user.getText().toString();

        Usuario usuario = buscar(login,senha);
        if(usuario != null){
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("lembrarLogin",boxLembrarLogin.isChecked());
            editor.commit();
            usuarioLogado.logar(usuario);
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
    public void btnCriarConta(View v){
        Intent atvCriarConta = new Intent(this,AtvCriarConta.class);
        startActivity(atvCriarConta);
    }
}
