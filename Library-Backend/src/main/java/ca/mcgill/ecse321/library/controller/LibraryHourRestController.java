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

    @GetMapping(value = {"/libraryhour/{id}", "/libraryhour/{id}/"})//
    public LibraryHourDTO getLibraryHour(@PathVariable("id") int id) throws IllegalArgumentException {
        LibraryHour libraryHour = service.getLibraryHour(id);
        return convertToDTO(libraryHour);
    }

    @PostMapping(value= {"/libraryhour/{id}/", "/libraryhour/{id}/"})//TODO use current user id
    @ResponseBody
    public LibraryHourDTO createLibraryHour(@PathVariable("id") int Id,
                                  @RequestBody JsonBody body,
                                            @RequestParam(value = "accountid", required = false)int accountId) throws IllegalArgumentException{
        LibraryHour libraryHour = service.createLibraryHour(body.getLibraryId(), body.getStartTime(), body.getEndTime(), body.getDayOfWeek(), accountId);
        return convertToDTO(libraryHour);
    }

    @PutMapping(value = {"/libraryhour/{libhourid}/{starttime}/{endtime}/{dayofweek}/", "/libraryhour/{libhourid}/{starttime}/{endtime}/{dayofweek}/"})
    public void modifyLibraryHours(@PathVariable("libhourid") int libHourId,
                                @PathVariable("starttime") Time startTime, @PathVariable("endtime") Time endTime,
                                @PathVariable("dayofweek") DayOfWeek DOW,
                                   @RequestParam(value = "accountId", required = false)int accountId){
        //do we need to use JsonBody or Path Variable??
        service.updateLibraryHour(libHourId, startTime, endTime, DOW, accountId);
    }

    @DeleteMapping(value = {"/libraryhour/{libraryhourid}/", "/libraryhour/{libraryhourid}/"})
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
        private Time startTime;
        private Time endTime;
        private DayOfWeek dayOfWeek;
        private Integer libraryId;

        public Time getStartTime() {
            return startTime;
        }

        public void setStartTime(Time startTime) {
            this.startTime = startTime;
        }

        public Time getEndTime() {
            return endTime;
        }

        public void setEndTime(Time endTime) {
            this.endTime = endTime;
        }

        public DayOfWeek getDayOfWeek() {
            return dayOfWeek;
        }

        public void setDayOfWeek(DayOfWeek dayOfWeek) {
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
