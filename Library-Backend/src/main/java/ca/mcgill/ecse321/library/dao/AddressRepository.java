package ca.mcgill.ecse321.library.dao;


import ca.mcgill.ecse321.library.model.Address;
import ca.mcgill.ecse321.library.model.Person;
import org.springframework.data.repository.CrudRepository;


public interface AddressRepository extends CrudRepository<Address, Integer> {
    Address findAddressByAddressID(Integer id);
}
