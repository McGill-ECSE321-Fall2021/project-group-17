package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Newspaper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestNewspaperPersistence {
    @Autowired
    private NewspaperRepository newspaperRepository;
    @AfterEach
    public void clearDatabase() {
        newspaperRepository.deleteAll();
    }
    @Test
    public void testPersistAndLoadNewspaper() {
        String name = "New York Times";
        Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.MAY,24));
        String headline = "US deaths near 100,000, an incalculable loss";
        Newspaper newspaper = new Newspaper(0, name, date, headline);
        newspaperRepository.save(newspaper);
        Integer id = newspaper.getId();
        newspaper = (Newspaper) newspaperRepository.findItemById(id);
        assertNotNull(newspaper);
        assertEquals(id,newspaper.getId());
        assertEquals(name, newspaper.getName());
        assertEquals(date, newspaper.getDatePublished());
        assertEquals(headline, newspaper.getHeadline());
    }

    @Test
    public void testFindNewspaperByHeadline() {
        String name = "New York Times";
        Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.MAY,24));
        String headline = "US deaths near 100,000, an incalculable loss";
        Newspaper newspaper = new Newspaper(0, name, date, headline);
        newspaperRepository.save(newspaper);
        Integer id = newspaper.getId();
        newspaper = newspaperRepository.findNewspaperByHeadline(headline);
        assertNotNull(newspaper);
        assertEquals(id,newspaper.getId());
        assertEquals(name, newspaper.getName());
        assertEquals(date, newspaper.getDatePublished());
        assertEquals(headline, newspaper.getHeadline());
    }
}
