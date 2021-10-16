package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.library.model.Item;

import java.sql.Date;
import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Integer> {
    Item findItemById(Integer id);
    List<Item> findItemByName(String name);
    List<Item> findItemByDatePublished(Date datePublished);
}
