package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.CheckableItemRepository;
import ca.mcgill.ecse321.library.dao.ItemInstanceRepository;
import ca.mcgill.ecse321.library.model.Book;
import ca.mcgill.ecse321.library.model.CheckableItem;
import ca.mcgill.ecse321.library.model.ItemInstance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestItemInstanceService {
    @Mock
    private ItemInstanceRepository itemInstanceRepository;

    @Mock
    private CheckableItemRepository checkableItemRepository;

    @InjectMocks
    private ItemInstanceService service;

    private final static int ITEMINSTANCE_KEY = 0;
    private final static int ITEM_KEY = 1;


    @BeforeEach
    public void setMockOutput() {
        lenient().when(itemInstanceRepository.findItemInstanceBySerialNum(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(ITEMINSTANCE_KEY)){
                ItemInstance itemInstance = new ItemInstance();
                itemInstance.setSerialNum(ITEMINSTANCE_KEY);
                return itemInstance;
            }
            else {
                return null;
            }
        });
        lenient().when(checkableItemRepository.findItemById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(ITEM_KEY)){
                CheckableItem item = new Book();
                item.setId(ITEM_KEY);
                return item;
            }
            else {
                return null;
            }
        });
    }

    @Test
    public void createValidItemInstance(){
        ItemInstance itemInstance = null;
        try{
            itemInstance = service.createItemInstance(ITEMINSTANCE_KEY);
        }
        catch (Exception e){
            fail();
        }
        assertNotNull(itemInstance);
        assertEquals(itemInstance.getSerialNum(),ITEMINSTANCE_KEY);
    }

    @Test
    public void getValidItemInstance(){
        ItemInstance itemInstance = null;
        try{
            itemInstance = service.getItemInstance(ITEMINSTANCE_KEY);
        }
        catch (Exception e){
            fail();
        }
        assertNotNull(itemInstance);
        assertEquals(itemInstance.getSerialNum(),ITEMINSTANCE_KEY);
    }

    @Test
    public void getItemInstanceMissing(){
        ItemInstance itemInstance = null;
        String error = "";
        try{
            itemInstance = service.getItemInstance(ITEMINSTANCE_KEY+1);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNull(itemInstance);
        assertEquals(error,"There exist no item instance by that Id");
    }
}

