package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.OnlineAccount;
import ca.mcgill.ecse321.library.model.Person;
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
public class TestLibrarianPersistence {
    @Autowired
    private LibrarianRepository librarianRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private OnlineAccountRepository onlineAccountRepository;
    @AfterEach
    public void clearDatabase() {
        personRepository.deleteAll();
        onlineAccountRepository.deleteAll();
        librarianRepository.deleteAll();
    }
    @Test
    @Transactional
    public void testPersistAndLoadLibrarian(){
        Person person = new Person();
        person.setName("bob");
        //person.setPersonRoleList(null);
        personRepository.save(person);
        int id = person.getId();

        OnlineAccount account = new OnlineAccount();
        account.setPassword("password");
        account.setUsername("username");
        account.setPersonRole(null);
        //onlineAccountRepository.save(account);

        Librarian l = new Librarian(0, person, account);
        librarianRepository.save(l);
        int libId = l.getId();
        l = null;
        account = null;
        person = null;

        l = (Librarian) librarianRepository.findPersonRoleById(libId);
        assertNotNull(l);
        account = onlineAccountRepository.findOnlineAccountByUsername("username");
        person = personRepository.findPersonById(id);

        assertEquals(person, l.getPerson());
        assertEquals(account, l.getAccount());
        assertEquals(libId, l.getId());
    }
}
