package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public Customer createCustomer(String id){
        Customer customer = new Customer();
        customer.setId(id);
        customerRepository.save(customer);
        return customer;
    }
    @Transactional
    public Customer getCustomer(String id){return (Customer) customerRepository.findPersonRoleById(id);}
}
