package ca.mcgill.ecse321.library.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class LibraryManagementSystem {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "id")
    private Set<Address> addressSet;

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "name")
    private Set<Person> personSet;

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "id")
    private Set<Item> itemSet;

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "id")
    private Set<LibraryCard> libraryCardSet;

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "id")
    private Set<PersonRole> personRoleSet;

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "id")
    private Set<Shift> shiftSet;

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "id")
    private Set<ItemInstance> itemInstanceSet;

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "id")
    private Set<Reservation> reservationSet;

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "id")
    private Set<Loan> loanSet;

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "id")
    private Set<LibraryHour> libraryHourSet;


    public Set<Address> getAddressSet() {
        return addressSet;
    }

    public void setAddressSet(Set<Address> addressSet) {
        this.addressSet = addressSet;
    }

    public Set<Person> getPersonSet() {
        return personSet;
    }

    public void setPersonSet(Set<Person> personSet) {
        this.personSet = personSet;
    }

    public Set<Item> getItemSet() {
        return itemSet;
    }

    public void setItemSet(Set<Item> itemSet) {
        this.itemSet = itemSet;
    }

    public Set<LibraryCard> getLibraryCardSet() {
        return libraryCardSet;
    }

    public void setLibraryCardSet(Set<LibraryCard> libraryCardSet) {
        this.libraryCardSet = libraryCardSet;
    }

    public Set<PersonRole> getPersonRoleSet() {
        return personRoleSet;
    }

    public void setPersonRoleSet(Set<PersonRole> personRoleSet) {
        this.personRoleSet = personRoleSet;
    }

    public Set<Shift> getShiftSet() {
        return shiftSet;
    }

    public void setShiftSet(Set<Shift> shiftSet) {
        this.shiftSet = shiftSet;
    }

    public Set<ItemInstance> getItemInstanceSet() {
        return itemInstanceSet;
    }

    public void setItemInstanceSet(Set<ItemInstance> itemInstanceSet) {
        this.itemInstanceSet = itemInstanceSet;
    }

    public Set<Reservation> getReservationSet() {
        return reservationSet;
    }

    public void setReservationSet(Set<Reservation> reservationSet) {
        this.reservationSet = reservationSet;
    }

    public Set<Loan> getLoanSet() {
        return loanSet;
    }

    public void setLoanSet(Set<Loan> loanSet) {
        this.loanSet = loanSet;
    }

    public Set<LibraryHour> getLibraryHourSet() {
        return libraryHourSet;
    }

    public void setLibraryHourSet(Set<LibraryHour> libraryHourSet) {
        this.libraryHourSet = libraryHourSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
