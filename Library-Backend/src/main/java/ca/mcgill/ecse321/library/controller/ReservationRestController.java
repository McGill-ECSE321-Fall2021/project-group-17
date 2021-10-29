package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dto.ReservationDTO;
import ca.mcgill.ecse321.library.model.Reservation;
import ca.mcgill.ecse321.library.service.ReservationService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.sql.Date;

@CrossOrigin(origins = "*")
@RestController
public class ReservationRestController {
    @Autowired
    private ReservationService service;

    @PostMapping({"/reservation/", "/reservation"})
    public ReservationDTO createReservation(@RequestBody JsonBody body){
        Reservation reservation = service.createReservation(body.getDateReserved(), body.getPickupDay(), body.getItemInstanceId(), body.getCustomerId());
        return convertToDTO(reservation);
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
