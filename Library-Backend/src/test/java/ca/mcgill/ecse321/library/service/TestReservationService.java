
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
    private static final int ITEM_INSTANCE_KEY = 3;
    private static final int LIBRARIAN_KEY = 5;
    private static final Customer CUSTOMER = new Customer(2,null,0,null,null,null);

    private static final Date startDate = Date.valueOf("2021-10-11");
    private static final Date endDate = Date.valueOf("2021-10-31");

    @BeforeEach
    public void setMockOutput() {
        lenient().when(reservationRepository.findReservationById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(RESERVATION_KEY)) {
                Reservation reservation = new Reservation();
                reservation.setId(RESERVATION_KEY);
                Customer customer = new Customer();
                customer.setId(CUSTOMER_KEY);
                reservation.setCustomer(customer);
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
            } else {
                return null;
            }
        });
        lenient().when(customerRepository.findPersonRoleById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(CUSTOMER_KEY)) {
                Customer customer = new Customer();
                customer.setId(CUSTOMER_KEY);
                return customer;
            } else {
                return null;
            }
        });
        lenient().when(itemInstanceRepository.findItemInstanceBySerialNum(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(ITEM_INSTANCE_KEY)) {
                ItemInstance itemInstance = new ItemInstance();
                itemInstance.setSerialNum(ITEM_INSTANCE_KEY);
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
    //END CREATE RESERVATION TESTS

}

