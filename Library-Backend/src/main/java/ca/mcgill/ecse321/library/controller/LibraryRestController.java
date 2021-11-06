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

import ca.mcgill.ecse321.library.dto.LibraryDTO;
import ca.mcgill.ecse321.library.model.Library;
import ca.mcgill.ecse321.library.service.LibraryService;

@CrossOrigin(origins = "*")
@RestController
public class LibraryRestController {
    @Autowired
    private LibraryService service;

    @GetMapping(value = {"/library/{id}", "/library/{id}/"})
    public LibraryDTO getLibrary(@PathVariable("id") int id) throws IllegalArgumentException {
        Library library = service.createLibrary(id, null);
        return convertToDTO(library);
    }

    @PostMapping(value= {"/library/{id}", "/library/{id}/"})
    @ResponseBody
    public LibraryDTO createLibrary(@PathVariable("id") int id,
                                  @RequestBody JsonBody body) throws IllegalArgumentException{
        Library library = service.createLibrary(id, body.getSystemId());
        return convertToDTO(library);
    }

    private LibraryDTO convertToDTO(Library library) {
        if (library == null) {
            throw new IllegalArgumentException("There is no such Library!");
        }

        LibraryDTO libraryDTO = new LibraryDTO();
        libraryDTO.setName(library.getName());
        libraryDTO.setId(library.getId());
        //libraryDTO.setSystem(library.getSystem());
        return libraryDTO;
    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private static class JsonBody{
        Integer systemId;

        public Integer getSystemId() {
            return systemId;
        }

        public void setSystemId(int systemId) {
            this.systemId = systemId;
        }
        public JsonBody(){}
    }

}
