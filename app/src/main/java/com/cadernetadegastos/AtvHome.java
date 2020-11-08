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


}