package business;

import business.bllexception.BLLDataException;
import business.bllexception.BLLFatalException;
import data.AbstractDAOFactory;
import data.DAO;
import data.daoexception.DAOFatalException;
import data.daoexception.DAOSQLException;
import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by Катерина on 04.03.2015.
 */
public abstract class Business<T> {

    private static Logger logger = Logger.getLogger(Business.class);

    protected AbstractDAOFactory daoFactory;
    protected DAO<T> entityDAO;

    public Business(AbstractDAOFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }

    public T getObjectById(int id) throws BLLDataException, BLLFatalException
    {
        logger.info(" - [ENTERING METHOD: getObjectById(int id), PARAMETERS: int id = " + id + "]");
        T entity = null;
        try {
            entity = entityDAO.read(id);
        }
        catch (DAOSQLException e) {
            logger.error(" [DAO CAUSED EXCEPTION] - in getObjectById(int id)", e);
            throw new BLLDataException(e);
        }
        catch (DAOFatalException e)
        {
            throw new BLLFatalException(e);
        }
        return  entity;
    }

    public T getFullObjectById(int id) throws BLLDataException, BLLFatalException
    {
        logger.info(" - [ENTERING METHOD: getFullObjectById(int id), PARAMETERS: int id = " + id + "]");
        return getObjectById(id);
    }

    public int createObject(T object) throws BLLDataException, BLLFatalException
    {

        logger.info(" - [ENTERING METHOD: createObject(T object), PARAMETERS: T object = " + object + "]");
        int result = -1;
        try {
            result = entityDAO.create(object);
        }
        catch (DAOSQLException e) {
            logger.error(" [DAO CAUSED EXCEPTION] - in createObject(T object)", e);
            throw new BLLDataException(e);
        }
        catch (DAOFatalException e)
        {
            throw new BLLFatalException(e);
        }
        return  result;
    }

    public boolean updateObject(T object) throws BLLDataException, BLLFatalException
    {
        logger.info(" - [ENTERING METHOD: updateObject(T object), PARAMETERS: T object = " + object + "]");
        boolean result = false;
        try {
            result = entityDAO.update(object);
        }
        catch (DAOSQLException e) {
            logger.error(" [DAO CAUSED EXCEPTION] - in updateObject(T object)", e);
            throw new BLLDataException(e);
        }
        catch (DAOFatalException e)
        {
            throw new BLLFatalException(e);
        }
        return result;
    }

    public boolean deleteObjectById(int id) throws BLLDataException, BLLFatalException
    {
        logger.info(" - [ENTERING METHOD: deleteObjectById(int id), PARAMETERS: int id = " + id + "]");
        boolean result = false;
        try {
            result = entityDAO.delete(id);
        }
        catch (DAOSQLException e) {
            logger.error(" [DAO CAUSED EXCEPTION] - in deleteObjectById(int id)", e);
            throw new BLLDataException(e);
        }
        catch (DAOFatalException e)
        {
            throw new BLLFatalException(e);
        }
        return result;

    }

    public ArrayList<T> readAllObjects() throws BLLDataException, BLLFatalException
    {
        logger.info(" - [ENTERING METHOD: readAllObjects(), NO PARAMETERS]");
        ArrayList<T> objects = null;
        try {
            objects = entityDAO.readAll();
        }
        catch (DAOSQLException e) {
            logger.error(" [DAO CAUSED EXCEPTION] - in readAllObjects()", e);
            throw new BLLDataException(e);
        }
        catch (DAOFatalException e)
        {
            throw new BLLFatalException(e);
        }
        return objects;
    }
}
