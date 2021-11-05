package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.CustomerRepository;
import ca.mcgill.ecse321.library.dao.ItemInstanceRepository;
import ca.mcgill.ecse321.library.dao.LibraryManagementSystemRepository;
import ca.mcgill.ecse321.library.dao.LoanRepository;
import ca.mcgill.ecse321.library.model.Customer;
import ca.mcgill.ecse321.library.model.ItemInstance;
import ca.mcgill.ecse321.library.model.LibraryManagementSystem;
import ca.mcgill.ecse321.library.model.Loan;
import ca.mcgill.ecse321.library.service.Exception.LoanException;
import ca.mcgill.ecse321.library.service.Exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
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
}
