package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.PersonRepository;
import ca.mcgill.ecse321.library.dao.PersonRoleRepository;
import ca.mcgill.ecse321.library.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestPersonService {
    @Mock
    private PersonRepository personRepository;
    @Mock
    private PersonRoleRepository personRoleRepository;

    @InjectMocks
    private PersonService service;

    private static final int PERSON_KEY = 0;
    private static final String PERSON_NAME = "Tom";
    private static final int PERSON_ROLE_KEY_CUSTOMER = 2;
    private static final int PERSON_ROLE_KEY_LIBRARIAN = 12;
    List<Integer> PERSON_ROLES = Collections.singletonList(PERSON_ROLE_KEY_CUSTOMER);


    @BeforeEach
    public void setMockOutput() {
        lenient().when(personRepository.findPersonById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(PERSON_KEY)) {
                Person person = new Person();
                person.setId(PERSON_KEY);
                //person.setPersonRoleList(new ArrayList<>());
                return person;
            } else {
                return null;
            }
        });
        lenient().when(personRepository.findPersonByName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(PERSON_NAME)) {
                List<Person> persons = new ArrayList<>();
                Person person = new Person();
                person.setName(PERSON_NAME);
                persons.add(person);
                return persons;
            } else {
                return null;
            }
        });
        lenient().when(personRoleRepository.findPersonRoleById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(PERSON_ROLE_KEY_CUSTOMER)) {
                PersonRole person = new Customer();
                person.setId(PERSON_ROLE_KEY_CUSTOMER);
                return person;
            }
            else if(invocation.getArgument(0).equals(PERSON_ROLE_KEY_LIBRARIAN)){
                PersonRole person = new Customer();
                person.setId(PERSON_ROLE_KEY_LIBRARIAN);
                return person;
            }
            else {
                return null;
            }
        });
    }

    //START CREATE PERSON TESTS
    @Test
    public void testCreatePersonValid(){
        Person person = null;
        try{
            person = service.createPerson(PERSON_NAME,PERSON_ROLES);
        }
        catch (Exception e){
            fail();
        }
        assertNotNull(person);
        assertEquals(person.getId(), PERSON_KEY);
        assertEquals(person.getName(),PERSON_NAME);
        //assertEquals(person.getPersonRoleList().size(), PERSON_ROLES.size());
    }

    @Test
    public void testCreatePersonNullName(){
        Person person = null;
        String error = "";
        try{
            person = service.createPerson(null,PERSON_ROLES);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(person);
        assertEquals(error, "Cannot have person with no name");
    }

    @Test
    public void testCreatePersonNullPersonRole(){
        Person person = null;
        List<Integer> roles = new ArrayList<>(PERSON_ROLES);
        roles.add(null);
        String error = "";
        try{
            person = service.createPerson(PERSON_NAME,roles);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(person);
        assertEquals(error, "PersonRole id cannot be null");
    }

    @Test
    public void testCreatePersonMissingPersonRole(){
        Person person = null;
        List<Integer> roles = new ArrayList<>(PERSON_ROLES);
        roles.add(PERSON_ROLE_KEY_CUSTOMER+1);
        String error = "";
        try{
            person = service.createPerson(PERSON_NAME,roles);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(person);
        assertEquals(error, "Cannot find person role with that id");
    }
    //END CREATE PERSON TESTS

    //START GET ID PERSON TESTS
    @Test
    public void testGetPersonIdValid(){
        Person person = null;
        try{
            person =  service.getPerson(PERSON_KEY);
        }
        catch (Exception e){
            fail();
        }
        assertNotNull(person);
        assertEquals(person.getId(),PERSON_KEY);
    }

    @Test
    public void testGetPersonIdMissing(){
        Person person = null;
        String error = "";
        try{
            person =  service.getPerson(PERSON_KEY+1);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(person);
        assertEquals(error,"Cannot find a person with that id");
    }
    //END GET ID PERSON TESTS

    //START GET NAME PERSON TESTS
    @Test
    public void testGetPersonNameValid(){
        List<Person> persons = null;
        try{
            persons =  service.getPerson(PERSON_NAME);
        }
        catch (Exception e){
            fail();
        }
        assertNotNull(persons);
        assertEquals(persons.size(),1);
        assertEquals(persons.get(0).getName(), PERSON_NAME);
    }
    //END GET NAME PERSON TESTS

    //START UPDATE PERSON TESTS
    @Test
    public void testUpdatePersonValid(){
        Person p = null;
        List<Integer> roles = new ArrayList<>(PERSON_ROLES);
        roles.add(PERSON_ROLE_KEY_LIBRARIAN);
        try{
            p = service.updatePerson(PERSON_KEY,"BOB",roles);
        }
        catch (Exception e){
            fail();
        }
        assertNotNull(p);
        assertEquals(p.getName(),"BOB");
        //assertEquals(roles.size(),p.getPersonRoleList().size());
    }

    @Test
    public void testUpdatePersonIDMissing(){
        Person p = null;
        List<Integer> roles = new ArrayList<>(PERSON_ROLES);
        roles.add(PERSON_ROLE_KEY_LIBRARIAN);
        String error = "";
        try{
            p = service.updatePerson(PERSON_KEY+1,"BOB",roles);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(p);
        assertEquals(error,"Cannot find person with this id");
    }
    @Test
    public void testUpdatePersonPersonRoleNull(){
        Person p = null;
        List<Integer> roles = new ArrayList<>(PERSON_ROLES);
        roles.add(null);
        String error = "";
        try{
            p = service.updatePerson(PERSON_KEY,"BOB",roles);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(p);
        assertEquals(error,"PersonRole id cannot be null");
    }

    @Test
    public void testUpdatePersonPersonRoleMissing(){
        Person p = null;
        List<Integer> roles = new ArrayList<>(PERSON_ROLES);
        roles.add(PERSON_ROLE_KEY_CUSTOMER + 1);
        String error = "";
        try{
            p = service.updatePerson(PERSON_KEY,"BOB",roles);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(p);
        assertEquals(error,"Cannot find person role with that id");
    }
    //END UPDATE PERSON TESTS

    //START DELETE PERSON TESTS
    @Test
    public void testDeletePersonValid(){
        Person person = null;
        try{
            service.deletePerson(PERSON_KEY);
        }
        catch (Exception e){
            fail();
        }
    }
    //END DELETE PERSON TESTS
}
