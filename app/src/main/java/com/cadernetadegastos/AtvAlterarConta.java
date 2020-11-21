package com.cadernetadegastos;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AtvAlterarConta extends AppCompatActivity {

    private TextView userNameRegister;
    private TextView emailRegister;
    private TextView phoneNumberRegister;
    private TextView edtRenda;

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atv_alterar_conta);


        userNameRegister = findViewById(R.id.userNameRegister);
        emailRegister = findViewById(R.id.emailRegister);
        phoneNumberRegister = findViewById(R.id.phoneNumberRegister);
        edtRenda = findViewById(R.id.edtRenda);

        preencher();
    }

    private void preencher(){
        UsuarioLogado usuarioLogado = new UsuarioLogado(this);
        this.usuario = usuarioLogado.logado();

        userNameRegister.setText(usuario.getLogin());
        emailRegister.setText(usuario.getEmail());
        phoneNumberRegister.setText(usuario.getFone());
        edtRenda.setText(String.valueOf(usuario.getRenda()));
    }

    public void btnAlterar(View v){
        usuario.setLogin(userNameRegister.getText().toString());
        usuario.setEmail(emailRegister.getText().toString());
        usuario.setFone(phoneNumberRegister.getText().toString());
        usuario.setRenda(Float.parseFloat(edtRenda.getText().toString()));

        UsuarioDao usuarioDao = new UsuarioDao(this);
        usuarioDao.update(usuario);

        finish();
    }

    public void voltar(View v){
        finish();
    }
}
