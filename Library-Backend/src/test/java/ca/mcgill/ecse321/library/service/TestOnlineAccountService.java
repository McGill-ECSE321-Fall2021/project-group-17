package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.CustomerRepository;
import ca.mcgill.ecse321.library.dao.HeadLibrarianRepository;
import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.dao.OnlineAccountRepository;
import ca.mcgill.ecse321.library.model.Customer;
import ca.mcgill.ecse321.library.model.HeadLibrarian;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.OnlineAccount;
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
public class TestOnlineAccountService {
    @Mock
    private OnlineAccountRepository onlineAccountRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private LibrarianRepository librarianRepository;
    @Mock
    private HeadLibrarianRepository headLibrarianRepository;

    @InjectMocks
    private OnlineAccountService service;

    private static final String USERNAME = "bob";
    private static final String PASSWORD = "1234";
    private static final String EMAIL = "bob@mail.com";
    private static final int CUSTOMER_KEY = 1;
    private static final int LIBRARIAN_KEY = 2;
    private static final int HEAD_LIBRARIAN_KEY = 3;
    private static final boolean LOGGEDIN = false;

    @BeforeEach
    public void setMockOutput() {
        lenient().when(onlineAccountRepository.findOnlineAccountByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(USERNAME)) {
                OnlineAccount account = new OnlineAccount();
                account.setUsername(USERNAME);
                account.setPassword(PASSWORD);
                account.setLoggedIn(LOGGEDIN);
                Customer customer = new Customer();
                customer.setId(CUSTOMER_KEY);
                account.setPersonRole(customer);
                return account;
            }
            else {
                return null;
            }
        });
        lenient().when(customerRepository.findPersonRoleById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(CUSTOMER_KEY)) {
                Customer customer = new Customer();
                customer.setId(CUSTOMER_KEY);
                return customer;
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
        lenient().when(headLibrarianRepository.findPersonRoleById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(HEAD_LIBRARIAN_KEY)) {
                HeadLibrarian librarian = new HeadLibrarian();
                librarian.setId(HEAD_LIBRARIAN_KEY);
                return librarian;
            } else {
                return null;
            }
        });
    }

    /**
     * Creates an OnlineAccount for a customer successfully
     */
    @Test
    public void testCreateOnlineAccountCustomerValid() {
        OnlineAccount account = null;

        try {
            account = service.createOnlineAccountCustomer(USERNAME, PASSWORD, CUSTOMER_KEY, EMAIL);
        } catch (Exception e) {
            fail();
        }
        assertNotNull(account);
        assertEquals(account.getUsername(), USERNAME);
        assertEquals(account.getPassword(), PASSWORD);
        assertNotNull(account.getPersonRole());
        assertEquals(account.getPersonRole().getId(), CUSTOMER_KEY);
        assertEquals(LOGGEDIN, account.getLoggedIn());
    }

    /**
     * Fails to create an OnlineAccount for a customer because the username field is null
     */
    @Test
    public void testCreateOnlineAccountCustomerNoUsername() {
        OnlineAccount account = null;
        String error = "";

        try {
            account = service.createOnlineAccountCustomer(null, PASSWORD, CUSTOMER_KEY, EMAIL);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(account);
        assertEquals("Cannot create account with null username.", error);
    }

    /**
     * Fails to create an OnlineAccount for a customer because the password field is null
     */
    @Test
    public void testCreateOnlineAccountCustomerNoPassword() {
        OnlineAccount account = null;
        String error = "";

        try {
            account = service.createOnlineAccountCustomer(USERNAME, null, CUSTOMER_KEY, EMAIL);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(account);
        assertEquals("Cannot create account with null password.", error);
    }

    /**
     * Fails to create an OnlineAccount for a customer because the personRoleId field is null
     */
    @Test
    public void testCreateOnlineAccountCustomerNoPersonRoleId() {
        OnlineAccount account = null;
        String error = "";

        try {
            account = service.createOnlineAccountCustomer(USERNAME, PASSWORD, null, EMAIL);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(account);
        assertEquals("Cannot create an account without a user.", error);
    }

    /**
     * Fails to create an OnlineAccount for a customer because the email field is null
     */
    @Test
    public void testCreateOnlineAccountCustomerNoEmail() {
        OnlineAccount account = null;
        String error = "";

        try {
            account = service.createOnlineAccountCustomer(USERNAME, PASSWORD, CUSTOMER_KEY, null);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(account);
        assertEquals("Cannot create an account without an email", error);
    }

    /**
     * Fails to create an OnlineAccount for a customer because the personRoleId does not
     * correspond to a customer in the database
     */
    @Test
    public void testCreateOnlineAccountCustomerInvalidPersonRoleId() {
        OnlineAccount account = null;
        String error = "";

        try {
            account = service.createOnlineAccountCustomer(USERNAME, PASSWORD, 4321, EMAIL);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(account);
        assertEquals("No user exists with the personRoleId given", error);
    }

    /**
     * Creates an OnlineAccount for a librarian successfully
     */
    @Test
    public void testCreateOnlineAccountLibrarianValid() {
        OnlineAccount account = null;

        try {
            account = service.createOnlineAccountLibrarian(USERNAME, PASSWORD, LIBRARIAN_KEY, EMAIL);
        } catch (Exception e) {
            fail();
        }
        assertNotNull(account);
        assertEquals(account.getUsername(), USERNAME);
        assertEquals(account.getPassword(), PASSWORD);
        assertNotNull(account.getPersonRole());
        assertEquals(account.getPersonRole().getId(), LIBRARIAN_KEY);
        assertEquals(LOGGEDIN, account.getLoggedIn());
    }

    /**
     * Fails to create an OnlineAccount for a librarian because the username field is null
     */
    @Test
    public void testCreateOnlineAccountLibrarianNoUsername() {
        OnlineAccount account = null;
        String error = "";

        try {
            account = service.createOnlineAccountLibrarian(null, PASSWORD, LIBRARIAN_KEY, EMAIL);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(account);
        assertEquals("Cannot create account with null username.", error);
    }

    /**
     * Fails to create an OnlineAccount for a librarian because the password field is null
     */
    @Test
    public void testCreateOnlineAccountLibrarianNoPassword() {
        OnlineAccount account = null;
        String error = "";

        try {
            account = service.createOnlineAccountLibrarian(USERNAME, null, LIBRARIAN_KEY, EMAIL);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(account);
        assertEquals("Cannot create account with null password.", error);
    }

    /**
     * Fails to create an OnlineAccount for a librarian because the personRoleId field is null
     */
    @Test
    public void testCreateOnlineAccountLibrarianNoPersonRoleId() {
        OnlineAccount account = null;
        String error = "";

        try {
            account = service.createOnlineAccountLibrarian(USERNAME, PASSWORD, null, EMAIL);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(account);
        assertEquals("Cannot create an account without a user.", error);
    }

    /**
     * Fails to create an OnlineAccount for a librarian because the email field is null
     */
    @Test
    public void testCreateOnlineAccountLibrarianNoEmail() {
        OnlineAccount account = null;
        String error = "";

        try {
            account = service.createOnlineAccountLibrarian(USERNAME, PASSWORD, CUSTOMER_KEY, null);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(account);
        assertEquals("Cannot create an account without an email", error);
    }

    /**
     * Fails to create an OnlineAccount for a librarian because the personRoleId does not correspond
     * to a librarian in the database
     */
    @Test
    public void testCreateOnlineAccountLibrarianInvalidPersonRoleId() {
        OnlineAccount account = null;
        String error = "";

        try {
            account = service.createOnlineAccountLibrarian(USERNAME, PASSWORD, 4321, EMAIL);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(account);
        assertEquals("No user exists with the personRoleId given", error);
    }

    /**
     * Creates an OnlineAccount for a head librarian successfully
     */
    @Test
    public void testCreateOnlineAccountHeadLibrarianValid() {
        OnlineAccount account = null;

        try {
            account = service.createOnlineAccountHeadLibrarian(USERNAME, PASSWORD, HEAD_LIBRARIAN_KEY, EMAIL);
        } catch (Exception e) {
            fail();
        }
        assertNotNull(account);
        assertEquals(account.getUsername(), USERNAME);
        assertEquals(account.getPassword(), PASSWORD);
        assertNotNull(account.getPersonRole());
        assertEquals(account.getPersonRole().getId(), HEAD_LIBRARIAN_KEY);
        assertEquals(LOGGEDIN, account.getLoggedIn());
    }

    /**
     * Fails to create an OnlineAccount for a head librarian because the username field is null
     */
    @Test
    public void testCreateOnlineAccountHeadLibrarianNoUsername() {
        OnlineAccount account = null;
        String error = "";

        try {
            account = service.createOnlineAccountCustomer(null, PASSWORD, CUSTOMER_KEY, EMAIL);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(account);
        assertEquals("Cannot create account with null username.", error);
    }

    /**
     * Fails to create an OnlineAccount for a head librarian because the password field is null
     */
    @Test
    public void testCreateOnlineAccountHeadLibrarianNoPassword() {
        OnlineAccount account = null;
        String error = "";

        try {
            account = service.createOnlineAccountCustomer(USERNAME, null, HEAD_LIBRARIAN_KEY, EMAIL);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(account);
        assertEquals("Cannot create account with null password.", error);
    }

    /**
     * Fails to create an OnlineAccount for a head librarian because the personRoleId field is null
     */
    @Test
    public void testCreateOnlineAccountHeadLibrarianNoPersonRoleId() {
        OnlineAccount account = null;
        String error = "";

        try {
            account = service.createOnlineAccountHeadLibrarian(USERNAME, PASSWORD, null, EMAIL);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(account);
        assertEquals("Cannot create an account without a user.", error);
    }

    /**
     * Fails to create an OnlineAccount for a head librarian because the email field is null
     */
    @Test
    public void testCreateOnlineAccountHeadLibrarianNoEmail() {
        OnlineAccount account = null;
        String error = "";

        try {
            account = service.createOnlineAccountHeadLibrarian(USERNAME, PASSWORD, CUSTOMER_KEY, null);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(account);
        assertEquals("Cannot create an account without an email", error);
    }

    /**
     * Fails to create an OnlineAccount for a head librarian because the personRoleId does not
     * correspond to a head librarian in the database
     */
    @Test
    public void testCreateOnlineAccountHeadLibrarianInvalidPersonRoleId() {
        OnlineAccount account = null;
        String error = "";

        try {
            account = service.createOnlineAccountHeadLibrarian(USERNAME, PASSWORD, 4321, EMAIL);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(account);
        assertEquals("No user exists with the personRoleId given", error);
    }

    /**
     * Retrieves an account successfully for the given username
     */
    @Test
    public void testGetOnlineAccountValid() {
        OnlineAccount account = null;

        try {
            account = service.getOnlineAccount(USERNAME);
        } catch (Exception e) {
            fail();
        }

        assertNotNull(account);
        assertEquals(account.getUsername(), USERNAME);
        assertEquals(account.getPassword(), PASSWORD);
        assertNotNull(account.getPersonRole());
        assertEquals(account.getPersonRole().getId(), CUSTOMER_KEY);
        assertEquals(LOGGEDIN, account.getLoggedIn());
    }

    /**
     * Fails to get an OnlineAccount because the username field is null
     */
    @Test
    public void testGetOnlineAccountNoUsername() {
        OnlineAccount account = null;
        String error = "";

        try {
            account = service.getOnlineAccount(null);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertNull(account);
        assertEquals("Cannot get account with a null username", error);
    }

    /**
     * Fails to get an OnlineAccount for the given username because it does not exist within the
     * database
     */
    @Test
    public void testGetOnlineAccountInvalidUsername() {
        OnlineAccount account = null;
        String error = "";

        try {
            account = service.getOnlineAccount("john");
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertNull(account);
        assertEquals("Cannot find the account for the given username", error);
    }

    /**
     * Successfully logs in an online account for the given username
     */
    @Test
    public void testLogInValid() {
        OnlineAccount account = null;

        try {
            account = service.logIn(USERNAME, PASSWORD);
        } catch (Exception e) {
            fail();
        }

        assertNotNull(account);
        assertTrue(account.getLoggedIn());
    }

    /**
     * Fails to login an OnlineAccount because the username field is null
     */
    @Test
    public void testLogInNoUsername() {
        OnlineAccount account = null;
        String error = "";

        try {
            account = service.logIn(null, PASSWORD);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertNull(account);
        assertEquals("Cannot find username to login user.", error);
    }

    /**
     * Fails to login an OnlineAccount because the password field is null
     */
    @Test
    public void testLogInNoPassword() {
        OnlineAccount account = null;
        String error = "";

        try {
            account = service.logIn(USERNAME, null);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertNull(account);
        assertEquals("Cannot find password to login user.", error);
    }

    /**
     * Fails to login an OnlineAccount because the account for the given username does not exist
     * within the database
     */
    @Test
    public void testLogInInvalidUsername() {
        OnlineAccount account = null;
        String error = "";

        try {
            account = service.logIn("john", PASSWORD);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertNull(account);
        assertEquals("Cannot find account by username.", error);
    }

    /**
     * Logs out an OnlineAccount for the given username successfully.
     */
    @Test
    public void testLogoutValid() {
        OnlineAccount account = null;

        try {
            account = service.logout(USERNAME);
        } catch (Exception e) {
            fail();
        }

        assertNotNull(account);
        assertFalse(account.getLoggedIn());
    }

    /**
     * Fails to logout an OnlineAccount because the username field is null
     */
    @Test
    public void testLogoutNoUsername() {
        OnlineAccount account = null;
        String error = "";

        try {
            account = service.logout(null);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertNull(account);
        assertEquals("Cannot find username to delete account.", error);
    }

    /**
     * Fails to logout an OnlineAccount because the account for the given username does not exist
     * in the database
     */
    @Test
    public void testLogoutInvalidUsername() {
        OnlineAccount account = null;
        String error = "";

        try {
            account = service.logout("john");
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertNull(account);
        assertEquals("No account with said username exists", error);
    }

    /**
     * Successfully deletes the OnlineAccount for a customer
     */
    @Test
    public void testDeleteAccountCustomerValid() {

        try {
            service.deleteOnlineAccountCustomer(USERNAME, CUSTOMER_KEY);
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Fails to delete the customer OnlineAccount because the username field is null
     */
    @Test
    public void testDeleteAccountCustomerNoUsername() {
        String error = "";

        try {
            service.deleteOnlineAccountCustomer(null, CUSTOMER_KEY);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Cannot find username to delete account.", error);
    }

    /**
     * Fails to delete the customer OnlineAccount because the username does not correspond to an
     * account in the database
     */
    @Test
    public void testDeleteAccountCustomerInvalidUsername() {
        String error = "";

        try {
            service.deleteOnlineAccountCustomer("john", CUSTOMER_KEY);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Cannot find account.", error);
    }

    /**
     * Fails to delete the customer OnlineAccount because the personRoleId field is null
     */
    @Test
    public void testDeleteAccountCustomerNoPersonRoleId() {
        String error = "";

        try {
            service.deleteOnlineAccountCustomer(USERNAME, null);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Cannot find personRoleId to delete account.", error);
    }

    /**
     * Fails to delete the customer OnlineAccount because the personRoleId field does not
     * correspond to a personRole in the database
     */
    @Test
    public void testDeleteAccountCustomerInvalidPersonRoleId() {
        String error = "";

        try {
            service.deleteOnlineAccountCustomer(USERNAME, 4321);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Cannot find personRole to delete account.", error);
    }

    /**
     * Successfully deletes the OnlineAccount for a librarian
     */
    @Test
    public void testDeleteAccountLibrarianValid() {

        try {
            service.deleteOnlineAccountLibrarian(USERNAME, LIBRARIAN_KEY);
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Fails to delete the librarian OnlineAccount because the username field is null
     */
    @Test
    public void testDeleteAccountLibrarianNoUsername() {
        String error = "";

        try {
            service.deleteOnlineAccountLibrarian(null, LIBRARIAN_KEY);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Cannot find username to delete account.", error);
    }

    /**
     * Fails to delete the librarian OnlineAccount because the username does not correspond to an
     * account in the database
     */
    @Test
    public void testDeleteAccountLibrarianInvalidUsername() {
        String error = "";

        try {
            service.deleteOnlineAccountLibrarian("john", LIBRARIAN_KEY);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Cannot find account.", error);
    }

    /**
     * Fails to delete the librarian OnlineAccount because the personRoleId field is null
     */
    @Test
    public void testDeleteAccountLibrarianNoPersonRoleId() {
        String error = "";

        try {
            service.deleteOnlineAccountLibrarian(USERNAME, null);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Cannot find personRoleId to delete account.", error);
    }

    /**
     * Fails to delete the librarian OnlineAccount because the personRoleId field does not
     * correspond to a personRole in the database
     */
    @Test
    public void testDeleteAccountLibrarianInvalidPersonRoleId() {
        String error = "";

        try {
            service.deleteOnlineAccountLibrarian(USERNAME, 4321);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Cannot find personRole to delete account.", error);
    }

    /**
     * Successfully deletes the OnlineAccount for a head librarian
     */
    @Test
    public void testDeleteAccountHeadLibrarianValid() {

        try {
            service.deleteOnlineAccountHeadLibrarian(USERNAME, HEAD_LIBRARIAN_KEY);
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Fails to delete the head librarian OnlineAccount because the username field is null
     */
    @Test
    public void testDeleteAccountHeadLibrarianNoUsername() {
        String error = "";

        try {
            service.deleteOnlineAccountHeadLibrarian(null, HEAD_LIBRARIAN_KEY);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Cannot find username to delete account.", error);
    }

    /**
     * Fails to delete the head librarian OnlineAccount because the username does not correspond to an
     * account in the database
     */
    @Test
    public void testDeleteAccountHeadLibrarianInvalidUsername() {
        String error = "";

        try {
            service.deleteOnlineAccountHeadLibrarian("john", HEAD_LIBRARIAN_KEY);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Cannot find account.", error);
    }

    /**
     * Fails to delete the head librarian OnlineAccount because the personRoleId field is null
     */
    @Test
    public void testDeleteAccountHeadLibrarianNoPersonRoleId() {
        String error = "";

        try {
            service.deleteOnlineAccountHeadLibrarian(USERNAME, null);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Cannot find personRoleId to delete account.", error);
    }

    /**
     * Fails to delete the head librarian OnlineAccount because the personRoleId field does not
     * correspond to a personRole in the database
     */
    @Test
    public void testDeleteAccountHeadLibrarianInvalidPersonRoleId() {
        String error = "";

        try {
            service.deleteOnlineAccountHeadLibrarian(USERNAME, 4321);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Cannot find personRole to delete account.", error);
    }
}
