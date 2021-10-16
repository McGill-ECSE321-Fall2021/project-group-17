package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.ItemInstance;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.library.model.CheckableItem;

@Primary
public interface CheckableItemRepository extends ItemRepository{
    //CheckableItem findCheckableItemByItemInstance(ItemInstance itemInstance);
}
