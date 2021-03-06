package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.AddressRepository;
import ca.mcgill.ecse321.library.dao.CustomerRepository;
import ca.mcgill.ecse321.library.model.Address;
import ca.mcgill.ecse321.library.model.Customer;
import ca.mcgill.ecse321.library.model.PersonRole;
import ca.mcgill.ecse321.library.service.Exception.AddressException;
import ca.mcgill.ecse321.library.service.Exception.OnlineAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CustomerRepository customerRepository;

    /**
     * @author Victoria Sanchez
     * @param id
     * @param streetNumber
     * @param street
     * @param city
     * @param country
     * @param customerId
     * @return Address given input is valid
     */
    @Transactional

    public Address createAddress(int id, Integer streetNumber, String street, String city, String country, Integer customerId) {
        if(addressRepository.findAddressById(id)!=null){
            throw new AddressException("cannot have two addresses with the same ID");
        }
        if(streetNumber==0 || street==null||city==null||country==null ){
            throw new AddressException("incomplete Address given");
        }
        Address address = new Address(id, streetNumber, street, city, country);

        if (customerId != null) {
            PersonRole personRole = customerRepository.findPersonRoleById(customerId);

            if (personRole instanceof Customer) {
                address.setCustomer((Customer) personRole);
            }
        }

        addressRepository.save(address);
        return address;
    }

    /**
     * @author Victoria Sanchez
     * @param id
     *
     */
    @Transactional
    public void deleteAddress(Integer id) {
        if(id==null){
            throw new AddressException("Cannot find address to delete.");}

            Address address= addressRepository.findAddressById(id);
        if(address==null){
            throw new AddressException("Address does not exist");
        }
      addressRepository.delete(address);
    }

    /**
     * @author Victoria Sanchez
     * @param id
     * @return address corresponding to the input ID
     */

    @Transactional
    public Address getAddress(int id) {
        Address address=addressRepository.findAddressById(id);
        if(address==null){
            throw new AddressException("Address not found");
        }
        else{
            return address;
        }

    }

    /**
     * @author Victoria Sanchez
     * @param id
     * @param streetNumber
     * @param street
     * @param city
     * @param country
     * @return Address with new information
     */

    @Transactional
    public Address updateAddress(int id, Integer streetNumber, String street, String city, String country) {
        Address address = addressRepository.findAddressById(id);

        if (address == null) {
            throw new AddressException("Can't update address because no address exists for the given id.");
        }

        if (streetNumber != null) {
            address.setStreetNumber(streetNumber);
        }

        if (street != null) {
            address.setStreet(street);
        }

        if (city != null) {
            address.setCity(city);
        }

        if (country != null) {
            address.setCountry(country);
        }

        addressRepository.save(address);
        return address;
    }
}
