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

        Calendar dtAtual = Calendar.getInstance();
        dtAtual.setTime(new Date());
        int mesAtual = dtAtual.get(Calendar.MONTH);
        int anoAtual = dtAtual.get(Calendar.YEAR);

        for (Lancamento lancamento : lista) {

            Calendar dtLancamento = Calendar.getInstance();
            dtLancamento.setTime(lancamento.getData());
            int mesLancamento = dtLancamento.get(Calendar.MONTH);
            int anoLancamento = dtLancamento.get(Calendar.YEAR);

            if(mesAtual == mesLancamento && anoAtual == anoLancamento ){
                gastoMes = gastoMes + lancamento.getValor();
            }
        }
        return gastoMes;
    }

}
