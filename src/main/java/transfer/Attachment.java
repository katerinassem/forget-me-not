package transfer;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.UUID;

/**
 * Created by Катерина on 23.02.2015.
 */
public class Attachment extends TransferObject
{
    String fileName;
    DateTime uploadDate;
    String comment;
    Integer contactId;
    boolean deleted = false;

    public Attachment() {
    }

    public Attachment(Integer id, String fileName, DateTime uploadDate, String comment, Integer contactId) {
        super(id);
        this.fileName = fileName;
        this.uploadDate = uploadDate;
        this.comment = comment;
        this.contactId = contactId;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public DateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(DateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getFormattedUploadDate(){
        if(uploadDate == null){
            return null;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("HH:mm:ss, dd.MM.YYYY");
        return dateTimeFormatter.print(uploadDate);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
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
