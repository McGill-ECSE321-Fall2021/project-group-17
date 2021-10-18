package ca.mcgill.ecse321.library.model;

import javax.persistence.*;

@Entity
public class ItemInstance {
    private String serialNum;
    private CheckableItem checkableItem;

    public ItemInstance() {}

    private LibraryManagementSystem system;

    public ItemInstance(String serialNum, CheckableItem item) {
        this.serialNum = serialNum;
        this.checkableItem = item;
    }

    @Id
    public String getSerialNum() {
        return this.serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    //had to remove optional tag to get tests to pass idk if this will be a problem later -tom
    @ManyToOne(cascade={CascadeType.ALL})
    public CheckableItem getCheckableItem() {
        return this.checkableItem;
    }

    public void setCheckableItem(CheckableItem item) {
        this.checkableItem = item;
    }

    /*@OneToOne
    public Reservation getReservation() {
        return this.reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }*/

    /*@OneToOne
    public Loan getLoan() {
        return this.loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }*/
    @ManyToOne
    @JoinColumn()
    public LibraryManagementSystem getSystem() {
        return system;
    }

    public void setSystem(LibraryManagementSystem system) {
        this.system = system;
    }
}
