package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.LibraryRepository;
import ca.mcgill.ecse321.library.model.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestLibraryService {
    @Mock
    private LibraryRepository libraryRepository;

    @InjectMocks
    private LibraryService service;

    private final static int LIBRARY_KEY = 0;
    @BeforeEach
    public void setMockOutput() {
        lenient().when(libraryRepository.findLibraryById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(LIBRARY_KEY)){
                Library library = new Library();
                library.setId(LIBRARY_KEY);
                return library;
            }
            else {
                return null;
            }
        });
    }

    @Test
    public void createValidLibrary(){
        Library l = null;
        try{
            l = service.createLibrary(LIBRARY_KEY);
        }
        catch (Exception e){
            fail();
        }
        assertNotNull(l);
        assertEquals(l.getId(),LIBRARY_KEY);
    }

    @Test
    public void getValidLibrary(){
        Library l = null;
        try{
            l = service.getLibrary(LIBRARY_KEY);
        }
        catch (Exception e){
            fail();
        }
        assertNotNull(l);
        assertEquals(l.getId(),LIBRARY_KEY);
    }

    @Test
    public void getLibraryKeyNull(){
        Library l = null;
        String error = "";
        try{
            l = service.getLibrary(null);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(l);
        assertEquals(error,"Invalid id");
    }

    @Test
    public void getLibraryKeyNegative(){
        Library l = null;
        String error = "";
        try{
            l = service.getLibrary(LIBRARY_KEY-100);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(l);
        assertEquals(error,"Invalid id");
    }

    @Test
    public void getLibraryMissing(){
        Library l = null;
        String error = "";
        try{
            l = service.getLibrary(LIBRARY_KEY+1);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(l);
        assertEquals(error,"There exist no library by that Id");
    }
}
