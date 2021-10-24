package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dto.PersonDTO;
import ca.mcgill.ecse321.library.model.Person;
import ca.mcgill.ecse321.library.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class PersonRestController {
    @Autowired
    private PersonService service;
    //MAPPING SECTION
    @GetMapping(value = {"/person/{name}", "/person/{name}/"})
    public PersonDTO getPerson(@PathVariable("name") String name) throws IllegalArgumentException{
        Person person = service.createPerson(name);
        return convertToDTO(person);
    }

    @PostMapping(value= {"/person/{name}","/person/{name}/"})
    public PersonDTO createPerson(@PathVariable("name") String name) throws IllegalArgumentException{
        Person person = service.createPerson(name);
        return convertToDTO(person);
    }

    //DTO CONVERSION SECTION

    private PersonDTO convertToDTO(Person p){
        if (p == null) {
            throw new IllegalArgumentException("There is no such Person!");
        }
        PersonDTO personDto = new PersonDTO(p.getId());
        personDto.setId(p.getId());
        personDto.setName(p.getName());
        personDto.setSystem(p.getSystem());
        personDto.setPersonRoleList(p.getPersonRoleList());
        return personDto;
    }

}