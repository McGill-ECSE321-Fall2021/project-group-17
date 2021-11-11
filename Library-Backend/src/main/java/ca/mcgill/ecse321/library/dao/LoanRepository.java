package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Customer;
import ca.mcgill.ecse321.library.model.ItemInstance;
import ca.mcgill.ecse321.library.model.Loan;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface LoanRepository extends CrudRepository<Loan, Integer>  {
    Loan findLoanById(Integer id);
    List<Loan> findLoanByCheckedOut(Date checkedOut);
    List<Loan> findLoanByReturnDate(Date returnDate);
    Loan findByItemInstance(ItemInstance itemInstance);
    List<Loan> findLoansByCustomer(Customer customer);
}
