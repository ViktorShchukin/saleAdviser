package ru.aquamarina.saleadviser.core.model;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

// todo try to simplify methods. It seem that some constructions are ugly
public class TableFunction {

    UUID productId;
    int[] listQuantity;
    List<ZonedDateTime> listDate;

    public TableFunction() {
    }

    public TableFunction(UUID productId, int[] quantity, List<ZonedDateTime> listDate) {
        boolean check = quantity.length == listDate.size();
        if (!check){
            throw new IllegalArgumentException("size of quantity list is not match the size of date list");
        }
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

    // todo maybe simplify

    /**
     * if this function doesn't contain a passed argument it will be interpolated
     * if the passed argument is less or greater than min or max, return min or max of the function
     *
     * @param dateTime passed argument
     * @return value of the passed argument
     */
    public double getValue(ZonedDateTime dateTime) {
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
                            &&
                            Duration.between(listDate.get(i + 1), dateTime).isNegative()) {
                        return interpolate(dateTime, listDate.get(i), listDate.get(i + 1));
                    }
                }
            }
        }
        return 0;
    }

    public double getValueAmount(ZonedDateTime from, ZonedDateTime to) {
        return listDate.stream()
                .filter(dateTime -> dateTime.isAfter(from) || dateTime.isEqual(from))
                .filter(dateTime -> dateTime.isBefore(to) || dateTime.isEqual(to))
                .mapToDouble(this::getValue)
                .sum();
    }

    // todo maybe simplify
    // todo tests
    private double interpolate(ZonedDateTime x, ZonedDateTime x0, ZonedDateTime x1) {
        long fx0 = listQuantity[listDate.indexOf(x0)];
        long fx1 = listQuantity[listDate.indexOf(x1)];
        long xMinusX0 = -Duration.between(x, x0).toDays();
        long x1MinusX0 = -Duration.between(x1, x0).toDays();
        double deltaF = fx1 - fx0;
        double multiplication = deltaF * xMinusX0;
        double division = multiplication / x1MinusX0;
        return fx0 + division;
    }

    // todo create tests
    // todo what i should do with delta
    // todo i use delta in 1 month but sales are listed with minute precision
    // todo implement normal derivative. Maybe with Gradient descent
    public double derivative(ZonedDateTime dateTime) {
        int delta = 1; // delta in months
        double value = getValue(dateTime);
        ZonedDateTime dateTimeWithDelta = dateTime.minusMonths(delta);
        double valueWithDelta = getValue(dateTimeWithDelta);

        return (valueWithDelta - value) / delta;
    }


}
