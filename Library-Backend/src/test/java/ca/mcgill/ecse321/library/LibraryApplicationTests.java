package ca.mcgill.ecse321.library;

import ca.mcgill.ecse321.library.dao.AddressRepository;
import ca.mcgill.ecse321.library.dao.PersonRepository;
import ca.mcgill.ecse321.library.model.Address;
import ca.mcgill.ecse321.library.model.Person;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.criteria.CriteriaBuilder;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class LibraryApplicationTests {

	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Test
	void contextLoads() {
	}

	@AfterEach
	public void clearDatabase(){
		personRepository.deleteAll();
		addressRepository.deleteAll();
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

	@Test
	public void testPersistAndLoadAddressCity(){
		String city = "Newark";
		String street = "main";
		String country = "US";
		int streetNum = 1600;
		Address add = new Address();
		Integer i = add.getAddressID();
		add.setStreetNumber(streetNum);
		add.setStreet(street);
		add.setCity(city);
		add.setCountry(country);
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
	@Test
	public void testPersistAndLoadAddressId(){
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
		Integer i = add.getAddressID();

		add = null;

		add = addressRepository.findAddressByAddressID(i);
		assertNotNull(add);
		assertEquals(city,add.getCity());
	}
}
