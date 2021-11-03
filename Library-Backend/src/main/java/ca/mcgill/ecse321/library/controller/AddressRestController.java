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
        Address address = service.createAddress(id, streetNum, street, city, country, body.getCustomerId(), body.getSystemId());
        return convertToDTO(address);
    }

    @PutMapping(value={"/address/{id}/{streetNum}/{street}/{city}/{country}","/address/{id}/{streetNum}/{street}/{city}/{country}/"})
    public void updateAddress(@PathVariable("id") int id, @PathVariable("streetNum") int streetNum,
                                    @PathVariable("street") String street, @PathVariable("city") String city,
                                    @PathVariable("country") String country) throws IllegalArgumentException {
        service.updateAddress(id, streetNum, street, city, country);
    }

    private AddressDTO convertToDTO(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Cannot create address");
        }
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressID(address.getId());
        addressDTO.setStreetNumber(address.getStreetNumber());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setCity(addressDTO.getCity());
        addressDTO.setCountry(addressDTO.getCountry());
        addressDTO.setCustomer(address.getCustomer());
        addressDTO.setSystem(address.getSystem());

        return addressDTO;
    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private static class JsonBody{
        private Integer customerId;
        private Integer systemId;

        public Integer getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Integer customerId) {
            this.customerId = customerId;
        }

        public Integer getSystemId() {
            return systemId;
        }

        public void setSystemId(Integer systemId) {
            this.systemId = systemId;
        }
    }
}
