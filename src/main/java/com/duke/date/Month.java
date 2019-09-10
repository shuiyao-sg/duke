package com.duke.date;

/**
 * Encapsulates a month, from January to December.
 */
public enum Month {
    JANUARY(1),
    FEBRUARY(2),
    MARCH(3),
    APRIL(4),
    MAY(5),
    JUNE(6),
    JULY(7),
    AUGUST(8),
    SEPTEMBER(9),
    OCTOBER(10),
    NOVEMBER(11),
    DECEMBER(12);

    private int value;

    Month(int value) {
        this.value = value;
    }

    /**
     * Parses int to corresponding Month object.
     *
     * @param i integer representation of month.
     * @return Month object.
     */
    public static Month parseFromInt(int i) {
        switch (i) {
        case 1:
            return JANUARY;
        case 2:
            return FEBRUARY;
        case 3:
            return MARCH;
        case 4:
            return APRIL;
        case 5:
            return MAY;
        case 6:
            return JUNE;
        case 7:
            return JULY;
        case 8:
            return AUGUST;
        case 9:
            return SEPTEMBER;
        case 10:
            return OCTOBER;
        case 11:
            return NOVEMBER;
        case 12:
            return DECEMBER;
        default:
            return null;
        }
    }

    protected static Month parseFromString(String month) {
        if (month.equalsIgnoreCase("JANUARY")) {
            return JANUARY;
        } else if (month.equalsIgnoreCase("FEBRUARY")) {
            return FEBRUARY;
        } else if (month.equalsIgnoreCase("MARCH")) {
            return MARCH;
        } else if (month.equalsIgnoreCase("APRIL")) {
            return APRIL;
        } else if (month.equalsIgnoreCase("MAY")) {
            return MAY;
        } else if (month.equalsIgnoreCase("JUNE")) {
            return JUNE;
        } else if (month.equalsIgnoreCase("JULY")) {
            return JULY;
        } else if (month.equalsIgnoreCase("AUGUST")) {
            return AUGUST;
        } else if (month.equalsIgnoreCase("SEPTEMBER")) {
            return SEPTEMBER;
        } else if (month.equalsIgnoreCase("OCTOBER")) {
            return OCTOBER;
        } else if (month.equalsIgnoreCase("NOVEMBER")) {
            return NOVEMBER;
        } else if (month.equalsIgnoreCase("DECEMBER")) {
            return DECEMBER;
        } else {
            return null;
        }
    }

    public int getValue() {
        return value;
    }
}
