package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestCustomerService {
    @Mock
    private PersonRepository personRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private LibraryCardRepository libraryCardRepository;


    @InjectMocks
    private CustomerService service;
    @InjectMocks
    private PersonService service1;
    @InjectMocks
    private AddressService service2;
    @InjectMocks
    private LibraryCardService service3;


    private static final int PERSON_KEY = 3;
    private static final int PENALTY = 3;
    private static final int LIBRARYCARD_KEY = 7;
    private static final int ADDRESS_KEY = 5;
    private static final boolean VERIFIED = true;

    private static final int CUSTOMER_KEY = 2;
    private static final int CUSTOMER_KEY2 = 8;
    private static final int CUSTOMER_KEY3 = -5;

    @BeforeEach
    public void setMockOutput() {
        lenient().when(libraryCardRepository.findLibraryCardById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(LIBRARYCARD_KEY)) {
                LibraryCard libraryCard = new LibraryCard();
                libraryCard.setId(LIBRARYCARD_KEY);
                return libraryCard;
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
        lenient().when(personRepository.findPersonById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(PERSON_KEY)) {
                Person person = new Person();
                person.setId(PERSON_KEY);
                return person;
            } else {
                return null;
            }
        });
        lenient().when(addressRepository.findAddressById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(ADDRESS_KEY)) {
                Address address = new Address();
                address.setId(ADDRESS_KEY);
                return address;
            } else {
                return null;
            }
        });
    }
    @Test
    @AfterEach
    public void clearDatabase() {
        addressRepository.deleteAll();
        customerRepository.deleteAll();
        libraryCardRepository.deleteAll();
        personRepository.deleteAll();
    }

    /**
     * tests creating a customer
     */
    @Test

    public void createCustomer(){
        Person person=null;
        try{
            person= service1.getPerson(PERSON_KEY);
        }
        catch(Exception e){
            fail();
        }
        Address address=null;
        try{
            address=service2.getAddress(ADDRESS_KEY);
        }
        catch(Exception e){
            fail();
        }
        LibraryCard libraryCard=null;
        try{
            libraryCard=service3.getLibraryCard(LIBRARYCARD_KEY);
        }
        catch(Exception e){
            fail();
        }
        Customer customer = null;
        try{
            customer = service.createCustomer(CUSTOMER_KEY2,person.getId(),0,address.getId(),libraryCard);
        }
        catch (Exception e){
            fail();
        }
        assertNotNull(customer);
        assertEquals(customer.getPerson().getId(),person.getId());
        assertEquals(customer.getAddress().getId(),address.getId());
        assertEquals(customer.getLibraryCard(),libraryCard);

    }

    /**
     * tests what happens when a customer is created with incomplete info
     */
    @Test
    public void createCustomerNotEnoughInfo(){
        String error=null;

        Person person=null;
        Address address=null;
        try{
            address=service2.getAddress(ADDRESS_KEY);
        }
        catch(Exception e){
            fail();
        }
        LibraryCard libraryCard=null;
        try{
            libraryCard=service3.getLibraryCard(LIBRARYCARD_KEY);
        }
        catch(Exception e){
            fail();
        }
        Customer customer = null;
        try{
            customer = service.createCustomer(CUSTOMER_KEY2,null,0,address.getId(),libraryCard);
        }
        catch (Exception e){
           error= e.getMessage();
        }
        assertEquals(error,"Cannot create Customer because person is null");

    }

    /**
     * retrieves customer
     */
    @Test
    public void getCustomer(){
        try{
            service.getCustomer(CUSTOMER_KEY);
        } catch (Exception e){
            fail();
        }
    }

    /**
     * tests what happens when try to find customer that does not exist
     */
    @Test
    public void getCustomerNotFound(){
        String error = null;
        try{
            service.getCustomer(CUSTOMER_KEY2);
        } catch (Exception e){
            error = e.getMessage();
        }
        assertEquals(error,"This customer does not exist");
    }

    /**
     * tests what happens when you retrieve a customer with an invalid id
     */
    @Test
    public void getCustomerInvalid(){
        String error = null;
        try{
            service.getCustomer(CUSTOMER_KEY3);
        } catch (Exception e){
            error = e.getMessage();
        }
        assertEquals(error,"Invalid Id");
    }

    /**
     * tests verifying the customer Address
     */

    @Test
    public void verifyUpdateAddress(){
        Customer customer = null;
        try{
            customer = service.createCustomer(CUSTOMER_KEY,service1.getPerson(PERSON_KEY).getId(),0,service2.getAddress(ADDRESS_KEY).getId(),null);
        }
        catch (Exception e){
            fail();
        }
        try{
            customer.setIsVerified(VERIFIED);
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(customer);
        assertEquals(customer.getIsVerified(),VERIFIED);
    }

    /**
     * tests updating the customer penalty
     */
    @Test
    public void updateCustomerPenalty(){
       Customer customer=null;
        try{
            customer= service.updateCustomer(CUSTOMER_KEY,PENALTY,ADDRESS_KEY,service3.getLibraryCard(LIBRARYCARD_KEY));
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(customer);
        assertEquals(customer.getId(),CUSTOMER_KEY);
        assertEquals(customer.getLibraryCard().getId(),service3.getLibraryCard(LIBRARYCARD_KEY).getId());
        assertEquals(customer.getAddress().getId(),service2.getAddress(ADDRESS_KEY).getId());
        assertEquals(customer.getPenalty(),PENALTY);
    }

    /**
     * tests updating the customer address
     */
    @Test
    public void updateCustomerAddress(){
        Customer customer=null;
        Address address= null;


        try{
            customer= service.updateCustomer(CUSTOMER_KEY,PENALTY,service2.getAddress(ADDRESS_KEY).getId(),service3.getLibraryCard(LIBRARYCARD_KEY));
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(customer);
        assertEquals(customer.getId(),CUSTOMER_KEY);
        assertEquals(customer.getLibraryCard().getId(),service3.getLibraryCard(LIBRARYCARD_KEY).getId());
        assertEquals(customer.getAddress().getId(),service2.getAddress(ADDRESS_KEY).getId());
        assertEquals(customer.getPenalty(),PENALTY);
    }

    /**
     * tests what happens when you try to update the information of a customer that does not exist
     */
    @Test
    public void updateCustomerNotFound(){
        Customer customer=null;
        String error=null;
        try{
            customer= service.updateCustomer(CUSTOMER_KEY2,PENALTY,ADDRESS_KEY,service3.getLibraryCard(LIBRARYCARD_KEY));
        }
        catch(Exception e){
            error= e.getMessage();
        }
        assertEquals(error,"cannot find customer");
    }

    /**
     * tests deleting a customer
     */
    @Test
    public void testDeleteCustomer(){
        try{
            service.deleteCustomer(CUSTOMER_KEY);
        }
        catch (Exception e){
            fail();
        }
    }

    /**
     * testing deleting a customer that does not exist
     */
    @Test
    public void testDeleteCustomerNotFound(){
        String error=null;
        try{
            service.deleteCustomer(CUSTOMER_KEY2);
        }
        catch (Exception e){
            error=e.getMessage();
        }
        assertEquals(error,"Customer does not exist");
    }

    /**
     * tests what happens when you try to delete a customer with an invalid jey
     */
    @Test
    public void testDeleteCustomerInvalid(){
        String error=null;
        try{
            service.deleteCustomer(null);
        }
        catch (Exception e){
            error=e.getMessage();
        }
        assertEquals(error,"Cannot find customer to delete.");
    }

    /**
     * tests verifying the address of a customer that does not exist
     */
    @Test
    public void testVerifyCustomerInvalid(){
        String error=null;
        try{
            service.verifyAddress(CUSTOMER_KEY2);
        }
        catch (Exception e){
            error=e.getMessage();
        }
        assertEquals(error,"Customer not found in system. Address could not be verified");
    }



}
