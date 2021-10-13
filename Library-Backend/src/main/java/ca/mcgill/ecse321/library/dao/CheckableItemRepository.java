package ca.mcgill.ecse321.library.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.library.model.CheckableItem;

@Primary
public interface CheckableItemRepository extends ItemRepository{
    //CheckableItem findCheckableItemById(Integer id);
}
