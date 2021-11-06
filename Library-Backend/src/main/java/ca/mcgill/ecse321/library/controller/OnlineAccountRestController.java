package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dto.OnlineAccountDTO;
import ca.mcgill.ecse321.library.dto.PersonRoleDTO;
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

    /*@GetMapping(value = { "/onlineaccount", "/onlineaccount/" })
    public List<OnlineAccountDTO> getAllOnlineAccounts() {
        List<OnlineAccountDTO> onlineAccountDTOList = new ArrayList<OnlineAccountDTO>();
        for (OnlineAccount account : service.getAllOnlineAccounts()) {
            onlineAccountDTOList.add(convertToDTO(account));
        }
        return onlineAccountDTOList;
    }*/

    @PostMapping(value = { "/onlineaccount/{username}/{password}", "/onlineaccount/{username}/{password}/" })
    public OnlineAccountDTO createOnlineAccount(@PathVariable("username") String username, @PathVariable("password") String password, @RequestParam(value = "customerId",required = false) Integer customerId) throws IllegalArgumentException {
        OnlineAccount account = service.createOnlineAccount(username, password, customerId);
        return convertToDTO(account);
    }

    @DeleteMapping(value = { "/onlineaccount/{username}", "/onlineaccount/{username}/"})
    public void deleteOnlineAccount(@PathVariable("username") String username, @RequestParam(value = "customerId",required = false) Integer customerId) {
        service.deleteOnlineAccount(username, customerId);
    }

    @PutMapping(value = {"/login/{username}/{password}", "/login/{username}/{password}/"})
    public OnlineAccountDTO login(@PathVariable("username") String username, @PathVariable("password") String password) throws IllegalArgumentException {
        return convertToDTO(service.logIn(username, password));
    }

    private OnlineAccountDTO convertToDTO(OnlineAccount account) {
        if (account == null) {
            throw new IllegalArgumentException("There is no such Online Account!");
        }
        OnlineAccountDTO accountDTO = new OnlineAccountDTO();
        accountDTO.setUsername(account.getUsername());
        accountDTO.setPassword(account.getPassword());
        accountDTO.setPersonRole(account.getPersonRole());
        accountDTO.setSystem(account.getSystem());
        return accountDTO;
    }
}
