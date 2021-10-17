package ca.mcgill.ecse321.library;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.model.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class LibraryApplicationTests {

	/*@Autowired
	private PersonRepository personRepository;
	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private NewspaperRepository newspaperRepository;
	@Autowired
	private ItemInstanceRepository itemInstanceRepository;
	@Autowired
	private CheckableItemRepository checkableItemRepository;
	@Test
	void contextLoads() {
	}

	@AfterEach
	public void clearDatabase(){
		personRepository.deleteAll();
		addressRepository.deleteAll();
		itemInstanceRepository.deleteAll();
		newspaperRepository.deleteAll();
		checkableItemRepository.deleteAll();
	}

	@Test
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



	@Test
	public void testPersistAndLoadNewspaper() {
		Integer id = 1234;
		String name = "New York Times";
		Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.MAY,24));
		String headline = "US deaths near 100,000, an incalculable loss";
		Newspaper newspaper = new Newspaper(id, name, date, headline);
		newspaperRepository.save(newspaper);
		newspaper = (Newspaper) newspaperRepository.findItemById(id);
		assertNotNull(newspaper);
		assertEquals(id,newspaper.getId());
		assertEquals(name, newspaper.getName());
		assertEquals(date, newspaper.getDatePublished());
		assertEquals(headline, newspaper.getHeadline());
	}

	@Test
	public void testFindNewspaperByHeadline() {
		Integer id = 1234;
		String name = "New York Times";
		Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.MAY,24));
		String headline = "US deaths near 100,000, an incalculable loss";
		Newspaper newspaper = new Newspaper(id, name, date, headline);
		newspaperRepository.save(newspaper);
		newspaper = newspaperRepository.findNewspaperByHeadline(headline);
		assertNotNull(newspaper);
		assertEquals(id,newspaper.getId());
		assertEquals(name, newspaper.getName());
		assertEquals(date, newspaper.getDatePublished());
		assertEquals(headline, newspaper.getHeadline());
	}

	@Test
	public void testPersistAndLoadItemInstance() {
		String serialNum = "1234";
		CheckableItem checkableItem = new Music(1234,"My Brilliant Friend",
				java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12)),"Victoria","Sony");
		checkableItemRepository.save(checkableItem);
		ItemInstance itemInstance = new ItemInstance(serialNum, checkableItem);
		itemInstanceRepository.save(itemInstance);
		itemInstance = itemInstanceRepository.findItemInstanceBySerialNum(serialNum);
		assertNotNull(itemInstance);
		assertEquals(serialNum, itemInstance.getSerialNum());
		assertEquals(checkableItem.getId(), itemInstance.getCheckableItem().getId());
	}

	@Test
	public void testFindItemInstanceByCheckableItem() {
		String serialNum1 = "1234";
		String serialNum2 = "5678";
		CheckableItem checkableItem = new Music(1234,"My Brilliant Friend",
				java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12)),"Victoria","Sony");
		checkableItemRepository.save(checkableItem);
		ItemInstance itemInstance1 = new ItemInstance(serialNum1, checkableItem);
		ItemInstance itemInstance2 = new ItemInstance(serialNum2, checkableItem);
		itemInstanceRepository.save(itemInstance1);
		itemInstanceRepository.save(itemInstance2);
		List<ItemInstance> itemInstances = itemInstanceRepository.findByCheckableItem(checkableItem);
		assertNotNull(itemInstances);
		itemInstance1 = itemInstances.get(0);
		assertEquals(serialNum1, itemInstance1.getSerialNum());
		assertEquals(checkableItem.getId(), itemInstance1.getCheckableItem().getId());
		itemInstance2 = itemInstances.get(1);
		assertEquals(serialNum2, itemInstance2.getSerialNum());
		assertEquals(checkableItem.getId(), itemInstance2.getCheckableItem().getId());
	}*/

}
