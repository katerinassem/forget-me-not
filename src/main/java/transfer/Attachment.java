package transfer;

import java.util.Date;

/**
 * Created by Катерина on 23.02.2015.
 */
public class Attachment extends TransferObject
{
    String fileName;
    Date uploadDate;
    String comment;
    Contact contact;

    public Attachment() {}

    public Attachment(Integer id, String fileName, Date uploadDate, String comment, Contact contact) {
        super(id);
        this.fileName = fileName;
        this.uploadDate = uploadDate;
        this.comment = comment;
        this.contact = contact;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact _contact) {
        contact = _contact;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String _fileName) {
        fileName = _fileName;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date _uploadDate) {
        uploadDate = _uploadDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String _comment) {
        comment = _comment;
    }
}
