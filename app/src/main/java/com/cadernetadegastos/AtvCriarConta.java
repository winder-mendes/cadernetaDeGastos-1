package com.cadernetadegastos;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AtvCriarConta extends AppCompatActivity {

    private TextView userNameRegister;
    private TextView emailRegister;
    private TextView phoneNumberRegister;
    private TextView passwordRegister;
    private TextView confirmPasswordRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.criar_conta);


        userNameRegister = findViewById(R.id.userNameRegister);
        emailRegister = findViewById(R.id.emailRegister);
        phoneNumberRegister = findViewById(R.id.phoneNumberRegister);
        passwordRegister = findViewById(R.id.passwordRegister);
        confirmPasswordRegister = findViewById(R.id.confirmPasswordRegister);
    }

    public void btnCadastrar(View v){
        String password = passwordRegister.getText().toString();
        String confirmPassword = confirmPasswordRegister.getText().toString();

        if(password.equals(confirmPassword)){
            Usuario usuario = new Usuario();
            usuario.setLogin(userNameRegister.getText().toString());
            usuario.setEmail(emailRegister.getText().toString());
            usuario.setFone(phoneNumberRegister.getText().toString());
            usuario.setSenha(passwordRegister.getText().toString());

            UsuarioDao usuarioDao = new UsuarioDao(this);
            usuarioDao.insert(usuario);

            finish();
        }
        else {
            Toast.makeText(this,"AS SENHAS ESTÃO DIFERENTES",Toast.LENGTH_LONG).show();
        }

    }

    public void btnEntrar(View v){
        finish();
    }
}
