package com.cadernetadegastos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AtvCriaFornecedor extends AppCompatActivity {
    private TextView editNome;
    private TextView editEmail;
    private TextView editTelefone;
    private TextView editUf;

    private Button cadastrar;
    private Button alterar;
    private Button excluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atv_cria_fornecedor);

        carregarComponentes();

        LinearLayout tela = findViewById(R.id.tela);
        FornecedorDao fornecedorDao = new FornecedorDao(this);

        Button excluir = new Button(this);
        Button alterar = new Button(this);
        Button criar = new Button(this);
        Button voltar = new Button(this);

        voltar.setText(getString(R.string.voltar));
        voltar.setOnClickListener((View v) -> {
            finish();
        });
        tela.addView(voltar);

        Bundle extras = getIntent().getExtras();
        String acao = extras.getString("acao");

        excluir.setText("Excluir");
        excluir.setOnClickListener((View v) -> {
            Long id = extras.getLong("idFornecedor");
            Fornecedor fornecedor = fornecedorDao.get(id);

            fornecedorDao.remove(fornecedor);
            finish();
        });

        alterar.setText("Alterar");
        alterar.setOnClickListener((View v) -> {
            Long id = extras.getLong("idFornecedor");
            Fornecedor fornecedor = fornecedorDao.get(id);

            atualizarValoresFornecedor(fornecedor);
            fornecedorDao.update(fornecedor);
            finish();
        });

        criar.setText("Criar");
        criar.setOnClickListener((View v) -> {
            Fornecedor fornecedor = new Fornecedor();
            atualizarValoresFornecedor(fornecedor);

            fornecedorDao.insert(fornecedor);
            finish();
        });

        if(acao.equals("cadastrar")){
            tela.addView(criar);
        }else if(acao.equals("alterar")){
            Long id = extras.getLong("idFornecedor");

            Fornecedor fornecedor = fornecedorDao.get(id);
            preencherTela(fornecedor);

            tela.addView(alterar);
            tela.addView(excluir);
        }
    }

    private void carregarComponentes(){
        editEmail = findViewById(R.id.editEmail);
        editTelefone = findViewById(R.id.editTelefone);
        editNome = findViewById(R.id.editNome);
        editUf = findViewById(R.id.editUf);
    }

    private void preencherTela(Fornecedor fornecedor){
        editNome.setText(fornecedor.getNome());
        editTelefone.setText(fornecedor.getTelefone());
        editEmail.setText(fornecedor.getEmail());
        editUf.setText(fornecedor.getUf());
    }

    private void atualizarValoresFornecedor(Fornecedor fornecedor){
        fornecedor.setNome(editNome.getText().toString());
        fornecedor.setTelefone(editTelefone.getText().toString());
        fornecedor.setEmail(editEmail.getText().toString());
        fornecedor.setUf(editUf.getText().toString());
    }
}