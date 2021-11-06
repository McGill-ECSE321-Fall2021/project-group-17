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
    
    // add items
    @PostMapping(value= {"/librarian/{id}/add/newspaper/{newspaperId}/{datePublished}/{name}/{headline}",
	"/librarian/{id}/add/newspaper/{newspaperId}/{datePublished}/{name}/{headline}/"})
	public NewspaperDTO addNewspaper(@PathVariable("id") int id, @PathVariable("newspaperId") int newspaperId,
		@PathVariable("datePublished") String datePublished, @PathVariable("name") String name,
		@PathVariable("headline") String headline)
	throws IllegalArgumentException, ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		Date convertedDatePublished = (Date) sdf.parse(datePublished);
		Librarian librarian = librarianService.createLibrarian(id);
		Random rand = new Random();
		
		// Obtain a number between [0 - 500000000]. This will be used to add to the database.
		int n = rand.nextInt(500000000);
		ItemInstance iInstance = new ItemInstance();

		iInstance.setSerialNum(n);
		librarian.getSystem().getItemInstanceList().add(iInstance);
		return new NewspaperDTO(newspaperId, name, convertedDatePublished, headline);
	}
    
    @PostMapping(value= {"/librarian/{id}/add/movie/{movieId}/{datePublished}/{name}/{director}/{runningTime}/{rating}/{filmDistributor}",
    		"/librarian/{id}/add/movie/{movieId}/{datePublished}/{name}/{director}/{runningTime}/{rating}/{filmDistributor}/"})
    public MovieDTO addMovie(@PathVariable("id") int id, @PathVariable("movieId") int movieId,
    		@PathVariable("datePublished") String datePublished, @PathVariable("name") String name,
    		@PathVariable("director") String director, @PathVariable("runningTime") int runningTime, 
    		@PathVariable("rating") String rating, @PathVariable("filmDistributor") String filmDistributor)
    throws IllegalArgumentException, ParseException{
    	SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
    	Date convertedDatePublished = (Date) sdf.parse(datePublished);
    	Librarian librarian = librarianService.createLibrarian(id);
    	List<Item> items = librarian.getSystem().getItemList();
    	Random rand = new Random();

    	// Obtain a number between [0 - 500000000]. This will be used to add to the database.
    	int n = rand.nextInt(500000000);
    	for (Item item : items) {
    		if(item.getId() == movieId) {
    			// TODO: serial number is auto-generated upon creation?
    			ItemInstance iInstance = new ItemInstance();
    			iInstance.setSerialNum(n);
    			iInstance.setCheckableItem((CheckableItem) item);
    			librarian.getSystem().getItemInstanceList().add(iInstance);
    			return new MovieDTO(movieId, name, convertedDatePublished, director, runningTime, rating, filmDistributor);
    		}
    	}
    	// create a new checkableitem
    	librarian.getSystem().getItemList().add(new Movie(
    			movieId, name, convertedDatePublished, director, runningTime, rating, filmDistributor));
    	for (Item item : items) {
    		if(item.getId() == movieId) {
    			// TODO: serial number is auto-generated upon creation?
    			ItemInstance iInstance = new ItemInstance();
    			iInstance.setSerialNum(n);
    			iInstance.setCheckableItem((CheckableItem) item);
    			librarian.getSystem().getItemInstanceList().add(iInstance);
    			return new MovieDTO(id, name, convertedDatePublished, director, runningTime, rating, filmDistributor);
    		}
    	}
    	return null;	// return status code
    }
    
    @PostMapping(value= {"/librarian/{id}/add/book/{bookId}/{datePublished}/{name}/{author}/{publisher}/{genre}",
    		"/librarian/{id}/add/book/{bookId}/{datePublished}/{name}/{author}/{publisher}/{genre}/"})
    public BookDTO addBook(@PathVariable("id") int id, @PathVariable("bookId") int bookId,
    		@PathVariable("datePublished") String datePublished, @PathVariable("name") String name,
    		@PathVariable("author") String author, @PathVariable("publisher") String publisher, 
    		@PathVariable("genre") String genre)
    throws IllegalArgumentException, ParseException{
    	SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
    	Date convertedDatePublished = (Date) sdf.parse(datePublished);
    	Librarian librarian = librarianService.createLibrarian(id);
    	List<Item> items = librarian.getSystem().getItemList();
    	Random rand = new Random();

    	// Obtain a number between [0 - 500000000]. This will be used to add to the database.
    	int n = rand.nextInt(500000000);
    	for (Item item : items) {
    		if(item.getId() == bookId) {
    			// TODO: serial number is auto-generated upon creation?
    			ItemInstance iInstance = new ItemInstance();
    			iInstance.setSerialNum(n);
    			iInstance.setCheckableItem((CheckableItem) item);
    			librarian.getSystem().getItemInstanceList().add(iInstance);
    			return new BookDTO(bookId, name, convertedDatePublished, author, publisher, genre);
    		}
    	}
    	// create a new checkableitem
    	librarian.getSystem().getItemList().add(new Book(
    			bookId, name, convertedDatePublished, author, publisher, genre));
    	for (Item item : items) {
    		if(item.getId() == bookId) {
    			// TODO: serial number is auto-generated upon creation?
    			ItemInstance iInstance = new ItemInstance();
    			iInstance.setSerialNum(n);
    			iInstance.setCheckableItem((CheckableItem) item);
    			librarian.getSystem().getItemInstanceList().add(iInstance);
    			return new BookDTO(bookId, name, convertedDatePublished, author, publisher, genre);
    		}
    	}
    	return null;	// return status code
    }
    
    @PostMapping(value= {"/librarian/{id}/add/music/{musicId}/{datePublished}/{name}/{musician}/{recordLabel}",
    		"/librarian/{id}/add/music/{musicId}/{datePublished}/{name}/{musician}/{recordLabel}/"})
    public MusicDTO addMusic(@PathVariable("id") int id, @PathVariable("musicId") int musicId,
    		@PathVariable("datePublished") String datePublished, @PathVariable("name") String name,
    		@PathVariable("musician") String musician, @PathVariable("recordLabel") String recordLabel)
    throws IllegalArgumentException, ParseException{
    	SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
    	Date convertedDatePublished = (Date) sdf.parse(datePublished);
    	Librarian librarian = librarianService.createLibrarian(id);
    	List<Item> items = librarian.getSystem().getItemList();
    	Random rand = new Random();

    	// Obtain a number between [0 - 500000000]. This will be used to add to the database.
    	int n = rand.nextInt(500000000);
    	for (Item item : items) {
    		if(item.getId() == musicId) {
    			// TODO: serial number is auto-generated upon creation?
    			ItemInstance iInstance = new ItemInstance();
    			iInstance.setSerialNum(n);
    			iInstance.setCheckableItem((CheckableItem) item);
    			librarian.getSystem().getItemInstanceList().add(iInstance);
    			return new MusicDTO(musicId, name, convertedDatePublished, musician, recordLabel);
    		}
    	}
    	// create a new checkableitem
    	librarian.getSystem().getItemList().add(new Music(
    			musicId, name, convertedDatePublished, musician, recordLabel));
    	for (Item item : items) {
    		if(item.getId() == musicId) {
    			// TODO: serial number is auto-generated upon creation?
    			ItemInstance iInstance = new ItemInstance();
    			iInstance.setSerialNum(n);
    			iInstance.setCheckableItem((CheckableItem) item);
    			librarian.getSystem().getItemInstanceList().add(iInstance);
    			return new MusicDTO(musicId, name, convertedDatePublished, musician, recordLabel);
    		}
    	}
    	return null;	// return status code
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
    		
//    	int i = 0;
//    	for (Item item : items) {
//    		if(item.getId() == itemId) {
//    			items.remove(i);
//    			break;
//    		}
//    		i+=1;
//    	}
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
