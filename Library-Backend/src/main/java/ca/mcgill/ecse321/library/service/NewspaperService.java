package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.NewspaperRepository;
import ca.mcgill.ecse321.library.model.Newspaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Service
public class NewspaperService {
    @Autowired
    NewspaperRepository newspaperRepository;

    @Transactional
    public Newspaper createNewspaper(int id, String name, Date date, String headline){
        Newspaper n= new Newspaper();
        n.setId(id);
        n.setName(name);
        n.setDatePublished(date);
        n.setHeadline(headline);
        newspaperRepository.save(n);
        return n;
    }
    @Transactional
    public Newspaper getNewspaper(Integer newspaperId){
        Newspaper n= (Newspaper) newspaperRepository.findItemById(newspaperId);
        return n;
    }
    @Transactional
    public Newspaper getNewspaperByHeadline(String headline){
        Newspaper result= newspaperRepository.findNewspaperByHeadline(headline);
        return result;
    }


}
