package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dto.LoanDTO;
import ca.mcgill.ecse321.library.model.Loan;
import ca.mcgill.ecse321.library.service.LoanService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin("*")
public class LoanRestController {

    @Autowired
    private LoanService service;
    @PostMapping(value= {"/loan","/loan/"})
    public LoanDTO createLoan(@RequestParam(value = "librarianId",required = false) Integer librarianId, @RequestBody JsonBody body){
        Loan loan = service.createLoan(body.getCheckedOut(), body.getItemId(), body.getCustomerId(),
                body.getReturnDate() ,librarianId);
        return convertToDto(loan);
    }

    @DeleteMapping(value={"/loan/{id}","/loan/{id}/"})
    public void deleteLoan(@PathVariable Integer id, @RequestParam(value = "customerId",required = false) Integer customerId){
        service.deleteLoan(id,customerId);
    }

    //CONVERT TO DTO

    private LoanDTO convertToDto(Loan loan){
        if (loan == null) {
            throw new IllegalArgumentException("There is no such loan!");
        }
        LoanDTO loanDTO = new LoanDTO(loan.getId());
        loanDTO.setCheckedOut(loan.getCheckedOut());
        loanDTO.setCustomer(loan.getCustomer());
        loanDTO.setItemInstance(loan.getItemInstance());
        loanDTO.setReturnDate(loan.getReturnDate());
        return loanDTO;
    }
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private static class JsonBody{
        private Integer itemId;
        private Integer customerId;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private Date checkedOut;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private Date returnDate;

        public Integer getItemId() {
            return itemId;
        }

        public void setItemId(Integer itemId) {
            this.itemId = itemId;
        }

        public Integer getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Integer customerId) {
            this.customerId = customerId;
        }

        public Date getCheckedOut() {
            return checkedOut;
        }

        public void setCheckedOut(Date checkedOut) {
            this.checkedOut = checkedOut;
        }

        public Date getReturnDate() {
            return returnDate;
        }

        public void setReturnDate(Date returnDate) {
            this.returnDate = returnDate;
        }
    }
}
