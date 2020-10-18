package com.cadernetadegastos;

import java.util.List;

public interface Dao<T> {

    public void save(T t);

    public List<T> list();

    public void remove(T t);

    public void remove(long id);

    public void get(long id);

}
