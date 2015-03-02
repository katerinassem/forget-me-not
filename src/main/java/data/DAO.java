package data;

import java.util.ArrayList;

/**
 * Created by Катерина on 23.02.2015.
 */
public interface DAO<T>
{
    int create (T object) throws Exception;
    T read(int id) throws Exception;
    boolean update (T object) throws Exception;
    boolean delete (T object) throws Exception;
    ArrayList<T> readAll() throws Exception;
}
