package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.model.*;
import ca.mcgill.ecse321.library.service.Exception.AddressException;
import ca.mcgill.ecse321.library.service.Exception.CustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public Customer createCustomer(Integer id,Person person, Integer penalty, Address address, LibraryCard libCard){
        Customer customer = new Customer();
        if(person==null||address==null){
            throw new CustomerException("cannot create Customer");
        }
        customer.setPenalty(penalty);
        customer.setAddress(address);
        customer.setLibraryCard(libCard);
        customer.setPerson(person);
        customer.setId(id);
        customerRepository.save(customer);
        return customer;
    }
    public Customer updateCustomer(Integer id, Integer penalty, Address address, LibraryCard libCard){
        Customer customer = (Customer) customerRepository.findPersonRoleById(id);
        if(customer==null){
            throw new CustomerException("cannot find customer");
        }
        if(penalty!=null){
            customer.setPenalty(penalty);
        }
        if(address!=null){
            customer.setAddress(address);
        }
        if(libCard!=null){
            customer.setLibraryCard(libCard);
        }
        customerRepository.save(customer);
        return customer;
    }
    @Transactional
    public Customer getCustomer(Integer id){
        if(id == null || id < 0){
            throw new CustomerException("Invalid Id");
        }
        Customer c = (Customer) customerRepository.findPersonRoleById(id);
        if (c == null) throw new CustomerException("This customer does not exist");
        return c;
    }

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
    @Transactional
    public void deleteCustomer(Integer id) {
        if(id==null){
            throw new CustomerException("Cannot find customer to delete.");}

        Customer customer =(Customer)customerRepository.findPersonRoleById(id);
        if(customer==null){
            throw new CustomerException("Customer does not exist");
        }
        customerRepository.delete(customer);
    }
}
