package ru.aquamarina.saleadviser.model;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

public class TableFunction {

    UUID productId;
    int[] listQuantity;
    List<ZonedDateTime> listDate;

    public TableFunction() {
    }

    public TableFunction(UUID productId, int[] quantity, List<ZonedDateTime> listDate) {
        this.productId = productId;
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

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }
    // todo create interpolation
    // todo maybe should return optional and if given argument is out of range return optional.empty??
    // todo create tests

    /**
     * if this function doesn't contain a passed argument it will be interpolated
     * if the passed argument is less or greater than min or max, return min or max of the function
     *
     * @param dateTime passed argument
     * @return value of the passed argument
     */
    public int getValue(ZonedDateTime dateTime) {
        int index = listDate.indexOf(dateTime);
        return listQuantity[index];
    }

    // todo create tests
    // todo what i should do with delta
    // todo i use delta in 1 month but sales are listed with minute precision
    // todo implement normal derivative. Maybe with Gradient descent
    public int derivative(ZonedDateTime dateTime) {
        int delta = 1; // delta in months
        int value = getValue(dateTime);
        ZonedDateTime dateTimeWithDelta = dateTime.minusMonths(delta);
        int valueWithDelta = getValue(dateTimeWithDelta);

        return (valueWithDelta - value) / delta;
    }


}
