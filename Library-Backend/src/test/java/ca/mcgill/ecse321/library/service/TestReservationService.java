
//package ca.mcgill.ecse321.library.service;
//
//import ca.mcgill.ecse321.library.dao.*;
//import ca.mcgill.ecse321.library.model.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.invocation.InvocationOnMock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.lenient;
//
//@ExtendWith(MockitoExtension.class)
//public class TestReservationService {
//    @Mock
//    private ReservationRepository reservationRepository;
//    @Mock
//    private CustomerRepository customerRepository;
//    @Mock
//    private ItemInstanceRepository itemInstanceRepository;
//    @Mock
//    private LibraryManagementSystemRepository libraryManagementSystemRepository;
//    @Mock
//    private LibrarianRepository librarianRepository;
//
//    @InjectMocks
//    private ReservationService service;
//
//    private static final int RESERVATION_KEY = 1;
//    private static final int CUSTOMER_KEY = 2;
//    private static final int ITEM_INSTANCE_KEY = 3;
//    private static final int LIBRARY_MANAGEMENT_SYSTEM_KEY = 4;
//    private static final int LIBRARIAN_KEY = 5;
//    private static final Customer CUSTOMER = new Customer(2,null,0,null,null,null);
//
//    private static final Date startDate = Date.valueOf("2021-10-11");
//    private static final Date endDate = Date.valueOf("2021-10-31");
//
//    @BeforeEach
//    public void setMockOutput() {
//        lenient().when(reservationRepository.findReservationById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
//            if(invocation.getArgument(0).equals(RESERVATION_KEY)) {
//                Reservation reservation = new Reservation();
//                reservation.setId(RESERVATION_KEY);
//                Customer customer = new Customer();
//                customer.setId(CUSTOMER_KEY);
//                reservation.setCustomer(customer);
//                return reservation;
//            } else {
//                return null;
//            }
//        });
//        lenient().when(reservationRepository.findByCustomer(any())).thenAnswer((InvocationOnMock invocation) -> {
//            if(invocation.getArgument(0).equals(CUSTOMER)) {
//                List<Reservation> reservations = new ArrayList<>();
//                Reservation reservation1 = new Reservation();
//                reservation1.setId(RESERVATION_KEY);
//                reservations.add(reservation1);
//                return reservations;
//            } else {
//                return null;
//            }
//        });
//        lenient().when(customerRepository.findPersonRoleById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
//            if(invocation.getArgument(0).equals(CUSTOMER_KEY)) {
//                Customer customer = new Customer();
//                customer.setId(CUSTOMER_KEY);
//                return customer;
//            } else {
//                return null;
//            }
//        });
//        lenient().when(itemInstanceRepository.findItemInstanceBySerialNum(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
//            if(invocation.getArgument(0).equals(ITEM_INSTANCE_KEY)) {
//                ItemInstance itemInstance = new ItemInstance();
//                itemInstance.setSerialNum(ITEM_INSTANCE_KEY);
//                return itemInstance;
//            } else {
//                return null;
//            }
//        });
//        lenient().when(libraryManagementSystemRepository.findLibraryManagementSystemById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
//            if(invocation.getArgument(0).equals(LIBRARY_MANAGEMENT_SYSTEM_KEY)) {
//                LibraryManagementSystem lms = new LibraryManagementSystem();
//                lms.setId(LIBRARY_MANAGEMENT_SYSTEM_KEY);
//                return lms;
//            } else {
//                return null;
//            }
//        });
//        lenient().when(librarianRepository.findPersonRoleById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
//            if(invocation.getArgument(0).equals(LIBRARIAN_KEY)) {
//                Librarian librarian = new Librarian();
//                librarian.setId(LIBRARIAN_KEY);
//                return librarian;
//            } else {
//                return null;
//            }
//        });
//    }
//    //START GET RESERVATION TESTS
//    @Test
//    public void testGetReservationValid(){
//        Reservation reservation = null;
//        try{
//            reservation = service.getReservation(RESERVATION_KEY, CUSTOMER_KEY);
//        }
//        catch (Exception e){
//            fail();
//        }
//        assertNotNull(reservation);
//        assertEquals(reservation.getId(),RESERVATION_KEY);
//    }
//    @Test
//    public void testGetReservationIdNull(){
//        Reservation reservation = null;
//        String error = "";
//        try{
//            reservation = service.getReservation(null, CUSTOMER_KEY);
//        }
//        catch (Exception e){
//            error = e.getMessage();
//        }
//        assertNull(reservation);
//        assertEquals("Reservation id cannot be null",error);
//    }
//    @Test
//    public void testGetReservationIdNotFound(){
//        Reservation reservation = null;
//        String error = "";
//        try{
//            reservation = service.getReservation(123214, CUSTOMER_KEY);
//        }
//        catch (Exception e){
//            error = e.getMessage();
//        }
//        assertNull(reservation);
//        assertEquals("Reservation with given id cannot be found",error);
//    }
//    @Test
//    public void testGetReservationCustomerIdNull(){
//        Reservation reservation = null;
//        String error = "";
//        try{
//            reservation = service.getReservation(RESERVATION_KEY, null);
//        }
//        catch (Exception e){
//            error = e.getMessage();
//        }
//        assertNull(reservation);
//        assertEquals("Customer id cannot be null",error);
//    }
//    @Test
//    public void testGetReservationCustomerIdNotFound(){
//        Reservation reservation = null;
//        String error = "";
//        try{
//            reservation = service.getReservation(RESERVATION_KEY, 1212313);
//        }
//        catch (Exception e){
//            error = e.getMessage();
//        }
//        assertNull(reservation);
//        assertEquals("Customer id does not match customer id in reservation",error);
//    }
//    //END GET RESERVATION TESTS
//
//    //START GET ALL RESERVATIONS TESTS
//
//    @Test
//    public void testGetAllReservationsValid(){
//        List<Reservation> reservations = null;
//        try{
//            reservations = service.getAllReservations(CUSTOMER_KEY);
//        }
//        catch (Exception e){
//            fail();
//        }
//        assertNotNull(reservations);
//        assertEquals(reservations.size(),1);
//        assertEquals(reservations.get(0).getId(),RESERVATION_KEY);
//    }
//    @Test
//    public void testGetAllReservationsCustomerNull(){
//        List<Reservation> reservations = null;
//        String error = "";
//        try{
//            reservations = service.getAllReservations(null);
//        }
//        catch (Exception e){
//            error = e.getMessage();
//        }
//        assertNull(reservations);
//        assertEquals(error,"Customer id cannot be null");
//    }
//
//    //END GET ALL RESERVATIONS TESTS
//
//    //START CREATE RESERVATION TESTS
//    @Test
//    public void testCreateReservationValid(){
//        Reservation r = null;
//        try{
//            r = service.createReservation(startDate,endDate,ITEM_INSTANCE_KEY,CUSTOMER_KEY,null,LIBRARY_MANAGEMENT_SYSTEM_KEY);
//        }
//        catch (Exception e){
//            fail();
//        }
//        assertNotNull(r);
//        assertNotNull(r.getCustomer());
//        assertEquals(startDate, r.getDateReserved());
//        assertEquals(endDate, r.getPickupDay());
//        assertNotNull(r.getItemInstance());
//        assertNotNull(r.getSystem());
//    }
//
//    @Test
//    public void testCreateReservationValidLibrarian(){
//        Reservation r = null;
//        try{
//            r = service.createReservation(startDate,endDate,ITEM_INSTANCE_KEY,CUSTOMER_KEY,LIBRARIAN_KEY,LIBRARY_MANAGEMENT_SYSTEM_KEY);
//        }
//        catch (Exception e){
//            fail();
//        }
//        assertNotNull(r);
//        assertNotNull(r.getCustomer());
//        assertEquals(startDate, r.getDateReserved());
//        assertEquals(endDate, r.getPickupDay());
//        assertNotNull(r.getItemInstance());
//        assertNotNull(r.getSystem());
//    }
//
//    @Test
//    public void testCreateReservationLibrarianInvalid(){
//        Reservation r = null;
//        String error = "";
//        try{
//            r = service.createReservation(startDate,endDate,ITEM_INSTANCE_KEY,CUSTOMER_KEY,LIBRARIAN_KEY+1,LIBRARY_MANAGEMENT_SYSTEM_KEY);
//        }
//        catch (Exception e){
//            error = e.getMessage();
//        }
//        assertNull(r);
//        assertEquals(error,"Invalid librarian creating the reservation");
//    }
//
//    @Test
//    public void testCreateReservationNoStartDate(){
//        Reservation r = null;
//        String error = "";
//        try{
//            r = service.createReservation(null,endDate,ITEM_INSTANCE_KEY,CUSTOMER_KEY,null,LIBRARY_MANAGEMENT_SYSTEM_KEY);
//        }
//        catch (Exception e){
//            error = e.getMessage();
//        }
//        assertNull(r);
//        assertEquals(error,"Cannot have empty reservation date");
//    }
//
//    @Test
//    public void testCreateReservationNoEndDate(){
//        Reservation r = null;
//        String error = "";
//        try{
//            r = service.createReservation(startDate,null,ITEM_INSTANCE_KEY,CUSTOMER_KEY,null,LIBRARY_MANAGEMENT_SYSTEM_KEY);
//        }
//        catch (Exception e){
//            error = e.getMessage();
//        }
//        assertNull(r);
//        assertEquals(error,"Cannot have empty pickup day");
//    }
//
//    @Test
//    public void testCreateReservationNullItemInstance(){
//        Reservation r = null;
//        String error = "";
//        try{
//            r = service.createReservation(startDate,endDate,null,CUSTOMER_KEY,null,LIBRARY_MANAGEMENT_SYSTEM_KEY);
//        }
//        catch (Exception e){
//            error = e.getMessage();
//        }
//        assertNull(r);
//        assertEquals(error,"Item instance serial number cannot be missing");
//    }
//
//    @Test
//    public void testCreateReservationMissingItemInstance(){
//        Reservation r = null;
//        String error = "";
//        try{
//            r = service.createReservation(startDate,endDate,ITEM_INSTANCE_KEY+1,CUSTOMER_KEY,null,LIBRARY_MANAGEMENT_SYSTEM_KEY);
//        }
//        catch (Exception e){
//            error = e.getMessage();
//        }
//        assertNull(r);
//        assertEquals(error,"Item instance does not exist");
//    }
//
//    @Test
//    public void testCreateReservationNullCustomer(){
//        Reservation r = null;
//        String error = "";
//        try{
//            r = service.createReservation(startDate,endDate,ITEM_INSTANCE_KEY,null,null,LIBRARY_MANAGEMENT_SYSTEM_KEY);
//        }
//        catch (Exception e){
//            error = e.getMessage();
//        }
//        assertNull(r);
//        assertEquals(error,"Need to have a customer for a reservation");
//    }
//
//    @Test
//    public void testCreateReservationMissingCustomer(){
//        Reservation r = null;
//        String error = "";
//        try{
//            r = service.createReservation(startDate,endDate,ITEM_INSTANCE_KEY,CUSTOMER_KEY+1,null,LIBRARY_MANAGEMENT_SYSTEM_KEY);
//        }
//        catch (Exception e){
//            error = e.getMessage();
//        }
//        assertNull(r);
//        assertEquals(error,"Invalid customer provided");
//    }
//
//    @Test
//    public void testCreateReservationNullSystem(){
//        Reservation r = null;
//        String error = "";
//        try{
//            r = service.createReservation(startDate,endDate,ITEM_INSTANCE_KEY,CUSTOMER_KEY,null,null);
//        }
//        catch (Exception e){
//            error = e.getMessage();
//        }
//        assertNull(r);
//        assertEquals(error,"Cannot have null systemId");
//    }
//
//    @Test
//    public void testCreateReservationMissingSystem(){
//        Reservation r = null;
//        String error = "";
//        try{
//            r = service.createReservation(startDate,endDate,ITEM_INSTANCE_KEY,CUSTOMER_KEY,null,LIBRARY_MANAGEMENT_SYSTEM_KEY+1);
//        }
//        catch (Exception e){
//            error = e.getMessage();
//        }
//        assertNull(r);
//        assertEquals(error,"System not found");
//    }
//    //END CREATE RESERVATION TESTS
//
//}

package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.model.*;
import ca.mcgill.ecse321.library.service.ReservationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestReservationService {
    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private ItemInstanceRepository itemInstanceRepository;
    @Mock
    private LibrarianRepository librarianRepository;

    @InjectMocks
    private ReservationService service;

    private static final int RESERVATION_KEY = 1;
    private static final int CUSTOMER_KEY = 2;
    private static final int CUSTOMER_GREEDY_KEY = 102;
    private static final int ITEM_INSTANCE_KEY = 3;
    private static final int LIBRARIAN_KEY = 5;
    private static final Customer CUSTOMER = new Customer(2,null,0,null,null,null);
    private static final Customer GREEDY_CUSTOMER = new Customer(CUSTOMER_GREEDY_KEY,null,0,null,null,null);

    private static final Date startDate = Date.valueOf("2021-10-11");
    private static final Date endDate = Date.valueOf("2021-10-31");
    private static final Date newStartDate = Date.valueOf("2021-10-21");
    private static final Date newEndDate = Date.valueOf("2021-11-31");

    @BeforeEach
    public void setMockOutput() {
        lenient().when(reservationRepository.findReservationById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(RESERVATION_KEY)) {
                Reservation reservation = new Reservation();
                reservation.setId(RESERVATION_KEY);
                Customer customer = new Customer();
                customer.setId(CUSTOMER_KEY);
                reservation.setCustomer(customer);
                reservation.setPickupDay(endDate);
                reservation.setDateReserved(startDate);
                ItemInstance itemInstance = new ItemInstance();
                itemInstance.setSerialNum(ITEM_INSTANCE_KEY);
                reservation.setItemInstance(itemInstance);
                return reservation;
            } else {
                return null;
            }
        });
        lenient().when(reservationRepository.findByCustomer(any())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(CUSTOMER)) {
                List<Reservation> reservations = new ArrayList<>();
                Reservation reservation1 = new Reservation();
                reservation1.setId(RESERVATION_KEY);
                reservations.add(reservation1);
                return reservations;
            }
            else if(invocation.getArgument(0).equals(GREEDY_CUSTOMER)) {
                List<Reservation> reservations = new ArrayList<>();
                for(int i =0; i <6; i++){
                    Reservation reservation1 = new Reservation();
                    reservation1.setId(RESERVATION_KEY);
                    reservations.add(reservation1);
                }
                return reservations;
            }
            else {
                return null;
            }
        });
        lenient().when(customerRepository.findPersonRoleById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(CUSTOMER_KEY)) {
                Customer customer = new Customer();
                customer.setId(CUSTOMER_KEY);
                return customer;
            }
            else if(invocation.getArgument(0).equals(CUSTOMER_KEY+10)) {
                Customer customer = new Customer();
                customer.setId(CUSTOMER_KEY+10);
                return customer;
            }
            else if(invocation.getArgument(0).equals(CUSTOMER_GREEDY_KEY)) {
                Customer customer = new Customer();
                customer.setId(CUSTOMER_GREEDY_KEY);
                return customer;
            }
            else {
                return null;
            }
        });
        lenient().when(itemInstanceRepository.findItemInstanceBySerialNum(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(ITEM_INSTANCE_KEY)) {
                ItemInstance itemInstance = new ItemInstance();
                itemInstance.setSerialNum(ITEM_INSTANCE_KEY);
                return itemInstance;
            } else  if(invocation.getArgument(0).equals(ITEM_INSTANCE_KEY+10)) {
                ItemInstance itemInstance = new ItemInstance();
                itemInstance.setSerialNum(ITEM_INSTANCE_KEY+10);
                return itemInstance;
            } else {
                return null;
            }
        });
        lenient().when(librarianRepository.findPersonRoleById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(LIBRARIAN_KEY)) {
                Librarian librarian = new Librarian();
                librarian.setId(LIBRARIAN_KEY);
                return librarian;
            } else {
                return null;
            }
        });
    }
    @AfterEach
    public void clearDatabase() {
        reservationRepository.deleteAll();
        customerRepository.deleteAll();
        itemInstanceRepository.deleteAll();
    }
    //START GET RESERVATION TESTS
    @Test
    public void testGetReservationValid(){
        Reservation reservation = null;
        try{
            reservation = service.getReservation(RESERVATION_KEY, CUSTOMER_KEY);
        }
        catch (Exception e){
            fail();
        }
        assertNotNull(reservation);
        assertEquals(reservation.getId(),RESERVATION_KEY);
    }
    @Test
    public void testGetReservationIdNull(){
        Reservation reservation = null;
        String error = "";
        try{
            reservation = service.getReservation(null, CUSTOMER_KEY);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(reservation);
        assertEquals("Reservation id cannot be null",error);
    }
    @Test
    public void testGetReservationIdNotFound(){
        Reservation reservation = null;
        String error = "";
        try{
            reservation = service.getReservation(123214, CUSTOMER_KEY);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(reservation);
        assertEquals("Reservation with given id cannot be found",error);
    }
    @Test
    public void testGetReservationCustomerIdNull(){
        Reservation reservation = null;
        String error = "";
        try{
            reservation = service.getReservation(RESERVATION_KEY, null);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(reservation);
        assertEquals("Customer id cannot be null",error);
    }
    @Test
    public void testGetReservationCustomerIdNotFound(){
        Reservation reservation = null;
        String error = "";
        try{
            reservation = service.getReservation(RESERVATION_KEY, 1212313);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(reservation);
        assertEquals("Customer id does not match customer id in reservation",error);
    }
    //END GET RESERVATION TESTS

    //START GET ALL RESERVATIONS TESTS

    @Test
    public void testGetAllReservationsValid(){
        List<Reservation> reservations = null;
        try{
            reservations = service.getAllReservations(CUSTOMER_KEY);
        }
        catch (Exception e){
            fail();
        }
        assertNotNull(reservations);
        assertEquals(reservations.size(),1);
        assertEquals(reservations.get(0).getId(),RESERVATION_KEY);
    }
    @Test
    public void testGetAllReservationsCustomerNull(){
        List<Reservation> reservations = null;
        String error = "";
        try{
            reservations = service.getAllReservations(null);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(reservations);
        assertEquals(error,"Customer id cannot be null");
    }

    //END GET ALL RESERVATIONS TESTS

    //START CREATE RESERVATION TESTS
    @Test
    public void testCreateReservationValid(){
        Reservation r = null;
        try{
            r = service.createReservation(startDate,endDate,ITEM_INSTANCE_KEY,CUSTOMER_KEY,null);
        }
        catch (Exception e){
            fail();
        }
        assertNotNull(r);
        assertNotNull(r.getCustomer());
        assertEquals(startDate, r.getDateReserved());
        assertEquals(endDate, r.getPickupDay());
        assertNotNull(r.getItemInstance());
    }

    @Test
    public void testCreateReservationValidLibrarian() {
        Reservation r = null;
        try {
            r = service.createReservation(startDate, endDate, ITEM_INSTANCE_KEY, CUSTOMER_KEY, LIBRARIAN_KEY);
        } catch (Exception e) {
            fail();
        }
        assertNotNull(r);
        assertNotNull(r.getCustomer());
        assertEquals(startDate, r.getDateReserved());
        assertEquals(endDate, r.getPickupDay());
    }

    @Test
    public void testCreateReservationLibrarianInvalid(){
        Reservation r = null;
        String error = "";
        try{
            r = service.createReservation(startDate,endDate,ITEM_INSTANCE_KEY,CUSTOMER_KEY,LIBRARIAN_KEY+1);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(r);
        assertEquals(error,"Invalid librarian creating the reservation");
    }

    @Test
    public void testCreateReservationNoStartDate(){
        Reservation r = null;
        String error = "";
        try{
            r = service.createReservation(null,endDate,ITEM_INSTANCE_KEY,CUSTOMER_KEY,null);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(r);
        assertEquals(error,"Cannot have empty reservation date");
    }

    @Test
    public void testCreateReservationNoEndDate(){
        Reservation r = null;
        String error = "";
        try{
            r = service.createReservation(startDate,null,ITEM_INSTANCE_KEY,CUSTOMER_KEY,null);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(r);
        assertEquals(error,"Cannot have empty pickup day");
    }

    @Test
    public void testCreateReservationNullItemInstance(){
        Reservation r = null;
        String error = "";
        try{
            r = service.createReservation(startDate,endDate,null,CUSTOMER_KEY,null);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(r);
        assertEquals(error,"Item instance serial number cannot be missing");
    }

    @Test
    public void testCreateReservationMissingItemInstance(){
        Reservation r = null;
        String error = "";
        try{
            r = service.createReservation(startDate,endDate,ITEM_INSTANCE_KEY+1,CUSTOMER_KEY,null);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(r);
        assertEquals(error,"Item instance does not exist");
    }

    @Test
    public void testCreateReservationNullCustomer(){
        Reservation r = null;
        String error = "";
        try{
            r = service.createReservation(startDate,endDate,ITEM_INSTANCE_KEY,null,null);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(r);
        assertEquals(error,"Need to have a customer for a reservation");
    }

    @Test
    public void testCreateReservationMissingCustomer(){
        Reservation r = null;
        String error = "";
        try{
            r = service.createReservation(startDate,endDate,ITEM_INSTANCE_KEY,CUSTOMER_KEY+1,null);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(r);
        assertEquals(error,"Invalid customer provided");
    }
    @Test
    public void testCreateReservationPickupAfterStart(){
        Reservation r = null;
        String error = "";
        try{
            r = service.createReservation(Date.valueOf(startDate.toLocalDate().plusMonths(3)),endDate,ITEM_INSTANCE_KEY,CUSTOMER_KEY,null);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(r);
        assertEquals(error,"Cannot have pickup date before reservation date");
    }
    @Test
    public void testCreateReservationTooManyReservations(){
        Reservation r = null;
        String error = "";
        try{
            r = service.createReservation(startDate,endDate,ITEM_INSTANCE_KEY,CUSTOMER_GREEDY_KEY,null);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(r);
        assertEquals(error,"This customer already has the maximum number of loans");
    }
    //END CREATE RESERVATION TESTS

    //START UPDATE RESERVATION TESTS
    @Test
    public void testUpdateReservationValid(){
        Reservation r = null;
        try{
            r = service.updateReservation(RESERVATION_KEY, newStartDate,newEndDate,CUSTOMER_KEY+10, ITEM_INSTANCE_KEY+10);
        }
        catch (Exception e){
            fail();
        }
        assertNotNull(r);
        assertEquals(r.getId(), RESERVATION_KEY);
        assertEquals(r.getDateReserved(),newStartDate);
        assertEquals(r.getPickupDay(),newEndDate);
        assertEquals(r.getCustomer().getId(),CUSTOMER_KEY +10);
        assertEquals(r.getItemInstance().getSerialNum(),ITEM_INSTANCE_KEY +10);
    }

    @Test
    public void testUpdateReservationNoStartDate(){
        Reservation r = null;
        try{
            r = service.updateReservation(RESERVATION_KEY, null,newEndDate,CUSTOMER_KEY+10, ITEM_INSTANCE_KEY+10);
        }
        catch (Exception e){
            fail();
        }
        assertNotNull(r);
        assertEquals(r.getId(), RESERVATION_KEY);
        assertEquals(r.getDateReserved(),startDate);
        assertEquals(r.getPickupDay(),newEndDate);
        assertEquals(r.getCustomer().getId(),CUSTOMER_KEY +10);
        assertEquals(r.getItemInstance().getSerialNum(),ITEM_INSTANCE_KEY +10);
    }

    @Test
    public void testUpdateReservationNoEndDate(){
        Reservation r = null;
        try{
            r = service.updateReservation(RESERVATION_KEY, newStartDate,null,CUSTOMER_KEY+10, ITEM_INSTANCE_KEY+10);
        }
        catch (Exception e){
            fail();
        }
        assertNotNull(r);
        assertEquals(r.getId(), RESERVATION_KEY);
        assertEquals(r.getDateReserved(),newStartDate);
        assertEquals(r.getPickupDay(),endDate);
        assertEquals(r.getCustomer().getId(),CUSTOMER_KEY +10);
        assertEquals(r.getItemInstance().getSerialNum(),ITEM_INSTANCE_KEY +10);
    }

    @Test
    public void testUpdateReservationNullItemInstance(){
        Reservation r = null;
        try{
            r = service.updateReservation(RESERVATION_KEY, newStartDate,newEndDate,CUSTOMER_KEY+10, null);
        }
        catch (Exception e){
            fail();
        }
        assertNotNull(r);
        assertEquals(r.getId(), RESERVATION_KEY);
        assertEquals(r.getDateReserved(),newStartDate);
        assertEquals(r.getPickupDay(),newEndDate);
        assertEquals(r.getCustomer().getId(),CUSTOMER_KEY +10);
        assertEquals(r.getItemInstance().getSerialNum(),ITEM_INSTANCE_KEY);
    }

    @Test
    public void testUpdateReservationMissingItemInstance(){
        Reservation r = null;
        String error = "";
        try{
            r = service.updateReservation(RESERVATION_KEY, newStartDate,newEndDate,CUSTOMER_KEY+10, ITEM_INSTANCE_KEY+1);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(r);
        assertEquals(error,"Cannot find item instance to update reservation to");
    }

    @Test
    public void testUpdateReservationNullCustomer(){
        Reservation r = null;
        try{
            r = service.updateReservation(RESERVATION_KEY, newStartDate,newEndDate,null, ITEM_INSTANCE_KEY+10);
        }
        catch (Exception e){
            fail();
        }
        assertNotNull(r);
        assertEquals(r.getId(), RESERVATION_KEY);
        assertEquals(r.getDateReserved(),newStartDate);
        assertEquals(r.getPickupDay(),newEndDate);
        assertEquals(r.getCustomer().getId(),CUSTOMER_KEY);
        assertEquals(r.getItemInstance().getSerialNum(),ITEM_INSTANCE_KEY +10);
    }

    @Test
    public void testUpdateReservationMissingCustomer(){
        Reservation r = null;
        String error = "";
        try{
            r = service.updateReservation(RESERVATION_KEY, newStartDate,newEndDate,CUSTOMER_KEY+1, ITEM_INSTANCE_KEY+10);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(r);
        assertEquals(error,"Cannot find person to update reservation to");
    }

    @Test
    public void testUpdateReservationNullReservation(){
        Reservation r = null;
        String error = "";
        try{
            r = service.updateReservation(null, newStartDate,newEndDate,CUSTOMER_KEY+10, ITEM_INSTANCE_KEY+10);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(r);
        assertEquals(error,"Reservation id cannot be null");
    }

    @Test
    public void testUpdateReservationMissingReservation(){
        Reservation r = null;
        String error = "";
        try{
            r = service.updateReservation(RESERVATION_KEY+1, newStartDate,newEndDate,CUSTOMER_KEY+10, ITEM_INSTANCE_KEY+10);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(r);
        assertEquals(error,"Reservation cannot be found");
    }

    @Test
    public void testUpdateReservationPickupAfterStartStartProvided(){
        Reservation r = null;
        String error = "";
        try{
            r = service.updateReservation(RESERVATION_KEY,Date.valueOf(startDate.toLocalDate().plusMonths(3)),endDate,ITEM_INSTANCE_KEY,CUSTOMER_KEY);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(r);
        assertEquals(error,"Cannot have pickup date before reservation date");
    }

    @Test
    public void testUpdateReservationPickupAfterStartPickupProvided(){
        Reservation r = null;
        String error = "";
        try{
            r = service.updateReservation(RESERVATION_KEY,startDate,Date.valueOf(endDate.toLocalDate().minusMonths(3)),ITEM_INSTANCE_KEY,CUSTOMER_KEY);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(r);
        assertEquals(error,"Cannot have pickup date before reservation date");
    }

    @Test
    public void testUpdateReservationTooManyReservations(){
        Reservation r = null;
        String error = "";
        try{
            r = service.updateReservation(RESERVATION_KEY,startDate,endDate,CUSTOMER_GREEDY_KEY,ITEM_INSTANCE_KEY);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(r);
        assertEquals(error,"This customer already has the maximum number of loans");
    }


    //END UPDATE RESERVATION TESTS

    //START DELETE RESERVATION TESTS
    @Test
    public void testDeleteReservationValid(){
        try{
            service.deleteReservation(RESERVATION_KEY,CUSTOMER_KEY);
        }
        catch (Exception e){
            fail();
        }
    }

    @Test
    public void testDeleteReservationNullCustomer(){
        String error = "";
        try{
            service.deleteReservation(RESERVATION_KEY,null);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertEquals(error,"Cannot authorize customer to delete loan");
    }

    @Test
    public void testDeleteReservationNotExistingCustomer(){
        String error = "";
        try{
            service.deleteReservation(RESERVATION_KEY,CUSTOMER_KEY+1);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertEquals(error,"Owner of loan does not match customer in request");
    }

    @Test
    public void testDeleteReservationNullReservation(){
        String error = "";
        try{
            service.deleteReservation(null,CUSTOMER_KEY);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertEquals(error,"Cannot find reservationId to delete");
    }

    @Test
    public void testDeleteReservationMissingReservation(){
        String error = "";
        try{
            service.deleteReservation(RESERVATION_KEY + 1,CUSTOMER_KEY);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertEquals(error,"Cannot find reservation to delete");
    }
    //END DELETE RESERVATION TESTS

}

