package com.cadernetadegastos;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


@RunWith(RobolectricTestRunner.class)
public class UsuarioDaoTest {

    @Test
    public void test(){
        final Context context = ApplicationProvider.getApplicationContext();

        UsuarioDao usuarioDao = new UsuarioDao(context);

        Usuario usuario = new Usuario();
        usuario.setLogin("matheus");

        long id = usuarioDao.insert(usuario);
        assertNotEquals(-1, id);
        Usuario usuarioDoBanco = usuarioDao.get(id);
        assertNotEquals(null,usuarioDoBanco);
        assertEquals("matheus",usuarioDoBanco.getLogin());

        usuarioDao.update(usuario);

        usuarioDoBanco = usuarioDao.get(id);
        assertNotEquals(null,usuarioDoBanco);
        assertEquals("matheus",usuarioDoBanco.getLogin());

        usuarioDao.remove(usuario);
        usuarioDoBanco = usuarioDao.get(id);
        assertEquals(null,usuarioDoBanco);
    }

    @Test
    public void testList(){
        final Context context = ApplicationProvider.getApplicationContext();

        UsuarioDao usuarioDao = new UsuarioDao(context);

        Usuario usuario1 = new Usuario();
        usuario1.setSenha("alimentação1");
        usuarioDao.insert(usuario1);

        Usuario usuario2 = new Usuario();
        usuario2.setSenha("alimentação2");
        usuarioDao.insert(usuario2);

        List<Usuario> usuarios = usuarioDao.list();

        assertEquals(2, usuarios.size());

        usuarioDao.remove(usuario2);

        usuarios = usuarioDao.list();

        assertEquals(1, usuarios.size());
    }
}
