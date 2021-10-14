package ca.mcgill.ecse321.library.model;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
public class Address {
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer addressID;

    private int streetNumber;
    private String street;
    private String city;
    private String country;

    @ManyToMany
    private User owner;

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    @Id
    public Integer getAddressID() {
        return addressID;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


}
