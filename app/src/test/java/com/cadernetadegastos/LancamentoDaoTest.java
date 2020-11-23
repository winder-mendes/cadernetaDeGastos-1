package com.cadernetadegastos;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(RobolectricTestRunner.class)
public class LancamentoDaoTest {


        @Test
        public void test(){
            final Context context = ApplicationProvider.getApplicationContext();

            LancamentoDao lancamentoDao = new LancamentoDao(context);

            Lancamento lancamento = new Lancamento();
            lancamento.setDescricao("matheus");

            long id = lancamentoDao.insert(lancamento);
            assertNotEquals(-1, id);
            Lancamento lancamentoDoBanco = lancamentoDao.get(id);
            assertNotEquals(null,lancamentoDoBanco);
            assertEquals("matheus",lancamentoDoBanco.getDescricao());

            lancamentoDao.update(lancamento);

            lancamentoDoBanco = lancamentoDao.get(id);
            assertNotEquals(null,lancamentoDoBanco);
            assertEquals("matheus",lancamentoDoBanco.getDescricao());

            lancamentoDao.remove(lancamento);
            lancamentoDoBanco = lancamentoDao.get(id);
            assertEquals(null,lancamentoDoBanco);
        }

        @Test
        public void testList(){
            final Context context = ApplicationProvider.getApplicationContext();

            LancamentoDao lancamentoDao = new LancamentoDao(context);

            Lancamento lancamento1 = new Lancamento();
            lancamento1.setDescricao("alimentação1");
            lancamentoDao.insert(lancamento1);

            Lancamento lancamento2 = new Lancamento();
            lancamento2.setDescricao("alimentação2");
            lancamentoDao.insert(lancamento2);

            List<Lancamento> lancamentos = lancamentoDao.list();

            assertEquals(2, lancamentos.size());

            lancamentoDao.remove(lancamento2);

            lancamentos = lancamentoDao.list();

            assertEquals(1, lancamentos.size());
        }

    }
