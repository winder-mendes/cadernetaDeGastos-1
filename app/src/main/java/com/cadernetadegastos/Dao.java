package com.cadernetadegastos;

import java.util.List;

public interface Dao<T> {

    public long insert(T t);

    public long update(T t);

    public List<T> list();

    public long remove(T t);

    public long remove(long id);

    public T get(long id);

}
