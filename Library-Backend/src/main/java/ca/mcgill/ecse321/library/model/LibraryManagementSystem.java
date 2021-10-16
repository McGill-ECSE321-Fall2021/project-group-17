package ca.mcgill.ecse321.library.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class LibraryManagementSystem {
    private int id;
    private Set<Address> addressSet;
    private Set<Person> personSet;
    private Set<Item> itemSet;
    private Set<LibraryCard> libraryCardSet;
    private Set<PersonRole> personRoleSet;
    private Set<Shift> shiftSet;
    private Set<ItemInstance> itemInstanceSet;
    private Set<Reservation> reservationSet;
    private Set<Loan> loanSet;
    private Set<LibraryHour> libraryHourSet;

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "addressID")
    public Set<Address> getAddressSet() {
        return addressSet;
    }

    public void setAddressSet(Set<Address> addressSet) {
        this.addressSet = addressSet;
    }

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "name")
    public Set<Person> getPersonSet() {
        return personSet;
    }

    public void setPersonSet(Set<Person> personSet) {
        this.personSet = personSet;
    }

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "id")
    public Set<Item> getItemSet() {
        return itemSet;
    }
    public void setItemSet(Set<Item> itemSet) {
        this.itemSet = itemSet;
    }

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "id")
    public Set<LibraryCard> getLibraryCardSet() {
        return libraryCardSet;
    }

    public void setLibraryCardSet(Set<LibraryCard> libraryCardSet) {
        this.libraryCardSet = libraryCardSet;
    }

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "id")
    public Set<PersonRole> getPersonRoleSet() {
        return personRoleSet;
    }

    public void setPersonRoleSet(Set<PersonRole> personRoleSet) {
        this.personRoleSet = personRoleSet;
    }

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "id")
    public Set<Shift> getShiftSet() {
        return shiftSet;
    }

    public void setShiftSet(Set<Shift> shiftSet) {
        this.shiftSet = shiftSet;
    }

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "serialNum")
    public Set<ItemInstance> getItemInstanceSet() {
        return itemInstanceSet;
    }

    public void setItemInstanceSet(Set<ItemInstance> itemInstanceSet) {
        this.itemInstanceSet = itemInstanceSet;
    }

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "id")
    public Set<Reservation> getReservationSet() {
        return reservationSet;
    }

    public void setReservationSet(Set<Reservation> reservationSet) {
        this.reservationSet = reservationSet;
    }

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "id")
    public Set<Loan> getLoanSet() {
        return loanSet;
    }

    public void setLoanSet(Set<Loan> loanSet) {
        this.loanSet = loanSet;
    }

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "libraryHourId")
    public Set<LibraryHour> getLibraryHourSet() {
        return libraryHourSet;
    }

    public void setLibraryHourSet(Set<LibraryHour> libraryHourSet) {
        this.libraryHourSet = libraryHourSet;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
