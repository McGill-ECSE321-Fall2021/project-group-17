package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.CustomerRepository;
import ca.mcgill.ecse321.library.dao.LibraryCardRepository;
import ca.mcgill.ecse321.library.model.Customer;
import ca.mcgill.ecse321.library.model.LibraryCard;
import ca.mcgill.ecse321.library.service.Exception.CustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
public class LibraryCardService {

    @Autowired
    LibraryCardRepository libraryCardRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Transactional
    public LibraryCard createLibraryCard(int id) {
        Customer customer= (Customer) customerRepository.findPersonRoleById(id);
        if(customer==null){
            throw new CustomerException("customer does not exist");
        }
        LibraryCard libraryCard = new LibraryCard();
        libraryCard.setCustomer(customer);
        customer.setLibraryCard(libraryCard);
        customerRepository.save(customer);
        libraryCardRepository.save(libraryCard);
        return libraryCard;
    }

    @Transactional
    public LibraryCard getLibraryCard(int id) {
        return libraryCardRepository.findLibraryCardById(id);
    }
}


