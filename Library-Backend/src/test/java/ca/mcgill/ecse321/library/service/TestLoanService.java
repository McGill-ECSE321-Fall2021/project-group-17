package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.CustomerRepository;
import ca.mcgill.ecse321.library.dao.ItemInstanceRepository;
import ca.mcgill.ecse321.library.dao.LoanRepository;
import ca.mcgill.ecse321.library.model.*;
import ca.mcgill.ecse321.library.service.Exception.LoanException;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    private LoanService loanService;
    private ItemService itemService;

    private static final int LOAN_KEY = 1;
    private static final int CUSTOMER_KEY = 2;
    private static final int ITEM_INSTANCE_KEY = 3;
    private static final int loanID = 123;
    private static final int customerID = 456;

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
    public void testGetInventory(){
        int size = itemService.viewInventory().size();
        assertEquals(size,1);
    }


    @Test
    public void testGetLoan(){
        try{
            loanService.viewActiveLoans(CUSTOMER_KEY);
        } catch (Exception e){
            fail();
        }

    }

    @Test
    public void testGetLoanCustomerNotFound(){
        String error = null;
        try{
            loanService.viewActiveLoans(LOAN_KEY);
        } catch (Exception e){
            error = e.getMessage();
        }
        assertEquals("Customer not found", error);
    }

    @Test
    public void testGetLoanByNullId(){
        String error = null;
        try{
            loanService.viewActiveLoans(null);
        } catch (Exception e){
            error = e.getMessage();
        }
        assertEquals("Cannot find customer", error);
    }


    @Test
    public void testGetDate(){
        Loan loan = null;
        try{
            loan = loanService.viewLoanReturnDate(LOAN_KEY,CUSTOMER_KEY);
        } catch (Exception e){
            fail();
        }
        assertNotNull(loan);
        assertEquals(LOAN_KEY,loan.getId());
    }

    @Test
    public void testGetDateNotFound(){
        String error = null;
        try{
            loanService.viewLoanReturnDate(LOAN_KEY+1, CUSTOMER_KEY);
        } catch (Exception e){
            error = e.getMessage();
        }
        assertEquals("Loan does not exist", error);
    }

    @Test
    public void testGetDateByEmptyId(){
        String error = null;
        try{
            loanService.viewLoanReturnDate(null,CUSTOMER_KEY);
        } catch (Exception e){
            error = e.getMessage();
        }
        assertEquals("Please provide a valid ID", error);
    }


}
