package com.cadernetadegastos;

import android.content.Context;
import android.provider.ContactsContract;


import androidx.test.core.app.ApplicationProvider;

import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.robolectric.RobolectricTestRunner;
import org.junit.Test;

import java.util.List;

@RunWith(RobolectricTestRunner.class)
public class FornecedorDaoTest {


    @Test
    public void test(){
        final Context context = ApplicationProvider.getApplicationContext();

        FornecedorDao fornecedorDao = new FornecedorDao(context);

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setEmail("matheus");

        long id = fornecedorDao.insert(fornecedor);
        assertNotEquals(-1, id);
        Fornecedor fornecedorDoBanco = fornecedorDao.get(id);
        assertNotEquals(null,fornecedorDoBanco);
        assertEquals("matheus",fornecedorDoBanco.getEmail());

        fornecedorDao.update(fornecedor);

        fornecedorDoBanco = fornecedorDao.get(id);
        assertNotEquals(null,fornecedorDoBanco);
        assertEquals("matheus",fornecedorDoBanco.getEmail());

        fornecedorDao.remove(fornecedor);
        fornecedorDoBanco = fornecedorDao.get(id);
        assertEquals(null,fornecedorDoBanco);
    }

    @Test
    public void testList(){
        final Context context = ApplicationProvider.getApplicationContext();

        FornecedorDao fornecedorDao = new FornecedorDao(context);

        Fornecedor fornecedor1 = new Fornecedor();
        fornecedor1.setEmail("alimentação1");
        fornecedorDao.insert(fornecedor1);

        Fornecedor fornecedor2 = new Fornecedor();
        fornecedor2.setEmail("alimentação2");
        fornecedorDao.insert(fornecedor2);

        List<Fornecedor> fornecedores = fornecedorDao.list();

        assertEquals(2, fornecedores.size());

        fornecedorDao.remove(fornecedor2);

        fornecedores = fornecedorDao.list();

        assertEquals(1, fornecedores.size());
    }

}
