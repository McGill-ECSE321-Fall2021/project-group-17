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

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestAddressService {
    @Mock
    private PersonRepository personRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private AddressRepository addressRepository;


    @InjectMocks
    private CustomerService customerService;
    @InjectMocks
    private PersonService personService;
    @InjectMocks
    private AddressService addressService;

    private static final int PERSON_KEY = 3;
    private static final int ADDRESS_KEY = 5;
    private static final int ADDRESS_KEY2 = 2;
    private static final int ADDRESS_STREET_NUMBER = 3456;
    private static final int ADDRESS_STREET_NUMBER2 = 3453;
    private static final String ADDRESS_STREET = "Peel";
    private static final String ADDRESS_STREET2 = "Park";
    private static final String ADDRESS_CITY = "Montreal";
    private static final String ADDRESS_CITY2 = "Toronto";
    private static final String ADDRESS_COUNTRY = "Canada";
    private static final String ADDRESS_COUNTRY2 = "US";
    private static final String PERSON_NAME = "victoria";
    private static final int CUSTOMER_KEY = 2;
    @BeforeEach
    public void setMockOutput() {

        lenient().when(personRepository.findPersonById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(PERSON_KEY)) {
                Person person = new Person();
                person.setId(PERSON_KEY);
                return person;
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

        lenient().when(addressRepository.findAddressById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(ADDRESS_KEY)) {
                Customer customer = new Customer();
                customer.setId(CUSTOMER_KEY);
                Address address = new Address();
                address.setId(ADDRESS_KEY);
                address.setCustomer(customer);
                return address;
            } else {
                return null;
            }
        });
    }

    @AfterEach
    public void clearDatabase() {
        addressRepository.deleteAll();
        customerRepository.deleteAll();
        personRepository.deleteAll();
    }


    @Test
    public void createAddress(){
        int id = CUSTOMER_KEY;
        Address address=null;
        try{
            address= addressService.createAddress(ADDRESS_KEY2,ADDRESS_STREET_NUMBER,ADDRESS_STREET,ADDRESS_CITY,ADDRESS_COUNTRY,CUSTOMER_KEY);
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(address);
        assertEquals(address.getId(),ADDRESS_KEY2);
        assertEquals(address.getStreetNumber(),ADDRESS_STREET_NUMBER);
        assertEquals(address.getStreet(),ADDRESS_STREET);
        assertEquals(address.getCity(),ADDRESS_CITY);
        assertEquals(address.getCountry(),ADDRESS_COUNTRY);
        assertEquals(address.getCustomer().getId(),CUSTOMER_KEY);
    }
    @Test
    public void createAddressDuplicateID(){
        String error=null;
        int id = CUSTOMER_KEY;
        Address address=null;
        try{
            address= addressService.createAddress(ADDRESS_KEY,ADDRESS_STREET_NUMBER,ADDRESS_STREET,ADDRESS_CITY,ADDRESS_COUNTRY,CUSTOMER_KEY);
        }
        catch(Exception e){
            error=e.getMessage();
        }
        assertEquals("cannot have two addresses with the same ID",error);
    }
    @Test
    public void createAddressIncompleteInfo(){
        int id = CUSTOMER_KEY;
        String error=null;
        Address address=null;
        try{
            address= addressService.createAddress(ADDRESS_KEY2,ADDRESS_STREET_NUMBER,null,ADDRESS_CITY,ADDRESS_COUNTRY,CUSTOMER_KEY);
        }
        catch(Exception e){
            error=e.getMessage();
        }
       assertEquals(error,"incomplete Address given");
    }
    @Test
    public void updateAddressCity(){
        int id = CUSTOMER_KEY;
        Address address=null;
        try{
            address= addressService.updateAddress(ADDRESS_KEY, ADDRESS_STREET_NUMBER,ADDRESS_STREET,ADDRESS_CITY2,ADDRESS_COUNTRY);
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(address);
        assertEquals(address.getId(),ADDRESS_KEY);
        assertEquals(address.getStreetNumber(),ADDRESS_STREET_NUMBER);
        assertEquals(address.getStreet(),ADDRESS_STREET);
        assertEquals(address.getCity(),ADDRESS_CITY2);
        assertEquals(address.getCountry(),ADDRESS_COUNTRY);
        assertEquals(address.getCustomer().getId(),CUSTOMER_KEY);
    }
    @Test
    public void updateAddressCountry(){
        int id = CUSTOMER_KEY;
        Address address=null;

        try{
           address= addressService.updateAddress(ADDRESS_KEY, ADDRESS_STREET_NUMBER,ADDRESS_STREET,ADDRESS_CITY,ADDRESS_COUNTRY2);
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(address);
        assertEquals(address.getId(),ADDRESS_KEY);
        assertEquals(address.getStreetNumber(),ADDRESS_STREET_NUMBER);
        assertEquals(address.getStreet(),ADDRESS_STREET);
        assertEquals(address.getCity(),ADDRESS_CITY);
        assertEquals(address.getCountry(),ADDRESS_COUNTRY2);
        assertEquals(address.getCustomer().getId(),CUSTOMER_KEY);
    }
    @Test
    public void updateAddressStreetNumber(){
        int id = CUSTOMER_KEY;
        Address address=null;
        try{
            address=addressService.updateAddress(ADDRESS_KEY, ADDRESS_STREET_NUMBER2,ADDRESS_STREET,ADDRESS_CITY,ADDRESS_COUNTRY);
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(address);
        assertEquals(address.getId(),ADDRESS_KEY);
        assertEquals(address.getStreetNumber(),ADDRESS_STREET_NUMBER2);
        assertEquals(address.getStreet(),ADDRESS_STREET);
        assertEquals(address.getCity(),ADDRESS_CITY);
        assertEquals(address.getCountry(),ADDRESS_COUNTRY);
        assertEquals(address.getCustomer().getId(),CUSTOMER_KEY);
    }
    @Test
    public void updateAddressStreet(){
        int id = CUSTOMER_KEY;
        Address address=null;
        try{
           address= addressService.updateAddress(ADDRESS_KEY, ADDRESS_STREET_NUMBER,ADDRESS_STREET2,ADDRESS_CITY,ADDRESS_COUNTRY);
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(address);
        assertEquals(address.getId(),ADDRESS_KEY);
        assertEquals(address.getStreetNumber(),ADDRESS_STREET_NUMBER);
        assertEquals(address.getStreet(),ADDRESS_STREET2);
        assertEquals(address.getCity(),ADDRESS_CITY);
        assertEquals(address.getCountry(),ADDRESS_COUNTRY);
        assertEquals(address.getCustomer().getId(),CUSTOMER_KEY);
    }
    @Test
    public void updateAddressNotFound(){
        String error = null;
        Address address=null;
        try{
            addressService.updateAddress(ADDRESS_KEY2, ADDRESS_STREET_NUMBER,ADDRESS_STREET2,ADDRESS_CITY,ADDRESS_COUNTRY);
        }
        catch(Exception e){
           error=e.getMessage();
        }
        assertEquals(error,"Can't update address because no address exists for the given id.");
    }

    @Test
    public void getAddress(){
        String error = null;
        try{
           addressService.getAddress(ADDRESS_KEY);
        } catch (Exception e){
            error = e.getMessage();
        }
    }
    @Test
    public void getAddressNotFound(){
        String error = null;
        try{
            addressService.getAddress(ADDRESS_KEY2);
        } catch (Exception e){
            error = e.getMessage();
        }
        assertEquals(error,"Address not found");
    }
    @Test
    public void testDeleteAddressValid(){
        try{
            addressService.deleteAddress(ADDRESS_KEY);
        }
        catch (Exception e){
            fail();
        }
    }
    @Test
    public void testDeleteAddressNotFound(){
        String error=null;
        try{
            addressService.deleteAddress(ADDRESS_KEY2);
        }
        catch (Exception e){
            error=e.getMessage();
        }
        assertEquals(error,"Address does not exist");
    }
    @Test
    public void testDeleteAddressNullId(){
        String error=null;
        try{
            addressService.deleteAddress(null);
        }
        catch (Exception e){
            error=e.getMessage();
        }
        assertEquals(error,"Cannot find address to delete.");
    }


}

