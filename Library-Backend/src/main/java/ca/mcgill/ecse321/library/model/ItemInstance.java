package ca.mcgill.ecse321.library.model;

import javax.persistence.*;

@Entity
public class ItemInstance {
    private int id;
    private String serialNum;
    private CheckableItem checkableItem;
    //private Reservation reservation;
    //private Loan loan;

    public ItemInstance() {

    }

    public ItemInstance(int id, String serialNum, CheckableItem item) {
        this.id = id;
        this.serialNum = serialNum;
        this.checkableItem = item;
    }

    @Id
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerialNum() {
        return this.serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    @ManyToOne(optional=false)
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

}
