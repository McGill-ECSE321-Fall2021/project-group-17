package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.OnlineAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestOnlineAccountPersistence {
    @Autowired
    private OnlineAccountRepository onlineAccountRepository;
    @AfterEach
    public void clearDatabase() {
        onlineAccountRepository.deleteAll();
    }
    @Test
    public void testPersistAndLoadOnlineAccountUsername(){
        String username = "fiona";
        String password = "abc123";
        OnlineAccount acct = new OnlineAccount();
        acct.setUsername(username);
        acct.setPassword(password);
        onlineAccountRepository.save(acct);

        String s = acct.getUsername();

        acct = null;

        acct = onlineAccountRepository.findOnlineAccountByUsername(s);
        assertNotNull(acct);
        assertEquals(username, acct.getUsername());
    }

    @Test
    public void testPersistAndLoadOnlineAccountPassword(){
        String username = "fiona";
        String password = "abc123";
        OnlineAccount acct = new OnlineAccount();
        acct.setUsername(username);
        acct.setPassword(password);
        onlineAccountRepository.save(acct);

        String s = acct.getUsername();

        acct = null;

        acct = onlineAccountRepository.findOnlineAccountByUsername(s);
        assertNotNull(acct);
        assertEquals(password, acct.getPassword());
    }
}
