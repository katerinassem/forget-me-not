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
    public int create(Attachment object) throws Exception {
        int generatedId = -1;
        return generatedId;
    }

    @Override
    public Attachment read(int id)  throws Exception {
        return null;
    }

    @Override
    public boolean update(Attachment object) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Attachment object) throws Exception {
        return false;
    }

    @Override
    public ArrayList<Attachment> readAll() throws Exception {
        return null;
    }
}
