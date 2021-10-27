package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dto.PersonDTO;
import ca.mcgill.ecse321.library.model.Person;
import ca.mcgill.ecse321.library.service.LibraryManagementSystemService;
import ca.mcgill.ecse321.library.service.PersonService;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;


@CrossOrigin(origins = "*")
@RestController
public class PersonRestController {
    @Autowired
    private PersonService service;
    //MAPPING SECTION
    @GetMapping(value = {"/person/{name}", "/person/{name}/"})
    public PersonDTO getPerson(@PathVariable("name") String name) throws IllegalArgumentException{
        Person person = service.createPerson(name,null,null);
        return convertToDTO(person);
    }

    @PostMapping(value= {"/person/{name}","/person/{name}/"})
    @ResponseBody
    public PersonDTO createPerson(@PathVariable("name") String name,
                                  @RequestBody JsonBody body) throws IllegalArgumentException{
        Person person = service.createPerson(name, body.getSystemId(), body.getPersonRoles());
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

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private static class JsonBody{
        List<String> personRoles;
        Integer systemId;

        public List<String> getPersonRoles() {
            return personRoles;
        }

        public void setPersonRoles(List<String> personRoles) {
            this.personRoles = personRoles;
        }

        public Integer getSystemId() {
            return systemId;
        }

        public void setSystemId(int systemId) {
            this.systemId = systemId;
        }
        public JsonBody(){}
    }

}
