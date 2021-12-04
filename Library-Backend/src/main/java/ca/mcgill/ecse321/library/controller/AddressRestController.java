package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dto.AddressDTO;
import ca.mcgill.ecse321.library.model.Address;
import ca.mcgill.ecse321.library.service.AddressService;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class AddressRestController {
    @Autowired
    private AddressService service;


    @GetMapping(value = {"/address/{id}", "/address/{id}/"})
    public AddressDTO getAddress(@PathVariable("id") int id) throws IllegalArgumentException{
        Address address = service.getAddress(id);
        return convertToDTO(address);
    }

    @PostMapping(value= {"/address/{id}/{streetNum}/{street}/{city}/{country}","/address/{id}/{streetNum}/{street}/{city}/{country}/"})
    @ResponseBody
    public AddressDTO createAddress(@PathVariable("id") int id, @PathVariable("streetNum") int streetNum,
                                    @PathVariable("street") String street, @PathVariable("city") String city,
                                    @PathVariable("country") String country,
                                  @RequestBody JsonBody body) throws IllegalArgumentException{
        Address address = service.createAddress(id, streetNum, street, city, country, body.getCustomerId());
        return convertToDTO(address);
    }

    @PostMapping(value={"/address/{id}","/address/{id}/"})
    public AddressDTO createAddress1(@PathVariable("id") int id, @RequestParam(value = "streetNum",required = false) int streetNum,
                                     @RequestParam(value = "streetName",required = false) String street,
                                     @RequestParam(value = "city",required = false) String city,
                                     @RequestParam(value = "country",required = false) String country) throws IllegalArgumentException {
        Address address = service.createAddress(id, streetNum, street, city, country, null);
        return convertToDTO(address);
    }

    @PutMapping(value={"/address/{id}/{streetNum}/{street}/{city}/{country}","/address/{id}/{streetNum}/{street}/{city}/{country}/"})
    public AddressDTO updateAddress(@PathVariable("id") int id, @PathVariable("streetNum") int streetNum,
                                    @PathVariable("street") String street, @PathVariable("city") String city,
                                    @PathVariable("country") String country) throws IllegalArgumentException {
        return convertToDTO(service.updateAddress(id, streetNum, street, city, country));
    }
    @DeleteMapping(value={"/address/{id}","/address/{id}/"})
    public void deleteAddress(@PathVariable("id") int id) throws IllegalArgumentException {
        service.deleteAddress(id);
    }

    private AddressDTO convertToDTO(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Cannot create address");
        }
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressID(address.getId());
        addressDTO.setStreetNumber(address.getStreetNumber());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setCity(address.getCity());
        addressDTO.setCountry(address.getCountry());
        addressDTO.setCustomer(address.getCustomer());

        return addressDTO;
    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private static class JsonBody{
        private Integer customerId;

        public Integer getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Integer customerId) {
            this.customerId = customerId;
        }
    }
}
