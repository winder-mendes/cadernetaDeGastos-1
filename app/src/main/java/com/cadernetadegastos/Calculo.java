package com.cadernetadegastos;

import androidx.appcompat.app.AppCompatActivity;

public class Calculo extends AppCompatActivity {

    //Gastos(vindo do bd) - Renda(vindo do bd)


    public float CalcularDiferenca(){
        UsuarioDao ud = new UsuarioDao(this);
        LancamentoDao ld = new LancamentoDao(this);
        Usuario user = new Usuario();
        float b = user.getRenda();
        Lancamento l = new Lancamento();
        float a = l.getValor();
        float resultado = b - a;
        return resultado;
    }

}
