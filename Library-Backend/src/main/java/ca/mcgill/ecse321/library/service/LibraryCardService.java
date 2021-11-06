package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.LibraryCardRepository;
import ca.mcgill.ecse321.library.model.LibraryCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
public class LibraryCardService {

    @Autowired
    LibraryCardRepository libraryCardRepository;

    @Transactional
    public LibraryCard createLibraryCard(int id) {
        LibraryCard libraryCard = new LibraryCard();
        libraryCard.setId(id);
        libraryCardRepository.save(libraryCard);
        return libraryCard;
    }

    @Transactional
    public LibraryCard getLibraryCard(int id) {
        return libraryCardRepository.findLibraryCardById(id);
    }
}


