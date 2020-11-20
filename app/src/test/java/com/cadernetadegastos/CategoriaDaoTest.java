package com.cadernetadegastos;

import android.content.Context;


import androidx.test.core.app.ApplicationProvider;

import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.robolectric.RobolectricTestRunner;
import org.junit.Test;

import java.util.List;

@RunWith(RobolectricTestRunner.class)
public class CategoriaDaoTest {

    @Test
    public void insertAndGet(){
        final Context context = ApplicationProvider.getApplicationContext();

        CategoriaDao categoriaDao = new CategoriaDao(context);

        Categoria categoria = new Categoria();
        categoria.setDescricao("alimentação");

        long id = categoriaDao.insert(categoria);
        Categoria categoriaDoBanco = categoriaDao.get(id);
        assertEquals("alimentação",categoriaDoBanco.getDescricao());
    }

    @Test
    public void removeAndList(){
        final Context context = ApplicationProvider.getApplicationContext();

        CategoriaDao categoriaDao = new CategoriaDao(context);

        Categoria categoria1 = new Categoria();
        categoria1.setDescricao("alimentação1");
        categoriaDao.insert(categoria1);

        Categoria categoria2 = new Categoria();
        categoria2.setDescricao("alimentação2");
        categoriaDao.insert(categoria2);

        List<Categoria> categorias = categoriaDao.list();

        assertEquals(2, categorias.size());

        categoriaDao.remove(categoria2);

        categorias = categoriaDao.list();

        assertEquals(1, categorias.size());
    }

    @Test
    public void update(){
        final Context context = ApplicationProvider.getApplicationContext();

        CategoriaDao categoriaDao = new CategoriaDao(context);

        Categoria categoria = new Categoria();
        categoria.setDescricao("alimentação");

        long id = categoriaDao.insert(categoria);

        categoria.setDescricao("posto");

        categoriaDao.update(categoria);

        Categoria categoriaDoBanco = categoriaDao.get(id);
        assertEquals("posto",categoriaDoBanco.getDescricao());
    }
}
