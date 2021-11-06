package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.model.Customer;
import ca.mcgill.ecse321.library.model.ItemInstance;

import java.sql.Date;

public class ReservationDTO {
    private Integer id;
    private Date dateReserved;
    private Date pickupDay;
    private ItemInstance itemInstance;
    private Customer customer;

    public ReservationDTO() {

    }

    public ReservationDTO(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Date getDateReserved() {
        return dateReserved;
    }

    public Date getPickupDay() {
        return pickupDay;
    }

    public ItemInstance getItemInstance() {
        return itemInstance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDateReserved(Date dateReserved) {
        this.dateReserved = dateReserved;
    }

    public void setPickupDay(Date pickupDay) {
        this.pickupDay = pickupDay;
    }

    public void setItemInstance(ItemInstance itemInstance) {
        this.itemInstance = itemInstance;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
