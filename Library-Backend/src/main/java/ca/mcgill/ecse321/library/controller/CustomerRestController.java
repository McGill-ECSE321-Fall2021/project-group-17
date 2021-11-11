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
    public CustomerDTO createCustomer(@PathVariable("id") int id, @RequestParam Integer addressId,
                                      @RequestBody JsonBody body) throws IllegalArgumentException{
        Customer customer = customerService.createCustomer(id,body.getPersonId(), body.getPenalty(),
                addressId, body.getLibCard());
        return convertToDTO(customer);
    }

    @GetMapping(value = {"/customer/{id}", "/customer/{id}/"})
    public CustomerDTO getCustomer(@PathVariable("id") int id) throws IllegalArgumentException{
        Customer customer = customerService.getCustomer(id);
        return convertToDTO(customer);
    }
    @PutMapping(value = {"/verifyCustomer/{id}", "/verifyCustomer/{id}/"})
    public void setIsVerified(@PathVariable("id") int id) throws IllegalArgumentException{
        customerService.verifyAddress(id);

    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private static class JsonBody{
        Integer personId;
        Integer penalty;
        Integer addressId;
        LibraryCard libCard;

        public void setPenalty(Integer penalty){this.penalty = penalty;}
        public Integer getPenalty(){return penalty;}

        public void setPersonId(Integer personId){this.personId = personId;}
        public Integer getPersonId(){return personId;}

        public void setAddressId(Integer address){this.addressId = address;}
        public Integer getAddressId(){return addressId;}

        public void setLibCard(LibraryCard libCard){this.libCard = libCard;}
        public LibraryCard getLibCard(){return libCard;}

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
        cDTO.setPenalty(customer.getPenalty());
        cDTO.setPerson(customer.getPerson());
        return cDTO;
    }
}
