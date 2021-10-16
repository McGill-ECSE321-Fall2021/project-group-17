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
        Person person = new Person();
        Loan loan = new Loan();
        List<Loan> loans = null;
        loans.add(loan);
        Address address = new Address();
        OnlineAccount account = new OnlineAccount();
        LibraryCard libCard = new LibraryCard();
        Customer c = new Customer();
        c.setAddress(address);
        c.setLoans(loans);
        c.setAccount(account);
        c.setRoleType(roleType);
        c.setLibraryCard(libCard);
        c.setPerson(person);
        c.setPenalty(0);
        customerRepository.save(c);
        c = null;
        c = (Customer) customerRepository.findPersonRoleByRoleType(roleType);
        assertNotNull(c);
        assertEquals(roleType,c.getRoleType());
        assertEquals(loans, c.getLoans());
        assertEquals(account, c.getAccount());
        assertEquals(person, c.getPerson());
        assertEquals(0, c.getPenalty());
        assertEquals(libCard, c.getLibraryCard());
        assertEquals(address, c.getAddress());
    }

    @Test
    public void testPersistAndLoadLibrarian(){

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
        Librarian librarian = new Librarian();
        Shift shift = new Shift();
        shift.setLibrarian(librarian);
        shift.setDayOfWeek(DOW);
        shift.setEndTime(endTime);
        shift.setStartTime(startTime);
        shift.setId(shiftID);
        shiftRepository.save(shift);
        shift = null;
        shift = shiftRepository.findShiftByID(shiftID);
        assertNotNull(shift);
        assertEquals(librarian, shift.getLibrarian());
        assertEquals(shiftID, shift.getId());
        assertEquals(startTime, shift.getStartTime());
        assertEquals(endTime, shift.getEndTime());
        assertEquals(DOW, shift.getDayOfWeek());
    }
}
