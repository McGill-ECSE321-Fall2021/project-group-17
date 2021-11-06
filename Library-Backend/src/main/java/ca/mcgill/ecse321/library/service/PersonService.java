package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.model.*;
import ca.mcgill.ecse321.library.service.Exception.PersonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonRoleRepository personRoleRepository;

    @Transactional
    public Person createPerson(String name,List<Integer> personRoles){
        Person person = new Person();
        person.setName(name);
        person.setPersonRoleList(new ArrayList<>());

        if(personRoles != null){
            for(int s :personRoles){
                PersonRole role = personRoleRepository.findPersonRoleById(s);
                List<PersonRole> roles = person.getPersonRoleList();
                roles.add(role);
                role.setPerson(person);
                person.setPersonRoleList(roles);
            }
        }
        personRepository.save(person);
        return person;
    }

    @Transactional
    public List<Person> getPerson(String name){
        return personRepository.findPersonByName(name);
    }
}
