package com.cadernetadegastos;

import android.content.Context;


import androidx.test.core.app.ApplicationProvider;

import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.robolectric.RobolectricTestRunner;
import org.junit.Test;

@RunWith(RobolectricTestRunner.class)
public class FornecedorDaoTest {

    @Test
    public void insertFornecedor(){

        final Context context = ApplicationProvider.getApplicationContext();

        FornecedorDao fornecedorDao = new FornecedorDao(context);

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setEmail("teste");

        long id = fornecedorDao.insert(fornecedor);
        Fornecedor fornecedorDoBanco = fornecedorDao.get(id);
        assertEquals("teste",fornecedorDoBanco.getEmail());
    }
}
