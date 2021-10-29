package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
@Service
public class HeadLibrarianService {
    @Autowired
    private HeadLibrarianRepository headLibrarianRepository;

    @Transactional
    public HeadLibrarian createHeadLibrarian(int id){
        HeadLibrarian headLibrarian = new HeadLibrarian();
        headLibrarian.setId(id);
        headLibrarianRepository.save(headLibrarian);
        return headLibrarian;
    }
    @Transactional
    public HeadLibrarian getHeadLibrarian(int id){return (HeadLibrarian) headLibrarianRepository.findPersonRoleById(id);}
}
