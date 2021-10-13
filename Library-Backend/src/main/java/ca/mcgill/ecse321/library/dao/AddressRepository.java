package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.library.model.Address;
//import ca.mcgill.ecse321.library.model.User;
import ca.mcgill.ecse321.library.model.Item;

public interface AddressRepository extends CrudRepository<Address,String>{
	
	//boolean findByUser(User user);
	Address findItemById(String id);

}