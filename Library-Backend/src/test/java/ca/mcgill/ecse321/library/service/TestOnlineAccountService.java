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
    public void createOnlineAccount() {
        OnlineAccount account = null;

        try {
            account = service.createOnlineAccount(USERNAME, PASSWORD, CUSTOMER_KEY);
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
    public void getOnlineAccount() {
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
    public void logIn() {
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
    public void logout() {
        OnlineAccount account = null;

        try {
            account = service.logout(USERNAME);
        } catch (Exception e) {
            fail();
        }

        assertNotNull(account);
        assertEquals(false, account.getLoggedIn());
    }
}
