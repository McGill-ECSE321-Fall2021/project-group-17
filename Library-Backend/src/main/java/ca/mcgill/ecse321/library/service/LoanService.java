package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.CustomerRepository;
import ca.mcgill.ecse321.library.dao.ItemInstanceRepository;
import ca.mcgill.ecse321.library.dao.LoanRepository;
import ca.mcgill.ecse321.library.dto.LoanDTO;
import ca.mcgill.ecse321.library.model.*;
import ca.mcgill.ecse321.library.service.Exception.LoanException;
import ca.mcgill.ecse321.library.service.Exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private ItemInstanceRepository itemInstanceRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    /**
     * Assumes if no end date is given that it defaults to 21 days
     * @param start
     * @param itemId
     * @param customerId
     * @param returnDate
     * @return loan with parameters given
     */
    public Loan createLoan(Date start, Integer itemId, Integer customerId, Date returnDate, Integer librarianId){
        Loan loan = new Loan();

        //system.getLoanList().add(loan);

        if(itemId != null){
            ItemInstance instance = itemInstanceRepository.findItemInstanceBySerialNum(itemId);
            loan.setItemInstance(instance);
        }
        if(customerId != null){
            Customer customer = (Customer) customerRepository.findPersonRoleById(customerId);
            loan.setCustomer(customer);
        }
        if(start == null){
            throw new LoanException("Cannot create loan with no start date");
        }
        if(returnDate == null){
            returnDate = Date.valueOf(LocalDate.parse(start.toString()).plusDays(21));
        }
        if(start.after(returnDate)){
            throw new LoanException("Cannot create loan with start date after end date");
        }
        loan.setCheckedOut(start);
        loan.setReturnDate(returnDate);

        List<Loan> loans = (List<Loan>) loanRepository.findAll();
        loanRepository.save(loan);
        return loan;
    }

    /**
     * Used to return item, which is equivalent to returning an item
     * @param loanId
     * @param customerId
     */
    @Transactional
    public void deleteLoan(Integer loanId, Integer customerId){
        if(loanId == null){
            throw new LoanException("Cannot find loanId to delete");
        }
        Loan loan = loanRepository.findLoanById(loanId);
        if(loan == null){
            throw new NotFoundException("Cannot find loan to delete");
        }
        if(customerId == null){
            throw new LoanException("Cannot authorize customer to delete loan");
        }
        Customer customer = (Customer) customerRepository.findPersonRoleById(customerId);
        if(customer == null){
            throw new LoanException("Owner of loan does not match customer in request");
        }
        loanRepository.delete(loan);
        loan = null;
    }

    /**
     * Update loan
     * @param id
     * @param checkedOut
     * @param returnDate
     * @param customerId
     * @param itemInstanceId
     * @return updated loan
     */

    @Transactional
    public Loan updateLoan(Integer id,Date checkedOut, Date returnDate, Integer customerId, Integer itemInstanceId){
        if(id == null){
            throw new LoanException("Loan id cannot be null");
        }
        Loan r = loanRepository.findLoanById(id);
        if(r == null){
            throw new NotFoundException("Loan cannot be found");
        }
        if(checkedOut != null){
            //TODO add validation if date is before now
            r.setCheckedOut(checkedOut);
        }
        if(returnDate != null){
            r.setReturnDate(returnDate);
        }
        if(customerId != null){
            Customer c = (Customer) customerRepository.findPersonRoleById(customerId);
            if(c == null){
                throw new LoanException("Cannot find person to update loan to");
            }
            r.setCustomer(c);
        }
        if(itemInstanceId != null){
            ItemInstance i = itemInstanceRepository.findItemInstanceBySerialNum(itemInstanceId);
            if(i == null){
                throw new LoanException("Cannot find item instance to update loan to");
            }
            r.setItemInstance(i);
        }
        loanRepository.save(r);
        return r;
    }



    /**
     * view active loans
     * @param id
     * @return all loans of customer with the id
     */

    //TODO check if loan is active or not
    @Transactional
    public List<LoanDTO> viewActiveLoans(Integer id){

        if(id == null){
            throw new LoanException("Cannot find customer");
        }

        PersonRole customer1 = customerRepository.findPersonRoleById(id);

        if(customer1 == null) {
            throw new LoanException("Customer not found");
        }

        List<Loan> customerLoans = (List<Loan>) loanRepository.findAll();

        List<LoanDTO> customerLoansDTO = new ArrayList<>();
        for (Loan loan: customerLoans){
            LoanDTO loanDTO = LoanService.loantoDTO(loan);
            customerLoansDTO.add(loanDTO);
        }
        return customerLoansDTO;
    }

    /**
     * View loan return date
     * @param loanID
     * @param customerID
     * @return loan of the customer with customerID and the loanID
     */
    @Transactional
    public Loan viewLoanReturnDate(Integer loanID, Integer customerID){
        if(loanID == null){
            throw new LoanException("Please provide a valid loan ID");
        }
        if(customerID == null){
            throw new LoanException("Please provide a valid customer ID");
        }

        Customer customer = (Customer) customerRepository.findPersonRoleById(customerID);

        if(customer == null){
            throw new LoanException("Customer does not exist");
        }

        Loan loan = loanRepository.findLoanById(loanID);

        if(loan == null){
            throw new LoanException("Loan does not exist");
        }

        return loan;
    }


    /**
     * turn loan to loanDTO
     * @param loan
     * @return loan DTO
     */
    public static LoanDTO loantoDTO(Loan loan){
        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setCheckedOut(loan.getCheckedOut());
        loanDTO.setCustomer(loan.getCustomer());
        loanDTO.setId(loan.getId());
        loanDTO.setItemInstance(loan.getItemInstance());
        loanDTO.setReturnDate(loan.getReturnDate());
        return loanDTO;
    }


}
