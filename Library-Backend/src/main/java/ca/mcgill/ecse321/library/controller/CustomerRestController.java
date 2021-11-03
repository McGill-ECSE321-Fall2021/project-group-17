package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.model.Customer;
import ca.mcgill.ecse321.library.dto.CustomerDTO;
import ca.mcgill.ecse321.library.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
public class CustomerRestController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(value= {"/customer/{id}","/customer/{id}/"})
    public CustomerDTO createCustomer(@PathVariable("id") int id) throws IllegalArgumentException{
        Customer customer = customerService.createCustomer(id);
        return convertToDTO(customer);
    }

    @GetMapping(value = {"/customer/{id}", "/customer/{id}/"})
    public CustomerDTO getCustomer(@PathVariable("id") int id) throws IllegalArgumentException{
        Customer customer = customerService.createCustomer(id);
        return convertToDTO(customer);
    }
    @PutMapping(value = {"/customer/{id}", "/customer/{id}/"})
    public void setIsVerified(@PathVariable("id") int id) throws IllegalArgumentException{
        customerService.verifyAddress(id);

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
