package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dto.OnlineAccountDTO;
import ca.mcgill.ecse321.library.model.OnlineAccount;
import ca.mcgill.ecse321.library.service.OnlineAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class OnlineAccountRestController {
    @Autowired
    private OnlineAccountService service;

    @GetMapping(value = { "/getaccount/{username}", "/getaccount/{username}/" })
    public OnlineAccountDTO getOnlineAccount(@PathVariable("username") String username) {
        return convertToDTO(service.getOnlineAccount(username));
    }

    @PostMapping(value = { "/onlineaccount/{username}/{password}", "/onlineaccount/{username}/{password}/" })
    public OnlineAccountDTO createOnlineAccount(@PathVariable("username") String username, @PathVariable("password") String password) throws IllegalArgumentException {
        OnlineAccount account = service.createOnlineAccount(username, password);
        return convertToDTO(account);
    }

    @DeleteMapping(value = { "/onlineaccount/{username}", "/onlineaccount/{username}/"})
    public void deleteOnlineAccount(@PathVariable("username") String username, @RequestParam(value = "customerId",required = false) Integer customerId) {
        service.deleteOnlineAccount(username, customerId);
    }

    @PutMapping(value = {"/login/{username}/{password}", "/login/{username}/{password}/"})
    public OnlineAccountDTO logIn(@PathVariable("username") String username, @PathVariable("password") String password) throws IllegalArgumentException {
        return convertToDTO(service.logIn(username, password));
    }

    @PutMapping(value={"/logout/{username}", "logout/{username}/"})
    public void logOut(@PathVariable("username") String username) {
        service.logout(username);
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
        accountDTO.setLoggedIn(account.getLoggedIn());
        return accountDTO;
    }
}
