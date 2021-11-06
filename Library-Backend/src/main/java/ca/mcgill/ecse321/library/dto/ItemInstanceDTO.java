package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.model.CheckableItem;

public class ItemInstanceDTO {
    private int serialNum;
    private CheckableItem checkableItem;

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

}
