package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.LibraryCard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestLibraryCardPersistence {
    @Autowired
    private LibraryCardRepository libraryCardRepository;
    @AfterEach
    public void clearDatabase() {
        libraryCardRepository.deleteAll();
    }
    @Test
    public void testPersistAndLoadLibraryCardId(){

        LibraryCard card = new LibraryCard();
        libraryCardRepository.save(card);
        int s = card.getId();

        card = null;

        card = libraryCardRepository.findLibraryCardById(s);
        assertNotNull(card);
        assertEquals(s, card.getId());
    }
}
