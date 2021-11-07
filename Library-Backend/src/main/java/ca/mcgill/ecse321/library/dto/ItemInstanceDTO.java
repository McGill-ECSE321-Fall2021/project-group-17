package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.model.*;

import ca.mcgill.ecse321.library.model.CheckableItem;

public class ItemInstanceDTO {
    private int serialNum;
    private CheckableItem checkableItem;

    public ItemInstanceDTO(){}

    public ItemInstanceDTO(int serialNum,CheckableItem checkableItem){
        this.checkableItem = checkableItem;
        this.serialNum = serialNum;
    }

    public int getSerialNum() { return this.serialNum; }

    public void setSerialNum(int serialNum) { this.serialNum = serialNum; }

    public CheckableItem getCheckableItem() { return this.checkableItem; }

    public void setCheckableItem(CheckableItem item) { this.checkableItem = item; }

}
