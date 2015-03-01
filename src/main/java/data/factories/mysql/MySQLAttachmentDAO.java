package data.factories.mysql;
import data.DAO;
import transfer.Attachment;
import java.util.ArrayList;

/**
 * Created by Катерина on 23.02.2015.
 */
public class MySQLAttachmentDAO implements DAO<Attachment>
{
    @Override
    public void create(Attachment object) {

    }

    @Override
    public Attachment read(int id) {
        return null;
    }

    @Override
    public boolean update(Attachment object) {
        return false;
    }

    @Override
    public boolean delete(Attachment object) {
        return false;
    }

    @Override
    public ArrayList<Attachment> readAll() {
        return null;
    }
}
