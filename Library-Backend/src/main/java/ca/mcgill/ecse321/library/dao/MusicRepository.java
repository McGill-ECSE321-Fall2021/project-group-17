package ca.mcgill.ecse321.library.dao;


import ca.mcgill.ecse321.library.model.Music;

import java.util.List;
//adds features to find music based on attributes
public interface MusicRepository extends CheckableItemRepository{
List<Music> findMusicByMusician(String musician);
List<Music> findMusicByRecordLabel(String label);
}
