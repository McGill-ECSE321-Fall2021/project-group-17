package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.model.Customer;
import ca.mcgill.ecse321.library.model.ItemInstance;
import ca.mcgill.ecse321.library.model.LibraryManagementSystem;

import java.sql.Date;

public class LoanDTO {
    private Integer id;
    private Date checkedOut;
    private Date returnDate;
    private ItemInstance itemInstance;
    private Customer customer;
    private LibraryManagementSystem system;

    public LoanDTO() {

    }

    public LoanDTO(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Date getCheckedOut() {
        return checkedOut;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public ItemInstance getItemInstance() {
        return itemInstance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LibraryManagementSystem getSystem() {
        return system;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCheckedOut(Date checkedOut) {
        this.checkedOut = checkedOut;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public void setItemInstance(ItemInstance itemInstance) {
        this.itemInstance = itemInstance;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setSystem(LibraryManagementSystem system) {
        this.system = system;
    }
}
