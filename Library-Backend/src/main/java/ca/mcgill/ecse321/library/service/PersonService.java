package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.model.*;
import ca.mcgill.ecse321.library.service.Exception.NotFoundException;
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
        if(name == null){
            throw new PersonException("Cannot have person with no name");
        }
        person.setName(name);
        //person.setPersonRoleList(new ArrayList<>());

        if(personRoles != null){
            for(Integer s :personRoles){
                if (s == null){
                    throw new PersonException("PersonRole id cannot be null");
                }
                PersonRole role = personRoleRepository.findPersonRoleById(s);
                if(role == null){
                    throw new PersonException("Cannot find person role with that id");
                }
                //List<PersonRole> roles = person.getPersonRoleList();
                //roles.add(role);
                role.setPerson(person);
                //person.setPersonRoleList(roles);
            }
        }
        personRepository.save(person);
        return person;
    }

    @Transactional
    public List<Person> getPerson(String name){
        return personRepository.findPersonByName(name);
    }

    @Transactional
    public Person getPerson(Integer id){

        Person p = personRepository.findPersonById(id);
        if(p == null){
            throw new NotFoundException("Cannot find a person with that id");
        }
        return p;
    }

    @Transactional
    public Person updatePerson(Integer id,String name, List<Integer> roles){
        Person p = personRepository.findPersonById(id);
        if(p == null){
            throw  new NotFoundException("Cannot find person with this id");
        }
        if(name != null){
            p.setName(name);
        }
        if(roles != null){
            for(Integer s :roles){
                if (s == null){
                    throw new PersonException("PersonRole id cannot be null");
                }
                PersonRole role = personRoleRepository.findPersonRoleById(s);
                if(role == null){
                    throw new PersonException("Cannot find person role with that id");
                }
                /*List<PersonRole> personRoles = p.getPersonRoleList();
                if(!personRoles.contains(role)){
                    personRoles.add(role);
                }
                role.setPerson(p);
                p.setPersonRoleList(personRoles);*/
            }
        }
        return p;
    }

    @Transactional
    public void deletePerson(Integer id){
        personRepository.deleteById(id);
    }
}
