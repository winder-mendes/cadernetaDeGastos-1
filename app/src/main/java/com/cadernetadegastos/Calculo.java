package com.cadernetadegastos;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.time.LocalDate;
import java.time.Month;

import java.util.Date;
import java.util.List;

public class Calculo {

    private List<Lancamento> lista;
    private float renda;

    public Calculo(List<Lancamento> lista, float renda) {
        this.lista = lista;
        this.renda = renda;
    }

    public float retornaRenda() {
        return renda;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public float retornSaldo() {
        float gasto = gastoMensal();
        float resultado = renda - gasto;
        return resultado;
    }

    public float retornaGastoTotal() {
        float somaTotal = 0;
        for (Lancamento lancamento : lista) {
            somaTotal = somaTotal + lancamento.getValor();
        }
        return somaTotal;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public float gastoMensal() {
        float gastoMes = 0;
        LocalDate currentdate = LocalDate.now();
        Month currentMonth = currentdate.getMonth();
        for (Lancamento lancamento : lista) {
            Month dtLancamento = Month.of(lancamento.getData().getMonth());
            if(dtLancamento == currentMonth){
                gastoMes = gastoMes + lancamento.getValor();
            }
        }
        return gastoMes;
    }

}
