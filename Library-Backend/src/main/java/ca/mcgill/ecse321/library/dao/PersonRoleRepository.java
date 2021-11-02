package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.PersonRole;
import org.springframework.data.repository.CrudRepository;

public interface PersonRoleRepository extends CrudRepository<PersonRole, Integer>{
    PersonRole findPersonRoleById(int id);
}
