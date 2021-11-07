package ca.mcgill.ecse321.library.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Reservation {
    private Integer id;
    private Date dateReserved;
    private Date pickupDay;
    private ItemInstance itemInstance;
    private Customer customer;


    public Reservation() { }

    public Reservation(Integer id, Date dateReserved, Date pickupDay, ItemInstance itemInstance, Customer customer) {
        //this.id = id;
        this.dateReserved = dateReserved;
        this.pickupDay = pickupDay;
        this.itemInstance = itemInstance;
        this.customer = customer;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer getId() {
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

    @OneToOne
    @JoinColumn
    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


}
