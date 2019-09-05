package com.duke.date;

/**
 * Encapsulates a date and time.
 */
public class MyDate {
    private int day;
    private Month month;
    private int year;
    private int fromHour;
    private int fromMin;
    private int toHour;
    private int toMin;

    private MyDate(int day, int month, int year, int fromHour, int fromMin, int toHour, int toMin) {
        this.day = day;
        this.month = Month.parseFromInt(month);
        this.year = year;
        this.fromHour = fromHour;
        this.fromMin = fromMin;
        this.toHour = toHour;
        this.toMin = toMin;
    }

    private MyDate(int day, Month month, int year, int fromHour, int fromMin, int toHour, int toMin) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.fromHour = fromHour;
        this.fromMin = fromMin;
        this.toHour = toHour;
        this.toMin = toMin;
    }

    /**
     * Generates a MyDate object with a date String.
     *
     * @param date String representation of date.
     * @return MyDate object specified by the String.
     */
    public static MyDate genMyDate(String date) {
        String[] tempArray = date.split(" ");

        if (tempArray.length > 2) {
            int day = Integer.parseInt(tempArray[0]);
            Month month = Month.parseFromString(tempArray[1]);
            int year = Integer.parseInt(tempArray[2]);

            if (tempArray.length == 3) {
                return new MyDate(day, month, year, -1, -1, -1, -1);
            }

            int fromHour = Integer.parseInt(tempArray[3].substring(0, 2));
            int fromMin = Integer.parseInt(tempArray[3].substring(3));

            if (tempArray.length == 4) {
                return new MyDate(day, month, year, fromHour, fromMin, -1, -1);
            }

            int toHour = Integer.parseInt(tempArray[5].substring(0, 2));
            int toMin = Integer.parseInt(tempArray[5].substring(3));

            return new MyDate(day, month, year, fromHour, fromMin, toHour, toMin);
        }

        String[] dateArray = tempArray[0].split("/");
        int day = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]);
        int year = Integer.parseInt(dateArray[2]);

        if (tempArray.length == 1) {
            return new MyDate(day, month, year, -1, -1, -1, -1);
        }

        String[] timeArray = tempArray[1].split("-");
        int fromHour = Integer.parseInt(timeArray[0].substring(0, 2));
        int fromMin = Integer.parseInt(timeArray[0].substring(2));

        if (timeArray.length == 1) {
            return new MyDate(day, month, year, fromHour, fromMin, -1, -1);
        }

        int toHour = Integer.parseInt(timeArray[1].substring(0, 2));
        int toMin = Integer.parseInt(timeArray[1].substring(2));

        return new MyDate(day, month, year, fromHour, fromMin, toHour, toMin);

    }

    @Override
    public String toString() {
        return toHour < 0
                ? fromHour < 0
                ? day + " " + month.toString() + " " + year
                : day + " " + month.toString() + " " + year + " " + String.format("%02d", fromHour) + ":"
                + String.format("%-2s", fromMin).replace(' ', '0')
                : day + " " + month.toString() + " " + year + " " + String.format("%02d", fromHour) + ":"
                + String.format("%-2s", fromMin).replace(' ', '0') + " to "
                + String.format("%02d", toHour) + ":" + String.format("%-2s", toMin).replace(' ', '0');
    }

}
