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

import javax.transaction.Transactional;
import javax.persistence.criteria.CriteriaBuilder;
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
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private LibrarianRepository librarianRepository;
    @Autowired
    private HeadLibrarianRepository headLibrarianRepository;
    @Autowired
    private ShiftRepository shiftRepository;
    @Autowired
    private LibraryManagementSystemRepository libraryManagementSystemRepository;
    @Autowired
    private PersonRepository personRepository;

    @AfterEach
    public void clearDatabase() {
        loanRepository.deleteAll();
        reservationRepository.deleteAll();
        itemInstanceRepository.deleteAll();
        personRepository.deleteAll();
        bookRepository.deleteAll();
        movieRepository.deleteAll();
        musicRepository.deleteAll();
        checkableItemRepository.deleteAll();
        newspaperRepository.deleteAll();
        addressRepository.deleteAll();
        libraryHourRepository.deleteAll();
        libraryRepository.deleteAll();
        customerRepository.deleteAll();
        shiftRepository.deleteAll();
        librarianRepository.deleteAll();
        headLibrarianRepository.deleteAll();
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
    	Integer streetNumber = 1234;
    	String street = "Main st";
    	String city = "Montreal";
    	String country = "Canada";
    	Address address = new Address(0, streetNumber, street, city, country);
    	addressRepository.save(address);
    	int addressID = address.getAddressID();
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
    @Transactional
    public void testPersistAndLoadLibraryHour() {
    	String name = "McLennen";
    	Library library = new Library(name);
    	Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		Time endTime = java.sql.Time.valueOf(LocalTime.of(13, 25));
    	DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
    	LibraryHour libraryHour = new LibraryHour(0, startTime, endTime, dayOfWeek, null);
    	libraryHour.setLibrary(library);
    	libraryHourRepository.save(libraryHour);
    	int libraryHourId = libraryHour.getId();
    	libraryHour = null;
    	libraryHour = libraryHourRepository.findLibraryHourById(libraryHourId);
		assertNotNull(libraryHour);
		assertEquals(libraryHourId, libraryHour.getId());
		assertEquals(startTime, libraryHour.getStartTime());
		assertEquals(endTime, libraryHour.getEndTime());
		assertEquals(dayOfWeek, libraryHour.getDayOfWeek());
		assertEquals(library.getName(), libraryHour.getLibrary().getName());
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

    @Test
    public void testPersistAndLoadOnlineAccountPassword(){
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
        assertEquals(password, acct.getPassword());
    }

    @Test
    public void testPersistAndLoadReservation() {
        String serialNum = "1234";
        CheckableItem checkableItem = new Music(1234,"My Brilliant Friend",
                java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12)),"Victoria","Sony");
        checkableItemRepository.save(checkableItem);

        ItemInstance itemInstance = new ItemInstance(serialNum, checkableItem);
        itemInstanceRepository.save(itemInstance);

        Customer customer = new Customer("customer", null, 0, null, null, null);
        customerRepository.save(customer);

		int id = 6789;
		Date dateReserved = java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
		Date pickupDay = java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,16));
		Reservation reservation = new Reservation(id, dateReserved, pickupDay, itemInstance, customer);
		reservationRepository.save(reservation);
		reservation = reservationRepository.findReservationById(id);
		assertNotNull(reservation);
		assertEquals(id, reservation.getId());
		assertEquals(dateReserved, reservation.getDateReserved());
		assertEquals(pickupDay, reservation.getPickupDay());
		assertEquals(itemInstance.getSerialNum(), reservation.getItemInstance().getSerialNum());
		assertEquals(customer.getId(), reservation.getCustomer().getId());
    }

    @Test
    public void testFindReservationByDateReserved() {
        String serialnum = "1234";
        String serialnum2 = "8888";
        Book b1 = new Book(5678, "My Brilliant Friend", java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER, 12)), "Victoria", "Harper", "horror");
        CheckableItem checkableItem1 = b1;
        checkableItemRepository.save(checkableItem1);
        ItemInstance item1 = new ItemInstance(serialnum, checkableItem1);
        itemInstanceRepository.save(item1);
        Book b2 = new Book(5678, "The Lost Child", java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER, 12)), "Victoria", "Harper", "horror");
        CheckableItem checkableItem2 = b2;
        checkableItemRepository.save(checkableItem2);
        ItemInstance item2 = new ItemInstance(serialnum2, checkableItem2);
        itemInstanceRepository.save(item2);
        Customer c = new Customer();
        String customerId = "778";
        c.setId(customerId);
        customerRepository.save(c);

        Integer id1 = 345;
        Integer id2 = 456;
        Date dateReserved = java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER, 12));
        Date pickupDay = java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER, 17));
        Reservation r1 = new Reservation(id1, dateReserved, pickupDay, item1, c);
        Reservation r2 = new Reservation(id2, dateReserved, pickupDay, item2, c);
        reservationRepository.save(r1);
        reservationRepository.save(r2);

        List<Reservation> reservations = reservationRepository.findReservationByDateReserved(dateReserved);
        assertNotNull(reservations);
        assertEquals(2, reservations.size());

        r1 = reservations.get(0);
        r2 = reservations.get(1);

        assertEquals(r1.getId(), id1);
        assertEquals(r1.getDateReserved(), dateReserved);
        assertEquals(r1.getPickupDay(), pickupDay);
        assertEquals(r1.getItemInstance().getSerialNum(), item1.getSerialNum());
        assertEquals(r1.getCustomer().getId(), c.getId());

        assertEquals(r2.getId(), id2);
        assertEquals(r2.getDateReserved(), dateReserved);
        assertEquals(r2.getPickupDay(), pickupDay);
        assertEquals(r2.getItemInstance().getSerialNum(), item2.getSerialNum());
        assertEquals(r2.getCustomer().getId(), c.getId());
    }

    @Test
	public void testPersistAndLoadLoan() {
        String serialNum = "1234";
        CheckableItem checkableItem = new Music(1234,"My Brilliant Friend",
                java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12)),"Victoria","Sony");
        checkableItemRepository.save(checkableItem);

        ItemInstance itemInstance = new ItemInstance(serialNum, checkableItem);
        itemInstanceRepository.save(itemInstance);

        Customer customer = new Customer("customer", null, 0, null, null, null);
        customerRepository.save(customer);

		int id = 6789;
		Date checkedOut = java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
		Date returnDate = java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,16));
		Loan loan = new Loan(id, checkedOut, returnDate, itemInstance, customer);
		loanRepository.save(loan);
		loan = loanRepository.findLoanById(id);
		assertNotNull(loan);
		assertEquals(id, loan.getId());
		assertEquals(checkedOut, loan.getCheckedOut());
		assertEquals(returnDate, loan.getReturnDate());
		assertEquals(itemInstance.getSerialNum(), loan.getItemInstance().getSerialNum());
		assertEquals(customer.getId(), loan.getCustomer().getId());
	}

    @Test
    public void findLoanByCheckedOut() {
        List<Loan> loans;
        String serialnum = "1234";
        String serialnum2 = "8888";
        Book b1 = new Book(5678, "My Brilliant Friend", java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER, 12)), "Victoria", "Harper", "horror");
        CheckableItem checkableItem1 = b1;
        checkableItemRepository.save(checkableItem1);
        ItemInstance item1 = new ItemInstance(serialnum, checkableItem1);
        itemInstanceRepository.save(item1);
        Book b2 = new Book(5678, "The Lost Child", java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER, 12)), "Victoria", "Harper", "horror");
        CheckableItem checkableItem2 = b2;
        checkableItemRepository.save(checkableItem2);
        ItemInstance item2 = new ItemInstance(serialnum2, checkableItem2);
        itemInstanceRepository.save(item2);
        Date checkedOut = java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER, 12));
        Date returnDate = java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER, 16));
        Date returnDate2 = java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER, 16));
        Customer c = new Customer();
        String customerId = "778";
        c.setId(customerId);
        customerRepository.save(c);
        Loan l1 = new Loan(6789, checkedOut, returnDate, item1, c);
        Loan l2 = new Loan(8933, checkedOut, returnDate2, item2, c);

        loanRepository.save(l1);
        loanRepository.save(l2);
        loans = loanRepository.findLoanByCheckedOut(checkedOut);

        assertNotNull(loans);
        assertEquals(2, loans.size());
        l1 = loans.get(0);
        l2 = loans.get(1);
        assertEquals(checkedOut, l1.getCheckedOut());
        assertEquals(checkedOut, l2.getCheckedOut());
        //assertEquals(item1.getSerialNum(), l1.getItemInstance().getSerialNum());
        //assertEquals(item2.getSerialNum(), l2.getItemInstance().getSerialNum());
        assertEquals(c.getId(), l1.getCustomer().getId());
        assertEquals(c.getId(), l2.getCustomer().getId());
    }

    @Test
    @Transactional
    public void testPersistAndLoadCustomer(){
        int pId = 1432;
        Person person = new Person();
        person.setId(pId);
        person.setName("bob");
        person.setPersonRoleList(null);
        personRepository.save(person);

        int aId = 123;
        Address address = new Address();
        address.setAddressID(aId);
        address.setStreetNumber(1);
        address.setStreet(null);
        address.setCountry(null);
        address.setCity(null);
        addressRepository.save(address);

        String libId = "542";
        LibraryCard libCard = new LibraryCard();
        libCard.setId(libId);
        libCard.setCustomer(null);
        libraryCardRepository.save(libCard);

        String username = "username";
        OnlineAccount account = new OnlineAccount();
        account.setPassword("password");
        account.setUsername(username);
        account.setPersonRole(null);
        onlineAccountRepository.save(account);

        int penalty = 0;
        String customerId = "3234";
        Customer c = new Customer(customerId, person, penalty, address, libCard, account);
        customerRepository.save(c);
        c = null;
        account = null;
        person = null;
        libCard = null;
        address = null;

        c = (Customer) customerRepository.findPersonRoleById(customerId);
        assertNotNull(c);
        account = onlineAccountRepository.findOnlineAccountByUsername(username);
        person = personRepository.findPersonById(pId);
        libCard = libraryCardRepository.findLibraryCardById(libId);
        address = addressRepository.findAddressByAddressID(aId);

        assertEquals(person, c.getPerson());
        assertEquals(account, c.getAccount());
        assertEquals(libCard, c.getLibraryCard());
        assertEquals(address, c.getAddress());
        assertEquals(penalty, c.getPenalty());
        assertEquals(customerId, c.getId());
    }

    @Test
    @Transactional
    public void testPersistAndLoadLibrarian(){
        Person person = new Person();
        person.setId(1432);
        person.setName("bob");
        person.setPersonRoleList(null);
        personRepository.save(person);

        OnlineAccount account = new OnlineAccount();
        account.setPassword("password");
        account.setUsername("username");
        account.setPersonRole(null);
        onlineAccountRepository.save(account);

        Librarian l = new Librarian("3214", person, account);
        librarianRepository.save(l);
        l = null;
        account = null;
        person = null;

        l = (Librarian) librarianRepository.findPersonRoleById("3214");
        assertNotNull(l);
        account = onlineAccountRepository.findOnlineAccountByUsername("username");
        person = personRepository.findPersonById(1432);

        assertEquals(person, l.getPerson());
        assertEquals(account, l.getAccount());
        assertEquals("3214", l.getId());
    }
    @Test
    @Transactional
    public void testPersistAndLoadHeadLibrarian(){
        Person person = new Person();
        person.setId(1432);
        person.setName("bob");
        person.setPersonRoleList(null);
        personRepository.save(person);

        OnlineAccount account = new OnlineAccount();
        account.setPassword("password");
        account.setUsername("username");
        account.setPersonRole(null);
        onlineAccountRepository.save(account);

        HeadLibrarian headLibrarian = new HeadLibrarian("3214", person, account);
        librarianRepository.save(headLibrarian);
        headLibrarian = null;
        account = null;
        person = null;

        headLibrarian = (HeadLibrarian) librarianRepository.findPersonRoleById("3214");
        assertNotNull(headLibrarian);
        account = onlineAccountRepository.findOnlineAccountByUsername("username");
        person = personRepository.findPersonById(1432);

        assertEquals(person, headLibrarian.getPerson());
        assertEquals(account, headLibrarian.getAccount());
        assertEquals("3214", headLibrarian.getId());
    }
    @Test
    @Transactional
    public void testPersistAndLoadShift(){
        Integer shiftID = 5432;
        Time startTime = java.sql.Time.valueOf(java.time.LocalTime.now());
        Time endTime = java.sql.Time.valueOf(java.time.LocalTime.now());
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,16));
        DayOfWeek DOW = java.time.DayOfWeek.valueOf("MONDAY");
        Librarian librarian = new Librarian("Librarian",null,null);
        librarianRepository.save(librarian);

        Shift shift = new Shift();
        shift.setLibrarian(librarian);
        shift.setDayOfWeek(DOW);
        shift.setEndTime(endTime);
        shift.setStartTime(startTime);
        shift.setId(shiftID);
        shiftRepository.save(shift);
        shift = null;
        librarian = null;
        shift = shiftRepository.findShiftById(shiftID);
        assertNotNull(shift);
        librarian = (Librarian) librarianRepository.findPersonRoleById("Librarian");
        assertEquals(librarian, shift.getLibrarian());
        assertEquals(shiftID, shift.getId());
        assertEquals(startTime, shift.getStartTime());
        assertEquals(endTime, shift.getEndTime());
        assertEquals(DOW, shift.getDayOfWeek());
    }

    @Test
    @Transactional
    public void testPersistAndLoadLMS(){
        LibraryManagementSystem lms = new LibraryManagementSystem();

        Address address = new Address();
        ArrayList<Address> addresses = new ArrayList<>();
        addresses.add(address);
        lms.setAddressList(addresses);

        LibraryHour hour = new LibraryHour();
        hour.setLibrary(new Library("jawn"));
        ArrayList<LibraryHour> hours = new ArrayList<>();
        hours.add(hour);
        lms.setLibraryHourList(hours);

        Person person = new Person();
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(person);
        lms.setPersonList(persons);

        Item item = new Book();
        item.setId(120);
        ArrayList<Item> items = new ArrayList<>();
        items.add(item);
        lms.setItemList(items);

        LibraryCard card = new LibraryCard();
        card.setId("mr turbo");
        ArrayList<LibraryCard> cards = new ArrayList<>();
        cards.add(card);
        lms.setLibraryCardList(cards);

        PersonRole role = new Librarian();
        role.setId("id");
        ArrayList<PersonRole> roles = new ArrayList<>();
        roles.add(role);
        lms.setPersonRoleList(roles);

        Shift shift = new Shift();
        ArrayList<Shift> shifts = new ArrayList<>();
        shifts.add(shift);
        lms.setShiftList(shifts);

        ItemInstance itemInstance = new ItemInstance();
        itemInstance.setSerialNum("jawnie");
        itemInstance.setCheckableItem((Book)item);
        ArrayList<ItemInstance> itemInstances = new ArrayList<>();
        itemInstances.add(itemInstance);
        lms.setItemInstanceList(itemInstances);

        Reservation reservation = new Reservation();
        reservation.setId(12345);
        ArrayList<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation);
        lms.setReservationList(reservations);

        Loan loan = new Loan();
        loan.setId(12346);
        ArrayList<Loan> loans = new ArrayList<>();
        loans.add(loan);
        lms.setLoanList(loans);


        libraryManagementSystemRepository.save(lms);
        int id = lms.getId();
        lms = null;
        lms = libraryManagementSystemRepository.findLibraryManagementSystemById(id);
        assertNotNull(lms);
        assertEquals(id, lms.getId());

        assertEquals(addresses.size(),lms.getAddressList().size());
        assertEquals(address,lms.getAddressList().get(0));

        assertEquals(hours.size(),lms.getLibraryHourList().size());
        assertEquals(hour,lms.getLibraryHourList().get(0));

        assertEquals(persons.size(),lms.getPersonList().size());
        assertEquals(person,lms.getPersonList().get(0));

        assertEquals(items.size(),lms.getItemList().size());
        assertEquals(item,lms.getItemList().get(0));

        assertEquals(cards.size(),lms.getLibraryCardList().size());
        assertEquals(card,lms.getLibraryCardList().get(0));

        assertEquals(roles.size(),lms.getPersonRoleList().size());
        assertEquals(role,lms.getPersonRoleList().get(0));

        assertEquals(shifts.size(),lms.getShiftList().size());
        assertEquals(shift,lms.getShiftList().get(0));

        assertEquals(itemInstances.size(),lms.getItemInstanceList().size());
        assertEquals(itemInstance,lms.getItemInstanceList().get(0));

        assertEquals(reservations.size(),lms.getReservationList().size());
        assertEquals(reservation,lms.getReservationList().get(0));

        assertEquals(loans.size(),lms.getLoanList().size());
        assertEquals(loan,lms.getLoanList().get(0));
    }

    @Test
    @Transactional
    public void testPersistAndLoadPerson(){
        String name = "TestPerson";
        List<Person> persons = null;
        Person person = new Person();
        Person person1 = new Person();
        person.setName(name);
        person1.setName(name);
        personRepository.save(person);
        personRepository.save(person1);
        persons = personRepository.findPersonByName(name);
        assertNotNull(persons);
        person = persons.get(0);
        person1 = persons.get(1);
        assertEquals(2, persons.size());
        assertEquals(name, person.getName());
        assertEquals(name, person1.getName());
    }

    @Test
    public void testPersistAndLoadAddressCity(){
        String city = "Newark";
        String street = "main";
        String country = "US";
        int streetNum = 1600;
        Address add = new Address();
        Integer i = add.getAddressID();
        add.setStreetNumber(streetNum);
        add.setStreet(street);
        add.setCity(city);
        add.setCountry(country);
        addressRepository.save(add);

        add = null;

        add = addressRepository.findAddressByCity(city);
        assertNotNull(add);
        assertEquals(city,add.getCity());
    }

    @Test
    public void testPersistAndLoadAddressStreetNum(){
        String city = "Newark";
        String street = "main";
        String country = "US";
        int streetNum = 1600;
        Address add = new Address();

        add.setStreetNumber(streetNum);
        add.setStreet(street);
        add.setCity(city);
        add.setCountry(country);
        addressRepository.save(add);

        add = null;

        add = addressRepository.findAddressByStreetNumber(streetNum);
        assertNotNull(add);
        assertEquals(city,add.getCity());
    }
}


