package transfer;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Катерина on 23.02.2015.
 */
public class Contact extends TransferObject
{
    String firstName;
    String secondName;
    String nameByFather;
    Date dateOfBirth;
    Sex sex;
    String sitizenship;
    String webSite;
    String email;
    String company;
    String photoUrl;

    //  Списки присоединений, адрес, телефоны
    Address address;
    ArrayList<Telephone> telephones;
    ArrayList<Attachment> attachments;

    public Contact(){
        super();
        telephones = new ArrayList<Telephone>();
        attachments = new ArrayList<Attachment>();
    }

    public Contact(Integer id, String firstName, String secondName, String nameByFather, Date dateOfBirth, Sex sex, String sitizenship, String webSite, String email, String company, String photoUrl, Address address) {
        super(id);
        telephones = new ArrayList<Telephone>();
        attachments = new ArrayList<Attachment>();
        this.firstName = firstName;
        this.secondName = secondName;
        this.nameByFather = nameByFather;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.sitizenship = sitizenship;
        this.webSite = webSite;
        this.email = email;
        this.company = company;
        this.photoUrl = photoUrl;
        this.address = address;
    }

    public ArrayList<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(ArrayList<Attachment> _attachments) {
        attachments = _attachments;
    }

    public ArrayList<Telephone> getTelephones() {
        return telephones;
    }

    public void setTelephones(ArrayList<Telephone> _telephones) {
        telephones = _telephones;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address _address) {
        address = _address;
    }

    public String getFirstName()
    {
        return  firstName;
    }

    public void setFirstName(String _firstName)
    {
        firstName = _firstName;
    }

    public String getSecondName()
    {
        return secondName;
    }

    public  void setSecondName(String _secondName)
    {
        secondName = _secondName;
    }

    public String getNameByFather() {
        return nameByFather;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Sex getSex() {
        return sex;
    }

    public String getSitizenship() {
        return sitizenship;
    }

    public String getWebSite() {
        return webSite;
    }

    public String getEmail() {
        return email;
    }

    public String getCompany() {
        return company;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setNameByFather(String _nameByFather) {
        nameByFather = _nameByFather;
    }

    public void setDateOfBirth(Date _dateOfBirth) {
        dateOfBirth = _dateOfBirth;
    }

    public void setSex(Sex _sex) {
        sex = _sex;
    }

    public void setSitizenship(String _sitizenship) {
        sitizenship = _sitizenship;
    }

    public void setWebSite(String _webSite) {
        webSite = _webSite;
    }

    public void setEmail(String _email) {
        email = _email;
    }

    public void setCompany(String _company) {
        company = _company;
    }

    public void setPhotoUrl(String _photoUrl) {
        photoUrl = _photoUrl;
    }
}
