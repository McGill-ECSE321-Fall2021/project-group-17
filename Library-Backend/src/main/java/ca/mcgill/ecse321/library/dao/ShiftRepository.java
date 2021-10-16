package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Shift;
import org.springframework.data.repository.CrudRepository;

public interface ShiftRepository extends CrudRepository<Shift, Integer>{
    Shift findShiftById(Integer id);
}
