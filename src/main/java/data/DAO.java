package data;

import java.util.ArrayList;

/**
 * Created by Катерина on 23.02.2015.
 */
public interface DAO<T>
{
    void create (T object);
    T read(int id);
    boolean update (T object);
    boolean delete (T object);
    ArrayList<T> readAll();
}
