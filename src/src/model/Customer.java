package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Customer extends Model implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String lastname;
    private Gender gender;
    private String idNumber;
    private LocalDate birthDate;

    public Customer(String name, String lastname, Gender gender, String idNumber, LocalDate date) {
        this.name = name;
        this.lastname = lastname;
        this.gender = gender;
        this.idNumber = idNumber;
        this.birthDate = date;
    }
    
    public Customer(int customerId, String name, String lastname, Gender gender, String idNumber, LocalDate date) {
        this(name, lastname, gender, idNumber, date);
        this.modelId = customerId;

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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate date) {
        this.birthDate = date;
    }

    @Override
    public String toString() {
        return "Customer [customerId=" + modelId + ", name=" + name + ", lastname=" + lastname + ", gender=" + gender
        		+ ", IDNumber=" + idNumber + ", birthDate=" + birthDate + "]";
    }

}
