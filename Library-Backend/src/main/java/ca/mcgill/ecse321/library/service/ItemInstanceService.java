package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.CheckableItemRepository;
import ca.mcgill.ecse321.library.dao.ItemInstanceRepository;
import ca.mcgill.ecse321.library.dao.LoanRepository;
import ca.mcgill.ecse321.library.dao.ReservationRepository;
import ca.mcgill.ecse321.library.model.*;
import ca.mcgill.ecse321.library.service.Exception.ItemInstanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ItemInstanceService {
    @Autowired
    private ItemInstanceRepository itemInstanceRepository;
    @Autowired
    private CheckableItemRepository checkableItemRepository;
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    /**
     *
     * @param checkableItemId
     * @return
     */
    @Transactional
    public ItemInstance createItemInstance(Integer checkableItemId) {
        ItemInstance itemInstance = new ItemInstance();

        if (checkableItemId != null) {
            Item item = checkableItemRepository.findItemById(checkableItemId);
            if (item instanceof CheckableItem) {
                itemInstance.setCheckableItem((CheckableItem) item);
            }
        }

        itemInstanceRepository.save(itemInstance);
        return itemInstance;
    }

    @Transactional
    public ItemInstance getItemInstance(int serialNum) {
        ItemInstance itemInstance = itemInstanceRepository.findItemInstanceBySerialNum(serialNum);
        if(itemInstance == null){
            throw new ItemInstanceException("There exist no item instance by that Id");
        }
        return itemInstanceRepository.findItemInstanceBySerialNum(serialNum);
    }

    @Transactional
    public List<ItemInstance> viewInventory() {
        return (List<ItemInstance>) itemInstanceRepository.findAll();
    }

    @Transactional
    public void deleteItemInstance(int serialNum) {
        ItemInstance itemInstance = itemInstanceRepository.findItemInstanceBySerialNum(serialNum);
        if(itemInstance == null){
            throw new ItemInstanceException("There exists no item instance by that Id");
        }

        Loan loan = loanRepository.findByItemInstance(itemInstance);

        if (loan != null) {
            loanRepository.delete(loan);
        }

        Reservation reservation = reservationRepository.findByItemInstance(itemInstance);

        if (reservation != null) {
            reservationRepository.delete(reservation);
        }

        itemInstanceRepository.delete(itemInstance);
    }
}
