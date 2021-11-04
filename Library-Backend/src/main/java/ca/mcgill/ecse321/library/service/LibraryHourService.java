package ca.mcgill.ecse321.library.service;

import javax.transaction.Transactional;

import ca.mcgill.ecse321.library.service.Exception.LibraryHourException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.library.dao.LibraryHourRepository;
import ca.mcgill.ecse321.library.dao.LibraryManagementSystemRepository;
import ca.mcgill.ecse321.library.dao.LibraryRepository;
import ca.mcgill.ecse321.library.model.Library;
import ca.mcgill.ecse321.library.model.LibraryHour;
import ca.mcgill.ecse321.library.model.LibraryManagementSystem;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Service
public class LibraryHourService {
	@Autowired
    private LibraryHourRepository libraryHourRepository;
	@Autowired
    private LibraryRepository libraryRepository;
	@Autowired
    private LibraryManagementSystemRepository lmsRepository;
	
    @Transactional
    public LibraryHour createLibraryHour(int id, Integer libraryId, Integer systemId){
        LibraryHour libraryHour = new LibraryHour();
        libraryHour.setId(id);
        
        if (libraryId != null) {
            Library library = libraryRepository.findLibraryById(libraryId);
            if (library instanceof Library) {
            	libraryHour.setLibrary(library);
            }
        }
        
        if(systemId != null){
            LibraryManagementSystem system = lmsRepository.findLibraryManagementSystemById(systemId);
            libraryHour.setSystem(system);
        }
        
        libraryHourRepository.save(libraryHour);
        return libraryHour;
    }
    
    @Transactional
    public LibraryHour getLibraryHour(int id) {return libraryHourRepository.findLibraryHourById(id);}

    @Transactional
    public List<LibraryHour> getLibraryHours(Library lib){
        if(lib == null) throw new LibraryHourException("Invalid Library Name");
        List<LibraryHour> libHours = libraryHourRepository.findByLibrary(lib);
        return libHours;
    }

    @Transactional
    public LibraryHour updateLibraryHour(Integer id, Time startTime, Time endTime, DayOfWeek DOW, Library library){
        if(id < 0 || id == null) throw new LibraryHourException("Invalid Id");
        if(startTime.toLocalTime().isAfter(endTime.toLocalTime())) throw new LibraryHourException("Invalid Start or End Time");
        if(DOW == null)throw new LibraryHourException("Invalid Day of Week");
        if(library == null) throw new LibraryHourException("Invalid Library");

        LibraryHour libraryHour = libraryHourRepository.findLibraryHourById(id);
        if(libraryHour == null) throw new LibraryHourException("There does not exist a Library Hour by Id: " + id.toString());

        libraryHour.setLibrary(library);
        libraryHour.setDayOfWeek(DOW);
        libraryHour.setEndTime(endTime);
        libraryHour.setStartTime(startTime);
        libraryHourRepository.save(libraryHour);
        return libraryHour;
    }

    @Transactional
    public LibraryHour deleteLibraryHour(Integer id){
        if(id < 0 || id == null) throw new LibraryHourException("Invalid Id");

        LibraryHour libraryHour = libraryHourRepository.findLibraryHourById(id);
        if(libraryHour == null) throw new LibraryHourException("There does not exist a Library hour by Id: " + id.toString());

        libraryHourRepository.deleteById(id);
        return null;
    }

}
