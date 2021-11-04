package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.CustomerRepository;
import ca.mcgill.ecse321.library.dao.OnlineAccountRepository;
import ca.mcgill.ecse321.library.model.Customer;
import ca.mcgill.ecse321.library.model.OnlineAccount;
import ca.mcgill.ecse321.library.service.Exception.OnlineAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OnlineAccountService {
    @Autowired
    OnlineAccountRepository onlineAccountRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    public OnlineAccount createOnlineAccount(String username, String password) {
        OnlineAccount account = new OnlineAccount();
        account.setUsername(username);
        account.setPassword(password);
        onlineAccountRepository.save(account);
        return account;
    }

    @Transactional
    public OnlineAccount getOnlineAccount(String username) {
        return onlineAccountRepository.findOnlineAccountByUsername(username);
    }

    @Transactional
    public void logout(String username){

        OnlineAccount o= onlineAccountRepository.findOnlineAccountByUsername(username);
        if(o==null){
            throw new OnlineAccountException("no account with said username exists");
        }
        if (username == null) {
            throw new OnlineAccountException("Cannot find username to delete account.");
        }
        
        o.setLoggedIn(false);
    }
    @Transactional
    public void deleteOnlineAccount(String username, Integer customerId) {
        if (username == null) {
            throw new OnlineAccountException("Cannot find username to delete account.");
        }

        OnlineAccount account = onlineAccountRepository.findOnlineAccountByUsername(username);

        if (account == null) {
            throw new OnlineAccountException("Cannot find account.");
        }

        if (customerId == null) {
            throw new OnlineAccountException("Cannot find customerId to delete account.");
        }

        Customer customer = (Customer) customerRepository.findPersonRoleById(customerId);

        if (customer == null) {
            throw new OnlineAccountException("Cannot find customer to delete account.");
        }

        onlineAccountRepository.delete(account);
    }

    @Transactional
    public OnlineAccount logIn(String username, String password) {
        if (username == null) {
            throw new OnlineAccountException("Cannot find username to login user.");
        }

        OnlineAccount account = onlineAccountRepository.findOnlineAccountByUsername(username);

        if (account == null) {
            throw new OnlineAccountException("Cannot find account by username.");
        }

        if (password == null) {
            throw new OnlineAccountException("Cannot find password to login user.");
        }

        if (account.getPassword() == password) {
            account.setLoggedIn(true);
        }

        else {
            throw new OnlineAccountException("Password is incorrect. User cannot be logged in.");
        }

        return account;
    }

    @Transactional
    public OnlineAccount getOnlineAccounts(String username) {
        return onlineAccountRepository.findOnlineAccountByUsername(username);
    }
}
