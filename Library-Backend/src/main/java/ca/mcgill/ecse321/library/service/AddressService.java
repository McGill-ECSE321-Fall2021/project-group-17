package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.AddressRepository;
import ca.mcgill.ecse321.library.dao.CustomerRepository;
import ca.mcgill.ecse321.library.dao.LibraryManagementSystemRepository;
import ca.mcgill.ecse321.library.model.Address;
import ca.mcgill.ecse321.library.model.Customer;
import ca.mcgill.ecse321.library.model.LibraryManagementSystem;
import ca.mcgill.ecse321.library.model.PersonRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private LibraryManagementSystemRepository lmsRepository;

    @Transactional
    public Address createAddress(int id, Integer streetNumber, String street, String city, String country, Integer customerId, Integer systemId) {
        Address address = new Address(id, streetNumber, street, city, country);

        if (systemId != null) {
            LibraryManagementSystem lms = lmsRepository.findLibraryManagementSystemById(systemId);
            address.setSystem(lms);
        }

        if (customerId != null) {
            PersonRole personRole = customerRepository.findPersonRoleById(customerId);

            if (personRole instanceof Customer) {
                address.setCustomer((Customer) personRole);
            }
        }

        addressRepository.save(address);
        return address;
    }

    @Transactional
    public Address getAddress(int id) {
        return addressRepository.findAddressById(id);
    }

    @Transactional
    public Address updateAddress(int id, Integer streetNumber, String street, String city, String country) {
        Address address = addressRepository.findAddressById(id);

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
