package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Music;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestMusicPersistance {
    @Autowired
    private MusicRepository musicRepository;
    @AfterEach
    public void clearDatabase() {
        musicRepository.deleteAll();
    }
    @Test
    public void testPersistAndLoadMusic(){
        String musician="Victoria";
        String recordLabel="Sony";
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        String name= "My Brilliant Friend";
        Music music = new Music();
        music.setName(name);
        music.setDatePublished(date);
        music.setMusician(musician);
        music.setRecordLabel((recordLabel));
        musicRepository.save(music);
        Integer musicID= music.getId(); //create object to be tested
        music=null;
        music=(Music) musicRepository.findItemById(musicID);
        assertNotNull(music);
        assertEquals(musicID,music.getId());
        assertEquals(name, music.getName());
        assertEquals(date,music.getDatePublished());
        assertEquals(musician,music.getMusician());
        assertEquals(recordLabel,music.getRecordLabel());
    }
    @Test
    public void testFindMusicByMusician(){
        List<Music> music= new ArrayList<Music>();
        String musician= "Drake";
        String name="Club Paradise";
        String name2="5am in Toronto";
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        Music m= new Music(1234,name,date, musician,"OVO");
        Music m2= new Music(5678,name2,date, musician, "Label");
        musicRepository.save(m);
        musicRepository.save(m2);
        music=musicRepository.findMusicByMusician(musician);
        assertNotNull(music);
        m=music.get(0);
        m2=music.get(1);
        assertEquals(2,music.size());
        assertEquals(name, m.getName());
        assertEquals(name2,m2.getName());
    }
    //ensures database can return a list of music produced by the same label
    @Test
    public void testFindMusicByRecordLabel(){
        List<Music> music= new ArrayList<Music>();
        String label= "Ovo";
        String name="Club Paradise";
        String name2="5am in Toronto";
        Date date =java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER,12));
        Music m= new Music(1234,name,date, "Adele",label);
        Music m2= new Music(5678,name2,date, "Taylor Swift", label);
        musicRepository.save(m);
        musicRepository.save(m2);
        music=musicRepository.findMusicByRecordLabel(label);
        assertNotNull(music);
        m=music.get(0);
        m2=music.get(1);
        assertEquals(2,music.size());
        assertEquals(name, m.getName());
        assertEquals(name2,m2.getName());
    }
}
