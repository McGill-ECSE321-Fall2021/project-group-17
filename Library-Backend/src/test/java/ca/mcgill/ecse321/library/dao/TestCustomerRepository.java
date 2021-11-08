package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestCustomerRepository {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private LibraryCardRepository libraryCardRepository;
    @Autowired
    private OnlineAccountRepository onlineAccountRepository;
    @AfterEach
    public void clearDatabase() {
        customerRepository.deleteAll();
        personRepository.deleteAll();
        addressRepository.deleteAll();
        libraryCardRepository.deleteAll();
        onlineAccountRepository.deleteAll();
    }
    @Test
    @Transactional
    public void testPersistAndLoadCustomer(){
        Person person = new Person();
        person.setName("bob");
        person.setPersonRoleList(null);
        personRepository.save(person);
        int pId = person.getId();


        Address address = new Address();
        address.setStreetNumber(1);
        address.setStreet(null);
        address.setCountry(null);
        address.setCity(null);
        address.setCustomer(null);
        addressRepository.save(address);
        int aId = address.getId();


        LibraryCard libCard = new LibraryCard();
        libCard.setCustomer(null);
        libraryCardRepository.save(libCard);
        int libId = libCard.getId();

        String username = "username";
        OnlineAccount account = new OnlineAccount();
        account.setPassword("password");
        account.setUsername(username);
        account.setPersonRole(null);
        //onlineAccountRepository.save(account);

        int penalty = 0;

        Customer c = new Customer(0, person, penalty, address, libCard, account);
        customerRepository.save(c);
        int customerId = c.getId();
        c = null;
        account = null;
        person = null;
        libCard = null;
        address = null;

        c = (Customer) customerRepository.findPersonRoleById(customerId);
        assertNotNull(c);
        account = onlineAccountRepository.findOnlineAccountByUsername(username);
        person = personRepository.findPersonById(pId);
        libCard = libraryCardRepository.findLibraryCardById(libId);
        address = addressRepository.findAddressById(aId);

        assertEquals(person, c.getPerson());
        assertEquals(account, c.getAccount());
        assertEquals(libCard, c.getLibraryCard());
        assertEquals(address, c.getAddress());
        assertEquals(penalty, c.getPenalty());
        assertEquals(customerId, c.getId());
    }
}
