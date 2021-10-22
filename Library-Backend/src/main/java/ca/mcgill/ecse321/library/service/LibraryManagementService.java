package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LibraryManagementService {
    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public Person createPerson(String name){
        Person person = new Person();
        person.setName(name);
        personRepository.save(person);
        return person;
    }

    @Transactional
    public List<Person> getPerson(String name){
        return personRepository.findPersonByName(name);
    }
}
