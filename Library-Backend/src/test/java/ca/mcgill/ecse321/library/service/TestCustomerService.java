package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.model.*;
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
    private static final int PERSONROLE_KEY = 7;
    private static final int LIBRARYCARD_KEY = 7;
    private static final int ADDRESS_KEY = 5;
    private static final int ADDRESS_STREET_NUMBER = 3456;
    private static final boolean VERIFIED = true;
    private static final String ADDRESS_STREET = "Peel";
    private static final String ADDRESS_CITY = "Montreal";
    private static final String ADDRESS_COUNTRY = "Canada";
    private static final String PERSON_NAME = "victoria";
    private static final int CUSTOMER_KEY = 2;
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
    public void createCustomer(){
        int id = CUSTOMER_KEY;
        Person person=null;
        try{
            person= service1.createPerson(PERSON_NAME,null);
        }
        catch(Exception e){
            fail();
        }
        Address address=null;
        try{
            address=service2.createAddress(ADDRESS_KEY,ADDRESS_STREET_NUMBER,ADDRESS_STREET,ADDRESS_CITY,ADDRESS_COUNTRY,CUSTOMER_KEY);
        }
        catch(Exception e){
            fail();
        }
        LibraryCard libraryCard=null;
        try{
            libraryCard=service3.createLibraryCard(LIBRARYCARD_KEY);
        }
        catch(Exception e){
            fail();
        }
        Customer customer = null;
        try{
            customer = service.createCustomer(person,0,address,libraryCard);
        }
        catch (Exception e){
            fail();
        }
        assertNotNull(customer);
        assertEquals(customer.getPerson(),person);
        assertEquals(customer.getAddress(),address);
        assertEquals(customer.getLibraryCard(),libraryCard);

    }

    @Test
    public void verifyUpdateAddress(){
        int id = CUSTOMER_KEY;
        Person person=null;
        try{
            person= service1.createPerson(PERSON_NAME,null);
        }
        catch(Exception e){
            fail();
        }
        Customer customer = null;
        try{
            customer = service.createCustomer(person,0,null,null);
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



}
