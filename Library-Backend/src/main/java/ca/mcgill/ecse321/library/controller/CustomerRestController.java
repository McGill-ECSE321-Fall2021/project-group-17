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
        Customer customer = customerService.createCustomer(id,body.getPersonId(), body.getPenalty(),
                body.getAddressId(), body.getLibCard());
        return convertToDTO(customer);
    }

    @PutMapping(value={"/customer/{id}", "/customer/{id}/"})
    public CustomerDTO updateCustomer(@PathVariable("id") int id, @RequestBody JsonBody body) throws IllegalArgumentException{
        return convertToDTO(customerService.updateCustomer(id, body.getPenalty(), body.getAddressId(), body.getLibCard()));
    }
    @DeleteMapping(value={"/customer/{id}", "/customer/{id}/"})
    public void deleteCustomer(@PathVariable("id") int id) throws IllegalArgumentException{
        customerService.deleteCustomer(id);
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

    @PutMapping(value={"/customer/{id}/{username}", "/customer/{id}/{username}/"})
    public CustomerDTO updateCustomerAccount(@PathVariable("id") int id, @PathVariable("username") String username) throws IllegalArgumentException{
        return convertToDTO(customerService.updateCustomerAccount(id, username));
    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private static class JsonBody{
        private Integer personId;
        private Integer penalty;
        private Integer addressId;
        private LibraryCard libCard;

        public Integer getPersonId() {
            return personId;
        }

        public void setPersonId(Integer personId) {
            this.personId = personId;
        }

        public Integer getPenalty() {
            return penalty;
        }

        public void setPenalty(Integer penalty) {
            this.penalty = penalty;
        }

        public Integer getAddressId() {
            return addressId;
        }

        public void setAddressId(Integer addressId) {
            this.addressId = addressId;
        }

        public LibraryCard getLibCard() {
            return libCard;
        }

        public void setLibCard(LibraryCard libCard) {
            this.libCard = libCard;
        }
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
        cDTO.setAccount(customer.getAccount());
        return cDTO;
    }
}
