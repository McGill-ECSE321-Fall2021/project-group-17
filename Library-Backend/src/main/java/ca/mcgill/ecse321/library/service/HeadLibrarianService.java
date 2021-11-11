package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.model.*;
import ca.mcgill.ecse321.library.service.Exception.OnlineAccountException;
import ca.mcgill.ecse321.library.service.Exception.PersonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
@Service
public class HeadLibrarianService {
    @Autowired
    private HeadLibrarianRepository headLibrarianRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private OnlineAccountRepository onlineAccountRepository;

    @Transactional
    public HeadLibrarian createHeadLibrarian(Integer personId){
        HeadLibrarian headLibrarian = new HeadLibrarian();
        if(personId == null || personId < 0) throw new PersonException("Invalid Id");
        Person person = personRepository.findPersonById(personId);
        if(person == null) throw new PersonException("No person by this Id");
        headLibrarian.setPerson(person);
        headLibrarianRepository.save(headLibrarian);
        return headLibrarian;
    }
    @Transactional
    public HeadLibrarian updateHeadLibrarian(Integer headLibrarian, Integer personId, String username){
        if(headLibrarian == null || headLibrarian < 0)throw new OnlineAccountException("Invalid Id");
        HeadLibrarian librarian = (HeadLibrarian) headLibrarianRepository.findPersonRoleById(headLibrarian);
        if(librarian == null) throw new OnlineAccountException("No librarian by this id");
        if(personId == null || personId < 0) throw new PersonException("Invalid Id");
        Person person = personRepository.findPersonById(personId);
        if(person == null) throw new PersonException("No person by this Id");
        if(username == null)throw new OnlineAccountException("Invalid Id");
        OnlineAccount account = onlineAccountRepository.findOnlineAccountByUsername(username);
        if(account == null) throw new OnlineAccountException("No account by this username");
        librarian.setAccount(account);
        librarian.setPerson(person);
        headLibrarianRepository.save(librarian);
        return librarian;
    }
    @Transactional
    public HeadLibrarian getHeadLibrarian(Integer id){
        if(id == null || id < 0) throw new OnlineAccountException("Invalid Id");
        HeadLibrarian headLibrarian = (HeadLibrarian) headLibrarianRepository.findPersonRoleById(id);
        if(headLibrarian == null) throw new OnlineAccountException("No librarian by this id");
        return headLibrarian;
    }
}
