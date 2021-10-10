package ca.mcgill.ecse321.library.model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Address {
	
	private String addressId;
	private Integer streetNumber;
	private String street;
	private String city;
	private String country;
	
	// Address Associations
	// private User user;
	
    @Id
    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

	public boolean setStreetNumber(int streetNumber) {
	    boolean wasSet = false;
	    this.streetNumber = streetNumber;
	    wasSet = true;
	    return wasSet;
	}

	public boolean setStreet(String street) {
	    boolean wasSet = false;
	    this.street = street;
	    wasSet = true;
	    return wasSet;
	}

	public boolean setCity(String city) {
	    boolean wasSet = false;
	    this.city = city;
	    wasSet = true;
	    return wasSet;
	}

	public boolean setCountry(String country) {
	    boolean wasSet = false;
	    this.country = country;
	    wasSet = true;
	    return wasSet;
	}

	public int getStreetNumber() {
	    return streetNumber;
	}

	public String getStreet() {
	    return street;
	}

	public String getCity() {
	    return city;
	}

	public String getCountry() {
	    return country;
	}
	
	  /* Associations */
//	  public User getUser() {
//	    return user;
//	  }

//	  public boolean setUser(User user) {
//	    boolean wasSet = false;
//	    if (user == null) {
//	      //Unable to setUser to null, as address must always be associated to a user
//	      return wasSet;
//	    }
//	    
//	    Address existingAddress = user.getAddress();
//	    if (existingAddress != null && !equals(existingAddress)) {
//	      //Unable to setUser, the current user already has a address, which would be orphaned if it were re-assigned
//	      return wasSet;
//	    }
//	    
//	    User anOldUser = user;
//	    user = user;
//	    user.setAddress(this);
//
//	    if (anOldUser != null) {
//	      anOldUser.setAddress(null);
//	    }
//	    wasSet = true;
//	    return wasSet;
//	  }
	  

//	  public void delete() {
//	    User existingUser = user;
//	    user = null;
//	    if (existingUser != null) {
//	      existingUser.setAddress(null);
//	    }
//	  }


//	  public String toString() {
//	    return super.toString() + "["+
//	            "streetNumber" + ":" + getStreetNumber()+ "," +
//	            "street" + ":" + getStreet()+ "," +
//	            "city" + ":" + getCity()+ "," +
//	            "country" + ":" + getCountry()+ "]" + System.getProperties().getProperty("line.separator") +
//	            "  " + "user = "+(getUser()!=null?Integer.toHexString(System.identityHashCode(getUser())):"null") + System.getProperties().getProperty("line.separator") +
//	            "  " + "libraryManagementSystem = "+(getLibraryManagementSystem()!=null?Integer.toHexString(System.identityHashCode(getLibraryManagementSystem())):"null");
//	  }
}
