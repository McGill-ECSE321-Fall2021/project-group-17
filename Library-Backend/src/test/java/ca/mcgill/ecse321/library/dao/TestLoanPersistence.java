package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestLoanPersistence {
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ItemInstanceRepository itemInstanceRepository;
    @Autowired
    private CheckableItemRepository checkableItemRepository;
    @AfterEach
    public void clearDatabase() {
        loanRepository.deleteAll();
        customerRepository.deleteAll();
        itemInstanceRepository.deleteAll();
        checkableItemRepository.deleteAll();
    }
    @Test
    public void testPersistAndLoadLoan() {
        CheckableItem checkableItem = new Music(1234,"My Brilliant Friend",
                java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12)),"Victoria","Sony");
        checkableItemRepository.save(checkableItem);

        int serialNum = checkableItem.getId();

        ItemInstance itemInstance = new ItemInstance(checkableItem);
        itemInstanceRepository.save(itemInstance);

        Customer customer = new Customer(0, null, 0, null, null, null);
        customerRepository.save(customer);


        Date checkedOut = java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        Date returnDate = java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,16));
        Loan loan = new Loan(0, checkedOut, returnDate, itemInstance, customer);
        loanRepository.save(loan);
        int id = loan.getId();
        loan = loanRepository.findLoanById(id);
        assertNotNull(loan);
        assertEquals(id, loan.getId());
        assertEquals(checkedOut, loan.getCheckedOut());
        assertEquals(returnDate, loan.getReturnDate());
        assertEquals(itemInstance.getSerialNum(), loan.getItemInstance().getSerialNum());
        assertEquals(customer.getId(), loan.getCustomer().getId());
    }

    @Test
    public void findLoanByCheckedOut() {
        List<Loan> loans;


        Book b1 = new Book(5678, "My Brilliant Friend", java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER, 12)), "Victoria", "Harper", "horror");
        CheckableItem checkableItem1 = b1;
        checkableItemRepository.save(checkableItem1);
        int serialnum = checkableItem1.getId();
        ItemInstance item1 = new ItemInstance(checkableItem1);
        itemInstanceRepository.save(item1);
        Book b2 = new Book(5678, "The Lost Child", java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER, 12)), "Victoria", "Harper", "horror");
        CheckableItem checkableItem2 = b2;
        checkableItemRepository.save(checkableItem2);
        int serialnum2 = checkableItem2.getId();
        ItemInstance item2 = new ItemInstance(checkableItem2);
        itemInstanceRepository.save(item2);
        Date checkedOut = java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER, 12));
        Date returnDate = java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER, 16));
        Date returnDate2 = java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER, 16));
        Customer c = new Customer();
        customerRepository.save(c);
        Loan l1 = new Loan(6789, checkedOut, returnDate, item1, c);
        Loan l2 = new Loan(8933, checkedOut, returnDate2, item2, c);

        loanRepository.save(l1);
        loanRepository.save(l2);
        loans = loanRepository.findLoanByCheckedOut(checkedOut);

        assertNotNull(loans);
        assertEquals(2, loans.size());
        l1 = loans.get(0);
        l2 = loans.get(1);
        assertEquals(checkedOut, l1.getCheckedOut());
        assertEquals(checkedOut, l2.getCheckedOut());
        //assertEquals(item1.getSerialNum(), l1.getItemInstance().getSerialNum());
        //assertEquals(item2.getSerialNum(), l2.getItemInstance().getSerialNum());
        assertEquals(c.getId(), l1.getCustomer().getId());
        assertEquals(c.getId(), l2.getCustomer().getId());
    }
}
