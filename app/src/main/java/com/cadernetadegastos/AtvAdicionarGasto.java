package com.cadernetadegastos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AtvAdicionarGasto extends AppCompatActivity {

    private EditText editValor;
    private EditText editData;
    private EditText editDescricao;
    private Spinner spinTipoGasto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atv_adicionar_gasto);

        editValor = findViewById(R.id.editValor);
        editData = findViewById(R.id.editData);
        editDescricao = findViewById(R.id.editDescricao);
        spinTipoGasto = findViewById(R.id.spinTipoGasto);
    }

    public void adicionar(View view){
        try {
            Lancamento lancamento = new Lancamento();
            lancamento.setDescricao(editDescricao.getText().toString());

            String strValor = editValor.getText().toString();
            float valor = Float.parseFloat(strValor);
            lancamento.setValor(valor);

            lancamento.setTipo(spinTipoGasto.getSelectedItem().toString());

            String strData = editData.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = null;

            date = sdf.parse(strData);
            lancamento.setData(date);

            lancamento.setUsuario(usuarioLogado());

            LancamentoDao lancamentoDao = new LancamentoDao(this);
            lancamentoDao.insert(lancamento);
            finish();
        } catch (ParseException e) {
            Toast.makeText(this, "FORMATO DA DATA INCORRETO!", Toast.LENGTH_SHORT).show();
        }
    }

    private Usuario usuarioLogado() {
        UsuarioDao usuarioDao = new UsuarioDao( this);
        SharedPreferences pref = getSharedPreferences("config", 0);
        Long id = pref.getLong("logado", 1);
        return usuarioDao.get(id);
    }
}