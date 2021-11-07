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
        Time startTime = java.sql.Time.valueOf(java.time.LocalTime.now());
        Time endTime = java.sql.Time.valueOf(java.time.LocalTime.now());
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,16));
        DayOfWeek DOW = java.time.DayOfWeek.valueOf("MONDAY");
        Librarian librarian = new Librarian(0,null,null);
        librarianRepository.save(librarian);
        int libId = librarian.getId();

        Shift shift = new Shift();
        shift.setLibrarian(librarian);
        shift.setDayOfWeek(DOW);
        shift.setEndTime(endTime);
        shift.setStartTime(startTime);
        shift.setId(shiftID);
        shiftRepository.save(shift);
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
