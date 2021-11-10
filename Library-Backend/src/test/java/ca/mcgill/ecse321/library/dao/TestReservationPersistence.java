package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestReservationPersistence {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ItemInstanceRepository itemInstanceRepository;
    @Autowired
    private CheckableItemRepository checkableItemRepository;
    @AfterEach
    public void clearDatabase() {
        customerRepository.deleteAll();
        itemInstanceRepository.deleteAll();
        checkableItemRepository.deleteAll();

        reservationRepository.deleteAll();
    }
    @Test
    public void testPersistAndLoadReservation() {
        CheckableItem checkableItem = new Music(1234,"My Brilliant Friend",
                java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12)),"Victoria","Sony");
        checkableItemRepository.save(checkableItem);

        ItemInstance itemInstance = new ItemInstance(checkableItem);
        itemInstanceRepository.save(itemInstance);

        Customer customer = new Customer(0, null, 0, null, null, null);
        customerRepository.save(customer);

        int serialNum = itemInstance.getSerialNum();


        Date dateReserved = java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        Date pickupDay = java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,16));
        Reservation reservation = new Reservation(0, dateReserved, pickupDay, itemInstance, customer);
        reservationRepository.save(reservation);
        int id = reservation.getId();
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

        CheckableItem checkableItem1 = new Book(100, "My Brilliant Friend", Date.valueOf(LocalDate.of(2021, Month.OCTOBER, 12)), "Victoria", "Harper", "horror");
        checkableItemRepository.save(checkableItem1);


        ItemInstance item1 = new ItemInstance(checkableItem1);
        itemInstanceRepository.save(item1);
        int serialnum = item1.getSerialNum();
        CheckableItem checkableItem2 = new Book(5679, "The Lost Child", Date.valueOf(LocalDate.of(2021, Month.OCTOBER, 12)), "Victoria", "Harper", "horror");
        checkableItemRepository.save(checkableItem2);

        ItemInstance item2 = new ItemInstance(checkableItem2);

        itemInstanceRepository.save(item2);
        int serialnum2 =item2.getSerialNum();
        Customer c = new Customer();
        customerRepository.save(c);
        int customerId = c.getId();


        Date dateReserved = java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER, 12));
        Date pickupDay = java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER, 17));
        Reservation r1 = new Reservation(0, dateReserved, pickupDay, item1, c);
        Reservation r2 = new Reservation(0, dateReserved, pickupDay, item2, c);
        reservationRepository.save(r1);
        reservationRepository.save(r2);

        Integer id1 = r1.getId();
        Integer id2 = r2.getId();

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
}
