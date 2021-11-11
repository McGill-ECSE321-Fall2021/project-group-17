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
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;

    /**
     * @author Victoria Sanchez
     * @param id
     * @param personId
     * @param penalty
     * @param addressId
     * @param libCard
     * @return customer given valid input
     */
    @Transactional
    public Customer createCustomer(Integer id,Integer personId, Integer penalty, Integer addressId, LibraryCard libCard){
        Customer customer = new Customer();

        Person person = personRepository.findPersonById(personId);
        Address address = addressRepository.findAddressById(addressId);

        if(person==null){
            throw new CustomerException("Cannot create Customer because person is null");
        }
        if(address==null){
            throw new CustomerException("Cannot create Customer because address is null");
        }
        customer.setPenalty(penalty);
        customer.setAddress(address);
        customer.setLibraryCard(libCard);
        customer.setPerson(person);
        //customer.setId(id);
        customerRepository.save(customer);
        return customer;
    }

    /**
     * @author Victoria Sanchez
     * @param id
     * @param penalty
     * @param addressId
     * @param libCard
     * @return customer with new information
     */
    public Customer updateCustomer(Integer id, Integer penalty, Integer addressId, LibraryCard libCard){
        Customer customer = (Customer) customerRepository.findPersonRoleById(id);
        Address address= addressRepository.findAddressById(addressId);
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

    /**
     * @author Victoria Sanchez
     * @param id
     * @return customer corresponding to the id
     */
    @Transactional
    public Customer getCustomer(Integer id){
        if(id == null || id < 0){
            throw new CustomerException("Invalid Id");
        }
        Customer c = (Customer) customerRepository.findPersonRoleById(id);
        if (c == null) throw new CustomerException("This customer does not exist");
        return c;
    }

    /**
     * @author Victoria Sanchez
     * @param id
     * method sets customer status as address verified
     */

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

    /**
     * @author Victoria Sanchez
     * @param id
     */
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
