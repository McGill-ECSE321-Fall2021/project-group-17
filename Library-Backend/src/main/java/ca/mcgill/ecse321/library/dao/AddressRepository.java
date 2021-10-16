package ca.mcgill.ecse321.library.dao;


import ca.mcgill.ecse321.library.model.Address;
//import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import org.springframework.data.repository.CrudRepository;


public interface AddressRepository extends CrudRepository<Address, Integer> {
    Address findAddressByAddressID(int id);
    Address findAddressByCity(String street);
    Address findAddressByStreetNumber(int street);
}
