package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.CustomerRepository;
import ca.mcgill.ecse321.library.dao.ReservationRepository;
import ca.mcgill.ecse321.library.model.Customer;
import ca.mcgill.ecse321.library.model.ItemInstance;
import ca.mcgill.ecse321.library.model.Loan;
import ca.mcgill.ecse321.library.model.Reservation;
import ca.mcgill.ecse321.library.service.Exception.ReservationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Transactional
    public Reservation createReservation(Date dateReserved, Date pickupDay, Integer itemInstanceId, Integer customerId){
        Reservation r = new Reservation();
        if(dateReserved == null){
            throw new ReservationException("Cannot have empty reservation date");
        }
        r.setDateReserved(dateReserved);
        if(pickupDay == null){
            throw new ReservationException("Cannot have empty pickup day");
        }
        if(customerId == null){
            throw new ReservationException("Need to have a customer for a reservation");
        }
        r.setPickupDay(pickupDay);
        Customer c = (Customer) customerRepository.findPersonRoleById(customerId);
        if(c == null){
            throw new ReservationException("Invalid customer provided");
        }
        r.setCustomer(c);
        r.setId(generateId((List<Reservation>) reservationRepository.findAll()));
        reservationRepository.save(r);
        return r;
    }
    private int generateId(List<Reservation> reservations){
        int i = 0;
        reservations.sort((o1, o2) -> {
            if(o1.getId() == o2.getId()){
                return 0;
            }
            else if(o1.getId() < o2.getId()){
                return -1;
            }
            return 1;
        });
        for(Reservation reservation : reservations){
            if(i != reservation.getId()){
                return i;
            }
            i++;
        }
        return i + 1;
    }
}