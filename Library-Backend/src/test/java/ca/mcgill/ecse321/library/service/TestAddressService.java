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
    private CustomerService service;
    @InjectMocks
    private PersonService service1;
    @InjectMocks
    private AddressService service2;

    private static final int PERSON_KEY = 3;
    private static final int ADDRESS_KEY = 5;
    private static final int ADDRESS_STREET_NUMBER = 3456;
    private static final boolean VERIFIED = true;
    private static final String ADDRESS_STREET = "Peel";
    private static final String ADDRESS_CITY = "Montreal";
    private static final String ADDRESS_CITY2 = "Montreal";
    private static final String ADDRESS_COUNTRY = "Canada";
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
            address= service2.createAddress(ADDRESS_KEY,ADDRESS_STREET_NUMBER,ADDRESS_STREET,ADDRESS_CITY,ADDRESS_COUNTRY,CUSTOMER_KEY);
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(address);
        assertEquals(address.getId(),ADDRESS_KEY);
        assertEquals(address.getStreetNumber(),ADDRESS_STREET_NUMBER);
        assertEquals(address.getStreet(),ADDRESS_STREET);
        assertEquals(address.getCity(),ADDRESS_CITY);
        assertEquals(address.getCountry(),ADDRESS_COUNTRY);
        assertEquals(address.getCustomer().getId(),CUSTOMER_KEY);
    }
    @Test
    public void updateAddress(){
        int id = CUSTOMER_KEY;
        Address address=null;
        try{
            address= service2.createAddress(ADDRESS_KEY,ADDRESS_STREET_NUMBER,ADDRESS_STREET,ADDRESS_CITY,ADDRESS_COUNTRY,CUSTOMER_KEY);
        }
        catch(Exception e){
            fail();
        }
        try{
            service2.updateAddress(ADDRESS_KEY, ADDRESS_STREET_NUMBER,ADDRESS_STREET,ADDRESS_CITY2,ADDRESS_COUNTRY);
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



}

