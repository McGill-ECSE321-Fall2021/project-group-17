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
    public Music createMusic(int librarianId, int id, String name, Date date, String musician, String recordLabel){
        Librarian librarian = (Librarian) librarianRepository.findPersonRoleById(librarianId);
        if(librarian == null){
            throw new PersonException("Librarian not found in request");
        }
        if (!(librarian instanceof Librarian)) {
        	throw new PersonException("User must be a librarian");
        }
        Music m= new Music();
        m.setId(id);
        m.setName(name);
        m.setDatePublished(date);
        m.setMusician(musician);
        m.setRecordLabel(recordLabel);
        musicRepository.save(m);
        return m;
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
            throw new PersonException("Librarian not found in request");
        }
        if (!(librarian instanceof Librarian)) {
        	throw new PersonException("User must be a librarian");
        }
        checkableItemRepository.deleteById(musicId);
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
