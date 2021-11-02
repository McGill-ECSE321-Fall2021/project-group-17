package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.model.CheckableItem;
import ca.mcgill.ecse321.library.model.LibraryManagementSystem;

public class ItemInstanceDTO {
    private int serialNum;
    private CheckableItem checkableItem;
    private LibraryManagementSystem system;

    public ItemInstanceDTO() {

    }

    public ItemInstanceDTO(int serialNum) {
        this.serialNum = serialNum;
    }

    public void setSerialNum(int serialNum) {
        this.serialNum = serialNum;
    }

    public int getSerialNum() {
        return serialNum;
    }

    public void setCheckableItem(CheckableItem checkableItem) {
        this.checkableItem = checkableItem;
    }

    public CheckableItem getCheckableItem() {
        return checkableItem;
    }

    public void setSystem(LibraryManagementSystem system) {
        this.system = system;
    }

    public LibraryManagementSystem getSystem() {
        return system;
    }
}
