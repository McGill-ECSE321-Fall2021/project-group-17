package ca.mcgill.ecse321.library.data;

public class Reservation {
    int itemId;
    String dateCheckedOut;
    String dateReturned;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getDateCheckedOut() {
        return dateCheckedOut;
    }

    public void setDateCheckedOut(String dateCheckedOut) {
        this.dateCheckedOut = dateCheckedOut;
    }

    public String getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(String dateReturned) {
        this.dateReturned = dateReturned;
    }
}
