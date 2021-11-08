package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.HeadLibrarian;
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
public class TestHeadLibrarianPersistence {
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
    public void testPersistAndLoadHeadLibrarian(){
        Person person = new Person();
        //person.setId(1432);
        person.setName("bob");
        person.setPersonRoleList(null);
        personRepository.save(person);
        int id = person.getId();

        OnlineAccount account = new OnlineAccount();
        account.setPassword("password");
        account.setUsername("username");
        account.setPersonRole(null);
        //onlineAccountRepository.save(account);

        HeadLibrarian headLibrarian = new HeadLibrarian(0, person, account);
        librarianRepository.save(headLibrarian);
        int roleId = headLibrarian.getId();
        /*headLibrarian = null;
        account = null;
        person = null;*/

        headLibrarian = (HeadLibrarian) librarianRepository.findPersonRoleById(roleId);
        assertNotNull(headLibrarian);
        account = onlineAccountRepository.findOnlineAccountByUsername("username");
        person = personRepository.findPersonById(id);

        assertEquals(person, headLibrarian.getPerson());
        assertEquals(account, headLibrarian.getAccount());
        assertEquals(roleId, headLibrarian.getId());
    }
}
