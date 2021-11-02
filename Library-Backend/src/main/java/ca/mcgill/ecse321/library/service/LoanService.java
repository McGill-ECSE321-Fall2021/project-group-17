package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.CustomerRepository;
import ca.mcgill.ecse321.library.dao.ItemInstanceRepository;
import ca.mcgill.ecse321.library.dao.LibraryManagementSystemRepository;
import ca.mcgill.ecse321.library.dao.LoanRepository;
import ca.mcgill.ecse321.library.dto.LoanDTO;
import ca.mcgill.ecse321.library.model.*;
import ca.mcgill.ecse321.library.service.Exception.LoanException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private LibraryManagementSystemRepository systemRepository;
    @Autowired
    private ItemInstanceRepository itemInstanceRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    /**
     * Assumes if no end date is given that it defaults to 21 days
     */
    public Loan createLoan(Date start, Integer itemId, Integer customerId, Integer systemId, Date returnDate, Integer librarianId){
        Loan loan = new Loan();

        if(systemId == null){
            throw new LoanException("Cannot find valid LMS to create a loan in");
        }
        LibraryManagementSystem system = systemRepository.findLibraryManagementSystemById(systemId);
        loan.setSystem(system);
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
        loan.setId(generateId(loans));
        loanRepository.save(loan);
        return loan;
    }

    private int generateId(List<Loan> loans){
        int i = 0;
        loans.sort((o1, o2) -> {
            if(o1.getId() == o2.getId()){
                return 0;
            }
            else if(o1.getId() < o2.getId()){
                return -1;
            }
            return 1;
        });
        for(Loan l : loans){
            if(i != l.getId()){
                return i;
            }
            i++;
        }
        return i + 1;
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
            throw new LoanException("Cannot find loanId to delete");
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
     * view active loans
     * @param id
     */

    //TODO check if loan is active or not
    @Transactional
    public List<Loan> viewActiveLoans(Integer id){

        List<Loan> loans = new ArrayList<>();

        PersonRole customer1 = customerRepository.findPersonRoleById(id);

        if(customer1 == null) {
            throw new LoanException("Customer not found");
        }

        List<Loan> customerLoans = customer1.getSystem().getLoanList();

        List<LoanDTO> customerLoansDTO = new ArrayList<>();
        for (Loan loan: customerLoans){
            LoanDTO loanDTO = LoanService.loantoDTO(loan);
            customerLoansDTO.add(loanDTO);
        }
        return customerLoans;
    }

    /**
     * View loan return date
     * @param id
     */
    @Transactional
    public Loan viewLoanReturnDate(Integer id){
        if(id == null){
            throw new LoanException("Please provide a valid ID");
        }
        Loan loan = loanRepository.findLoanById(id);
        if(loan == null){
            throw new LoanException("Loan does not exist.");
        }

        return loan;
    }


    /**
     * turn loan to loanDTO
     * @param loan
     */
    public static LoanDTO loantoDTO(Loan loan){
        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setCheckedOut(loan.getCheckedOut());
        loanDTO.setCustomer(loan.getCustomer());
        loanDTO.setId(loan.getId());
        loanDTO.setItemInstance(loan.getItemInstance());
        loanDTO.setReturnDate(loan.getReturnDate());
        loanDTO.setSystem(loan.getSystem());
        return loanDTO;
    }


}
