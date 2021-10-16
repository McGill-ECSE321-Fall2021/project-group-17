package ca.mcgill.ecse321.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.sql.Date;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Reservation {
    private Integer id;
    private Date dateReserved;
    private Date pickupDay;
    private ItemInstance itemInstance;
  
    @ManyToOne
    @JoinColumn()
    private LibraryManagementSystem system

    public Reservation() {

    }

    public Reservation(Integer id, Date dateReserved, Date pickupDay, ItemInstance itemInstance) {
        this.id = id;
        this.dateReserved = dateReserved;
        this.pickupDay = pickupDay;
        this.itemInstance = itemInstance;
    }

    @Id
    @Column(name="id", updatable=false, nullable=false)
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
  
   public LibraryManagementSystem getSystem() {
        return system;
    }

    public void setSystem(LibraryManagementSystem system) {
        this.system = system;
}
