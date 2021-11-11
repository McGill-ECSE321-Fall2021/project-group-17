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

    // create accounts for customer, librarian and head librarian
    @PostMapping(value = { "/onlineaccount/customer/{username}/{password}", "/onlineaccount/customer/{username}/{password}/" })
    public OnlineAccountDTO createOnlineAccountCustomer(@PathVariable("username") String username, @PathVariable("password") String password, @RequestParam(value = "personRoleId",required = false) Integer personRoleId) throws IllegalArgumentException {
        OnlineAccount account = service.createOnlineAccountCustomer(username, password, personRoleId);
        return convertToDTO(account);
    }
    
    @PostMapping(value = { "/onlineaccount/librarian/{username}/{password}", "/onlineaccount/librarian/{username}/{password}/" })
    public OnlineAccountDTO createOnlineAccountLibrarian(@PathVariable("username") String username, @PathVariable("password") String password, @RequestParam(value = "personRoleId",required = false) Integer personRoleId) throws IllegalArgumentException {
        OnlineAccount account = service.createOnlineAccountLibrarian(username, password, personRoleId);
        return convertToDTO(account);
    }

    @PostMapping(value = { "/onlineaccount/headlibrarian/{username}/{password}", "/onlineaccount/headlibrarian/{username}/{password}/" })
    public OnlineAccountDTO createOnlineAccountHeadLibrarian(@PathVariable("username") String username, @PathVariable("password") String password, @RequestParam(value = "personRoleId",required = false) Integer personRoleId) throws IllegalArgumentException {
        OnlineAccount account = service.createOnlineAccountHeadLibrarian(username, password, personRoleId);
        return convertToDTO(account);
    }
    // create accounts for customer, librarian and head librarian

    @DeleteMapping(value = { "/onlineaccount/customer/{username}", "/onlineaccount/{username}/"})
    public void deleteOnlineAccountCustomer(@PathVariable("username") String username, @RequestParam(value = "personRoleId",required = false) Integer personRoleId) {
        service.deleteOnlineAccountCustomer(username, personRoleId);
    }

    @DeleteMapping(value = { "/onlineaccount/librarian/{username}", "/onlineaccount/{username}/"})
    public void deleteOnlineAccountLibrarian(@PathVariable("username") String username, @RequestParam(value = "personRoleId",required = false) Integer personRoleId) {
        service.deleteOnlineAccountLibrarian(username, personRoleId);
    }

    @PutMapping(value = {"/login/{username}/{password}", "/login/{username}/{password}/"})
    public OnlineAccountDTO logIn(@PathVariable("username") String username, @PathVariable("password") String password) throws IllegalArgumentException {
        return convertToDTO(service.logIn(username, password));
    }


    @PutMapping(value={"/logout/{username}", "/logout/{username}/"})
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
        accountDTO.setLoggedIn(account.getLoggedIn());
        return accountDTO;
    }
}
