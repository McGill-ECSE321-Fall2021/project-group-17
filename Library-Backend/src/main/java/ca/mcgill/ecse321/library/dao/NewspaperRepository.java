package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.library.model.Newspaper;

public interface NewspaperRepository extends ItemRepository {
    Newspaper findNewspaperByHeadline(String headline);
}
