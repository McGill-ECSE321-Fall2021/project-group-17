package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.CustomerRepository;
import ca.mcgill.ecse321.library.dao.OnlineAccountRepository;
import ca.mcgill.ecse321.library.model.Customer;
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
    OnlineAccountRepository onlineAccountRepository;
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private OnlineAccountService service;

    private static final String USERNAME = "bob";
    private static final String PASSWORD = "1234";
    private static final int CUSTOMER_KEY = 1;
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
    }

    @Test
    public void testCreateOnlineAccountValid() {
        OnlineAccount account = null;

        try {
            account = service.createOnlineAccountCustomer(USERNAME, PASSWORD, CUSTOMER_KEY);
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

    @Test
    public void testCreateOnlineAccountNoUsername() {
        OnlineAccount account = null;
        String error = "";

        try {
            account = service.createOnlineAccountCustomer(null, PASSWORD, CUSTOMER_KEY);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(account);
        assertEquals("Cannot create account with null username.", error);
    }

    @Test
    public void testCreateOnlineAccountNoPassword() {
        OnlineAccount account = null;
        String error = "";

        try {
            account = service.createOnlineAccountCustomer(USERNAME, null, CUSTOMER_KEY);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(account);
        assertEquals("Cannot create account with null password.", error);
    }

    @Test
    public void testCreateOnlineAccountNoPersonRoleId() {
        OnlineAccount account = null;
        String error = "";

        try {
            account = service.createOnlineAccountCustomer(USERNAME, PASSWORD, null);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(account);
        assertEquals("Cannot create an account without a user.", error);
    }

    @Test
    public void testCreateOnlineAccountInvalidPersonRoleId() {
        OnlineAccount account = null;
        String error = "";

        try {
            account = service.createOnlineAccountCustomer(USERNAME, PASSWORD, 4321);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(account);
        assertEquals("No user exists with the personRoleId given", error);
    }

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

    @Test
    public void testLogInValid() {
        OnlineAccount account = null;

        try {
            account = service.logIn(USERNAME, PASSWORD);
        } catch (Exception e) {
            fail();
        }

        assertNotNull(account);
        assertEquals(true, account.getLoggedIn());
    }

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

    @Test
    public void testLogoutValid() {
        OnlineAccount account = null;

        try {
            account = service.logout(USERNAME);
        } catch (Exception e) {
            fail();
        }

        assertNotNull(account);
        assertEquals(false, account.getLoggedIn());
    }

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

    @Test
    public void testDeleteAccountValid() {
        OnlineAccount account = null;

        try {
            service.deleteOnlineAccount(USERNAME, CUSTOMER_KEY);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testDeleteAccountNoUsername() {
        OnlineAccount account = null;
        String error = "";

        try {
            service.deleteOnlineAccount(null, CUSTOMER_KEY);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Cannot find username to delete account.", error);
    }

    @Test
    public void testDeleteAccountInvalidUsername() {
        OnlineAccount account = null;
        String error = "";

        try {
            service.deleteOnlineAccount("john", CUSTOMER_KEY);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Cannot find account.", error);
    }

    @Test
    public void testDeleteAccountNoPersonRoleId() {
        OnlineAccount account = null;
        String error = "";

        try {
            service.deleteOnlineAccount(USERNAME, null);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Cannot find personRoleId to delete account.", error);
    }

    @Test
    public void testDeleteAccountInvalidPersonRoleId() {
        OnlineAccount account = null;
        String error = "";

        try {
            service.deleteOnlineAccount(USERNAME, 4321);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Cannot find personRole to delete account.", error);
    }
}
