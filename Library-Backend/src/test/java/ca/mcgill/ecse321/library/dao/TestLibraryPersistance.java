package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.mcgill.ecse321.library.model.Movie;
import ca.mcgill.ecse321.library.model.Music;
import ca.mcgill.ecse321.library.model.Address;
import ca.mcgill.ecse321.library.model.Library;
import ca.mcgill.ecse321.library.model.LibraryHour;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestLibraryPersistance{

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MusicRepository musicRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private LibraryRepository libraryRepository;
    @Autowired
    private LibraryHourRepository libraryHourRepository;
    @Autowired
    private ItemInstanceRepository itemInstanceRepository;
    @Autowired
    private CheckableItemRepository checkableItemRepository;
    @Autowired
    private NewspaperRepository newspaperRepository;
    @Autowired
    private LibraryCardRepository libraryCardRepository;
    @Autowired
    private OnlineAccountRepository onlineAccountRepository;

    @AfterEach
    public void clearDatabase() {
        itemInstanceRepository.deleteAll();
        bookRepository.deleteAll();
        movieRepository.deleteAll();
        musicRepository.deleteAll();
        checkableItemRepository.deleteAll();
        newspaperRepository.deleteAll();
        addressRepository.deleteAll();
        libraryRepository.deleteAll();
        libraryHourRepository.deleteAll();
        customerRepository.deleteAll();
        librarianRepository.deleteAll();
        headLibrarianRepository.deleteAll();
        shiftRepository.deleteAll();
    }
    /*
    Read test for book class. Ensure a book and its attributes are properly stored and read from the database.
    Written by Victoria Sanchez
     */
 @Test
 public void testPersistAndLoadBook(){
    Integer bookID= 1234; //create object to be tested
    String author="Victoria";
    String publisher="penguin";
    Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
    String genre= "horror";
    String name= "My Brilliant Friend";
    Book book = new Book();
    book.setId(bookID);
    book.setName(name);
    book.setDatePublished(date);
    book.setAuthor(author);
    book.setPublisher(publisher);
    book.setGenre(genre);
    bookRepository.save(book);
    book=null;
    book=(Book) bookRepository.findItemById(bookID);
    assertNotNull(book);
    assertEquals(bookID,book.getId());
    assertEquals(author, book.getAuthor());
    assertEquals(publisher,book.getPublisher());
    assertEquals(date,book.getDatePublished());
    assertEquals(genre,book.getGenre());
 }
    /*
   Read test for Movie class. Ensure a movie and its attributes are properly stored and read from the database.
   Written by Victoria Sanchez
    */
    @Test
    public void testPersistAndLoadMovie(){
        Integer movieID= 1234; //create object to be tested
        String director="Victoria";
        Integer runningTime=55;
        String rating="PG-13";
        String filmDistributor="Sony";
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        String name= "My Brilliant Friend";
        Movie movie = new Movie();
        movie.setId(movieID);
        movie.setName(name);
        movie.setDatePublished(date);
        movie.setDirector(director);
        movie.setRunningTime(runningTime);
        movie.setRating(rating);
        movie.setFilmDistributor(filmDistributor);
        movieRepository.save(movie);
        movie=null;
        movie=(Movie) movieRepository.findItemById(movieID);
        assertNotNull(movie);
        assertEquals(movieID,movie.getId());
        assertEquals(name, movie.getName());
        assertEquals(date,movie.getDatePublished());
        assertEquals(director,movie.getDirector());
        assertEquals(runningTime,movie.getRunningTime());
        assertEquals(rating,movie.getRating());
        assertEquals(filmDistributor,movie.getFilmDistributor());

    }
    /*
Read test for music class. Ensure music and their attributes are properly stored and read from the database.
Written by Victoria Sanchez
 */
    @Test
    public void testPersistAndLoadMusic(){
        Integer musicID= 1234; //create object to be tested
        String musician="Victoria";
        String recordLabel="Sony";
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        String name= "My Brilliant Friend";
        Music music = new Music();
        music.setId(musicID);
        music.setName(name);
        music.setDatePublished(date);
        music.setMusician(musician);
        music.setRecordLabel((recordLabel));
        musicRepository.save(music);
        music=null;
        music=(Music) musicRepository.findItemById(musicID);
        assertNotNull(music);
        assertEquals(musicID,music.getId());
        assertEquals(name, music.getName());
        assertEquals(date,music.getDatePublished());
        assertEquals(musician,music.getMusician());
        assertEquals(recordLabel,music.getRecordLabel());
    }
    
    /*
Read test for address class. Ensure address and their attributes are properly stored and read from the database.
Written by Jerry Xia
 */
    @Test
    public void testPersistAndLoadAddress() {
    	int addressID = 4321;
    	Integer streetNumber = 1234;
    	String street = "Main st";
    	String city = "Montreal";
    	String country = "Canada";
    	Address address = new Address(addressID, streetNumber, street, city, country);
    	addressRepository.save(address);
    	address = null;
    	address = addressRepository.findAddressByAddressID(addressID);
		assertNotNull(address);
		assertEquals(addressID,address.getAddressID());
		assertEquals(streetNumber, address.getStreetNumber());
		assertEquals(street,address.getStreet());
		assertEquals(city,address.getCity());
		assertEquals(country,address.getCountry());
    }
    
    /*
Read test for library class. Ensure library and their attributes are properly stored and read from the database.
Written by Jerry Xia
 */
    @Test
    public void testPersistAndLoadLibrary() {
    	String name = "McLennen";
    	Library library = new Library(name);
    	libraryRepository.save(library);
    	library = null;
    	library = libraryRepository.findLibraryByName(name);
		assertNotNull(library);
		assertEquals(name, library.getName());
	}
    
    /*
Read test for libraryHour class. Ensure libraryHour and their attributes are properly stored and read from the database.
Written by Jerry Xia
 */
    @Test
    public void testPersistAndLoadLibraryHour() {
    	String name = "McLennen";
    	Library library = new Library(name);
    	String libraryHourId = "someId";
    	Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		Time endTime = java.sql.Time.valueOf(LocalTime.of(13, 25));
    	DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
    	LibraryHour libraryHour = new LibraryHour(libraryHourId, startTime, endTime, dayOfWeek, library);
    	libraryHourRepository.save(libraryHour);
    	libraryHour = null;
    	libraryHour = libraryHourRepository.findLibraryHourById(libraryHourId);
		assertNotNull(libraryHour);
		assertEquals(libraryHourId, libraryHour.getId());
		assertEquals(startTime, libraryHour.getStartTime());
		assertEquals(endTime, libraryHour.getEndTime());
		assertEquals(dayOfWeek, libraryHour.getDayOfWeek());
		assertEquals(library, libraryHour.getLibrary());
	}
    
//checks database can return a list of books with the same author
    @Test
    public void testFindBookByAuthor(){
        List<Book> b= new ArrayList<Book>();
        String author= "Victoria";
        String name="The Lost Child";
        String name2="The Lost Daughter";
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        Book book= new Book(1234,name,date,author, "Penguin","horror");
        Book book2= new Book(5678,name2,date, author,"Harper", "romance");
        bookRepository.save(book);
        bookRepository.save(book2);
        b=bookRepository.findBookByAuthor(author);
        assertNotNull(b);
        book=b.get(0);
        book2=b.get(1);
        assertEquals(2,b.size());
        assertEquals(name, book.getName());
        assertEquals(name2,book2.getName());
    }
    //ensures database can return a list of books with the same publisher
    @Test
    public void testFindBookByPublisher(){
        List<Book> b= new ArrayList<Book>();
        String publisher= "Penguin";
        String name="The Lost Child";
        String name2="The Lost Daughter";
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        Book book= new Book(1234,name,date,"Victoria", publisher,"horror");
        Book book2= new Book(5678,name2,date, "Hana",publisher, "romance");
        bookRepository.save(book);
        bookRepository.save(book2);
        b=bookRepository.findBookByPublisher(publisher);
        assertNotNull(b);
        book=b.get(0);
        book2=b.get(1);
        assertEquals(2,b.size());
        assertEquals(name, book.getName());
        assertEquals(name2,book2.getName());
    }
    //ensures data base can returns a list of books of the same genre
    @Test
    public void testFindBookByGenre(){
        List<Book> b= new ArrayList<Book>();
        String genre= "Horror";
        String name="The Lost Child";
        String name2="The Lost Daughter";
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        Book book= new Book(1234,name,date,"Victoria", "Penguin",genre);
        Book book2= new Book(5678,name2,date, "Hana","Harper", genre);
        bookRepository.save(book);
        bookRepository.save(book2);
        b=bookRepository.findBookByGenre(genre);
        assertNotNull(b);
        book=b.get(0);
        book2=b.get(1);
        assertEquals(2,b.size());
        assertEquals(name, book.getName());
        assertEquals(name2,book2.getName());
    }
    //ensures database can return of list of movies with the same director
    @Test
    public void testFindMovieByDirector(){
        List<Movie> movies= new ArrayList<Movie>();
        String director = "James Cameron";
        String name="The world";
        String name2="History";
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        Movie m= new Movie(1234,name,date, director,180,"PG-13","Disney");
        Movie m2= new Movie(5678,name2,date, director,120,"PG","WarnerBros");
        movieRepository.save(m);
        movieRepository.save(m2);
        movies=movieRepository.findMovieByDirector(director);
        assertNotNull(movies);
        m=movies.get(0);
        m2=movies.get(1);
        assertEquals(2,movies.size());
        assertEquals(name, m.getName());
        assertEquals(name2,m2.getName());
    }
    //ensures database can return a list of movies with the same distributor
    @Test
    public void testFindMovieByFilmDistributor(){
        List<Movie> movies= new ArrayList<Movie>();
        String distributor = "Disney";
        String name="The world";
        String name2="History";
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        Movie m= new Movie(1234,name,date, "Sofia Coppola",180,"PG-13",distributor);
        Movie m2= new Movie(5678,name2,date, "Greta Gerwig",120,"PG",distributor);
        movieRepository.save(m);
        movieRepository.save(m2);
        movies=movieRepository.findMovieByFilmDistributor(distributor);
        assertNotNull(movies);
        m=movies.get(0);
        m2=movies.get(1);
        assertEquals(2,movies.size());
        assertEquals(name, m.getName());
        assertEquals(name2,m2.getName());
    }
    //ensures database can return a list of movies with the same rating
    @Test
    public void testFindMovieByRating(){
        List<Movie> movies= new ArrayList<Movie>();
        String rating="PG";
        String name="The world";
        String name2="History";
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        Movie m= new Movie(1234,name,date, "Sofia Coppola",180,rating,"Disney");
        Movie m2= new Movie(5678,name2,date, "Greta Gerwig",120,rating,"WarnerBros");
        movieRepository.save(m);
        movieRepository.save(m2);
        movies=movieRepository.findMovieByRating(rating);
        assertNotNull(movies);
        m=movies.get(0);
        m2=movies.get(1);
        assertEquals(2,movies.size());
        assertEquals(name, m.getName());
        assertEquals(name2,m2.getName());
    }
    //ensures database can return a list of music by the same artist
    @Test
    public void testFindMusicByMusician(){
        List<Music> music= new ArrayList<Music>();
        String musician= "Drake";
        String name="Club Paradise";
        String name2="5am in Toronto";
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        Music m= new Music(1234,name,date, musician,"OVO");
        Music m2= new Music(5678,name2,date, musician, "Label");
        musicRepository.save(m);
        musicRepository.save(m2);
        music=musicRepository.findMusicByMusician(musician);
        assertNotNull(music);
        m=music.get(0);
        m2=music.get(1);
        assertEquals(2,music.size());
        assertEquals(name, m.getName());
        assertEquals(name2,m2.getName());
    }
    //ensures database can return a list of music produced by the same label
    @Test
    public void testFindMusicByRecordLabel(){
        List<Music> music= new ArrayList<Music>();
        String label= "Ovo";
        String name="Club Paradise";
        String name2="5am in Toronto";
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        Music m= new Music(1234,name,date, "Adele",label);
        Music m2= new Music(5678,name2,date, "Taylor Swift", label);
        musicRepository.save(m);
        musicRepository.save(m2);
        music=musicRepository.findMusicByRecordLabel(label);
        assertNotNull(music);
        m=music.get(0);
        m2=music.get(1);
        assertEquals(2,music.size());
        assertEquals(name, m.getName());
        assertEquals(name2,m2.getName());
    }


    @Test
    public void testPersistAndLoadItemInstance() {
        String serialNum = "1234";
        CheckableItem checkableItem = new Music(1234,"My Brilliant Friend",
                java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12)),"Victoria","Sony");
        checkableItemRepository.save(checkableItem);
        ItemInstance itemInstance = new ItemInstance(serialNum, checkableItem);
        itemInstanceRepository.save(itemInstance);
        itemInstance = itemInstanceRepository.findItemInstanceBySerialNum(serialNum);
        assertNotNull(itemInstance);
        assertEquals(serialNum, itemInstance.getSerialNum());
        assertEquals(checkableItem.getId(), itemInstance.getCheckableItem().getId());
    }

    @Test
    public void testFindItemInstanceByCheckableItem() {
        String serialNum1 = "1234";
        String serialNum2 = "5678";
        CheckableItem checkableItem = new Music(1234,"My Brilliant Friend",
                java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12)),"Victoria","Sony");
        checkableItemRepository.save(checkableItem);
        ItemInstance itemInstance1 = new ItemInstance(serialNum1, checkableItem);
        ItemInstance itemInstance2 = new ItemInstance(serialNum2, checkableItem);
        itemInstanceRepository.save(itemInstance1);
        itemInstanceRepository.save(itemInstance2);
        List<ItemInstance> itemInstances = itemInstanceRepository.findByCheckableItem(checkableItem);
        assertNotNull(itemInstances);
        itemInstance1 = itemInstances.get(0);
        assertEquals(serialNum1, itemInstance1.getSerialNum());
        assertEquals(checkableItem.getId(), itemInstance1.getCheckableItem().getId());
        itemInstance2 = itemInstances.get(1);
        assertEquals(serialNum2, itemInstance2.getSerialNum());
        assertEquals(checkableItem.getId(), itemInstance2.getCheckableItem().getId());
    }

    /*@Test
    public void testFindCheckableItemByItemInstance() {
        String serialNum = "1234";
        Integer id = 1234;
        String name = "My Brilliant Friend";
        Date date = java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        String musician = "Victoria";
        String recordLabel = "Sony";
        CheckableItem checkableItem = new Music(id,name,
                date,musician,recordLabel);
        checkableItemRepository.save(checkableItem);
        ItemInstance itemInstance = new ItemInstance(serialNum, checkableItem);
        checkableItem = checkableItemRepository.findCheckableItemByItemInstance(itemInstance);
        assertNotNull(checkableItem);
        assertEquals(checkableItem.getId(), id);
        assertEquals(checkableItem.getName(), name);
        assertEquals(checkableItem.getDatePublished(), date);

        Music music = (Music) checkableItem;
        assertEquals(music.getMusician(), musician);
        assertEquals(music.getRecordLabel(), recordLabel);
    }*/

    @Test
    public void testPersistAndLoadNewspaper() {
        Integer id = 1234;
        String name = "New York Times";
        Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.MAY,24));
        String headline = "US deaths near 100,000, an incalculable loss";
        Newspaper newspaper = new Newspaper(id, name, date, headline);
        newspaperRepository.save(newspaper);
        newspaper = (Newspaper) newspaperRepository.findItemById(id);
        assertNotNull(newspaper);
        assertEquals(id,newspaper.getId());
        assertEquals(name, newspaper.getName());
        assertEquals(date, newspaper.getDatePublished());
        assertEquals(headline, newspaper.getHeadline());
    }

    @Test
    public void testFindNewspaperByHeadline() {
        Integer id = 1234;
        String name = "New York Times";
        Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.MAY,24));
        String headline = "US deaths near 100,000, an incalculable loss";
        Newspaper newspaper = new Newspaper(id, name, date, headline);
        newspaperRepository.save(newspaper);
        newspaper = newspaperRepository.findNewspaperByHeadline(headline);
        assertNotNull(newspaper);
        assertEquals(id,newspaper.getId());
        assertEquals(name, newspaper.getName());
        assertEquals(date, newspaper.getDatePublished());
        assertEquals(headline, newspaper.getHeadline());
    }

    @Test
    public void testPersistAndLoadLibraryCardId(){
        String id = "123";
        LibraryCard card = new LibraryCard();
        card.setId(id);
        libraryCardRepository.save(card);

        String s = card.getId();

        card = null;

        card = libraryCardRepository.findLibraryCardById(s);
        assertNotNull(card);
        assertEquals(id, card.getId());
    }

    @Test
    public void testPersistAndLoadOnlineAccountUsername(){
        String username = "fiona";
        String password = "abc123";
        OnlineAccount acct = new OnlineAccount();
        acct.setUsername(username);
        acct.setPassword(password);
        onlineAccountRepository.save(acct);

        String s = acct.getUsername();

        acct = null;

        acct = onlineAccountRepository.findOnlineAccountByUsername(s);
        assertNotNull(acct);
        assertEquals(username, acct.getUsername());
    }

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private LibrarianRepository librarianRepository;
    @Autowired
    private HeadLibrarianRepository headLibrarianRepository;
    @Autowired
    private ShiftRepository shiftRepository;

    @Test
    public void testPersistAndLoadCustomer(){
        int penalty = 0;
        String roleType = "Customer";
        Person person = new Person();
        Loan loan = new Loan();
        Address address = new Address();
        OnlineAccount account = new OnlineAccount();
        LibraryCard libCard = new LibraryCard();
        Customer c = new Customer();
        c.setAddress(address);
        c.setAccount(account);
        c.setId(roleType);
        c.setLibraryCard(libCard);
        c.setPerson(person);
        c.setPenalty(0);
        customerRepository.save(c);
        c = null;
        c = (Customer) customerRepository.findPersonRoleById(roleType);
        assertNotNull(c);
        assertEquals(roleType,c.getId());
        assertEquals(account, c.getAccount());
        assertEquals(person, c.getPerson());
        assertEquals(0, c.getPenalty());
        assertEquals(libCard, c.getLibraryCard());
        assertEquals(address, c.getAddress());
    }

    @Test
    public void testPersistAndLoadLibrarian(){

    }
    @Test
    public void testPersistAndLoadHeadLibrarian(){

    }
    @Test
    public void testPersistAndLoadShift(){
        Integer shiftID = 5432;
        Time startTime = java.sql.Time.valueOf(java.time.LocalTime.now());
        Time endTime = java.sql.Time.valueOf(java.time.LocalTime.now());
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,16));
        DayOfWeek DOW = java.time.DayOfWeek.valueOf("Monday");
        Librarian librarian = new Librarian();
        Shift shift = new Shift();
        shift.setLibrarian(librarian);
        shift.setDayOfWeek(DOW);
        shift.setEndTime(endTime);
        shift.setStartTime(startTime);
        shift.setId(shiftID);
        shiftRepository.save(shift);
        shift = null;
        shift = shiftRepository.findShiftById(shiftID);
        assertNotNull(shift);
        assertEquals(librarian, shift.getLibrarian());
        assertEquals(shiftID, shift.getId());
        assertEquals(startTime, shift.getStartTime());
        assertEquals(endTime, shift.getEndTime());
        assertEquals(DOW, shift.getDayOfWeek());
    }
}


