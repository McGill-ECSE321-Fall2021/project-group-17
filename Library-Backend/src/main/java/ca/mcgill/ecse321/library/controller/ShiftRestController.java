package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dto.CustomerDTO;
import ca.mcgill.ecse321.library.dto.ShiftDTO;
import ca.mcgill.ecse321.library.model.*;
import ca.mcgill.ecse321.library.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonInclude;

import ca.mcgill.ecse321.library.dto.LibraryHourDTO;
import ca.mcgill.ecse321.library.service.LibraryHourService;

import java.sql.Time;
import java.time.DayOfWeek;

@CrossOrigin(origins = "*")
@RestController
public class ShiftRestController {
    @Autowired
    private ShiftService shiftService;

    @PostMapping(value= {"/shift/{id}/","/shift/{id}/"})
    @ResponseBody
    public ShiftDTO createShift(@PathVariable("id") Integer id, @RequestBody JsonBody body) throws IllegalArgumentException{
        return null;
    }

    @GetMapping(value = {"/shift/{id}/", "/shift/{id}/"})
    public ShiftDTO getLibraryHour(@PathVariable("id") int id) throws IllegalArgumentException {
        Shift shift = shiftService.getShift(id);
        return convertToDTO(shift);
    }
    
    @DeleteMapping(value = {"/shift/{shiftid}/", "/libraryhour/{shiftid}/"})
    public void deleteLibraryHour(@PathVariable("shiftid") int shiftId, @RequestParam(value = "accountid", required = false)int accountId){
        shiftService.deleteShift(accountId, shiftId);
    }
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private static class JsonBody{
        private Time startTime;
        private Time endTime;
        private DayOfWeek dayOfWeek;
        private Librarian librarian;

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

        public Librarian getLibrarian() {
            return librarian;
        }

        public void setLibrarian(Librarian librarian) {
            this.librarian = librarian;
        }

        public JsonBody(){}
    }

    private ShiftDTO convertToDTO(Shift shift){
        if (shift == null) {
            throw new IllegalArgumentException("There is no such Customer!");
        }
        ShiftDTO sDTO = new ShiftDTO();
        sDTO.setId(shift.getId());
        sDTO.setEndTime(shift.getEndTime());
        sDTO.setDayOfWeek(shift.getDayOfWeek());
        sDTO.setLibrarian(shift.getLibrarian());
        sDTO.setStartTime(shift.getStartTime());
        return sDTO;
    }
}
