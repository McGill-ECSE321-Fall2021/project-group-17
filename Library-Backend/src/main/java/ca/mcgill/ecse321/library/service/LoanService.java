package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.CustomerRepository;
import ca.mcgill.ecse321.library.dao.ItemInstanceRepository;
import ca.mcgill.ecse321.library.dao.LibraryCardRepository;
import ca.mcgill.ecse321.library.dao.LoanRepository;
import ca.mcgill.ecse321.library.dto.LoanDTO;
import ca.mcgill.ecse321.library.model.*;
import ca.mcgill.ecse321.library.service.Exception.LoanException;
import ca.mcgill.ecse321.library.service.Exception.NotFoundException;
import ca.mcgill.ecse321.library.service.Exception.ReservationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private ItemInstanceRepository itemInstanceRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private LibraryCardRepository libraryCardRepository;

    @Transactional
    /**
     * Assumes if no end date is given that it defaults to 21 days
     * @param start
     * @param itemId
     * @param customerId
     * @param returnDate
     * @return loan with parameters given
     */
    public Loan createLoan(Date start, Integer itemId, Integer customerId, Date returnDate){
        Loan loan = new Loan();

        if(itemId == null){
            throw new LoanException("Need to have a item instance for a loan");
        }
        ItemInstance instance = itemInstanceRepository.findItemInstanceBySerialNum(itemId);
        if(instance == null){
            throw new LoanException("Invalid item instance provided");
        }
        if(loanRepository.findByItemInstance(instance) != null){
            throw new LoanException("Item is already on loan");
        }
        loan.setItemInstance(instance);

        if(customerId == null){
            throw new LoanException("Need to have a customer for a loan");
        }
        Customer customer = (Customer) customerRepository.findPersonRoleById(customerId);
        if(customer == null){
            throw new LoanException("Invalid customer provided");
        }
        if(loanRepository.findLoansByCustomer(customer) != null && loanRepository.findLoansByCustomer(customer).size() > 4){
            throw new ReservationException("This customer already has the maximum number of loans");
        }
        loan.setCustomer(customer);

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

        Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());

        if (currentDate.after(loan.getReturnDate())) {
            customer.setPenalty(customer.getPenalty() + 1);

            customerRepository.save(customer);
        }

        loanRepository.delete(loan);
        loan = null;
    }

    @Transactional
    public Loan createLoanWithLibraryCard(Integer libraryCardNum, Integer serialNum, String returnDate) {
        if (returnDate == null) {
            throw new LoanException("Return date is required.");
        }

        Date date = new Date(Calendar.getInstance().getTimeInMillis());

        try {
            date = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(returnDate).getTime());
        } catch (Exception e) {
            throw new LoanException("Incorrect date provided.");
        }

        if (libraryCardNum == null) {
            throw new LoanException("Libary Card number is required!");
        }

        LibraryCard libraryCard = libraryCardRepository.findLibraryCardById(libraryCardNum);

        if (libraryCard == null) {
            throw new LoanException("Incorrect library card number provided.");
        }

        Customer customer = libraryCard.getCustomer();

        if (customer == null) {
            throw new LoanException("Can't find customer from library card");
        }

        if (serialNum == null) {
            throw new LoanException("Item serial number is required to create a loan");
        }

        ItemInstance itemInstance = itemInstanceRepository.findItemInstanceBySerialNum(serialNum);

        if (itemInstance == null) {
            throw new LoanException("Can't find item from serial number");
        }

        Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());

        Loan loan = new Loan();
        loan.setCheckedOut(currentDate);
        loan.setReturnDate(date);
        loan.setItemInstance(itemInstance);
        loan.setCustomer(customer);

        loanRepository.save(loan);

        return loan;
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
        Loan loan = loanRepository.findLoanById(id);
        if(loan == null){
            throw new NotFoundException("Loan cannot be found");
        }
        if(checkedOut != null){
            if(checkedOut.toLocalDate().isAfter(loan.getReturnDate().toLocalDate())){
                throw new ReservationException("Cannot have pickup date before loan date");
            }
            loan.setCheckedOut(checkedOut);
        }
        if(returnDate != null){
            if(loan.getCheckedOut().toLocalDate().isAfter(returnDate.toLocalDate())){
                throw new LoanException("Cannot have pickup date before loan date");
            }
            loan.setReturnDate(returnDate);
        }
        if(customerId != null){
            Customer c = (Customer) customerRepository.findPersonRoleById(customerId);
            if(c == null){
                throw new LoanException("Cannot find person to update loan to");
            }
            if(loanRepository.findLoansByCustomer(c) != null && loanRepository.findLoansByCustomer(c).size() > 4){
                throw new ReservationException("This customer already has the maximum number of loans");
            }
            loan.setCustomer(c);
        }
        if(itemInstanceId != null){
            ItemInstance i = itemInstanceRepository.findItemInstanceBySerialNum(itemInstanceId);
            if(i == null){
                throw new LoanException("Cannot find item instance to update loan to");
            }
            if(loanRepository.findByItemInstance(i) != null){
                throw new LoanException("Item is already on loan");
            }
            loan.setItemInstance(i);
        }
        loanRepository.save(loan);
        return loan;
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
     * Get loan
     * @param loanID
     * @param customerID
     * @return loan of the customer with customerID and the loanID
     */
    @Transactional
    public Loan getLoan(Integer loanID, Integer customerID){
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

    @Transactional
    public List<LoanDTO> getAllActiveLoans(){
        List<Loan> loans = (List<Loan>) loanRepository.findAll();

        List<LoanDTO> loansDTO = new ArrayList<>();
        for (Loan loan: loans){
            if (loan.getItemInstance().getCheckableItem() == null) {
                continue;
            }
            LoanDTO loanDTO = LoanService.loantoDTO(loan);
            loansDTO.add(loanDTO);
        }
        return loansDTO;
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
