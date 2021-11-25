package ca.mcgill.ecse321.library.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import ca.mcgill.ecse321.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.dao.MusicRepository;
import ca.mcgill.ecse321.library.model.Item;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.Music;
import ca.mcgill.ecse321.library.service.Exception.BookException;
import ca.mcgill.ecse321.library.service.Exception.MovieException;
import ca.mcgill.ecse321.library.service.Exception.NotFoundException;
import ca.mcgill.ecse321.library.service.Exception.OnlineAccountException;
import ca.mcgill.ecse321.library.service.Exception.PersonException;

@Service
public class MusicService {
	/*
	 * Repositories for aiding the service.
	 * These repositories allow for the storage of data
	 * into the database. Allows for create, get, update and delete functionalities.
	 */
    @Autowired
    private MusicRepository musicRepository;
    @Autowired
    private LibrarianRepository librarianRepository;
    
    @Transactional
    public Music createMusic(Integer librarianId, Integer id, String name, Date date, String musician, String recordLabel){
    	/*
    	 * Creates a music item with the provided ids from the rest controller method.
    	 * Throws errors based on arguments, and whether they were provided or not.
    	 * Saves item into repository at the end.
    	 */
    	String error = "";
    	if (librarianId == null) {
    		throw new IllegalArgumentException("Librarian does not exist! ");
    	}
    	Librarian librarian = (Librarian) librarianRepository.findPersonRoleById(librarianId);
        if (librarian == null) {
            error = error + "Librarian does not exist! ";
        }
        if (id == null) {
            error = error + "Id needs to be provided!";
        }
        if (name == null) {
            error = error + "Name needs to be provided!";
        }
        error = error.trim();

        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
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
    
    @Transactional
    public Music updateMusic(Integer librarianId, Integer id, String name, Date date, String musician, String recordLabel) {
    	/*
    	 * Updates a music item with the provided ids from the rest controller method.
    	 * Throws errors based on arguments, and whether they were provided or not.
    	 * Saves item into repository at the end.
    	 */
    	if (librarianId == null || librarianRepository.findPersonRoleById(librarianId) == null) {
        	throw new PersonException("Librarian does not exist!");
    	}
    	
    	Music music = (Music) musicRepository.findItemById(id);
        
        if (music == null) {
            throw new MovieException("Can't update music because no music exists for the given id.");
        }

        if (id != null) {
        	music.setId(id);
        }

        if (name != null) {
        	music.setName(name);
        }

        if (date != null) {
        	music.setDatePublished(date);
        }

        if (musician != null) {
        	music.setMusician(musician);
        }
        
        if (recordLabel != null) {
        	music.setRecordLabel(recordLabel);
        }
        
        musicRepository.save(music);
        return music;
    }
    

    /**
     * Used to delete item
     * @param musicId
     * @param librarianId
     */
    @Transactional
    public void deleteMusic(Integer musicId, Integer librarianId){
    	/*
    	 * Deletes item based on id, and requires librarian id to
    	 * perform this task. Deletes in repository at the end.
    	 */
        if(musicId == null){
            throw new BookException("Cannot find music with id to delete");
        }
        Music music = (Music) musicRepository.findItemById(musicId);
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
    	/*
    	 * Gets item based on field, from the corresponding repository.
    	 */
        List<Item> results = musicRepository.findItemByName(name);
        return results;
    }
    @Transactional
    public Music getMusic(Integer musicId){
    	/*
    	 * Gets item based on field, from the corresponding repository.
    	 */
        Music music = (Music) musicRepository.findItemById(musicId);
        return music;
    }
    @Transactional
    public List<Music> getMusicFromMusician(String musician){
    	/*
    	 * Gets item based on field, from the corresponding repository.
    	 */
        List<Music> results = musicRepository.findMusicByMusician(musician);
        return results;
    }
    @Transactional
    public List<Music> getMusicFromLabel(String label){
    	/*
    	 * Gets item based on field, from the corresponding repository.
    	 */
        List<Music> results = musicRepository.findMusicByRecordLabel(label);
        return results;
    }

    @Transactional
    public List<Music> getMusic() {
        List<Item> items = (List<Item>) musicRepository.findAll();

        List<Music> music = new ArrayList<>();

        for (Item item : items) {
            if (item instanceof Music) {
                music.add((Music) item);
            }
        }
        return music;
    }
}
