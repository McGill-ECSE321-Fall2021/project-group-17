package ca.mcgill.ecse321.library;

import ca.mcgill.ecse321.library.dao.PersonRepository;
import ca.mcgill.ecse321.library.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class LibraryApplicationTests {

	@Autowired
	private PersonRepository personRepository;
	@Test
	void contextLoads() {
	}

	@AfterEach
	public void clearDatabase(){
		personRepository.deleteAll();
	}

	@Test
	public void testPersistAndLoadPerson(){
		String name = "TestPerson";
		Person person = new Person();
		person.setName(name);
		personRepository.save(person);

		person = null;

		person = personRepository.findPersonByName(name);

		assertNotNull(person);
		assertEquals(name, person.getName());
	}

}
