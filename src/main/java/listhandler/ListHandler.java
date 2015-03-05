package listhandler;

import java.util.ArrayList;

/**
 * Created by Катерина on 05.03.2015.
 */
public interface ListHandler<T> {
    public ArrayList<T> handleList(ArrayList<T> list, int start, int count);
}
