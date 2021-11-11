package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.Shift;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestShiftPersistence {
    @Autowired
    private LibrarianRepository librarianRepository;
    @Autowired
    private ShiftRepository shiftRepository;
    @AfterEach
    public void clearDatabase() {
        librarianRepository.deleteAll();
        shiftRepository.deleteAll();

    }
    @Test
    @Transactional
    public void testPersistAndLoadShift(){
        Integer shiftID = 5432;
        Time startTime = Time.valueOf("12:42:53");
        Time endTime = Time.valueOf("13:43:12");
        DayOfWeek DOW = DayOfWeek.valueOf("MONDAY");
        String startTimeAsString = startTime.toString();
        String endTimeAsString= endTime.toString();
        String DOWAsString= DOW.toString();
        Librarian librarian = new Librarian(0,null,null);
        librarianRepository.save(librarian);
        int libId = librarian.getId();

        Shift shift = new Shift();
        shift.setLibrarian(librarian);
        shift.updateDayOfWeek(DOWAsString);
        shift.updateEndTime(endTimeAsString);
        shift.updateStartTime(startTimeAsString);
       // shift.setId(shiftID);
        shiftRepository.save(shift);
        shiftID = shift.getId();
        shift = null;
        librarian = null;
        shift = shiftRepository.findShiftById(shiftID);
        assertNotNull(shift);
        librarian = (Librarian) librarianRepository.findPersonRoleById(libId);
        assertEquals(librarian, shift.getLibrarian());
        assertEquals(shiftID, shift.getId());
        assertEquals(startTime, shift.getStartTime());
        assertEquals(endTime, shift.getEndTime());
        assertEquals(DOW, shift.getDayOfWeek());
    }
}
