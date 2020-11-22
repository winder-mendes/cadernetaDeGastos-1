package com.cadernetadegastos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AtvCriaFornecedor extends AppCompatActivity {
    private TextView editNome;
    private TextView editEmail;
    private TextView editTelefone;
    private TextView editUf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atv_cria_fornecedor);

        carregarComponentes();

        LinearLayout tela = findViewById(R.id.tela);
        FornecedorDao fornecedorDao = new FornecedorDao(this);

        Bundle extras = getIntent().getExtras();
        String acao = extras.getString("acao");

        if(acao.equals("cadastrar")){
            findViewById(R.id.btnCadastrar).setVisibility(View.VISIBLE);
        }else if(acao.equals("alterar")){
            findViewById(R.id.btnAlterar).setVisibility(View.VISIBLE);
            findViewById(R.id.btnExcluir).setVisibility(View.VISIBLE);

            Long id = extras.getLong("idFornecedor");
            Fornecedor fornecedor = fornecedorDao.get(id);
            preencherTela(fornecedor);
        }
    }

    public void excluir(View v){
        FornecedorDao fornecedorDao = new FornecedorDao(this);
        Bundle extras = getIntent().getExtras();
        Long id = extras.getLong("idFornecedor");
        Fornecedor fornecedor = fornecedorDao.get(id);

        fornecedorDao.remove(fornecedor);
        finish();
    }

    public void alterar(View v){
        FornecedorDao fornecedorDao = new FornecedorDao(this);
        Bundle extras = getIntent().getExtras();
        Long id = extras.getLong("idFornecedor");
        Fornecedor fornecedor = fornecedorDao.get(id);

        atualizarValoresFornecedor(fornecedor);
        fornecedorDao.update(fornecedor);
        finish();
    }

    public void criar(View v){
        FornecedorDao fornecedorDao = new FornecedorDao(this);
        Fornecedor fornecedor = new Fornecedor();
        atualizarValoresFornecedor(fornecedor);

        fornecedorDao.insert(fornecedor);
        finish();
    }

    public void voltar(View v){
        finish();
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