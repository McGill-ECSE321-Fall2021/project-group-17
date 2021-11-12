package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.HeadLibrarianRepository;
import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.dao.OnlineAccountRepository;
import ca.mcgill.ecse321.library.dao.PersonRepository;
import ca.mcgill.ecse321.library.model.HeadLibrarian;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.OnlineAccount;
import ca.mcgill.ecse321.library.model.Person;
import jdk.javadoc.internal.doclets.formats.html.markup.Head;
import org.junit.jupiter.api.AfterEach;
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
public class TestHeadLibrarianService {
    @Mock
    private OnlineAccountRepository onlineAccountRepository;
    @Mock
    private PersonRepository personRepository;
    @Mock
    private HeadLibrarianRepository headLibrarianRepository;
    @InjectMocks
    private PersonService personService;
    @InjectMocks
    private HeadLibrarianService headLibrarianService;

    private static final String USERNAME = "librarian";
    private static final String PASSWORD = "1234";
    private static final String EMAIL = "librarian@mail.com";
    private static final boolean LOGGEDIN = true;
    private static final int PERSON_KEY = 3;
    private static final int PERSON_KEY2 = 4;
    private static final int HEAD_LIBRARIAN_KEY = 5;
    private static final int HEAD_LIBRARIAN_KEY2 = 7;
    private static final int HEAD_LIBRARIAN_KEY3 = -3;

    @BeforeEach
    public void setMockOutput() {
        lenient().when(personRepository.findPersonById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(PERSON_KEY)) {
                Person person = new Person();
                person.setId(PERSON_KEY);
                return person;
            } else {
                return null;
            }
        });
        lenient().when(headLibrarianRepository.findPersonRoleById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(HEAD_LIBRARIAN_KEY)) {
                HeadLibrarian headLibrarian = new HeadLibrarian();
                headLibrarian.setId(HEAD_LIBRARIAN_KEY);
                return headLibrarian;
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
                HeadLibrarian headLibrarian = new HeadLibrarian();
                headLibrarian.setId(HEAD_LIBRARIAN_KEY);
                account.setPersonRole(headLibrarian);
                return account;
            }
            else {
                return null;
            }
        });

    }

    @AfterEach
    public void clearDatabase() {
        personRepository.deleteAll();
        headLibrarianRepository.deleteAll();
    }

    /**
     * creates a valid headLibrarian
     */
    @Test
    public void CreateHeadLibrarianValid(){
        Person person=null;
        HeadLibrarian headLibrarian=null;
        try{
            person= personService.getPerson(PERSON_KEY);
        }
        catch(Exception e){
            fail();
        }
        try{
            headLibrarian=headLibrarianService.createHeadLibrarian(person.getId());
        }
        catch (Exception e){
            fail();
        }
        assertNotNull(headLibrarian);
        assertEquals(headLibrarian.getPerson().getId(),person.getId());


    }

    /**
     * tests whats happens what you try to create a headlibrarian with an invalid id
     */
    @Test
    public void CreateHeadLibrarianInvalidID(){
        String error=null;
        HeadLibrarian headLibrarian=null;
        try{
            headLibrarian=headLibrarianService.createHeadLibrarian(null);

        }
        catch(Exception e){
            error=e.getMessage();
        }
        assertEquals("Invalid Id",error);

    }

    /**
     * tests what happens when you create a head librarian with a null person
     */
    @Test
    public void CreateHeadLibrarianNullPerson(){
        String error=null;
        HeadLibrarian headLibrarian=null;
        try{
            headLibrarian=headLibrarianService.createHeadLibrarian(PERSON_KEY2);
        }
        catch(Exception e){
            error=e.getMessage();
        }
        assertEquals("No person by this Id", error);
    }

    /**
     * tests retrieving the headlibrarian
     */
    @Test
    public void GetHeadLibrarian(){
        HeadLibrarian headLibrarian=null;
        try{
            headLibrarian=headLibrarianService.getHeadLibrarian(HEAD_LIBRARIAN_KEY);
        }
        catch(Exception e){
            fail();
        }
    }

    /**
     * tests what happens when you retrive a headlibrarian that does not exist
     */
    @Test
    public void GetHeadLibrarianNotFound(){
        String error=null;
        HeadLibrarian headLibrarian=null;
        try{
            headLibrarian=headLibrarianService.getHeadLibrarian(HEAD_LIBRARIAN_KEY2);
        }
        catch(Exception e){
            error=e.getMessage();
        }
        assertEquals("No librarian by this id",error);
    }

    /**
     * tests what happens when you try to retrieve a headlibrarian with an invalid id
     */
    @Test
    public void GetHeadLibrarianInvalid(){
        String error=null;
        HeadLibrarian headLibrarian=null;
        try{
            headLibrarian=headLibrarianService.getHeadLibrarian(HEAD_LIBRARIAN_KEY3);
        }
        catch(Exception e){
            error=e.getMessage();
        }
        assertEquals("Invalid Id",error);
    }

    /**
     * tests deleting a valid librarian
     */
    @Test
    public void DeleteHeadLibrarianValid(){
        try{
            headLibrarianService.deleteHeadLibrarian(HEAD_LIBRARIAN_KEY,USERNAME);
        }
        catch(Exception e){
            fail();
        }
    }

    /**
     * tests deleting a head librarian that does not exist
     */
    @Test
    public void DeleteHeadLibrarianInValid(){
        String error=null;
        try{
            headLibrarianService.deleteHeadLibrarian(HEAD_LIBRARIAN_KEY2,USERNAME);
        }
        catch(Exception e){
            error= e.getMessage();
        }
        assertEquals("No person role associated with this account",error);
    }

    /**
     * tests updating a head librarian valid
     */
    @Test
    public void UpdateHeadLibrarian(){
        HeadLibrarian headLibrarian = null;

        try {
            headLibrarian = headLibrarianService.updateHeadLibrarian(HEAD_LIBRARIAN_KEY, PERSON_KEY, USERNAME);
        } catch (Exception e) {
            fail();
        }

        assertNotNull(headLibrarian);
        assertEquals(HEAD_LIBRARIAN_KEY, headLibrarian.getId());
        assertEquals(PERSON_KEY, headLibrarian.getPerson().getId());
        assertEquals(USERNAME, headLibrarian.getAccount().getUsername());
    }

    /**
     * tests updating a head librarian when passing a null id
     */
    @Test
    public void UpdateHeadLibrarianInvalid(){
        HeadLibrarian headLibrarian =null;
        String error=null;
        try {
            headLibrarian = headLibrarianService.updateHeadLibrarian(null, PERSON_KEY, USERNAME);
        } catch (Exception e) {
            error=e.getMessage();
        }
        assertEquals(error,"Invalid Id");
    }



}


