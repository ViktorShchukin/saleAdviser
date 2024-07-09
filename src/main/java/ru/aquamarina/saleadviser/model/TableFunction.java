package ru.aquamarina.saleadviser.model;

import java.time.ZonedDateTime;
import java.util.List;

// todo add methods to return at least derivative and other...
// abstraction to calculate
public class TableFunction {

    int[] listQuantity;
    List<ZonedDateTime> listDate;

    public TableFunction() {
    }

    public TableFunction(int[] quantity, List<ZonedDateTime> listDate) {
        this.listQuantity = quantity;
        this.listDate = listDate;
    }

    public int[] getListQuantity() {
        return listQuantity;
    }

    public void setListQuantity(int[] listQuantity) {
        this.listQuantity = listQuantity;
    }

    public List<ZonedDateTime> getListDate() {
        return listDate;
    }

    public void setListDate(List<ZonedDateTime> listDate) {
        this.listDate = listDate;
    }


}
