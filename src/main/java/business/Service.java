package business;

import business.bllexception.BLLFatalException;
import business.bllexception.ServiceDataException;
import business.bllexception.ServiceFatalException;

/**
 * Created by Катерина on 05.03.2015.
 */
public interface Service {
    Object service(Object params) throws ServiceFatalException, ServiceDataException;
}
