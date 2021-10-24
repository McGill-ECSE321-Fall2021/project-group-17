package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.model.*;

import java.util.List;

public class LibraryManagementSystemDTO {
    private int id;
    private List<Address> addressList;
    private List<Person> personList;
    private List<Item> itemList;
    private List<LibraryCard> libraryCardList;
    private List<PersonRole> personRoleList;
    private List<Shift> shiftList;
    private List<ItemInstance> itemInstanceList;
    private List<Reservation> reservationList;
    private List<Loan> loanList;
    private List<LibraryHour> libraryHourList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public List<LibraryCard> getLibraryCardList() {
        return libraryCardList;
    }

    public void setLibraryCardList(List<LibraryCard> libraryCardList) {
        this.libraryCardList = libraryCardList;
    }

    public List<PersonRole> getPersonRoleList() {
        return personRoleList;
    }

    public void setPersonRoleList(List<PersonRole> personRoleList) {
        this.personRoleList = personRoleList;
    }

    public List<Shift> getShiftList() {
        return shiftList;
    }

    public void setShiftList(List<Shift> shiftList) {
        this.shiftList = shiftList;
    }

    public List<ItemInstance> getItemInstanceList() {
        return itemInstanceList;
    }

    public void setItemInstanceList(List<ItemInstance> itemInstanceList) {
        this.itemInstanceList = itemInstanceList;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public List<Loan> getLoanList() {
        return loanList;
    }

    public void setLoanList(List<Loan> loanList) {
        this.loanList = loanList;
    }

    public List<LibraryHour> getLibraryHourList() {
        return libraryHourList;
    }

    public void setLibraryHourList(List<LibraryHour> libraryHourList) {
        this.libraryHourList = libraryHourList;
    }
}
