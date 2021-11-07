//package ca.mcgill.ecse321.library.service;
//
//import ca.mcgill.ecse321.library.dao.LibraryManagementSystemRepository;
//import ca.mcgill.ecse321.library.model.Customer;
//import ca.mcgill.ecse321.library.model.ItemInstance;
//import ca.mcgill.ecse321.library.model.LibraryManagementSystem;
//import ca.mcgill.ecse321.library.model.Loan;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.invocation.InvocationOnMock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.lenient;
//
//@ExtendWith(MockitoExtension.class)
//public class TestLibraryManagementService {
//    @Mock
//    private LibraryManagementSystemRepository libraryManagementSystemRepository;
//
//    @InjectMocks
//    private LibraryManagementSystemService service;
//
//    private static final int LIBRARY_MANAGEMENT_SYSTEM_KEY = 0;
//    @BeforeEach
//    public void setMockOutput() {
//        lenient().when(libraryManagementSystemRepository.findLibraryManagementSystemById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
//            if(invocation.getArgument(0).equals(LIBRARY_MANAGEMENT_SYSTEM_KEY)) {
//                LibraryManagementSystem lms = new LibraryManagementSystem();
//                lms.setId(LIBRARY_MANAGEMENT_SYSTEM_KEY);
//                return lms;
//            } else {
//                return null;
//            }
//        });
//    }
//    //TODO kinda useless test lmao
//    @Test
//    public void testCreateSystem() {
//        LibraryManagementSystem system = null;
//        try{
//            system = service.createSystem();
//        }
//        catch (Exception e){
//            fail();
//        }
//        assertNotNull(system);
//        assertEquals(LIBRARY_MANAGEMENT_SYSTEM_KEY, system.getId());
//    }
//
//    @Test
//    public void testGetSystemExists(){
//        LibraryManagementSystem system = null;
//        try{
//            system = service.getSystem(LIBRARY_MANAGEMENT_SYSTEM_KEY);
//        }
//        catch (Exception e){
//            fail();
//        }
//        assertNotNull(system);
//        assertEquals(LIBRARY_MANAGEMENT_SYSTEM_KEY, system.getId());
//    }
//}
