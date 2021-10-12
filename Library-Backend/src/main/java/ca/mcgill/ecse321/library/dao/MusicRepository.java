package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Music;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MusicRepository extends CheckableItemRepository{
//    List<Music> findByMusician(String musician);
//    List<Music> findByRecordLabel(String label);
    //Music findMusicById(Integer ID);
}
