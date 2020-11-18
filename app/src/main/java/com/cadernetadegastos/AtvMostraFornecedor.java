package com.cadernetadegastos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AtvMostraFornecedor extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ArrayAdapter<Fornecedor> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atv_mostra_fornecedor);

        ListView listFornecedor = findViewById(R.id.listFornecedor);
        list = new ArrayAdapter<Fornecedor>(this,android.R.layout.simple_list_item_1);

        listFornecedor.setAdapter(list);
        listFornecedor.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        FornecedorDao fornecedorDao = new FornecedorDao(this);
        list.clear();
        list.addAll(fornecedorDao.list());
    }

    public void telaCadastro(View v){
        Intent telaCadastro = new Intent(this,AtvCriaFornecedor.class);
        telaCadastro.putExtra("acao","cadastrar");
        startActivityForResult(telaCadastro,1);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent atvCriaFornecedor = new Intent(this,AtvCriaFornecedor.class);
        atvCriaFornecedor.putExtra("acao","alterar");
        atvCriaFornecedor.putExtra("idFornecedor",list.getItem(position).getId());
        startActivityForResult(atvCriaFornecedor,1);
    }
}