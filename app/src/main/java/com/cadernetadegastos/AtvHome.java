package com.cadernetadegastos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AtvHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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

    public void logout(View v){
        UsuarioLogado usuarioLogado = new UsuarioLogado(this);
        usuarioLogado.logout();
        finish();
    }
}