package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.model.*;
import ca.mcgill.ecse321.library.service.Exception.LoanException;
import ca.mcgill.ecse321.library.service.Exception.NotFoundException;
import ca.mcgill.ecse321.library.service.Exception.ReservationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private LibrarianRepository librarianRepository;
    @Autowired
    private ItemInstanceRepository itemInstanceRepository;
    @Autowired
    private LibraryManagementSystemRepository libraryManagementSystemRepository;
    @Transactional
    public Reservation createReservation(Date dateReserved, Date pickupDay, Integer itemInstanceId, Integer customerId, Integer librarianId, Integer systemId){
        Reservation r = new Reservation();

        if(systemId == null){
            throw new ReservationException("Cannot have null systemId");
        }
        LibraryManagementSystem s = libraryManagementSystemRepository.findLibraryManagementSystemById(systemId);
        if(s == null){
            throw new ReservationException("System not found");
        }
        r.setSystem(s);
        s.getReservationList().add(r);
        if(dateReserved == null){
            throw new ReservationException("Cannot have empty reservation date");
        }
        r.setDateReserved(dateReserved);
        if(pickupDay == null){
            throw new ReservationException("Cannot have empty pickup day");
        }
        r.setPickupDay(pickupDay);
        if(customerId == null) {
            throw new ReservationException("Need to have a customer for a reservation");
        }
        if(librarianId != null){
            //librarian creates reservation for customer
            if(librarianRepository.findPersonRoleById(librarianId) == null){
                throw new ReservationException("Invalid librarian creating the reservation");
            }
        }
        Customer c = (Customer) customerRepository.findPersonRoleById(customerId);
        if(c == null){
            throw new ReservationException("Invalid customer provided");
        }
        r.setCustomer(c);

        if(itemInstanceId == null){
            throw new ReservationException("Item instance serial number cannot be missing");
        }
        ItemInstance i = itemInstanceRepository.findItemInstanceBySerialNum(itemInstanceId);
        if(i == null){
            throw new ReservationException("Item instance does not exist");
        }
        r.setItemInstance(i);
        //TODO add in check for item already on reservation
        r.setId(generateId((List<Reservation>) reservationRepository.findAll()));
        reservationRepository.save(r);
        libraryManagementSystemRepository.save(s);
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
    @Transactional
    public Reservation getReservation(Integer id, Integer customerId){
        if(id == null){
            throw new ReservationException("Reservation id cannot be null");
        }
        Reservation r = reservationRepository.findReservationById(id);
        if(r == null){
            throw new NotFoundException("Reservation with given id cannot be found");
        }
        if(customerId == null){
            throw new ReservationException("Customer id cannot be null");
        }
        Customer c = r.getCustomer();
        if(c == null){
            throw new ReservationException("Reservation has no customer cannot return properly");
        }
        if(c.getId() != customerId ){
            throw new ReservationException("Customer id does not match customer id in reservation");
        }
        return r;
    }

    @Transactional
    public List<Reservation> getAllReservations(Integer customerId){
        if(customerId == null){
            throw new ReservationException("Customer id cannot be null");
        }
        Customer c = (Customer) customerRepository.findPersonRoleById(customerId);
        return reservationRepository.findByCustomer(c);
    }

    @Transactional
    public Reservation updateReservation(Integer id,Date startDate, Date endDate, Integer customerId,
                                         Integer itemInstanceId, Integer systemId){
        if(id == null){
            throw new ReservationException("Reservation id cannot be null");
        }
        Reservation r = reservationRepository.findReservationById(id);
        if(r == null){
            throw new NotFoundException("Reservation cannot be found");
        }
        if(startDate != null){
            //TODO add validation if date is before now
            r.setDateReserved(startDate);
        }
        if(endDate != null){
            r.setPickupDay(endDate);
        }
        if(customerId != null){
            Customer c = (Customer) customerRepository.findPersonRoleById(customerId);
            if(c == null){
                throw new ReservationException("Cannot find person to update reservation to");
            }
            r.setCustomer(c);
        }
        if(itemInstanceId != null){
            ItemInstance i = itemInstanceRepository.findItemInstanceBySerialNum(itemInstanceId);
            if(i == null){
                throw new ReservationException("Cannot find item instance to update reservation to");
            }
            r.setItemInstance(i);
        }
        if(systemId != null){
            LibraryManagementSystem s = libraryManagementSystemRepository.findLibraryManagementSystemById(systemId);
            if(s == null){
                throw new ReservationException("Cannot find LMS to update reservation to");
            }
            r.setSystem(s);
        }
        reservationRepository.save(r);
        return r;
    }

    public void deleteReservation(Integer id, Integer customerId){
        if(id == null){
            throw new ReservationException("Cannot find reservationId to delete");
        }
        Reservation reservation = reservationRepository.findReservationById(id);
        if(reservation == null){
            throw new ReservationException("Cannot find reservation to delete");
        }
        if(customerId == null){
            throw new ReservationException("Cannot authorize customer to delete loan");
        }
        Customer customer = (Customer) customerRepository.findPersonRoleById(customerId);
        if(customer == null){
            throw new LoanException("Owner of loan does not match customer in request");
        }
        reservationRepository.delete(reservation);
        reservation =  null;
    }
}
