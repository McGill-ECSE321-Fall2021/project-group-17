package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPlayerRolePersistance {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private LibrarianRepository librarianRepository;
    @Autowired
    private HeadLibrarianRepository headLibrarianRepository;
    @Autowired
    private ShiftRepository shiftRepository;

    @AfterEach
    public void clearDatabase(){
        customerRepository.deleteAll();
        librarianRepository.deleteAll();
        headLibrarianRepository.deleteAll();
        shiftRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadCustomer(){
        int penalty = 0;
        String roleType = "Customer";
        //Associations
    }

    @Test
    public void testPersistAndLoadLibrarian(){
        //Associations
    }
    @Test
    public void testPersistAndLoadHeadLibrarian(){

    }
    @Test
    public void testPersistAndLoadShift(){
        Integer shiftID = 5432;
        Time startTime = java.sql.Time.valueOf(java.time.LocalTime.now());
        Time endTime = java.sql.Time.valueOf(java.time.LocalTime.now());
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,16));
        DayOfWeek DOW = java.time.DayOfWeek.valueOf("Monday");
        Shift shift = new Shift();
        shift.setDayOfWeek(DOW);
        shift.setEndTime(endTime);
        shift.setStartTime(startTime);
        shift.setId(shiftID);
        shiftRepository.save(shift);
        shift = null;
        shift = shiftRepository.findShiftByID(shiftID);
        assertNotNull(shift);
        assertEquals(shiftID, shift.getId());
        assertEquals(startTime, shift.getStartTime());
        assertEquals(endTime, shift.getEndTime());
        assertEquals(DOW, shift.getDayOfWeek());
    }
}
