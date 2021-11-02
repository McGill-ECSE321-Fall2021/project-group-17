package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.CheckableItemRepository;
import ca.mcgill.ecse321.library.dao.ItemInstanceRepository;
import ca.mcgill.ecse321.library.dao.LibraryManagementSystemRepository;
import ca.mcgill.ecse321.library.model.CheckableItem;
import ca.mcgill.ecse321.library.model.Item;
import ca.mcgill.ecse321.library.model.ItemInstance;
import ca.mcgill.ecse321.library.model.LibraryManagementSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ItemInstanceService {
    @Autowired
    private ItemInstanceRepository itemInstanceRepository;
    @Autowired
    private CheckableItemRepository checkableItemRepository;
    @Autowired
    private LibraryManagementSystemRepository lmsRepository;

    @Transactional
    public ItemInstance createItemInstance(int serialNum, Integer checkableItemId, Integer systemId) {
        ItemInstance itemInstance = new ItemInstance();
        itemInstance.setSerialNum(serialNum);

        if (checkableItemId != null) {
            Item item = checkableItemRepository.findItemById(checkableItemId);
            if (item instanceof CheckableItem) {
                itemInstance.setCheckableItem((CheckableItem) item);
            }
        }

        if(systemId != null){
            LibraryManagementSystem system = lmsRepository.findLibraryManagementSystemById(systemId);
            itemInstance.setSystem(system);
        }

        itemInstanceRepository.save(itemInstance);
        return itemInstance;
    }

    @Transactional
    public ItemInstance getItemInstance(int serialNum) {
        return itemInstanceRepository.findItemInstanceBySerialNum(serialNum);
    }
}
