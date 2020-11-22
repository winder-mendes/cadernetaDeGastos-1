package com.cadernetadegastos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AtvAdicionarGasto extends AppCompatActivity {

    private EditText editValor;
    private EditText editData;
    private EditText editDescricao;
    private Spinner spinTipoGasto;
    private Spinner spinEstabelecimento;
    private Spinner spinCategoria;

    private Button btnAlterar;
    private Button btnExcluir;
    private Button btnAdicionar;

    List<Fornecedor> fornecedors;
    List<Categoria> categorias;

    private Lancamento lancamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atv_adicionar_gasto);

        carregarCampos();
        preencherSpiners();

        Bundle extras = getIntent().getExtras();

        String acao = extras == null ? "" : extras.getString("acao","");

        if(acao.equals("alterar")){
            LancamentoDao lancamentoDao = new LancamentoDao(this);
            Long id = extras.getLong("idLancamento");
            lancamento = lancamentoDao.get(id);
            preencherCampos();

            btnExcluir.setVisibility(View.VISIBLE);
            btnAlterar.setVisibility(View.VISIBLE);
        }else{
            btnAdicionar.setVisibility(View.VISIBLE);
        }
    }

    private void carregarCampos() {
        editValor = findViewById(R.id.editValor);
        editData = findViewById(R.id.editData);
        editDescricao = findViewById(R.id.editDescricao);
        spinTipoGasto = findViewById(R.id.spinTipoGasto);
        spinEstabelecimento = findViewById(R.id.spinEstabelecimento);
        spinCategoria = findViewById(R.id.spinCategoria);

        btnAlterar = findViewById(R.id.btnAlterar);
        btnExcluir = findViewById(R.id.btnExcluir);
        btnAdicionar = findViewById(R.id.btnAdicionar);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        editData.setText(sdf.format(new Date()));
    }

    private void preencherCampos(){
        editDescricao.setText(lancamento.getDescricao());
        editValor.setText(String.valueOf(lancamento.getValor()));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        editData.setText(sdf.format(lancamento.getData()));

        spinTipoGasto.setSelection(lancamento.getTipo().equals("Despesa") ? 0 : 1);

        for(int i = 0;i < categorias.size(); i++){
            if(categorias.get(i).getDescricao().equals(lancamento.getCategoria().getDescricao())){
                spinCategoria.setSelection(i);
            }
        }

        for(int i = 0;i < fornecedors.size(); i++){
            if(fornecedors.get(i).getNome().equals(lancamento.getFornecedor().getNome())){
                spinEstabelecimento.setSelection(i);
            }
        }
    }

    private void preencherSpiners() {
        FornecedorDao fornecedorDao = new FornecedorDao(this);
        fornecedors = fornecedorDao.list();
        List<String> nomesF = new ArrayList<>();
        for (Fornecedor fornecedor : fornecedors){
            nomesF.add(fornecedor.getNome());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, nomesF);
        spinEstabelecimento.setAdapter(adapter);

        CategoriaDao categoriaDao = new CategoriaDao(this);
        categorias = categoriaDao.list();
        List<String> nomesG = new ArrayList<>();
        for (Categoria categoria : categorias){
            nomesG.add(categoria.getDescricao());
        }

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, nomesG);
        spinCategoria.setAdapter(adapter2);
    }

    public void excluir(View view){
        LancamentoDao lancamentoDao = new LancamentoDao(this);
        lancamentoDao.remove(lancamento);
        finish();
    }

    public void alterar(View view){
        try {
            lancamento.setDescricao(editDescricao.getText().toString());

            String strValor = editValor.getText().toString();
            float valor = Float.parseFloat(strValor);
            lancamento.setValor(valor);

            lancamento.setTipo(spinTipoGasto.getSelectedItem().toString());

            int position = spinEstabelecimento.getSelectedItemPosition();
            int position2 = spinCategoria.getSelectedItemPosition();

            Fornecedor fornecedor = fornecedors.get(position);
            Categoria categoria = categorias.get(position2);

            lancamento.setFornecedor(fornecedor);
            lancamento.setCategoria(categoria);

            String strData = editData.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = null;

            date = sdf.parse(strData);
            lancamento.setData(date);

            lancamento.setUsuario(usuarioLogado());

            LancamentoDao lancamentoDao = new LancamentoDao(this);
            lancamentoDao.update(lancamento);
            finish();
        } catch (ParseException e) {
            Toast.makeText(this, "FORMATO DA DATA INCORRETO!", Toast.LENGTH_SHORT).show();
        }
    }

    public void adicionar(View view){
        try {
            Lancamento lancamento = new Lancamento();
            lancamento.setDescricao(editDescricao.getText().toString());

            String strValor = editValor.getText().toString();
            float valor = Float.parseFloat(strValor);
            lancamento.setValor(valor);

            lancamento.setTipo(spinTipoGasto.getSelectedItem().toString());

            int position = spinEstabelecimento.getSelectedItemPosition();
            int position2 = spinCategoria.getSelectedItemPosition();

            Fornecedor fornecedor = fornecedors.get(position);
            Categoria categoria = categorias.get(position2);

            lancamento.setFornecedor(fornecedor);
            lancamento.setCategoria(categoria);

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
        UsuarioLogado usuarioLogado = new UsuarioLogado(this);
        return usuarioLogado.logado();
    }

    public void voltar(View v){
        finish();
    }
}