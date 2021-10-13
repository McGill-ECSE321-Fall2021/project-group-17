package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.CheckableItem;
import ca.mcgill.ecse321.library.model.Item;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.library.model.ItemInstance;

public interface ItemInstanceRepository extends CrudRepository<ItemInstance, Integer> {
    ItemInstance findItemInstanceById(Integer id);

    ItemInstance findByCheckableItem(CheckableItem checkableItem);
}
