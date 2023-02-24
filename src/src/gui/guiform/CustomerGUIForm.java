package gui.guiform;

import java.time.LocalDate;

import model.Gender;

public class CustomerGUIForm extends GUIForm {

    private String name;
    private String lastname;
    private String gender;
    private String idNumber;
    private String birthDate;

    public CustomerGUIForm(String name, String lastname, String gender, String idNumber, String date) {
        this.name = name;
        this.lastname = lastname;
        this.gender = gender;
        this.idNumber = idNumber;
        this.birthDate = date;
    }

    public CustomerGUIForm(String customerId, String name, String lastname, String gender, String idNumber, String date) {
        this(name, lastname, gender, idNumber, date);
        this.formIdField = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String date) {
        this.birthDate = date;
    }

    @Override
    public String toString() {
        return "CustomerGUIForm [customerId=" + formIdField + ", name=" + name + ", lastname=" + lastname + ", gender=" + gender+ ", idNumber=" + idNumber
                + ", birthDate=" + birthDate + "]";
    }
}
