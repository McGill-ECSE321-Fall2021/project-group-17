package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.library.model.Item;

public interface ItemRepository extends CrudRepository<Item, Integer> {
    Item findItemById(Integer id);
}
