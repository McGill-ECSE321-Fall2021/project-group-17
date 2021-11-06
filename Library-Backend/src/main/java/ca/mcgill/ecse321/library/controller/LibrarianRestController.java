package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.model.Book;
import ca.mcgill.ecse321.library.model.CheckableItem;
import ca.mcgill.ecse321.library.model.Item;
import ca.mcgill.ecse321.library.model.ItemInstance;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.Movie;
import ca.mcgill.ecse321.library.model.Music;
import ca.mcgill.ecse321.library.model.Shift;
import ca.mcgill.ecse321.library.dto.BookDTO;
import ca.mcgill.ecse321.library.dto.ItemInstanceDTO;
import ca.mcgill.ecse321.library.dto.LibrarianDTO;
import ca.mcgill.ecse321.library.dto.ShiftDTO;
import ca.mcgill.ecse321.library.dto.MovieDTO;
import ca.mcgill.ecse321.library.dto.MusicDTO;
import ca.mcgill.ecse321.library.dto.NewspaperDTO;
import ca.mcgill.ecse321.library.service.LibrarianService;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
public class LibrarianRestController {
    @Autowired
    private LibrarianService librarianService;

    @PostMapping(value = {"/librarian/{id}","/librarian/{id}/"})
    public LibrarianDTO createLibrarian(@PathVariable("id") int id) throws IllegalArgumentException{
        Librarian librarian = librarianService.createLibrarian(id);
        return convertToDTO(librarian);
    }

    @GetMapping(value = {"/librarian/{id}", "/librarian/{id}/"})
    public LibrarianDTO getLibrarian(@PathVariable("id") int id) throws IllegalArgumentException{
        Librarian librarian = librarianService.createLibrarian(id);
        return convertToDTO(librarian);
    }

    @GetMapping(value = {"/librarian/{id}/shifts", "/librarian/{id}/shifts/"})
    public List<ShiftDTO> viewShifts(@PathVariable("id") int id) throws IllegalArgumentException {
    	Librarian librarian = librarianService.createLibrarian(id);
    	List<Shift> shifts = librarian.getSystem().getShiftList();
    	List<ShiftDTO> out = new ArrayList<ShiftDTO>();
    	for (Shift shift : shifts) {
    		if (shift.getLibrarian() == librarian) {
    			out.add(new ShiftDTO(shift.getStartTime(), shift.getEndTime(), shift.getDayOfWeek()));
    		}
    	}
    	return out;
    }
    
    // remove items
    @PostMapping(value= {"/librarian/{id}/delete/{itemId}",
	"/librarian/{id}/delete/{itemId}/"})
	public void deleteItem(@PathVariable("id") int id, @PathVariable("itemId") int itemId)
	throws IllegalArgumentException, ParseException{
    	Librarian librarian = librarianService.createLibrarian(id);
//    	List<Item> items = librarian.getSystem().getItemList();
    	List<ItemInstance> itemInstances = librarian.getSystem().getItemInstanceList();
    	
    	for(int i = 0; i < itemInstances.size(); i++) {
    		if (itemInstances.get(i).getCheckableItem().getId() == itemId) {   
    			itemInstances.remove(i);
    			break;
    		}
    	}
    }
    
    private LibrarianDTO convertToDTO(Librarian librarian){
        if (librarian == null) {
            throw new IllegalArgumentException("There is no such Customer!");
        }
        LibrarianDTO lDTO = new LibrarianDTO();
        lDTO.setId(librarian.getId());
        lDTO.setAccount(librarian.getAccount());
        lDTO.setPerson(librarian.getPerson());
        return lDTO;
    }
        
}
