package ca.mcgill.ecse321.library.model;

import javax.persistence.*;

@Entity
public class Address {
    
    private int id;
    private int streetNumber;
    private String street;
    private String city;
    private String country;
    private LibraryManagementSystem system;
    private Customer customer;

    public Address() {}

	public Address(int id, Integer streetNumber, String street, String city, String country, Customer customer) {
		this.id = id;
		this.streetNumber = streetNumber;
		this.street = street;
		this.city = city;
		this.country = country;
		this.customer = customer;
	}

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
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

    @OneToOne
    @JoinColumn
    public Customer getCustomer(){
        return this.customer;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

}
