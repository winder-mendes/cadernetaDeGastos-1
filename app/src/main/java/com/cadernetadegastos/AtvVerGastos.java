package com.cadernetadegastos;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AtvVerGastos extends AppCompatActivity  {

   TextView txtValorGastoMensal;
   TextView txtValorSaldoMensal;
   TextView txtValorRendaMensal;
   TextView txtValorGastoTotal;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atv_ver_gastos);

        txtValorGastoMensal = findViewById(R.id.txtValorGastoMensal);
        txtValorSaldoMensal = findViewById(R.id.txtValorSaldoMensal);
        txtValorRendaMensal= findViewById(R.id.txtValorRendaMensal);
        txtValorGastoTotal = findViewById(R.id.txtValorGastoTotal);


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

        float renda = 500;
        l1.setId(1);
        l1.setValor(100);
        l1.setData(data);
        l2.setId(2);
        l2.setValor(200);
        l2.setData(data2);
        l3.setId(3);
        l3.setValor(150);
        l3.setData(data3);

        List<Lancamento> lista = new ArrayList<>();
        lista.add(l1);
        lista.add(l2);
        lista.add(l3);

        Calculo calculo = new Calculo(lista, renda);

        txtValorGastoMensal.setText("R$ " + String.valueOf(calculo.gastoMensal()));
        txtValorSaldoMensal.setText("R$ " +String.valueOf(calculo.retornSaldo()));
        txtValorRendaMensal.setText("R$ " +String.valueOf(calculo.retornaRenda()));
        txtValorGastoTotal.setText("R$ " +String.valueOf(calculo.retornaGastoTotal()));


    }

}