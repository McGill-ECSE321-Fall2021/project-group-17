package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dto.LoanDTO;
import ca.mcgill.ecse321.library.dto.ReservationDTO;
import ca.mcgill.ecse321.library.model.Reservation;
import ca.mcgill.ecse321.library.service.ReservationService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class ReservationRestController {
    @Autowired
    private ReservationService service;

    @PostMapping({"/reservation/", "/reservation"})
    public ReservationDTO createReservation(@RequestParam(value = "librarianId",required = false) Integer librarianId, @RequestBody JsonBody body){
        Reservation reservation = service.createReservation(body.getDateReserved(), body.getPickupDay(),
                body.getItemInstanceId(), body.getCustomerId(), librarianId);
        return convertToDTO(reservation);
    }
    @GetMapping({"/reservation","/reservation/"})
    public List<ReservationDTO> getAllReservations(@RequestParam("customerId") Integer customerId){
        return service.getAllReservations(customerId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    @GetMapping({"/reservation/{id}","/reservation/{id}"})
    public ReservationDTO getAllReservations(@PathVariable Integer id, @RequestParam("customerId") Integer customerId){
        return convertToDTO(service.getReservation(id,customerId));
    }

    @PatchMapping({"/reservation/{id}","/reservation/{id}/"})
    public ReservationDTO updateReservation(@PathVariable Integer id, @RequestBody JsonBody body){
        return convertToDTO(service.updateReservation(id,body.getDateReserved(),body.getPickupDay(), body.getCustomerId(),
                body.getItemInstanceId()));
    }

    @DeleteMapping({"/reservation/{id}","/reservation/{id}/"})
    public void deleteReservation(@PathVariable Integer id, @RequestParam("customerId") Integer customerId){
        service.deleteReservation(id, customerId);
    }

    @GetMapping("/reservation/active")
    public List<ReservationDTO> getAllActiveReservations() {
        List<Reservation> reservations = service.getAllActiveReservations();

        List<ReservationDTO> reservationDTOS = new ArrayList<>();

        for (Reservation reservation: reservations) {
            reservationDTOS.add(convertToDTO(reservation));
        }

        return reservationDTOS;
    }

    private ReservationDTO convertToDTO(Reservation r){
        if(r == null){
            throw new IllegalArgumentException("Cannot create Reservation");
        }
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setCustomer(r.getCustomer());
        reservationDTO.setDateReserved(r.getDateReserved());
        reservationDTO.setId(r.getId());
        reservationDTO.setPickupDay(r.getPickupDay());
        reservationDTO.setItemInstance(r.getItemInstance());
        return reservationDTO;
    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private static class JsonBody{
        @JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private Date dateReserved;
        @JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private Date pickupDay;
        private Integer itemInstanceId;
        private Integer customerId;

        public Date getDateReserved() {
            return dateReserved;
        }

        public void setDateReserved(Date dateReserved) {
            this.dateReserved = dateReserved;
        }

        public Date getPickupDay() {
            return pickupDay;
        }

        public void setPickupDay(Date pickupDay) {
            this.pickupDay = pickupDay;
        }

        public Integer getItemInstanceId() {
            return itemInstanceId;
        }

        public void setItemInstanceId(Integer itemInstanceId) {
            this.itemInstanceId = itemInstanceId;
        }

        public Integer getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Integer customerId) {
            this.customerId = customerId;
        }


    }
}
