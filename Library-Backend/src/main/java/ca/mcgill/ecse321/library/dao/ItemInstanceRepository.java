package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.CheckableItem;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.library.model.ItemInstance;

import java.util.List;

public interface ItemInstanceRepository extends CrudRepository<ItemInstance, String> {
    ItemInstance findItemInstanceBySerialNum(int serialNum);
    List<ItemInstance> findByCheckableItem(CheckableItem checkableItem);
}
