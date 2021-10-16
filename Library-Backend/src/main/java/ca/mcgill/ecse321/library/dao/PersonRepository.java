package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.library.model.Person;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Integer>{
    Person findPersonById(Integer Id);
    List<Person> findPersonByName(String name);
}
