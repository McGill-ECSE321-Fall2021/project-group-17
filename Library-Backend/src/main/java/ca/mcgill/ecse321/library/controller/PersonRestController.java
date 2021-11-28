package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dto.ItemInstanceDTO;
import ca.mcgill.ecse321.library.dto.PersonDTO;
import ca.mcgill.ecse321.library.model.Person;
import ca.mcgill.ecse321.library.service.PersonService;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;


@CrossOrigin(origins = "*")
@RestController
public class PersonRestController {
    @Autowired
    private PersonService service;



    //MAPPING SECTION
    @GetMapping(value = {"/person/{name}", "/person/{name}/"})
    public List<PersonDTO> getPerson(@PathVariable("name") String name) throws IllegalArgumentException{
        return service.getPerson(name).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping(value = {"/person/{id}", "/person/{id}/"})
    public PersonDTO getPerson(@PathVariable("id") Integer id) throws IllegalArgumentException{
        return convertToDTO( service.getPerson(id));
    }

    @PostMapping(value= {"/person/","/person"})
    @ResponseBody
    public PersonDTO createPerson(@RequestBody JsonBody body) throws IllegalArgumentException{
        Person person = service.createPerson(body.getName(), body.getPersonRoles());
        return convertToDTO(person);
    }

    @PatchMapping(value = {"/person/{id}", "/person/{id}/"})
    public PersonDTO updatePerson(@PathVariable Integer id, @RequestBody JsonBody body){
        return convertToDTO( service.updatePerson(id,body.getName(),body.getPersonRoles()));
    }

    @DeleteMapping(value = {"/person/{id}", "/person/{id}/"})
    public void deletePerson(@PathVariable Integer id){
        service.deletePerson(id);
    }


    @GetMapping(value = {"/persons/", "/persons"})
    public List<PersonDTO> getAllPersons() throws IllegalArgumentException {
        return service.getAllPersons().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    //DTO CONVERSION SECTION

    private PersonDTO convertToDTO(Person p){
        if (p == null) {
            throw new IllegalArgumentException("There is no such Person!");
        }
        PersonDTO personDto = new PersonDTO(p.getId());
        personDto.setId(p.getId());
        personDto.setName(p.getName());
        //personDto.setPersonRoleList(p.getPersonRoleList());
        return personDto;
    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private static class JsonBody{
        List<Integer> personRoles;
        String name;

        public List<Integer > getPersonRoles() {
            return personRoles;
        }

        public void setPersonRoles(List<Integer> personRoles) {
            this.personRoles = personRoles;
        }

        public JsonBody(){}

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
