package transfer;

import java.util.Date;

/**
 * Created by Катерина on 23.02.2015.
 */

public abstract class TransferObject
{
    Integer id;

    public TransferObject(){}

    public TransferObject(Integer id)
    {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
