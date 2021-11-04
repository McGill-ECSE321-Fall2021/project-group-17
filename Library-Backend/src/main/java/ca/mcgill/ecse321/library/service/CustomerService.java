package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.model.*;
import ca.mcgill.ecse321.library.service.Exception.CustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public Customer createCustomer(int id){
        Customer customer = new Customer();
        //customer.setId(id);
        customerRepository.save(customer);
        return customer;
    }
    @Transactional
    public Customer getCustomer(int id){return (Customer) customerRepository.findPersonRoleById(id);}

    @Transactional
    public void verifyAddress(Integer id){
        Customer c= (Customer) customerRepository.findPersonRoleById(id);
        if (c!=null){
            c.setIsVerified(true);
        }
        else{
            throw new CustomerException("Customer not found in system. Address could not be verified");
        }
    }
}
