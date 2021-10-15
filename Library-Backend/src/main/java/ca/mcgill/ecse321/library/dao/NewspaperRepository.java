package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.library.model.Newspaper;

import java.util.List;

public interface NewspaperRepository extends ItemRepository {
    List<Newspaper> findNewspaperByHeadline(String headline);
}
