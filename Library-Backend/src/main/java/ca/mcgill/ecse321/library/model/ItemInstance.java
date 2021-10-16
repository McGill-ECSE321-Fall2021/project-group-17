package ca.mcgill.ecse321.library.model;

import javax.persistence.*;

@Entity
public class ItemInstance {
    private String serialNum;
    private CheckableItem checkableItem;

    public ItemInstance() {

    }

    public ItemInstance(String serialNum, CheckableItem item) {
        this.serialNum = serialNum;
        this.checkableItem = item;
    }

    @Id
    public String getSerialNum() {
        return this.serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    @ManyToOne(optional=false)
    public CheckableItem getCheckableItem() {
        return this.checkableItem;
    }

    public void setCheckableItem(CheckableItem item) {
        this.checkableItem = item;
    }
}
