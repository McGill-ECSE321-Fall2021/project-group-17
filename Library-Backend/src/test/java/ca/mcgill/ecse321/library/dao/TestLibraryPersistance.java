package ca.mcgill.ecse321.library.dao;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.mcgill.ecse321.library.model.Library;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestLibraryPersistance {

    @Autowired
    private LibraryRepository libraryRepository;


    @AfterEach
    public void clearDatabase() {
        libraryRepository.deleteAll();
    }
    @Test
    public void testPersistAndLoadLibrary() {
        int id = 1234;
        String name = "McLennen";
        Library library = new Library(id);
        library.setName(name);
        libraryRepository.save(library);
        library = null;
        library = libraryRepository.findLibraryById(id);
        assertNotNull(library);
        assertEquals(name, library.getName());
    }
}