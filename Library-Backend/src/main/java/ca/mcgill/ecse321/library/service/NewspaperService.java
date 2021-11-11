package ca.mcgill.ecse321.library.service;

import java.sql.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.dao.NewspaperRepository;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.Newspaper;
import ca.mcgill.ecse321.library.service.Exception.NewspaperException;
import ca.mcgill.ecse321.library.service.Exception.NotFoundException;
import ca.mcgill.ecse321.library.service.Exception.OnlineAccountException;
import ca.mcgill.ecse321.library.service.Exception.PersonException;

@Service
public class NewspaperService {
    @Autowired
    private NewspaperRepository newspaperRepository;
    @Autowired
    private LibrarianRepository librarianRepository;

    @Transactional
    public Newspaper createNewspaper(Integer librarianId, Integer id, String name, Date date, String headline){
    	
    	String error = "";
        if (librarianId == null) {
        	throw new PersonException("Librarian not found in request");
        } else if (librarianRepository.findPersonRoleById(librarianId) == null) {
            error = error + "Librarian does not exist! ";
        }
        if (id == null) {
            error = error + "Id needs to be provided!";
        }
        if (name == null) {
            error = error + "Name needs to be provided!";
        }
        error = error.trim();

        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
    	Librarian librarian = (Librarian) librarianRepository.findPersonRoleById(librarianId);
        if (!(librarian instanceof Librarian)) {
        	throw new PersonException("User must be a librarian");
        }
        
        Newspaper newspaper = new Newspaper();
        newspaper.setId(id);
        newspaper.setName(name);
        newspaper.setDatePublished(date);
        newspaper.setHeadline(headline);
        newspaperRepository.save(newspaper);
        return newspaper;
    }
    
    /**
     * Used to delete item
     * @param newspaperId
     * @param customerId
     */
    @Transactional
    public void deleteNewspaper(Integer newspaperId, Integer librarianId){
        if(newspaperId == null){
            throw new NewspaperException("Cannot find newspaper with id to delete");
        }
        Newspaper newspaper = (Newspaper) newspaperRepository.findItemById(newspaperId);
        if(newspaper == null){
            throw new NotFoundException("Cannot find newspaper to delete");
        }
        if(librarianId == null){
            throw new NewspaperException("Cannot authorize librarian to delete newspaper");
        }
        Librarian librarian = (Librarian) librarianRepository.findPersonRoleById(librarianId);
        if(librarian == null){
            throw new OnlineAccountException("Librarian not found in request");
        }
        if (!(librarian instanceof Librarian)) {
        	throw new PersonException("User must be a librarian");
        }
        newspaperRepository.deleteById(newspaperId);
        newspaper = null;
    }
    
    @Transactional
    public Newspaper getNewspaper(Integer newspaperId){
        Newspaper newspaper = (Newspaper) newspaperRepository.findItemById(newspaperId);
        return newspaper;
    }
    @Transactional
    public Newspaper getNewspaperByHeadline(String headline){
        Newspaper result = newspaperRepository.findNewspaperByHeadline(headline);
        return result;
    }


}
