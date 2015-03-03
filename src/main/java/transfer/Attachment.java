package transfer;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Катерина on 23.02.2015.
 */
public class Attachment extends TransferObject
{
    String fileName;
    String uniqueName;
    Date uploadDate;
    String comment;
    Integer contactId;

    public Attachment() {}

    public Attachment(Integer id, String fileName, Date uploadDate, String comment, Integer contactId) {
        super(id);
        this.fileName = fileName;
        this.uploadDate = uploadDate;
        this.comment = comment;
        this.contactId = contactId;
        this.uniqueName = UUID.randomUUID().toString();
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

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }
}
