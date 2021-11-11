package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.model.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonInclude;

import ca.mcgill.ecse321.library.dto.LibraryHourDTO;
import ca.mcgill.ecse321.library.model.LibraryHour;
import ca.mcgill.ecse321.library.service.LibraryHourService;

import java.sql.Time;
import java.time.DayOfWeek;

@CrossOrigin(origins = "*")
@RestController
public class LibraryHourRestController {
    @Autowired
    private LibraryHourService service;
    @Autowired
    private LibraryRestController libraryService;

    @GetMapping(value = {"/libraryhour/{id}", "/libraryhour/{id}"})//
    public LibraryHourDTO getLibraryHour(@PathVariable("id") int id) throws IllegalArgumentException {
        LibraryHour libraryHour = service.getLibraryHour(id);
        return convertToDTO(libraryHour);
    }

    @PostMapping(value= {"/libraryhour/{id}", "/libraryhour/{id}"})
    @ResponseBody
    public LibraryHourDTO createLibraryHour(@PathVariable("id") int Id,
                                  @RequestBody JsonBody body,
                                            @RequestParam(value = "accountusername", required = false)String accountUsername) throws IllegalArgumentException{
        LibraryHour libraryHour = service.createLibraryHour(body.getLibraryId(), body.getStartTime(), body.getEndTime(), body.getDayOfWeek(), accountUsername);
        return convertToDTO(libraryHour);
    }

    @PutMapping(value = {"/libraryhour/{libhourid}/{starttime}/{endtime}/{dayofweek}", "/libraryhour/{libhourid}/{starttime}/{endtime}/{dayofweek}"})
    public void modifyLibraryHours(@PathVariable("libhourid") int libHourId,
                                @PathVariable("starttime") String startTime, @PathVariable("endtime") String endTime,
                                @PathVariable("dayofweek") String DOW,
                                   @RequestParam(value = "accountUsername", required = false)String accountUsername){
        //do we need to use JsonBody or Path Variable??
        service.updateLibraryHour(libHourId, startTime, endTime, DOW, accountUsername);
    }

    @DeleteMapping(value = {"/libraryhour/{libraryhourid}", "/libraryhour/{libraryhourid}"})
    public void deleteLibraryHour(@PathVariable("libraryhourid") int libraryHourid, @RequestParam(value = "accountid", required = false)int accountId){
        service.deleteLibraryHour(libraryHourid, accountId);
    }

    private LibraryHourDTO convertToDTO(LibraryHour libraryHour) {
        if (libraryHour == null) {
            throw new IllegalArgumentException("There is no such LibraryHour!");
        }

        LibraryHourDTO libraryHourDTO = new LibraryHourDTO();
        libraryHourDTO.setId(libraryHour.getId());
        libraryHourDTO.setLibrary(libraryHour.getLibrary());
        return libraryHourDTO;
    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private static class JsonBody{
        private String startTime;
        private String endTime;
        private String dayOfWeek;
        private Integer libraryId;

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getDayOfWeek() {
            return dayOfWeek;
        }

        public void setDayOfWeek(String dayOfWeek) {
            this.dayOfWeek = dayOfWeek;
        }

        public Integer getLibraryId() {
            return libraryId;
        }

        public void setLibraryId(Integer libraryId) {
            this.libraryId = libraryId;
        }

        public JsonBody(){}
    }
}
