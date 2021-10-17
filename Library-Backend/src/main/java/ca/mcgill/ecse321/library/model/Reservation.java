package ca.mcgill.ecse321.library.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Reservation {
    private Integer id;
    private Date dateReserved;
    private Date pickupDay;
    private ItemInstance itemInstance;
    //private Customer customer;

    private LibraryManagementSystem system;

    public Reservation() { }

    public Reservation(Integer id, Date dateReserved, Date pickupDay, ItemInstance itemInstance) {
        this.id = id;
        this.dateReserved = dateReserved;
        this.pickupDay = pickupDay;
        this.itemInstance = itemInstance;
    }

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateReserved() {
        return this.dateReserved;
    }

    public void setDateReserved(Date dateReserved) {
        this.dateReserved = dateReserved;
    }

    public Date getPickupDay() {
        return this.pickupDay;
    }

    public void setPickupDay(Date pickupDay) {
        this.pickupDay = pickupDay;
    }

    @OneToOne
    public ItemInstance getItemInstance() {
        return this.itemInstance;
    }

    public void setItemInstance(ItemInstance itemInstance) {
        this.itemInstance = itemInstance;
    }

    /*@OneToOne
    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
