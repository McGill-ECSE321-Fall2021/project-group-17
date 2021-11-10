package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Address;
import ca.mcgill.ecse321.library.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestAddressPersistence {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @AfterEach
    public void clearDatabase() {
        addressRepository.deleteAll();
        customerRepository.deleteAll();
    }
    @Test
    public void testPersistAndLoadAddress() {
        Integer streetNumber = 1234;
        String street = "Main st";
        String city = "Montreal";
        String country = "Canada";
        Customer customer = new Customer();
        customerRepository.save(customer);
        int custId = customer.getId();
        Address address = new Address(0, streetNumber, street, city, country, customer);
        addressRepository.save(address);
        int id = address.getId();
        address = null;
        address = addressRepository.findAddressById(id);
        assertNotNull(address);
        assertEquals(id,address.getId());
        assertEquals(streetNumber, address.getStreetNumber());
        assertEquals(street,address.getStreet());
        assertEquals(city,address.getCity());
        assertEquals(country,address.getCountry());
        assertEquals(custId,address.getCustomer().getId());
    }
    @Test
    public void testPersistAndLoadAddressCity(){
        String city = "Newark";
        String street = "main";
        String country = "US";
        int streetNum = 1600;
        Customer customer = new Customer();
        customerRepository.save(customer);
        Address add = new Address();
        Integer i = add.getId();
        add.setStreetNumber(streetNum);
        add.setStreet(street);
        add.setCity(city);
        add.setCountry(country);
        add.setCustomer(customer);
        addressRepository.save(add);

        add = null;

        add = addressRepository.findAddressByCity(city);
        assertNotNull(add);
        assertEquals(city,add.getCity());
    }

    @Test
    public void testPersistAndLoadAddressStreetNum(){
        String city = "Newark";
        String street = "main";
        String country = "US";
        int streetNum = 1600;
        Address add = new Address();

        add.setStreetNumber(streetNum);
        add.setStreet(street);
        add.setCity(city);
        add.setCountry(country);
        addressRepository.save(add);

        add = null;

        add = addressRepository.findAddressByStreetNumber(streetNum);
        assertNotNull(add);
        assertEquals(city,add.getCity());
    }
}
