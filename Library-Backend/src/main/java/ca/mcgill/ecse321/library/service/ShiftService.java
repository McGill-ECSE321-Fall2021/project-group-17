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
public class ShiftService {
}
