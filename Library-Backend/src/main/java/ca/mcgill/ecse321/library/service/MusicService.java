package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.MusicRepository;
import ca.mcgill.ecse321.library.model.Item;
import ca.mcgill.ecse321.library.model.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Service
public class MusicService {
    @Autowired
    MusicRepository musicRepository;
    @Transactional
    public Music createMusic(int id, String name, Date date, String musician, String recordLabel){
        Music m= new Music();
        m.setId(id);
        m.setName(name);
        m.setDatePublished(date);
        m.setMusician(musician);
        m.setMusician(recordLabel);
        musicRepository.save(m);
        return m;
    }
    @Transactional
    public void deleteMusic(int id){
        musicRepository.deleteById(id);;
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
