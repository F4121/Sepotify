package com.enigma.sepotify.entity;

import com.enigma.sepotify.enums.GenderEnum;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mst_profile")
public class Profile {

    @Id
    @GeneratedValue(generator = "profile_uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "profile_uuid", strategy = "uuid")
    private String id;
    private String firstName;
    private String middleName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    private String email;
    private String phone;
    private Date birthdate;
    private String location;

    @OneToOne(mappedBy = "profile")
    private Account account;

    public Profile() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
