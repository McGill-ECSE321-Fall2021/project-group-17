package ca.mcgill.ecse321.library.model;

import javax.persistence.*;
import java.sql.Date;


@Entity
public class Loan {
    private Integer id;
    private Date checkedOut;
    private Date returnDate;
    private ItemInstance itemInstance;
  

    private LibraryManagementSystem system;

    public Loan() {

    }

    public Loan(Integer id, Date checkedOut, Date returnDate, ItemInstance itemInstance) {
        this.id = id;
        this.checkedOut = checkedOut;
        this.returnDate = returnDate;
        this.itemInstance = itemInstance;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCheckedOut() {
        return this.checkedOut;
    }

    public void setCheckedOut(Date checkedOut) {
        this.checkedOut = checkedOut;
    }

    public Date getReturnDate() {
        return this.returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @OneToOne
    public ItemInstance getItemInstance() {
        return this.itemInstance;
    }

    public void setItemInstance(ItemInstance itemInstance) {
        this.itemInstance = itemInstance;
    }

    @ManyToOne
    @JoinColumn()
    public LibraryManagementSystem getSystem() {
        return system;
    }

    public void setSystem(LibraryManagementSystem system) {
        this.system = system;
    }
}
