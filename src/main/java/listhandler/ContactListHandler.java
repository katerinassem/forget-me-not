package listhandler;

import org.apache.log4j.Logger;
import transfer.Contact;

import java.util.ArrayList;

/**
 * Created by Катерина on 05.03.2015.
 */
public class ContactListHandler implements ListHandler<Contact> {

    private static Logger logger = Logger.getLogger(ContactListHandler.class);

    @Override
    public ArrayList<Contact> handleList(ArrayList<Contact> list, int start, int count) {

        logger.info(" - [ENTERING METHOD handleList(ArrayList<Contact> list, int start, int count), PARAMETERS: ArrayList<Contact> list, int start = " + start + ", int count = " + count + "]");
        if(list == null){
            return null;
        }
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        for(int i = start, j = 0; i < list.size() && j < count; i++, j++){
            contacts.add(list.get(i));
        }
        return contacts;
    }
}
