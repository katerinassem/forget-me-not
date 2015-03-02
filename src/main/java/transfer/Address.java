package transfer;

/**
 * Created by Катерина on 23.02.2015.
 */
public class Address extends TransferObject
{
    String country;
    String city;
    String street;
    Integer building;
    Integer apartment;
    Long index;
    Integer contactId;

    public Address(){}

    public Address(Integer id, String country, String city, String street, Integer building, Integer apartment, Long index, Integer contactId) {
        super(id);
        this.country = country;
        this.city = city;
        this.street = street;
        this.building = building;
        this.apartment = apartment;
        this.index = index;
        this.contactId = contactId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getBuilding() {
        return building;
    }

    public void setBuilding(Integer building) {
        this.building = building;
    }

    public Integer getApartment() {
        return apartment;
    }

    public void setApartment(Integer apartment) {
        this.apartment = apartment;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

}
