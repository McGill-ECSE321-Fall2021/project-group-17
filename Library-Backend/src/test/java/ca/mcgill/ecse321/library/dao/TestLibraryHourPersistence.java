package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Library;
import ca.mcgill.ecse321.library.model.LibraryHour;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestLibraryHourPersistence {
    @Autowired
    private LibraryHourRepository libraryHourRepository;
    @AfterEach
    public void clearDatabase() {
        libraryHourRepository.deleteAll();
    }
    /*
Read test for libraryHour class. Ensure libraryHour and their attributes are properly stored and read from the database.
Written by Jerry Xia
*/
    @Test
    @Transactional
    public void testPersistAndLoadLibraryHour() {
        int id = 1234;
        String name = "McLennen";
        Library library = new Library(id);
        library.setName(name);
        Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
        Time endTime = java.sql.Time.valueOf(LocalTime.of(13, 25));
        DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
        LibraryHour libraryHour = new LibraryHour(0, startTime, endTime, dayOfWeek, null);
        libraryHour.setLibrary(library);
        libraryHourRepository.save(libraryHour);
        int libraryHourId = libraryHour.getId();
        libraryHour = null;
        libraryHour = libraryHourRepository.findLibraryHourById(libraryHourId);
        assertNotNull(libraryHour);
        assertEquals(libraryHourId, libraryHour.getId());
        assertEquals(startTime, libraryHour.getStartTime());
        assertEquals(endTime, libraryHour.getEndTime());
        assertEquals(dayOfWeek, libraryHour.getDayOfWeek());
        assertEquals(library.getName(), libraryHour.getLibrary().getName());
    }
}
