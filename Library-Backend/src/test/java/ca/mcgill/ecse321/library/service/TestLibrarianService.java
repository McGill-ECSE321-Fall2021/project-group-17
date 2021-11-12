package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.dao.OnlineAccountRepository;
import ca.mcgill.ecse321.library.dao.PersonRepository;
import ca.mcgill.ecse321.library.model.Customer;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.OnlineAccount;
import ca.mcgill.ecse321.library.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestLibrarianService {
    @Mock
    private LibrarianRepository librarianRepository;
    @Mock
    private PersonRepository personRepository;
    @Mock
    private OnlineAccountRepository onlineAccountRepository;

    @InjectMocks
    private LibrarianService service;

    private static final String USERNAME = "librarian";
    private static final String PASSWORD = "1234";
    private static final String EMAIL = "librarian@mail.com";
    private static final int PERSON_ID = 1;
    private static final int LIBRARIAN_KEY = 1;
    private static final boolean LOGGEDIN = false;

    @BeforeEach
    public void setMockOutput() {
        lenient().when(librarianRepository.findPersonRoleById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(LIBRARIAN_KEY)) {
                Librarian librarian = new Librarian();
                librarian.setId(LIBRARIAN_KEY);
                return librarian;
            } else {
                return null;
            }
        });
        lenient().when(personRepository.findPersonById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(PERSON_ID)) {
                Person person = new Person();
                person.setId(PERSON_ID);
                return person;
            } else {
                return null;
            }
        });
        lenient().when(onlineAccountRepository.findOnlineAccountByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(USERNAME)) {
                OnlineAccount account = new OnlineAccount();
                account.setUsername(USERNAME);
                account.setPassword(PASSWORD);
                account.setLoggedIn(LOGGEDIN);
                account.setEmail(EMAIL);
                Librarian librarian = new Librarian();
                librarian.setId(LIBRARIAN_KEY);
                account.setPersonRole(librarian);
                return account;
            }
            else {
                return null;
            }
        });
    }

    @Test
    public void TestCreateLibrarianValid() {
        Librarian librarian = null;

        try {
            librarian = service.createLibrarian(PERSON_ID);
        } catch (Exception e) {
            fail();
        }

        assertNotNull(librarian);
        assertEquals(PERSON_ID, librarian.getPerson().getId());
    }

    @Test
    public void TestCreateLibrarianNoPersonId() {
        Librarian librarian = null;
        String error = "";

        try {
            librarian = service.createLibrarian(null);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertNull(librarian);
        assertEquals("Invalid Id", error);
    }

    @Test
    public void TestCreateLibrarianInvalidPersonId() {
        Librarian librarian = null;
        String error = "";

        try {
            librarian = service.createLibrarian(5);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertNull(librarian);
        assertEquals("No person by this Id", error);
    }

    @Test
    public void TestUpdateLibrarianValid() {
        Librarian librarian = null;

        try {
            librarian = service.updateLibrarian(LIBRARIAN_KEY, PERSON_ID, USERNAME);
        } catch (Exception e) {
            fail();
        }

        assertNotNull(librarian);
        assertEquals(LIBRARIAN_KEY, librarian.getId());
        assertEquals(PERSON_ID, librarian.getPerson().getId());
        assertEquals(USERNAME, librarian.getAccount().getUsername());
    }

    @Test
    public void TestUpdateLibrarianNoLibrarianId() {
        Librarian librarian = null;
        String error = "";

        try {
            librarian = service.updateLibrarian(null, PERSON_ID, USERNAME);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertNull(librarian);
        assertEquals("Invalid Id", error);
    }

    @Test
    public void TestUpdateLibrarianNoPersonId() {
        Librarian librarian = null;
        String error = "";

        try {
            librarian = service.updateLibrarian(LIBRARIAN_KEY, null, USERNAME);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertNull(librarian);
        assertEquals("Invalid Id", error);
    }

    @Test
    public void TestUpdateLibrarianNoUsername() {
        Librarian librarian = null;
        String error = "";

        try {
            librarian = service.updateLibrarian(LIBRARIAN_KEY, PERSON_ID, null);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertNull(librarian);
        assertEquals("Invalid Id", error);
    }

    @Test
    public void TestUpdateLibrarianInvalidLibrarianId() {
        Librarian librarian = null;
        String error = "";

        try {
            librarian = service.updateLibrarian(15, PERSON_ID, USERNAME);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertNull(librarian);
        assertEquals("No librarian by this id", error);
    }

    @Test
    public void TestUpdateLibrarianInvalidPersonId() {
        Librarian librarian = null;
        String error = "";

        try {
            librarian = service.updateLibrarian(LIBRARIAN_KEY, 12, USERNAME);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertNull(librarian);
        assertEquals("No person by this Id", error);
    }

    @Test
    public void TestUpdateLibrarianInvalidUsername() {
        Librarian librarian = null;
        String error = "";

        try {
            librarian = service.updateLibrarian(LIBRARIAN_KEY, PERSON_ID, "username");
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertNull(librarian);
        assertEquals("No account by this username", error);
    }

    @Test
    public void TestGetLibrarianValid() {
        Librarian librarian = null;

        try {
            librarian = service.getLibrarian(LIBRARIAN_KEY);
        } catch (Exception e) {
            fail();
        }

        assertNotNull(librarian);
        assertEquals(LIBRARIAN_KEY, librarian.getId());
    }

    @Test
    public void TestGetLibrarianNoLibrarianId() {
        Librarian librarian = null;
        String error = "";

        try {
            librarian = service.getLibrarian(null);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertNull(librarian);
        assertEquals("Invalid Id", error);
    }

    @Test
    public void TestGetLibrarianInvalidLibrarianId() {
        Librarian librarian = null;
        String error = "";

        try {
            librarian = service.getLibrarian(10);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertNull(librarian);
        assertEquals("No librarian by this id", error);
    }
}
