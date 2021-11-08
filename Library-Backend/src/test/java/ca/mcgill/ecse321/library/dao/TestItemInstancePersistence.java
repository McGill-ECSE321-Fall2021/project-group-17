package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.CheckableItem;
import ca.mcgill.ecse321.library.model.ItemInstance;
import ca.mcgill.ecse321.library.model.Music;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestItemInstancePersistence {
    @Autowired
    private ItemInstanceRepository itemInstanceRepository;
    @Autowired
    private CheckableItemRepository checkableItemRepository;
    @AfterEach
    public void clearDatabase() {
        itemInstanceRepository.deleteAll();
        checkableItemRepository.deleteAll();

    }
    @Test
    public void testPersistAndLoadItemInstance() {
        CheckableItem checkableItem = new Music(1234,"My Brilliant Friend",
                java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12)),"Victoria","Sony");
        checkableItemRepository.save(checkableItem);
        ItemInstance itemInstance = new ItemInstance(checkableItem);
        itemInstanceRepository.save(itemInstance);
        int serialNum = itemInstance.getSerialNum();
        itemInstance = itemInstanceRepository.findItemInstanceBySerialNum(serialNum);
        assertNotNull(itemInstance);
        assertEquals(serialNum, itemInstance.getSerialNum());
        assertEquals(checkableItem.getId(), itemInstance.getCheckableItem().getId());
    }

    @Test
    public void testFindItemInstanceByCheckableItem() {

        CheckableItem checkableItem = new Music(1234,"My Brilliant Friend",
                java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12)),"Victoria","Sony");
        checkableItemRepository.save(checkableItem);
        ItemInstance itemInstance1 = new ItemInstance(checkableItem);
        ItemInstance itemInstance2 = new ItemInstance(checkableItem);
        itemInstanceRepository.save(itemInstance1);
        itemInstanceRepository.save(itemInstance2);
        List<ItemInstance> itemInstances = itemInstanceRepository.findByCheckableItem(checkableItem);
        int serialNum1 = itemInstance1.getSerialNum();
        int serialNum2 =  itemInstance2.getSerialNum();
        assertNotNull(itemInstances);
        itemInstance1 = itemInstances.get(0);
        assertEquals(serialNum1, itemInstance1.getSerialNum());
        assertEquals(checkableItem.getId(), itemInstance1.getCheckableItem().getId());
        itemInstance2 = itemInstances.get(1);
        assertEquals(serialNum2, itemInstance2.getSerialNum());
        assertEquals(checkableItem.getId(), itemInstance2.getCheckableItem().getId());
    }
}
