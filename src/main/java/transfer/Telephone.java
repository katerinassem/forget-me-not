package transfer;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Катерина on 23.02.2015.
 */
public class Telephone extends TransferObject
{
    Integer countryCode;
    Integer operatorCode;
    Long telephoneNumber;
    TelephoneType type;
    String comment;
    Integer contactId;
    boolean deleted = false;

    public Telephone(){}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append(countryCode);
        sb.append("(");
        sb.append(operatorCode);
        sb.append(")");
        sb.append(telephoneNumber);
        if(type != null){
            sb.append(", ");
            sb.append(type);
        }
        if(StringUtils.isNotEmpty(comment)){
            sb.append(", ");
            sb.append(comment);
        }
        return sb.toString();
    }

    public Telephone(Integer id, Integer countryCode, Integer operatorCode, Long telephoneNumber, TelephoneType type, String comment, Integer contactId) {
        super(id);
        this.countryCode = countryCode;
        this.operatorCode = operatorCode;
        this.telephoneNumber = telephoneNumber;
        this.type = type;
        this.comment = comment;
        this.contactId = contactId;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(Integer operatorCode) {
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

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getTypeString(){
        return type == TelephoneType.h ? "домашний" : "мобильный";
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean getIsDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
