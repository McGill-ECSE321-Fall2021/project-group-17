package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.CustomerRepository;
import ca.mcgill.ecse321.library.dao.OnlineAccountRepository;
import ca.mcgill.ecse321.library.model.Customer;
import ca.mcgill.ecse321.library.model.OnlineAccount;
import ca.mcgill.ecse321.library.service.Exception.OnlineAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    /*@Transactional
    public List<OnlineAccount> getAllOnlineAccounts() {
        return toList(onlineAccountRepository.findAll());
    }*/
}
