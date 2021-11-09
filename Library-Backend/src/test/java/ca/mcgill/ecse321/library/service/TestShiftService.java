package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.sql.Time;
import java.time.DayOfWeek;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestShiftService {
    @Mock
    private ShiftRepository shiftRepository;
    @Mock
    private LibrarianRepository librarianRepository;
    @Mock
    private OnlineAccountRepository onlineAccountRepository;

    @InjectMocks
    private ShiftService shiftService;
    @InjectMocks
    private LibrarianService librarianService;
    @InjectMocks
    private OnlineAccountService onlineAccountService;

    private static final Time SHIFT_START_TIME = Time.valueOf("18:45:20");
    private static final Time SHIFT_END_TIME = Time.valueOf("19:52:19");
    private static final DayOfWeek DAY_OF_WEEK = DayOfWeek.valueOf("Monday");
    private static final DayOfWeek DAY_OF_WEEK_2 = DayOfWeek.valueOf("Wednesday");
    private static final int LIBRARIAN_KEY = 3;
    private static final String ACCOUNT_USERNAME = "username";
    private static final String ACCOUNT_PASSWORD = "password";
    private static final boolean LOGGED_IN = true;
    private static final int HEAD_LIBRARIAN_KEY = 5;
    private static final int SHIFT_KEY = 7;
    @BeforeEach
    public void setMockOutput() {

        lenient().when(shiftRepository.findShiftById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(SHIFT_KEY)) {
                Shift shift = new Shift();
                shift.setId(SHIFT_KEY);
                return shift;
            } else {
                return null;
            }
        });
        lenient().when(librarianRepository.findPersonRoleById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(LIBRARIAN_KEY)) {
                Librarian librarian = new Librarian();
                librarian.setId(LIBRARIAN_KEY);
                return librarian;
            } else {
                return null;
            }
        });

        lenient().when(onlineAccountRepository.findById(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(ACCOUNT_USERNAME)){
                OnlineAccount account = new OnlineAccount();
                account.setUsername(ACCOUNT_USERNAME);
                account.setPassword(ACCOUNT_PASSWORD);
                account.setLoggedIn(LOGGED_IN);
                HeadLibrarian headLibrarian = new HeadLibrarian();
                headLibrarian.setId(HEAD_LIBRARIAN_KEY);
                account.setPersonRole(headLibrarian);
                return account;
            } else {
                return null;
            }
        });
    }

    @Test
    public void createShift(){
        int id = LIBRARIAN_KEY;
        String id2 = ACCOUNT_USERNAME;
        Shift shift=null;
        try{
            shift = shiftService.createShift(SHIFT_START_TIME, SHIFT_END_TIME, DAY_OF_WEEK, LIBRARIAN_KEY, ACCOUNT_USERNAME);
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(shift);
        assertEquals(shift.getDayOfWeek(),DAY_OF_WEEK);
        assertEquals(shift.getStartTime(),SHIFT_START_TIME);
        assertEquals(shift.getEndTime(),SHIFT_END_TIME);
        assertEquals(shift.getLibrarian().getId(), LIBRARIAN_KEY);
    }

    @Test
    public void updateShift(){
        int id = LIBRARIAN_KEY;
        int id2 = SHIFT_KEY;
        String id3 = ACCOUNT_USERNAME;
        Shift shift = null;
        try{
            shift = shiftService.createShift(SHIFT_START_TIME, SHIFT_END_TIME, DAY_OF_WEEK, LIBRARIAN_KEY, ACCOUNT_USERNAME);
        }
        catch(Exception e){
            fail();
        }
        try{
            shiftService.updateShift(shift.getId(), SHIFT_START_TIME, SHIFT_END_TIME, DAY_OF_WEEK_2, LIBRARIAN_KEY, ACCOUNT_USERNAME);
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(shift);
        assertNotNull(shift);
        assertEquals(shift.getDayOfWeek(),DAY_OF_WEEK_2);
        assertEquals(shift.getStartTime(),SHIFT_START_TIME);
        assertEquals(shift.getEndTime(),SHIFT_END_TIME);
        assertEquals(shift.getLibrarian().getId(), LIBRARIAN_KEY);
    }
}