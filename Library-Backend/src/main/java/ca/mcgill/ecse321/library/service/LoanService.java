package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.CustomerRepository;
import ca.mcgill.ecse321.library.dao.ItemInstanceRepository;
import ca.mcgill.ecse321.library.dao.LibraryManagementSystemRepository;
import ca.mcgill.ecse321.library.dao.LoanRepository;
import ca.mcgill.ecse321.library.model.Customer;
import ca.mcgill.ecse321.library.model.ItemInstance;
import ca.mcgill.ecse321.library.model.LibraryManagementSystem;
import ca.mcgill.ecse321.library.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
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
    public Loan createLoan(Date start, Integer itemId, Integer customerId, Integer systemId){
        Loan loan = new Loan();

        if(systemId != null){
            LibraryManagementSystem system = systemRepository.findLibraryManagementSystemById(systemId);
            loan.setSystem(system);
        }
        else{
            loan.setId(1);
        }

        if(itemId != null){
            ItemInstance instance = itemInstanceRepository.findItemInstanceBySerialNum(itemId);
            loan.setItemInstance(instance);
        }
        if(customerId != null){
            Customer customer = (Customer) customerRepository.findPersonRoleById(customerId);
            loan.setCustomer(customer);
        }
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
}
