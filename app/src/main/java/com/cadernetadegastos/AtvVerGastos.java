package com.cadernetadegastos;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AtvVerGastos extends AppCompatActivity  implements AdapterView.OnItemClickListener{

   TextView txtValorGastoMensal;
   TextView txtValorSaldoMensal;
   TextView txtValorRendaMensal;
   TextView txtValorGastoTotal;
   TextView txtValorSaldoDolar;
   ArrayAdapter<Lancamento> arrayAdapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atv_ver_gastos);

        extraindoCampos();
    }

    @Override
    protected void onResume() {
        super.onResume();

        UsuarioLogado usuarioLogado = new UsuarioLogado(this);
        Usuario usuario = usuarioLogado.logado();

        LancamentoDao lancamentoDao = new LancamentoDao(this);
        List<Lancamento> lancamentos = lancamentoDao.list(usuario);

        ListView listView = findViewById(R.id.lista);
        this.arrayAdapter = new ArrayAdapter<Lancamento>(this,android.R.layout.simple_list_item_1);
        listView.setAdapter(arrayAdapter);
        arrayAdapter.addAll(lancamentos);
        listView.setOnItemClickListener(this);

        calcular(lancamentos,usuario.getRenda());
    }

    private void calcular(List<Lancamento> lancamentos, float renda){
        Calculo calculo = new Calculo(lancamentos, renda);

        float saldoMensal = calculo.retornSaldo();
        txtValorGastoMensal.setText("R$ " + String.valueOf(calculo.gastoMensal()));
        txtValorSaldoMensal.setText("R$ " +String.valueOf(saldoMensal));
        txtValorRendaMensal.setText("R$ " +String.valueOf(calculo.retornaRenda()));
        txtValorGastoTotal.setText("R$ " +String.valueOf(calculo.retornaGastoTotal()));
        dolar(saldoMensal,txtValorSaldoDolar);
    }

    private void dolar(float saldo,TextView textView){

        BigDecimal saldoReais = new BigDecimal(saldo);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://economia.awesomeapi.com.br")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceMoeda serviceMoeda = retrofit.create(ServiceMoeda.class);
        Call<List<MoedaEstrangeira>> call = serviceMoeda.getMoeda("USD-BRL");
        call.enqueue(new Callback<List<MoedaEstrangeira>>() {
            @Override
            public void onResponse(Call<List<MoedaEstrangeira>> call, Response<List<MoedaEstrangeira>> response) {
                if(response.isSuccessful()){
                    MoedaEstrangeira moeda = response.body().get(0);
                    BigDecimal valorDolar = new BigDecimal(moeda.getBid());
                    BigDecimal dolar = saldoReais.divide(valorDolar,BigDecimal.ROUND_UP);

                    textView.setText("$ " + dolar.toString());
                }
            }

            @Override
            public void onFailure(Call<List<MoedaEstrangeira>> call, Throwable t) {
                textView.setText("Não foi possível calcular");
                t.printStackTrace();
            }
        });
    }

    private void extraindoCampos() {
        txtValorGastoMensal = findViewById(R.id.txtValorGastoMensal);
        txtValorSaldoMensal = findViewById(R.id.txtValorSaldoMensal);
        txtValorRendaMensal= findViewById(R.id.txtValorRendaMensal);
        txtValorGastoTotal = findViewById(R.id.txtValorGastoTotal);
        txtValorSaldoDolar = findViewById(R.id.txtValorSaldoDolar);
    }

    private List<Lancamento> criandoDadosDeExemplo(){
        Lancamento l1 = new Lancamento();
        Lancamento l2 = new Lancamento();
        Lancamento l3 = new Lancamento();

        Calendar c = Calendar.getInstance();
        c.set(2015, Calendar.FEBRUARY, 25);
        Date data = c.getTime();
        c.set(2020, Calendar.NOVEMBER, 9);
        Date data2 = c.getTime();
        c.set(2020, Calendar.NOVEMBER, 9);
        Date data3 = c.getTime();

        l1.setId(1);
        l1.setValor(100);
        l1.setDescricao("l1");
        l1.setData(data);
        l2.setId(2);
        l2.setValor(200);
        l2.setData(data2);
        l2.setDescricao("l2");
        l3.setId(3);
        l3.setValor(150);
        l3.setData(data3);
        l3.setDescricao("l3");

        List<Lancamento> lista = new ArrayList<>();
        lista.add(l1);
        lista.add(l2);
        lista.add(l3);
        return lista;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Lancamento lancamento = arrayAdapter.getItem(position);
        Intent atvAdicionarGasto = new Intent(this,AtvAdicionarGasto.class);
        atvAdicionarGasto.putExtra("acao","alterar");
        atvAdicionarGasto.putExtra("idLancamento",lancamento.getId());
        startActivity(atvAdicionarGasto);
    }

    public void voltar(View v){
        finish();
    }
}