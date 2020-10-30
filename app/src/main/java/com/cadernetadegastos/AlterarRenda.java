package com.cadernetadegastos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AlterarRenda extends AppCompatActivity implements View.OnClickListener {

    EditText editTexNovaRenda;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_renda);

        button = findViewById(R.id.button);
        button.setOnClickListener(this);
        editTexNovaRenda = findViewById(R.id.editTexNovaRenda);

    }




    @Override
    public void onClick(View v) {
        if (v == button){
            
        }

    }
}