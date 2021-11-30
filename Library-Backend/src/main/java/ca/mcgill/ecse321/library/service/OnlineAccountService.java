package ca.mcgill.ecse321.library.service;

import javax.transaction.Transactional;

import ca.mcgill.ecse321.library.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.library.dao.CustomerRepository;
import ca.mcgill.ecse321.library.dao.HeadLibrarianRepository;
import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.dao.OnlineAccountRepository;
import ca.mcgill.ecse321.library.dao.PersonRoleRepository;
import ca.mcgill.ecse321.library.service.Exception.OnlineAccountException;

@Service
public class OnlineAccountService {
    @Autowired
    private OnlineAccountRepository onlineAccountRepository;
    @Autowired
    private LibrarianRepository librarianRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private HeadLibrarianRepository headLibrarianRepository;

    /**
     * Creates an account for a librarian
     * @param username
     * @param password
     * @param librarianId
     * @param email
     * @return an OnlineAccount created with the given username, password, email, and librarianId
     */
    @Transactional
    public OnlineAccount createOnlineAccountLibrarian(String username, String password, Integer librarianId, String email) {
        if (username == null) {
            throw new OnlineAccountException("Cannot create account with null username.");
        }

        if (password == null) {
            throw new OnlineAccountException("Cannot create account with null password.");
        }

        if (librarianId == null) {
            throw new OnlineAccountException("Cannot create an account without a user.");
        }

        if (email == null) {
            throw new OnlineAccountException("Cannot create an account without an email");
        }

        OnlineAccount account = new OnlineAccount();
        account.setUsername(username);
        account.setPassword(password);
        account.setEmail(email);

        Librarian librarian = (Librarian) librarianRepository.findPersonRoleById(librarianId);
        if (librarian == null) {
            throw new OnlineAccountException("No user exists with the personRoleId given");
        }

        account.setPersonRole(librarian);
        onlineAccountRepository.save(account);
        return account;
    }

    /**
     * Creates an account for a customer
     * @param username
     * @param password
     * @param customerId
     * @param email
     * @return an OnlineAccount created with the given username, password, email, and librarianId
     */
    @Transactional
    public OnlineAccount createOnlineAccountCustomer(String username, String password, Integer customerId, String email) {
        if (username == null) {
            throw new OnlineAccountException("Cannot create account with null username.");
        }

        if (password == null) {
            throw new OnlineAccountException("Cannot create account with null password.");
        }

        if (customerId == null) {
            throw new OnlineAccountException("Cannot create an account without a user.");
        }

        if (email == null) {
            throw new OnlineAccountException("Cannot create an account without an email");
        }

        OnlineAccount account = new OnlineAccount();
        account.setUsername(username);
        account.setPassword(password);
        account.setEmail(email);

        Customer customer = (Customer) customerRepository.findPersonRoleById(customerId);
        if (customer == null) {
            throw new OnlineAccountException("No user exists with the personRoleId given");
        }

        account.setPersonRole(customer);
        onlineAccountRepository.save(account);
        return account;
    }

    public OnlineAccount updateOnlineAccountCustomer( String username, String password,  String email,Integer streetNumber, String street, String city, String country) {
        OnlineAccount o1 =onlineAccountRepository.findOnlineAccountByUsername(username);
        Customer c1= (Customer) o1.getPersonRole();

        if (password != null) {
            o1.setPassword(password);
        }

        if (email != null) {
            o1.setEmail(email);
        }
        if(c1!=null){
                Address a= c1.getAddress();
                if(a!=null){
                    if(streetNumber!=null) {
                        a.setStreetNumber(streetNumber);
                    }
                    if(street!=null) {
                        a.setStreet(street);
                    }
                    if(city!=null) {
                        a.setCity(city);
                    }
                    if(country!=null) {
                        a.setCountry(country);
                    }
            }
        }
        onlineAccountRepository.save(o1);
        return o1;
    }
    /**
     * Creates an account for a head librarian
     * @param username
     * @param password
     * @param librarianId
     * @param email
     * @return an OnlineAccount created with the given username, password, email, and librarianId
     */
    @Transactional
    public OnlineAccount createOnlineAccountHeadLibrarian(String username, String password, Integer librarianId, String email) {
        if (username == null) {
            throw new OnlineAccountException("Cannot create account with null username.");
        }

        if (password == null) {
            throw new OnlineAccountException("Cannot create account with null password.");
        }

        if (librarianId == null) {
            throw new OnlineAccountException("Cannot create an account without a user.");
        }

        if (email == null) {
            throw new OnlineAccountException("Cannot create an account without an email");
        }

        OnlineAccount account = new OnlineAccount();
        account.setUsername(username);
        account.setPassword(password);
        account.setEmail(email);

        HeadLibrarian headLibrarian = (HeadLibrarian) headLibrarianRepository.findPersonRoleById(librarianId);

        if (headLibrarian == null) {
            throw new OnlineAccountException("No user exists with the personRoleId given");
        }

        account.setPersonRole(headLibrarian);
        onlineAccountRepository.save(account);
        return account;
    }

    /**
     * Retrieves the account for the given username
     * @param username
     * @return an OnlineAccount with the given username
     */
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
    public Integer determinePersonRoleType(String username) {
        if (username == null) {
            throw new OnlineAccountException("Cannot get account with a null username");
        }

        OnlineAccount account = onlineAccountRepository.findOnlineAccountByUsername(username);

        if (account == null) {
            throw new OnlineAccountException("Cannot find the account for the given username");
        }

        if (account.getPersonRole() instanceof HeadLibrarian) {
            return 2;
        }

        if (account.getPersonRole() instanceof Librarian) {
            return 1;
        }

        return 0;
    }

    /**
     * Sets the account for the given username to logged out
     * @param username
     * @return an OnlineAccount that has loggedIn = false
     */
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

    /**
     * Deletes the OnlineAccount for a customer
     * @param username
     * @param personRoleId
     */
    @Transactional
    public void deleteOnlineAccountCustomer(String username, Integer personRoleId) {
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

        PersonRole personRole = customerRepository.findPersonRoleById(personRoleId);

        if (personRole == null) {
            throw new OnlineAccountException("Cannot find personRole to delete account.");
        }

        onlineAccountRepository.delete(account);
    }

    /**
     * Deletes the OnlineAccount for a librarian
     * @param username
     * @param personRoleId
     */
    @Transactional
    public void deleteOnlineAccountLibrarian(String username, Integer personRoleId) {
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

        PersonRole personRole = librarianRepository.findPersonRoleById(personRoleId);

        if (personRole == null) {
            throw new OnlineAccountException("Cannot find personRole to delete account.");
        }

        onlineAccountRepository.delete(account);
    }

    /**
     * Deletes the OnlineAccount for a head librarian
     * @param username
     * @param personRoleId
     */
    @Transactional
    public void deleteOnlineAccountHeadLibrarian(String username, Integer personRoleId) {
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

        PersonRole personRole = headLibrarianRepository.findPersonRoleById(personRoleId);

        if (personRole == null) {
            throw new OnlineAccountException("Cannot find personRole to delete account.");
        }

        onlineAccountRepository.delete(account);
    }

    /**
     * Sets the account for the given username to loggedIn if the password is correct for the given username
     * @param username
     * @param password
     * @return an OnlineAccount with loggedIn = true
     */
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
