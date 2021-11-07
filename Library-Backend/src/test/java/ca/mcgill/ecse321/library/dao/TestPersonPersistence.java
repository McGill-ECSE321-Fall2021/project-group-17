package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersonPersistence {
    @Autowired
    private PersonRepository personRepository;
    @AfterEach
    public void clearDatabase() {
        personRepository.deleteAll();
    }
    @Test
    @Transactional
    public void testPersistAndLoadPerson(){
        String name = "TestPerson";
        List<Person> persons = null;
        Person person = new Person();
        Person person1 = new Person();
        person.setName(name);
        person1.setName(name);
        personRepository.save(person);
        personRepository.save(person1);
        persons = personRepository.findPersonByName(name);
        assertNotNull(persons);
        person = persons.get(0);
        person1 = persons.get(1);
        assertEquals(2, persons.size());
        assertEquals(name, person.getName());
        assertEquals(name, person1.getName());
    }
}
