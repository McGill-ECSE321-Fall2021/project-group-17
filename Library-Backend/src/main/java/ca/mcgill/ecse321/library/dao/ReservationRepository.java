package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Customer;
import ca.mcgill.ecse321.library.model.ItemInstance;
import ca.mcgill.ecse321.library.model.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
    Reservation findReservationById(Integer id);
    List<Reservation> findReservationByDateReserved(Date dateReserved);
    List<Reservation> findReservationByPickupDay(Date pickupDay);
    Reservation findByItemInstance(ItemInstance itemInstance);
    List<Reservation> findByCustomer(Customer customer);
}
