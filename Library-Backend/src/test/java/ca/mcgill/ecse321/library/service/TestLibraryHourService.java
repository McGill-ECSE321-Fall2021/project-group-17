package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.model.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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
public class TestLibraryHourService {
    @Mock
    private OnlineAccountRepository onlineAccountRepository;
    @Mock
    private LibraryHourRepository libraryHourRepository;
    @Mock
    private LibraryRepository libraryRepository;

    @InjectMocks
    private OnlineAccountService onlineAccountService;
    @InjectMocks
    private LibraryHourService libraryHourService;
    @InjectMocks
    private LibraryService libraryService;

    private static final Time LH_START_TIME = Time.valueOf("18:45:20");
    private static final String LH_START_TIME_AS_STRING = LH_START_TIME.toString();
    private static final Time LH_END_TIME = Time.valueOf("19:52:19");

    private static final String LH_END_TIME_AS_STRING = LH_END_TIME.toString();
    private static final DayOfWeek DAY_OF_WEEK = DayOfWeek.valueOf("MONDAY");
    private static final String DAY_OF_WEEK_AS_STRING = DAY_OF_WEEK.toString();
    private static final DayOfWeek DAY_OF_WEEK_2 = DayOfWeek.valueOf("WEDNESDAY");
    private static final String DAY_OF_WEEK_2_AS_STRING = DAY_OF_WEEK_2.toString();

    private static final int LIBRARY_KEY = 4;
    private static final String ACCOUNT_USERNAME = "username";
    private static final String ACCOUNT_PASSWORD = "password";
    private static final boolean LOGGED_IN = true;
    private static final int HEAD_LIBRARIAN_KEY = 5;
    private static final int LIBRARYHOUR_KEY = 7;
    @BeforeEach
    public void setMockOutput() {

        lenient().when(libraryHourRepository.findLibraryHourById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(LIBRARYHOUR_KEY)) {
                LibraryHour libraryHour = new LibraryHour();
                libraryHour.setId(LIBRARYHOUR_KEY);
                Library library = new Library();
                library.setId(LIBRARY_KEY);
                libraryHour.setLibrary(library);
                return libraryHour;
            } else {
                return null;
            }
        });
        lenient().when(libraryRepository.findLibraryById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(LIBRARY_KEY)) {
                Library library = new Library();
                library.setId(LIBRARY_KEY);
                return library;
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

        lenient().when(onlineAccountRepository.findOnlineAccountByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
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
    @AfterEach
    public void clearDatabase() {
        libraryHourRepository.deleteAll();
        onlineAccountRepository.deleteAll();
        libraryRepository.deleteAll();
    }

    @Test
    public void createLibraryHour(){
        int id = LIBRARYHOUR_KEY;
        String id2 = ACCOUNT_USERNAME;
        LibraryHour libraryHour = null;
        try{
            libraryHour = libraryHourService.createLibraryHour(LIBRARY_KEY, LH_START_TIME_AS_STRING,
                    LH_END_TIME_AS_STRING, DAY_OF_WEEK_AS_STRING, ACCOUNT_USERNAME);
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(libraryHour);
        assertEquals(libraryHour.getDayOfWeek(),DAY_OF_WEEK);
        assertEquals(libraryHour.getStartTime(),LH_START_TIME);
        assertEquals(libraryHour.getEndTime(),LH_END_TIME);
        assertEquals(libraryHour.getLibrary().getId(), LIBRARY_KEY);
    }

    @Test
    public void updateLibraryHour(){
        int id = LIBRARY_KEY;
        int id2 = LIBRARYHOUR_KEY;
        String id3 = ACCOUNT_USERNAME;
        LibraryHour libraryHour = null;
        try{
            libraryHour = libraryHourService.updateLibraryHour(LIBRARYHOUR_KEY, LH_START_TIME_AS_STRING,
                    LH_END_TIME_AS_STRING, DAY_OF_WEEK_2_AS_STRING, ACCOUNT_USERNAME);
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(libraryHour);
        assertEquals(libraryHour.getDayOfWeek(),DAY_OF_WEEK_2);
        assertEquals(libraryHour.getStartTime(),LH_START_TIME);
        assertEquals(libraryHour.getEndTime(),LH_END_TIME);
        assertEquals(libraryHour.getLibrary().getId(), LIBRARY_KEY);
    }
}