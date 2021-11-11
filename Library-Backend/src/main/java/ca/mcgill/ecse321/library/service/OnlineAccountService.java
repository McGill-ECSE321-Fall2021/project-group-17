package ca.mcgill.ecse321.library.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.library.dao.CustomerRepository;
import ca.mcgill.ecse321.library.dao.HeadLibrarianRepository;
import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.dao.OnlineAccountRepository;
import ca.mcgill.ecse321.library.dao.PersonRoleRepository;
import ca.mcgill.ecse321.library.model.Customer;
import ca.mcgill.ecse321.library.model.HeadLibrarian;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.OnlineAccount;
import ca.mcgill.ecse321.library.model.PersonRole;
import ca.mcgill.ecse321.library.service.Exception.OnlineAccountException;

@Service
public class OnlineAccountService {
    @Autowired
    private OnlineAccountRepository onlineAccountRepository;
    @Autowired
    private PersonRoleRepository personRoleRepository;
    @Autowired
    private LibrarianRepository librarianRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private HeadLibrarianRepository headLibrarianRepository;

    
    @Transactional
    public OnlineAccount createOnlineAccountLibrarian(String username, String password, Integer librarianId) {
        if (username == null) {
            throw new OnlineAccountException("Cannot create account with null username.");
        }

        if (password == null) {
            throw new OnlineAccountException("Cannot create account with null password.");
        }

        if (librarianId == null) {
            throw new OnlineAccountException("Cannot create an account without a user.");
        }

        OnlineAccount account = new OnlineAccount();
        account.setUsername(username);
        account.setPassword(password);

        Librarian librarian = (Librarian) librarianRepository.findPersonRoleById(librarianId);

        if (librarian == null) {
            throw new OnlineAccountException("No user exists with the personRoleId given");
        }

        account.setPersonRole(librarian);
        onlineAccountRepository.save(account);
        return account;
    }
    
    @Transactional
    public OnlineAccount createOnlineAccountCustomer(String username, String password, Integer librarianId) {
        if (username == null) {
            throw new OnlineAccountException("Cannot create account with null username.");
        }

        if (password == null) {
            throw new OnlineAccountException("Cannot create account with null password.");
        }

        if (librarianId == null) {
            throw new OnlineAccountException("Cannot create an account without a user.");
        }

        OnlineAccount account = new OnlineAccount();
        account.setUsername(username);
        account.setPassword(password);

        Customer customer = (Customer) customerRepository.findPersonRoleById(librarianId);
        if (customer == null) {
            throw new OnlineAccountException("No user exists with the personRoleId given");
        }

        account.setPersonRole(customer);
        onlineAccountRepository.save(account);
        return account;
    }
    
    @Transactional
    public OnlineAccount createOnlineAccountHeadLibrarian(String username, String password, Integer librarianId) {
        if (username == null) {
            throw new OnlineAccountException("Cannot create account with null username.");
        }

        if (password == null) {
            throw new OnlineAccountException("Cannot create account with null password.");
        }

        if (librarianId == null) {
            throw new OnlineAccountException("Cannot create an account without a user.");
        }

        OnlineAccount account = new OnlineAccount();
        account.setUsername(username);
        account.setPassword(password);

        HeadLibrarian headLibrarian = (HeadLibrarian) headLibrarianRepository.findPersonRoleById(librarianId);

        if (headLibrarian == null) {
            throw new OnlineAccountException("No user exists with the personRoleId given");
        }

        account.setPersonRole(headLibrarian);
        onlineAccountRepository.save(account);
        return account;
    }

    @Transactional
    public OnlineAccount getOnlineAccount(String username) {
        if (username == null) {
            throw new OnlineAccountException("Cannot get account with a null username");
        }

        OnlineAccount account = onlineAccountRepository.findOnlineAccountByUsername(username);

        if (account == null) {
            throw new OnlineAccountException("Cannot find the account for the given username");
        }

        return account;
    }

    @Transactional
    public OnlineAccount logout(String username){
        if (username == null) {
            throw new OnlineAccountException("Cannot find username to delete account.");
        }

        OnlineAccount o= onlineAccountRepository.findOnlineAccountByUsername(username);
        if(o==null){
            throw new OnlineAccountException("No account with said username exists");
        }

        o.setLoggedIn(false);
        onlineAccountRepository.save(o);

        return o;
    }

    @Transactional
    public void deleteOnlineAccount(String username, Integer personRoleId) {
        if (username == null) {
            throw new OnlineAccountException("Cannot find username to delete account.");
        }

        OnlineAccount account = onlineAccountRepository.findOnlineAccountByUsername(username);

        if (account == null) {
            throw new OnlineAccountException("Cannot find account.");
        }

        if (personRoleId == null) {
            throw new OnlineAccountException("Cannot find personRoleId to delete account.");
        }

        PersonRole personRole = personRoleRepository.findPersonRoleById(personRoleId);

        if (personRole == null) {
            throw new OnlineAccountException("Cannot find personRole to delete account.");
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

        if (account.getPassword().equals(password)) {
            account.setLoggedIn(true);
        }

        else {
            throw new OnlineAccountException("Password is incorrect. User cannot be logged in.");
        }

        onlineAccountRepository.save(account);

        return account;
    }
}
