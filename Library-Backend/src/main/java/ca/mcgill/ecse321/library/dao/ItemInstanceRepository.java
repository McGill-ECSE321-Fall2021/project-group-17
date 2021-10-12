package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.library.model.ItemInstance;

public interface ItemInstanceRepository extends CrudRepository<ItemInstance, Integer> {
    ItemInstance findItemInstanceById(Integer id);
}
