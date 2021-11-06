package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.CustomerRepository;
import ca.mcgill.ecse321.library.dao.ItemInstanceRepository;
import ca.mcgill.ecse321.library.dao.LoanRepository;
import ca.mcgill.ecse321.library.model.Customer;
import ca.mcgill.ecse321.library.model.ItemInstance;
import ca.mcgill.ecse321.library.model.Loan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestLoanService {
    @Mock
    private LoanRepository loanRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private ItemInstanceRepository itemInstanceRepository;

    @InjectMocks
    private LoanService service;

    private static final int LOAN_KEY = 1;
    private static final int CUSTOMER_KEY = 2;
    private static final int ITEM_INSTANCE_KEY = 3;

    private static final Date startDate = Date.valueOf("2021-10-11");
    private static final Date endDate = Date.valueOf("2021-10-31");

    @BeforeEach
    public void setMockOutput() {
        lenient().when(loanRepository.findLoanById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(LOAN_KEY)) {
                Loan loan = new Loan();
                loan.setId(LOAN_KEY);
                return loan;
            } else {
                return null;
            }
        });
        lenient().when(customerRepository.findPersonRoleById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(CUSTOMER_KEY)) {
                Customer customer = new Customer();
                customer.setId(CUSTOMER_KEY);
                return customer;
            } else {
                return null;
            }
        });
        lenient().when(itemInstanceRepository.findItemInstanceBySerialNum(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(ITEM_INSTANCE_KEY)) {
                ItemInstance itemInstance = new ItemInstance();
                itemInstance.setSerialNum(ITEM_INSTANCE_KEY);
                return itemInstance;
            } else {
                return null;
            }
        });
    }

    @Test
    public void testGetSystemById(){
        /*int id = LOAN_KEY;
        Loan loan = null;
        try {
            loan = loanRepository.findLoanById(LOAN_KEY);
        }
        catch (Exception e){
            fail();
        }
        assertNotNull(loan);
        assertEquals(id, loan.getId());*/
    }

    @Test
    public void testCreateLoan(){
        int id = LOAN_KEY;

        Loan loan = null;
        try{
            loan = service.createLoan(startDate,ITEM_INSTANCE_KEY,CUSTOMER_KEY,endDate,null);
        }
        catch (Exception e){
            fail();
        }
        assertNotNull(loan);
        assertEquals(id,loan.getId());
        assertNotNull(loan.getCustomer());
        assertEquals(startDate, loan.getCheckedOut());
        assertEquals(endDate, loan.getReturnDate());
        assertNotNull(loan.getItemInstance());
    }
    /*@Test
    public void testCreateLoanNoCustomer(){
        int id = LOAN_KEY;

        Loan loan = null;
        String error = "";
        try{
            loan = service.createLoan(startDate,ITEM_INSTANCE_KEY,null,LIBRARY_MANAGEMENT_SYSTEM_KEY,endDate,null);
        }
        catch (Exception e){
           error = e.getMessage();
        }
        assertNull(loan);
        assertEquals("",error);
    }*/
    @Test
    public void testCreateLoanNoStartDate(){
        int id = LOAN_KEY;

        Loan loan = null;
        String error = "";
        try{
            loan = service.createLoan(null,ITEM_INSTANCE_KEY,CUSTOMER_KEY,endDate,null);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(loan);
        assertEquals("Cannot create loan with no start date",error);
    }
    @Test
    public void testCreateLoanStartAfterEnd(){
        int id = LOAN_KEY;
        Date startDate = Date.valueOf("2021-11-10");
        Loan loan = null;
        String error = "";
        try{
            loan = service.createLoan(startDate,ITEM_INSTANCE_KEY,CUSTOMER_KEY,endDate,null);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(loan);
        assertEquals("Cannot create loan with start date after end date",error);
    }
}
