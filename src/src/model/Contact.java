package model;

public class Contact extends Model {

    private String phone;
    private String email;
    private String address;
    private String zipCode;
    private String city;
    private String province;
    private String country;

    public Contact(String phone, String email, String address, String zipCode, String city,
            String province, String country) {
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.province = province;
        this.country = country;
    }
    
    public Contact(int contactId, String phone, String email, String address, String zipCode, String city,
            String province, String country) {
        this(phone, email, address, zipCode, city, province, country);
        this.modelId = contactId;
    }

    public int getContactId() {
        return modelId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Contact [contactId=" + modelId + ", phone=" + phone + ", email=" + email + ", address=" + address
                + ", zipCode=" + zipCode + ", city=" + city + ", province=" + province + ", country=" + country + "]";
    }
}
