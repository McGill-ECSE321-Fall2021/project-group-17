package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dto.ShiftDTO;
import ca.mcgill.ecse321.library.service.LibrarianService;
import ca.mcgill.ecse321.library.service.ShiftService;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonInclude;

import ca.mcgill.ecse321.library.dao.PersonRoleRepository;
import ca.mcgill.ecse321.library.model.HeadLibrarian;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.PersonRole;
import ca.mcgill.ecse321.library.model.Shift;
import ca.mcgill.ecse321.library.service.Exception.PersonException;

@CrossOrigin(origins = "*")
@RestController
public class ShiftRestController {
    @Autowired
    private ShiftService shiftService;
    @Autowired
    private LibrarianService librarianService;
    @Autowired
    private PersonRoleRepository personRoleRepository;


    @PostMapping(value = { "/shift/librarian","/shift/librarian"})
    public ShiftDTO createShiftLibrarian(@RequestBody JsonBody body,
                                @RequestParam(value = "accountusername", required = false) String accountUsername) throws IllegalArgumentException{
        Shift shift = shiftService.createShiftLibrarian(body.getStartTime(), body.getEndTime(), body.getDayOfWeek(), body.getLibrarianId(), accountUsername);
        return convertToDTO(shift);
    }
    @PostMapping(value = { "/shift/headLibrarian","/shift/headLibrarian"})
    public ShiftDTO createShiftHeadLibrarian(@RequestBody JsonBody body,
                                @RequestParam(value = "accountUsername", required = false) String accountUsername) throws IllegalArgumentException{
        Shift shift = shiftService.createShiftHeadLibrarian(body.getStartTime(), body.getEndTime(), body.getDayOfWeek(), body.getLibrarianId(), accountUsername);
        return convertToDTO(shift);
    }

    @GetMapping(value = {"/shift/{id}", "/shift/{id}"})
    public ShiftDTO getShift(@PathVariable("id") int id) throws IllegalArgumentException {
        Shift shift = shiftService.getShift(id);
        return convertToDTO(shift);
    }

    @PutMapping(value = {"/shift/librarian/{shiftid}", "/shift/librarian/{shiftid}"})
    public void modifyLibraryHoursLibrarian(@PathVariable("shiftid") int shiftId,
                                    @RequestBody JsonBody body,
                                   @RequestParam(value = "accountUsername", required = false)String accountUsername){
        shiftService.updateShiftLibrarian(shiftId, body.getStartTime(), body.getEndTime(), body.getDayOfWeek(),
                body.getLibrarianId(), accountUsername);
    }
    @PutMapping(value = {"/shift/headLibrarian/{shiftid}", "/shift/headLibrarian/{shiftid}"})
    public void modifyLibraryHoursHeadLibrarian(@PathVariable("shiftid") int shiftId,
                                            @RequestBody JsonBody body,
                                            @RequestParam(value = "accountUsername", required = false)String accountUsername){
        shiftService.updateShiftHeadLibrarian(shiftId, body.getStartTime(), body.getEndTime(), body.getDayOfWeek(),
                body.getLibrarianId(), accountUsername);
    }
    @DeleteMapping(value = {"/shift/{shiftid}", "/libraryhour/{shiftid}"})
    public void deleteShift(@PathVariable("shiftid") int shiftId, @RequestParam(value = "accountusername", required = false)String accountUsername){
        shiftService.deleteShift(accountUsername, shiftId);
    }
    @GetMapping(value= {"/shift/librarian/{librarianId}"})
    @ResponseBody
    public List<ShiftDTO> getShifts(@PathVariable("librarianId") Integer librarianId) throws IllegalArgumentException{
    	return shiftService.getLibrarianShifts(librarianId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private static class JsonBody{
        private String startTime;
        private String endTime;
        private String dayOfWeek;
        private Integer librarianId;

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

        public Integer getLibrarianId() {
            return librarianId;
        }

        public void setLibrarianId(Integer librarianId) {
            this.librarianId = librarianId;
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
