package ca.mcgill.ecse321.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude;

import ca.mcgill.ecse321.library.dto.LibraryHourDTO;
import ca.mcgill.ecse321.library.model.LibraryHour;
import ca.mcgill.ecse321.library.service.LibraryHourService;

@CrossOrigin(origins = "*")
@RestController
public class LibraryHourRestController {
    @Autowired
    private LibraryHourService service;

    @GetMapping(value = {"/libraryhour/{id}", "/libraryhour/{id}/"})
    public LibraryHourDTO getLibraryHour(@PathVariable("id") int id) throws IllegalArgumentException {
        LibraryHour libraryHour = service.createLibraryHour(id, null, null);
        return convertToDTO(libraryHour);
    }

    @PostMapping(value= {"/libraryhour/{id}", "/libraryhour/{id}/"})
    @ResponseBody
    public LibraryHourDTO createLibraryHour(@PathVariable("id") int id,
                                  @RequestBody JsonBody body) throws IllegalArgumentException{
        LibraryHour libraryHour = service.createLibraryHour(id, body.getLibraryId(), body.getSystemId());
        return convertToDTO(libraryHour);
    }

    private LibraryHourDTO convertToDTO(LibraryHour libraryHour) {
        if (libraryHour == null) {
            throw new IllegalArgumentException("There is no such LibraryHour!");
        }

        LibraryHourDTO libraryHourDTO = new LibraryHourDTO();
        libraryHourDTO.setId(libraryHour.getId());
        libraryHourDTO.setLibrary(libraryHour.getLibrary());
        libraryHourDTO.setSystem(libraryHour.getSystem());
        return libraryHourDTO;
    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private static class JsonBody{
        Integer libraryId;
        Integer systemId;

        public Integer getLibraryId() {
            return libraryId;
        }

        public void setLibraryId(Integer libraryId) {
            this.libraryId = libraryId;
        }

        public Integer getSystemId() {
            return systemId;
        }

        public void setSystemId(int systemId) {
            this.systemId = systemId;
        }
        public JsonBody(){}
    }
}
