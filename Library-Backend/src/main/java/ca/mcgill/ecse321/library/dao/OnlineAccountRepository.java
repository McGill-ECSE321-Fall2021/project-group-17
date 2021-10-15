package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.library.model.OnlineAccount;

public interface OnlineAccountRepository extends CrudRepository<OnlineAccount, String>{
    OnlineAccount findOnlineAccountByUsername(String username);
    OnlineAccount findOnlineAccountByPassword(String password);
}
