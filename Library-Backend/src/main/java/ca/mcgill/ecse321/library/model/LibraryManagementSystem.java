package ca.mcgill.ecse321.library.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class LibraryManagementSystem {
    private int id;
    private List<Address> addressList;

    private List<Person> personList;
    private List<Item> itemList;
    private List<Loan> loanList;
    private List<PersonRole> personRoleList;
    private List<Shift> shiftList;
    private List<ItemInstance> itemInstanceList;
    private List<Reservation> reservationList;
    private List<LibraryCard> libraryCardList;
    private List<LibraryHour> libraryHourList;

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "id")
    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "id")
    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "id")
    public List<Item> getItemList() {
        return itemList;
    }
    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name = "id", referencedColumnName = "id")
    public List<LibraryCard> getLibraryCardList() {
        return libraryCardList;
    }

    public void setLibraryCardList(List<LibraryCard> libraryCardList) {
        this.libraryCardList = libraryCardList;
    }

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "id")
    public List<PersonRole> getPersonRoleList() {
        return personRoleList;
    }

    public void setPersonRoleList(List<PersonRole> personRoleList) {
        this.personRoleList = personRoleList;
    }

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "id")
    public List<Shift> getShiftList() {
        return shiftList;
    }

    public void setShiftList(List<Shift> shiftList) {
        this.shiftList = shiftList;
    }

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "serialNum")
    public List<ItemInstance> getItemInstanceList() {
        return itemInstanceList;
    }

    public void setItemInstanceList(List<ItemInstance> itemInstanceList) {
        this.itemInstanceList = itemInstanceList;
    }

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "id")
    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "id")
    public List<Loan> getLoanList() {
        return loanList;
    }

    public void setLoanList(List<Loan> loanList) {
        this.loanList = loanList;
    }

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "id")
    public List<LibraryHour> getLibraryHourList() {
        return libraryHourList;
    }

    public void setLibraryHourList(List<LibraryHour> libraryHourList) {
        this.libraryHourList = libraryHourList;
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
