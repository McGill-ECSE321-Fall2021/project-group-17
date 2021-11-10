package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.CustomerRepository;
import ca.mcgill.ecse321.library.dao.ItemInstanceRepository;
import ca.mcgill.ecse321.library.dao.LoanRepository;
import ca.mcgill.ecse321.library.dto.LoanDTO;
import ca.mcgill.ecse321.library.model.*;
import ca.mcgill.ecse321.library.service.Exception.LoanException;
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

    @InjectMocks
    private LoanService loanService;

    private static final int LOAN_KEY = 1;
    private static final int CUSTOMER_KEY = 2;
    private static final int ITEM_INSTANCE_KEY = 3;


    private static final Date startDate = Date.valueOf("2021-10-11");
    private static final Date endDate = Date.valueOf("2021-10-31");

    private static final Date newStartDate = Date.valueOf("2021-11-11");
    private static final Date newEndDate = Date.valueOf("2021-11-31");

    @BeforeEach
    public void setMockOutput() {
        lenient().when(loanRepository.findLoanById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(LOAN_KEY)) {
                Loan loan = new Loan();
                loan.setId(LOAN_KEY);
                Customer customer = new Customer();
                customer.setId(CUSTOMER_KEY);
                loan.setCustomer(customer);
                loan.setReturnDate(endDate);
                loan.setCheckedOut(startDate);
                ItemInstance itemInstance = new ItemInstance();
                itemInstance.setSerialNum(ITEM_INSTANCE_KEY);
                loan.setItemInstance(itemInstance);
                return loan;
            } else {
                return null;
            }
        });

        /*lenient().when(loanRepository.findLoanById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(LOAN_KEY)) {
                Loan loan = new Loan();
                loan.setId(LOAN_KEY);
                return loan;
            } else {
                return null;
            }
        });*/
        lenient().when(loanRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
            Customer customer = new Customer();
            customer.setId(CUSTOMER_KEY);
            Loan loan = new Loan();
            loan.setCustomer(customer);

            List<Loan> loans = new ArrayList<>();
            loans.add(loan);
            loans.get(0).setId(LOAN_KEY);
            return loans;

        });
        lenient().when(customerRepository.findPersonRoleById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(CUSTOMER_KEY)) {
                Customer customer = new Customer();
                customer.setId(CUSTOMER_KEY);
                return customer;
            } else if(invocation.getArgument(0).equals(CUSTOMER_KEY+10)) {
                Customer customer = new Customer();
                customer.setId(CUSTOMER_KEY+10);
                return customer;
            }
            else {
                return null;
            }
        });
        lenient().when(itemInstanceRepository.findItemInstanceBySerialNum(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(ITEM_INSTANCE_KEY)) {
                ItemInstance itemInstance = new ItemInstance();
                itemInstance.setSerialNum(ITEM_INSTANCE_KEY);
                return itemInstance;
            } else  if(invocation.getArgument(0).equals(ITEM_INSTANCE_KEY+10)) {
                ItemInstance itemInstance = new ItemInstance();
                itemInstance.setSerialNum(ITEM_INSTANCE_KEY+10);
                return itemInstance;
            } else {
                return null;
            }
        });
    }

    //UPDATE LOAN

    @Test
    public void testUpdateLoanValid(){
        Loan r = null;
        try{
            r = service.updateLoan(LOAN_KEY, startDate,endDate,CUSTOMER_KEY+10, ITEM_INSTANCE_KEY+10);
        }
        catch (Exception e){
            fail();
        }
        assertNotNull(r);
        assertEquals(r.getId(), LOAN_KEY);
        assertEquals(r.getCheckedOut(),startDate);
        assertEquals(r.getReturnDate(),endDate);
        assertEquals(r.getCustomer().getId(),CUSTOMER_KEY +10);
        assertEquals(r.getItemInstance().getSerialNum(),ITEM_INSTANCE_KEY +10);
    }

    @Test
    public void testUpdateLoanNoCheckOutDate(){
        Loan r = null;
        try{
            r = service.updateLoan(LOAN_KEY, null,newEndDate,CUSTOMER_KEY+10, ITEM_INSTANCE_KEY+10);
        }
        catch (Exception e){
            fail();
        }
        assertNotNull(r);
        assertEquals(r.getId(), LOAN_KEY);
        assertEquals(r.getCheckedOut(),startDate);
        assertEquals(r.getReturnDate(),newEndDate);
        assertEquals(r.getCustomer().getId(),CUSTOMER_KEY +10);
        assertEquals(r.getItemInstance().getSerialNum(),ITEM_INSTANCE_KEY +10);
    }

    @Test
    public void testUpdateLoanNoReturnDate(){
        Loan r = null;
        try{
            r = service.updateLoan(LOAN_KEY, newStartDate,null,CUSTOMER_KEY+10, ITEM_INSTANCE_KEY+10);
        }
        catch (Exception e){
            fail();
        }
        assertNotNull(r);
        assertEquals(r.getId(), LOAN_KEY);
        assertEquals(r.getCheckedOut(),newStartDate);
        assertEquals(r.getReturnDate(),endDate);
        assertEquals(r.getCustomer().getId(),CUSTOMER_KEY +10);
        assertEquals(r.getItemInstance().getSerialNum(),ITEM_INSTANCE_KEY +10);
    }


    @Test
    public void testUpdateLoanNullItemInstance(){
        Loan r = null;
        try{
            r = service.updateLoan(LOAN_KEY, newStartDate,newEndDate,CUSTOMER_KEY+10, null);
        }
        catch (Exception e){
            fail();
        }
        assertNotNull(r);
        assertEquals(r.getId(), LOAN_KEY);
        assertEquals(r.getCheckedOut(),newStartDate);
        assertEquals(r.getReturnDate(),newEndDate);
        assertEquals(r.getCustomer().getId(),CUSTOMER_KEY +10);
        assertEquals(r.getItemInstance().getSerialNum(),ITEM_INSTANCE_KEY);
    }



    @Test
    public void testUpdateLoanMissingItemInstance(){
        Loan r = null;
        String error = "";
        try{
            r = service.updateLoan(LOAN_KEY, newStartDate,newEndDate,CUSTOMER_KEY+10, ITEM_INSTANCE_KEY+1);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(r);
        assertEquals(error,"Cannot find item instance to update loan to");
    }

    @Test
    public void testUpdateLoanNullCustomer(){
        Loan r = null;
        try{
            r = service.updateLoan(LOAN_KEY, newStartDate,newEndDate,null, ITEM_INSTANCE_KEY+10);
        }
        catch (Exception e){
            fail();
        }
        assertNotNull(r);
        assertEquals(r.getId(), LOAN_KEY);
        assertEquals(r.getCheckedOut(),newStartDate);
        assertEquals(r.getReturnDate(),newEndDate);
        assertEquals(r.getCustomer().getId(),CUSTOMER_KEY);
        assertEquals(r.getItemInstance().getSerialNum(),ITEM_INSTANCE_KEY +10);
    }


    @Test
    public void testUpdateLoanMissingCustomer(){
        Loan r = null;
        String error = "";
        try{
            r = service.updateLoan(LOAN_KEY, newStartDate,newEndDate,CUSTOMER_KEY+1, ITEM_INSTANCE_KEY+10);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(r);
        assertEquals(error,"Cannot find person to update loan to");
    }

    @Test
    public void testUpdateLoanNullLoan(){
        Loan r = null;
        String error = "";
        try{
            r = service.updateLoan(null, newStartDate,newEndDate,CUSTOMER_KEY+10, ITEM_INSTANCE_KEY+10);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(r);
        assertEquals(error,"Loan id cannot be null");
    }

    @Test
    public void testUpdateLoanMissingLoan(){
        Loan r = null;
        String error = "";
        try{
            r = service.updateLoan(LOAN_KEY+1, newStartDate,newEndDate,CUSTOMER_KEY+10, ITEM_INSTANCE_KEY+10);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(r);
        assertEquals(error,"Loan cannot be found");
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


    // VIEW ACTIVE LOANS

    @Test
    public void testGetInventory(){
        List <LoanDTO> loans = null;
        try{
            loans = service.viewActiveLoans(CUSTOMER_KEY);
        }
        catch (Exception e){
            fail();
        }
        assertNotNull(loans);
        assertEquals(loans.size(),1);
        assertEquals(loans.get(0).getId(),LOAN_KEY);
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



    // VIEW LOAN RETURN DATE

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
    public void testGetDateLoanNotFound(){
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
        assertEquals("Please provide a valid loan ID", error);
    }

    @Test
    public void testGetDateByNullId(){
        String error = null;
        try{
            loanService.viewLoanReturnDate(LOAN_KEY,null);
        } catch (Exception e){
            error = e.getMessage();
        }
        assertEquals("Please provide a valid customer ID", error);
    }

    @Test
    public void testGetDateCustomerNotFound(){
        String error = null;
        try{
            loanService.viewLoanReturnDate(LOAN_KEY, CUSTOMER_KEY+1);
        } catch (Exception e){
            error = e.getMessage();
        }
        assertEquals("Customer does not exist", error);
    }

    //DELETE LOAN

    @Test
    public void testDeleteLoanValid(){
        try{
            service.deleteLoan(LOAN_KEY,CUSTOMER_KEY);
        }
        catch (Exception e){
            fail();
        }
    }

    @Test
    public void testDeleteLoanNullCustomer(){
        String error = "";
        try{
            service.deleteLoan(LOAN_KEY,null);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertEquals(error,"Cannot authorize customer to delete loan");
    }

    @Test
    public void testDeleteLoanNotExistingCustomer(){
        String error = "";
        try{
            service.deleteLoan(LOAN_KEY,CUSTOMER_KEY+1);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertEquals(error,"Owner of loan does not match customer in request");
    }

    @Test
    public void testDeleteLoanNullReservation(){
        String error = "";
        try{
            service.deleteLoan(null,CUSTOMER_KEY);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertEquals(error,"Cannot find loanId to delete");
    }

    @Test
    public void testDeleteLoanMissingReservation(){
        String error = "";
        try{
            service.deleteLoan(LOAN_KEY + 1,CUSTOMER_KEY);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertEquals(error,"Cannot find loan to delete");
    }

}
