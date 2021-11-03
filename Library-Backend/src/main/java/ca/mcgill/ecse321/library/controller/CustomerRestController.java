package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.model.Address;
import ca.mcgill.ecse321.library.model.Customer;
import ca.mcgill.ecse321.library.dto.CustomerDTO;
import ca.mcgill.ecse321.library.model.LibraryCard;
import ca.mcgill.ecse321.library.model.Person;
import ca.mcgill.ecse321.library.service.CustomerService;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class CustomerRestController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(value= {"/customer/{id}","/customer/{id}/"})
    @ResponseBody
    public CustomerDTO createCustomer(@PathVariable("id") int id,
                                      @RequestBody JsonBody body) throws IllegalArgumentException{
        Customer customer = customerService.createCustomer(body.getSystemId(), body.getPerson(), body.getPenalty(),
                body.getAddress(), body.getLibCard());
        return convertToDTO(customer);
    }

    @GetMapping(value = {"/customer/{id}", "/customer/{id}/"})
    public CustomerDTO getCustomer(@PathVariable("id") int id) throws IllegalArgumentException{
        Customer customer = customerService.createCustomer(id, null, null, null, null);
        return convertToDTO(customer);
    }
    @PutMapping(value = {"/customer/{id}", "/customer/{id}/"})
    public void setIsVerified(@PathVariable("id") int id) throws IllegalArgumentException{
        customerService.verifyAddress(id);

    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private static class JsonBody{
        Integer systemId;
        Person person;
        Integer penalty;
        Address address;
        LibraryCard libCard;

        public void setPenalty(int penalty){this.penalty = penalty;}
        public int getPenalty(){return penalty;}

        public void setPerson(Person person){this.person = person;}
        public Person getPerson(){return person;}

        public void setAddress(Address address){this.address = address;}
        public Address getAddress(){return address;}

        public void setLibCard(LibraryCard libCard){this.libCard = libCard;}
        public LibraryCard getLibCard(){return libCard;}

        public Integer getSystemId() {
            return systemId;
        }
        public void setSystemId(int systemId) {
            this.systemId = systemId;
        }
        public JsonBody(){}
    }

    private CustomerDTO convertToDTO(Customer customer){
        if (customer == null) {
            throw new IllegalArgumentException("There is no such Customer!");
        }
        CustomerDTO cDTO = new CustomerDTO();
        cDTO.setId(customer.getId());
        cDTO.setAddress(customer.getAddress());
        cDTO.setLibCard(customer.getLibraryCard());
        cDTO.setSystem(customer.getSystem());
        cDTO.setPenalty(customer.getPenalty());
        cDTO.setPerson(customer.getPerson());
        return cDTO;
    }
}
