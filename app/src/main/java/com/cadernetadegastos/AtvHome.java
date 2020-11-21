package com.cadernetadegastos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AtvHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        usuarioLogado();
    }

    private void usuarioLogado() {
        UsuarioLogado usuarioLogado = new UsuarioLogado(this);
        TextView txtUsuario = findViewById(R.id.txtUsuario);
        String nomeUsuario = usuarioLogado.logado().getLogin();
        txtUsuario.setText("Usuario: "+nomeUsuario);
    }

    public void addGasto(View v){
        Intent atvGastos = new Intent(this,AtvAdicionarGasto.class);
        startActivity(atvGastos);
    }

    public void alterarRenda(View v){
        Intent atvGastos = new Intent(this,AtvAlterarRenda.class);
        startActivity(atvGastos);
    }

    public void verGastos(View v){
        Intent atvGastos = new Intent(this,AtvVerGastos.class);
        startActivity(atvGastos);
    }

    public void fornecedor(View v){
        Intent atvMostraFornecedor = new Intent(this,AtvMostraFornecedor.class );
        startActivity(atvMostraFornecedor);
    }

    public void usuario(View v){
        Intent atvAlterarConta = new Intent(this,AtvAlterarConta.class );
        startActivity(atvAlterarConta);
    }

    public void logout(View v){
        UsuarioLogado usuarioLogado = new UsuarioLogado(this);
        usuarioLogado.logout();
        finish();
    }
}