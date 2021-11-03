package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.OnlineAccountRepository;
import ca.mcgill.ecse321.library.model.OnlineAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class OnlineAccountService {
    @Autowired
    OnlineAccountRepository onlineAccountRepository;

    @Transactional
    public OnlineAccount createOnlineAccount(String username) {
        OnlineAccount account = new OnlineAccount();
        account.setUsername(username);
        onlineAccountRepository.save(account);
        return account;
    }

    @Transactional
    public OnlineAccount getOnlineAccount(String username) {
        return onlineAccountRepository.findOnlineAccountByUsername(username);
    }
}
