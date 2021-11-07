package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.model.*;

import ca.mcgill.ecse321.library.model.CheckableItem;

public class ItemInstanceDTO {
    private int serialNum;
    private CheckableItem checkableItem;
    private LibraryManagementSystem system;

    public ItemInstanceDTO(){}

    public ItemInstanceDTO(int serialNum,CheckableItem checkableItem, LibraryManagementSystem system){
        this.checkableItem = checkableItem;
        this.serialNum = serialNum;
        this.system = system;
    }

    public int getSerialNum() { return this.serialNum; }

    public void setSerialNum(int serialNum) { this.serialNum = serialNum; }

    public CheckableItem getCheckableItem() { return this.checkableItem; }

    public void setCheckableItem(CheckableItem item) { this.checkableItem = item; }

    public LibraryManagementSystem getSystem() { return system; }

    public void setSystem(LibraryManagementSystem system) { this.system = system; }

}
