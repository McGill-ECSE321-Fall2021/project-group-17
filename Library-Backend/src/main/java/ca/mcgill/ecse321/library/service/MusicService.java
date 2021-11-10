package ca.mcgill.ecse321.library.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.library.dao.CheckableItemRepository;
import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.dao.MusicRepository;
import ca.mcgill.ecse321.library.model.Item;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.Music;
import ca.mcgill.ecse321.library.service.Exception.BookException;
import ca.mcgill.ecse321.library.service.Exception.NotFoundException;
import ca.mcgill.ecse321.library.service.Exception.OnlineAccountException;
import ca.mcgill.ecse321.library.service.Exception.PersonException;

@Service
public class MusicService {
    @Autowired
    MusicRepository musicRepository;
    @Autowired
    LibrarianRepository librarianRepository;
    @Autowired
    CheckableItemRepository checkableItemRepository;
    @Transactional
    public Music createMusic(Integer librarianId, Integer id, String name, Date date, String musician, String recordLabel){
    	
    	String error = "";
        if (librarianId == null) {
        	throw new PersonException("Librarian not found in request");
        } else if (librarianRepository.findPersonRoleById(librarianId) == null) {
            error = error + "Librarian does not exist! ";
        }
        if (id == null) {
            error = error + "Id needs to be provided!";
        } 
//        else if (musicRepository.findItemById(id) != null) {
//            error = error + "Item with id " + id + " already exists! ";
//        }
        if (name == null) {
            error = error + "Name needs to be provided!";
        }
        if (date == null) {
            error = error + "Date needs to be provided!";
        }
        if (musician == null) {
            error = error + "Musician needs to be provided!";
        }
        if (recordLabel == null) {
            error = error + "Record label needs to be provided!";
        }
        error = error.trim();

        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
    	
    	Librarian librarian = (Librarian) librarianRepository.findPersonRoleById(librarianId);
        if (!(librarian instanceof Librarian)) {
        	throw new PersonException("User must be a librarian");
        }

        Music music = new Music();
        music.setId(id);
        music.setName(name);
        music.setDatePublished(date);
        music.setMusician(musician);
        music.setRecordLabel(recordLabel);
        musicRepository.save(music);
        return music;
    }
    /*@Transactional
    public void deleteMusic(int id){
        musicRepository.deleteById(id);;
    }*/
    /**
     * Used to delete item
     * @param musicId
     * @param customerId
     */
    @Transactional
    public void deleteMusic(Integer musicId, Integer librarianId){
        if(musicId == null){
            throw new BookException("Cannot find music with id to delete");
        }
        Optional<Item> music = musicRepository.findById(musicId);
        if(music == null){
            throw new NotFoundException("Cannot find music to delete");
        }
        if(librarianId == null){
            throw new BookException("Cannot authorize librarian to delete music");
        }
        Librarian librarian = (Librarian) librarianRepository.findPersonRoleById(librarianId);
        if(librarian == null){
            throw new OnlineAccountException("Librarian not found in request");
        }
        if (!(librarian instanceof Librarian)) {
        	throw new PersonException("User must be a librarian");
        }
        musicRepository.deleteById(musicId);
        music = null;
    }
    @Transactional
    public List<Item> getMusicByName(String name){
        List<Item> results= musicRepository.findItemByName(name);
        return results;
    }
    @Transactional
    public Music getMusic(Integer musicId){
        Music m= (Music) musicRepository.findItemById(musicId);
        return m;
    }
    @Transactional
    public List<Music> getMusicFromArtist(String musician){
        List<Music> results= musicRepository.findMusicByMusician(musician);
        return results;
    }
    @Transactional
    public List<Music> getMusicFromLabel(String label){
        List<Music> results= musicRepository.findMusicByRecordLabel(label);
        return results;
    }
}
