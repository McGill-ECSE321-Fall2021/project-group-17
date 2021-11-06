package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dto.OnlineAccountDTO;
import ca.mcgill.ecse321.library.model.OnlineAccount;
import ca.mcgill.ecse321.library.service.OnlineAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*")
@RestController
public class OnlineAccountRestController {
    @Autowired
    private OnlineAccountService service;

    /*@GetMapping(value = { "/onlineaccounts", "/onlineaccounts/" })
    public List<OnlineAccountDTO> getAllOnlineAccounts() {
        List<OnlineAccountDTO> onlineAccountDTOList = new ArrayList<OnlineAccountDTO>();
        for (OnlineAccount account : service.getAllOnlineAccounts()) {
            onlineAccountDTOList.add(convertToDTO(account));
        }
        return onlineAccountDTOList;
    }*/

    @PostMapping(value = { "/onlineaccounts/{username}", "/onlineaccounts/{username}/" })
    public OnlineAccountDTO createOnlineAccount(@PathVariable("username") String username) throws IllegalArgumentException {
        OnlineAccount account = service.createOnlineAccount(username);
        return convertToDTO(account);
    }

    private OnlineAccountDTO convertToDTO(OnlineAccount account) {
        if (account == null) {
            throw new IllegalArgumentException("There is no such Online Account!");
        }
        OnlineAccountDTO accountDTO = new OnlineAccountDTO();
        accountDTO.setUsername(account.getUsername());
        accountDTO.setPassword(account.getPassword());
        accountDTO.setPersonRole(account.getPersonRole());
        return accountDTO;
    }
}
