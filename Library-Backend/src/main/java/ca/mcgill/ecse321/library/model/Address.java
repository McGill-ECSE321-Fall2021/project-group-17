package ca.mcgill.ecse321.library.model;

import javax.persistence.*;

@Entity
public class Address {

    private int addressID;
    private int streetNumber;
    private String street;
    private String city;
    private String country;
    private LibraryManagementSystem system;

    public Address() {}

	public Address(int addressID, Integer streetNumber, String street, String city, String country) {
		this.addressID = addressID;
		this.streetNumber = streetNumber;
		this.street = street;
		this.city = city;
		this.country = country;
	}

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getAddressID() {
        return addressID;
    }
    public void setAddressID(int id){
        this.addressID = id;
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

    @ManyToOne
    @JoinColumn()
    public LibraryManagementSystem getSystem() {
        return system;
    }

    public void setSystem(LibraryManagementSystem system) {
        this.system = system;
    }

    /*
    private User user;


    @OneToOne(optional=false)
    public User getUser(){
        return this.user;
    }

    public void setUser(User user){
        this.user = user;
    }

     */

}
