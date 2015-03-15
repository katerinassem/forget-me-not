package business.service;

import business.Service;
import business.bllexception.ServiceDataException;
import business.bllexception.ServiceFatalException;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

/**
 * Created by Катерина on 14.03.2015.
 */
public class ResolveTemplate implements Service {

    @Override
    public Object service(Object params) throws ServiceFatalException, ServiceDataException {

        Integer templateId = (Integer)params;

        STGroup group = new STGroupFile("congratulation.stg");
        ST template = null;
        switch (templateId){
            case 0:{
                template = group.getInstanceOf("congratulation");
            }
            break;
            case 1:{
                template = group.getInstanceOf("winner");
            }
            break;
        }
        return template;
    }
}
