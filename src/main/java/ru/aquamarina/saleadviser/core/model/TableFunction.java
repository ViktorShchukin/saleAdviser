package ru.aquamarina.saleadviser.core.model;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

// todo try to simplify methods. It seem that some constructions are ugly
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

    // todo maybe should return optional and if given argument is out of range return optional.empty??
    // todo maybe simplify
    // todo. not all case are checked. it should return zero at all
    // todo think about long and float. Where i should cast them????
    /**
     * if this function doesn't contain a passed argument it will be interpolated
     * if the passed argument is less or greater than min or max, return min or max of the function
     *
     * @param dateTime passed argument
     * @return value of the passed argument
     */
    public float getValue(ZonedDateTime dateTime) {
        Duration duration = Duration.between(listDate.getFirst(), dateTime);
        if (duration.isNegative()) {
            return listQuantity[0];
        } else if (Duration.between(listDate.getLast(), dateTime).isPositive()) {
            return listQuantity[listDate.size() - 1];
        } else {
            for (int i = 0; i < listQuantity.length; i++) {
                if (Duration.between(listDate.get(i), dateTime).isZero()) {
                    return listQuantity[i];
                } else {
                    if (Duration.between(listDate.get(i), dateTime).isPositive()
                            &
                            Duration.between(listDate.get(i + 1), dateTime).isNegative()) {
                        return interpolate(dateTime, listDate.get(i), listDate.get(i + 1));
                    }
                }
            }
        }
        return 0;
    }

    // todo maybe simplify
    // todo tests
    private float interpolate(ZonedDateTime x, ZonedDateTime x0, ZonedDateTime x1) {
        long fx0 = listQuantity[listDate.indexOf(x0)];
        long fx1 = listQuantity[listDate.indexOf(x1)];
        long xMinusX0 = -Duration.between(x, x0).toDays();
        long x1MinusX0 = -Duration.between(x1, x0).toDays();
        float deltaF = fx1 - fx0;
        float multiplication = deltaF * xMinusX0;
        float division = multiplication / x1MinusX0;
        return fx0 + division;
    }

    // todo create tests
    // todo what i should do with delta
    // todo i use delta in 1 month but sales are listed with minute precision
    // todo implement normal derivative. Maybe with Gradient descent
    public float derivative(ZonedDateTime dateTime) {
        int delta = 1; // delta in months
        float value = getValue(dateTime);
        ZonedDateTime dateTimeWithDelta = dateTime.minusMonths(delta);
        float valueWithDelta = getValue(dateTimeWithDelta);

        return (valueWithDelta - value) / delta;
    }


}
