package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.library.model.CheckableItem;

public interface CheckableItemRepository extends CrudRepository<CheckableItem, Integer> {
    CheckableItem findCheckableItemById(Integer id);
}
