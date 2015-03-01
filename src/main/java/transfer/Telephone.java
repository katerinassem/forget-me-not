package transfer;

/**
 * Created by Катерина on 23.02.2015.
 */
public class Telephone extends TransferObject
{
    Short countryCode;
    Short operatorCode;
    Long telephoneNumber;
    TelephoneType type;
    String comment;
    Contact contact;

    public Telephone(){};

    public Telephone(Integer id, Short countryCode, Short operatorCode, Long telephoneNumber, TelephoneType type, String comment, Contact contact) {
        super(id);
        this.countryCode = countryCode;
        this.operatorCode = operatorCode;
        this.telephoneNumber = telephoneNumber;
        this.type = type;
        this.comment = comment;
        this.contact = contact;
    }

    public Short getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Short countryCode) {
        this.countryCode = countryCode;
    }

    public Short getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(Short operatorCode) {
        this.operatorCode = operatorCode;
    }

    public Long getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(Long telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public TelephoneType getType() {
        return type;
    }

    public void setType(TelephoneType type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
