package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.CustomerRepository;
import ca.mcgill.ecse321.library.dao.ItemInstanceRepository;
import ca.mcgill.ecse321.library.dao.LoanRepository;
import ca.mcgill.ecse321.library.dto.LoanDTO;
import ca.mcgill.ecse321.library.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.sql.Date;


import static org.mockito.ArgumentMatchers.any;

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

    private static final int LOAN_KEY = 0;
    private static final int CUSTOMER_KEY = 2;
    private static final int ITEM_INSTANCE_KEY = 3;
    private static final int TAKEN_ITEM_INSTANCE_KEY = 103;

    private static final int GREEDY_CUSTOMER_KEY = 102;


    private static final Date startDate = Date.valueOf("2021-10-11");
    private static final Date endDate = Date.valueOf("2021-10-31");

    private static final Date newStartDate = Date.valueOf("2021-10-21");
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

        lenient().when(loanRepository.findByItemInstance(any())).thenAnswer((InvocationOnMock invocation) -> {
            if(((ItemInstance)invocation.getArgument(0)).getSerialNum() == (TAKEN_ITEM_INSTANCE_KEY)) {
                Loan loan = new Loan();
                loan.setItemInstance(new ItemInstance());
                loan.setId(LOAN_KEY);
                return loan;
            } else {
                return null;
            }
        });
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
        lenient().when(loanRepository.findLoansByCustomer(any())).thenAnswer((InvocationOnMock invocation) -> {
            if(((Customer)invocation.getArgument(0)).getId() == (GREEDY_CUSTOMER_KEY)) {
                List<Loan> loans = new ArrayList<>();
                for(int i = 0; i < 6; i++){
                    loans.add(new Loan());
                }
                return loans;
            } else {
                return null;
            }
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
            else if(invocation.getArgument(0).equals(GREEDY_CUSTOMER_KEY)) {
                Customer customer = new Customer();
                customer.setId(GREEDY_CUSTOMER_KEY);
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
            }
            else  if(invocation.getArgument(0).equals(TAKEN_ITEM_INSTANCE_KEY)) {
                ItemInstance itemInstance = new ItemInstance();
                itemInstance.setSerialNum(TAKEN_ITEM_INSTANCE_KEY);
                return itemInstance;
            }
            else {
                return null;
            }
        });
    }
    @AfterEach
    public void clearDatabase() {
        loanRepository.deleteAll();
        customerRepository.deleteAll();
        itemInstanceRepository.deleteAll();
    }


    // TESTS FOR UPDATE LOAN

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
    public void testUpdateReservationPickupAfterStartStartProvided(){
        Loan loan = null;
        String error = "";
        try{
            loan = service.updateLoan(LOAN_KEY,Date.valueOf(startDate.toLocalDate().plusMonths(3)),endDate,CUSTOMER_KEY,ITEM_INSTANCE_KEY);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(loan);
        assertEquals(error,"Cannot have pickup date before loan date");
    }

    @Test
    public void testUpdateReservationPickupAfterStartPickupProvided(){
        Loan loan = null;
        String error = "";
        try{
            loan = service.updateLoan(LOAN_KEY,startDate,Date.valueOf(endDate.toLocalDate().minusMonths(3)),CUSTOMER_KEY,ITEM_INSTANCE_KEY);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(loan);
        assertEquals(error,"Cannot have pickup date before loan date");
    }

    @Test
    public void testUpdateLoanItemInstanceAlreadyLoaned(){
        Loan loan = null;
        String error = "";
        try{
            loan = service.updateLoan(LOAN_KEY,startDate,endDate,CUSTOMER_KEY,TAKEN_ITEM_INSTANCE_KEY);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(loan);
        assertEquals("Item is already on loan",error);
    }

    @Test
    public void testUpdateLoanCustomerMaxedOut(){
        Loan loan = null;
        String error = "";
        try{
            loan = service.updateLoan(LOAN_KEY,startDate,endDate,GREEDY_CUSTOMER_KEY,ITEM_INSTANCE_KEY);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(loan);
        assertEquals("This customer already has the maximum number of loans",error);
    }


    // TESTS FOR CREATE LOAN


    @Test
    public void testCreateLoan(){
        Loan loan = null;
        try{
            loan = service.createLoan(startDate,ITEM_INSTANCE_KEY,CUSTOMER_KEY,endDate);
        }
        catch (Exception e){
            fail();
        }
        assertNotNull(loan);
        assertNotNull(loan.getCustomer());
        assertEquals(startDate, loan.getCheckedOut());
        assertEquals(endDate, loan.getReturnDate());
        assertNotNull(loan.getItemInstance());
    }

    @Test
    public void testCreateLoanNoStartDate(){
        int id = LOAN_KEY;

        Loan loan = null;
        String error = "";
        try{
            loan = service.createLoan(null,ITEM_INSTANCE_KEY,CUSTOMER_KEY,endDate);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(loan);
        assertEquals("Cannot create loan with no start date",error);
    }

    @Test
    public void testCreateLoanNoEndDate(){
        int id = LOAN_KEY;

        Loan loan = null;
        String error = "";
        try{
            loan = service.createLoan(startDate,ITEM_INSTANCE_KEY,CUSTOMER_KEY,null);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNotNull(loan);
        assertEquals(loan.getReturnDate(), Date.valueOf(LocalDate.parse(startDate.toString()).plusDays(21)));
    }

    @Test
    public void testCreateLoanStartAfterEnd(){
        int id = LOAN_KEY;
        Date startDate = Date.valueOf("2021-11-10");
        Loan loan = null;
        String error = "";
        try{
            loan = service.createLoan(startDate,ITEM_INSTANCE_KEY,CUSTOMER_KEY,endDate);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(loan);
        assertEquals("Cannot create loan with start date after end date",error);
    }

    @Test
    public void testCreateLoanNullItemInstanceId(){
        Loan loan = null;
        String error = "";
        try{
            loan = service.createLoan(startDate,null,CUSTOMER_KEY,endDate);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(loan);
        assertEquals("Need to have a item instance for a loan",error);
    }

    @Test
    public void testCreateLoanMissingItemInstance(){
        Loan loan = null;
        String error = "";
        try{
            loan = service.createLoan(startDate,ITEM_INSTANCE_KEY+1,CUSTOMER_KEY,endDate);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(loan);
        assertEquals("Invalid item instance provided",error);
    }

    @Test
    public void testCreateLoanItemInstanceAlreadyLoaned(){
        Loan loan = null;
        String error = "";
        try{
            loan = service.createLoan(startDate,TAKEN_ITEM_INSTANCE_KEY,CUSTOMER_KEY,endDate);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(loan);
        assertEquals("Item is already on loan",error);
    }

    @Test
    public void testCreateLoanNullCustomerId(){
        Loan loan = null;
        String error = "";
        try{
            loan = service.createLoan(startDate,ITEM_INSTANCE_KEY,null,endDate);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(loan);
        assertEquals("Need to have a customer for a loan",error);
    }

    @Test
    public void testCreateLoanMissingCustomer(){
        Loan loan = null;
        String error = "";
        try{
            loan = service.createLoan(startDate,ITEM_INSTANCE_KEY,CUSTOMER_KEY+1,endDate);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(loan);
        assertEquals("Invalid customer provided",error);
    }

    @Test
    public void testCreateLoanCustomerMaxedOut(){
        Loan loan = null;
        String error = "";
        try{
            loan = service.createLoan(startDate,ITEM_INSTANCE_KEY,GREEDY_CUSTOMER_KEY,endDate);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(loan);
        assertEquals("This customer already has the maximum number of loans",error);
    }


    // TESTS FOR VIEW ACTIVE LOANS

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
    public void testGetLoans(){
        try{
            loanService.viewActiveLoans(CUSTOMER_KEY);
        } catch (Exception e){
            fail();
        }

    }

    @Test
    public void testGetLoansCustomerNotFound(){
        String error = null;
        try{
            loanService.viewActiveLoans(LOAN_KEY);
        } catch (Exception e){
            error = e.getMessage();
        }
        assertEquals("Customer not found", error);
    }

    @Test
    public void testGetLoansByNullId(){
        String error = null;
        try{
            loanService.viewActiveLoans(null);
        } catch (Exception e){
            error = e.getMessage();
        }
        assertEquals("Cannot find customer", error);
    }


    // TESTS FOR GET LOAN

    @Test
    public void testGetLoan(){
        Loan loan = null;
        try{
            loan = loanService.getLoan(LOAN_KEY,CUSTOMER_KEY);
        } catch (Exception e){
            fail();
        }
        assertNotNull(loan);
        assertEquals(LOAN_KEY,loan.getId());
    }

    @Test
    public void testGetLoanNotFound(){
        String error = null;
        try{
            loanService.getLoan(LOAN_KEY+1, CUSTOMER_KEY);
        } catch (Exception e){
            error = e.getMessage();
        }
        assertEquals("Loan does not exist", error);
    }

    @Test
    public void testGetLoanByEmptyId(){
        String error = null;
        try{
            loanService.getLoan(null,CUSTOMER_KEY);
        } catch (Exception e){
            error = e.getMessage();
        }
        assertEquals("Please provide a valid loan ID", error);
    }

    @Test
    public void testGetLoanByNullId(){
        String error = null;
        try{
            loanService.getLoan(LOAN_KEY,null);
        } catch (Exception e){
            error = e.getMessage();
        }
        assertEquals("Please provide a valid customer ID", error);
    }

    @Test
    public void testGetLoanCustomerNotFound(){
        String error = null;
        try{
            loanService.getLoan(LOAN_KEY, CUSTOMER_KEY+1);
        } catch (Exception e){
            error = e.getMessage();
        }
        assertEquals("Customer does not exist", error);
    }

    // TESTS FOR DELETE LOAN

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
