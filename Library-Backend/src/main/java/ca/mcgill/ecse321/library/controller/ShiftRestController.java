package ca.mcgill.ecse321.library.controller;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude;

import ca.mcgill.ecse321.library.dto.ShiftDTO;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.Shift;
import ca.mcgill.ecse321.library.service.ShiftService;

@CrossOrigin(origins = "*")
@RestController
public class ShiftRestController {
    @Autowired
    private ShiftService shiftService;

    @PostMapping(value= {"/shift/{id}","/shift/{id}/"})
    @ResponseBody
    public ShiftDTO createShift(@PathVariable("id") Integer id, @RequestBody JsonBody body) throws IllegalArgumentException{
        return null;
    }
    @GetMapping(value= {"/shift/{librarianId}"})
    @ResponseBody
    public List<ShiftDTO> getShifts(@PathVariable("librarianId") Integer librarianId) throws IllegalArgumentException{
        List<Shift> shifts = shiftService.getShifts(librarianId);
        List<ShiftDTO> out = new ArrayList<ShiftDTO>();
        for(Shift s : shifts) {
        	out.add(convertToDTO(s));
        }
        return out;
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
