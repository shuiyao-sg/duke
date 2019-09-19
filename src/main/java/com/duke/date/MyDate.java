package com.duke.date;

import com.duke.exceptions.DukeIllegalArgumentException;

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
    private static final int NUMBER_OF_PARAMETER = 7;

    private MyDate(int day, int month, int year, int fromHour, int fromMin, int toHour, int toMin) {
        this.day = day;
        this.month = Month.parseFromInt(month);
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
        int[] dateArray = new int[NUMBER_OF_PARAMETER];

        try {
            if (tempArray.length > 2) {
                dateArray = genDateArrayFromFileInput(tempArray);
            } else {
                dateArray = genDateArrayFromUserInput(tempArray);
            }
            return genDateFromArray(dateArray);
        } catch (AssertionError e) {
            System.err.println("Input String is: " + date);
            throw e;
        }
    }

    private static int[] genDateArrayWithoutTimeFromUserInput(String[] array) {
        assert array.length == 1 : "Array length is " + array.length + " while it should be 1";

        String[] dateArray = array[0].split("/");
        int day = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]);
        int year = Integer.parseInt(dateArray[2]);

        return genDefaultDateArray(day, month, year);
    }

    private static int[] genDateArrayWithTimePointFromUserInput(String[] array) {
        assert array.length == 2 : "Array length is " + array.length + " while it should be 2";

        String[] dateStringArray = {array[0]};
        int[] outputArray = genDateArrayWithoutTimeFromUserInput(dateStringArray);

        try {
            int fromHour = Integer.parseInt(array[1].substring(0, 2));
            int fromMin = Integer.parseInt(array[1].substring(2));
            outputArray[3] = fromHour;
            outputArray[4] = fromMin;
            return outputArray;
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeIllegalArgumentException("Invalid input for time. Please key in time in the format 'hhmm'");
        }
    }

    private static int[] genDateArrayWithTimeDurationFromUserInput(String[] array) {
        assert array.length == 2 : "Array length is " + array.length + " while it should be 2";

        String[] timeArray = array[1].split("-");
        assert timeArray.length == 2 : "Array length is " + array.length + " while it should be 2";

        String[] tempArray = {array[0], timeArray[0]};
        int[] outputArray = genDateArrayWithTimePointFromUserInput(tempArray);

        try {
            int toHour = Integer.parseInt(timeArray[1].substring(0, 2));
            int toMin = Integer.parseInt(timeArray[1].substring(2));
            outputArray[5] = toHour;
            outputArray[6] = toMin;
            return outputArray;
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeIllegalArgumentException("Invalid input for time. Please key in time in the format 'hhmm'");
        }
    }

    private static int[] genDateArrayWithTimeFromUserInput(String[] array) {
        String[] timeArray = array[1].split("-");
        if (timeArray.length == 1) {
            return genDateArrayWithTimePointFromUserInput(array);
        } else if (timeArray.length == 2) {
            return genDateArrayWithTimeDurationFromUserInput(array);
        } else {
            throw new DukeIllegalArgumentException("Invalid input for time");
        }
    }

    private static int[] genDateArrayFromUserInput(String[] array) {
        if (array.length == 1) {
            return genDateArrayWithoutTimeFromUserInput(array);
        } else if (array.length == 2) {
            return genDateArrayWithTimeFromUserInput(array);
        } else {
            throw new DukeIllegalArgumentException("Invalid input for date & time");
        }
    }

    private static MyDate genDateFromArray(int[] array) {
        return new MyDate(array[0], array[1], array[2], array[3], array[4], array[5], array[6]);
    }

    private static int[] genDateArrayWithoutTimeFromFileInput(String[] array) {
        assert array.length == 3 : "Array length is " + array.length + " while it should be 3";

        int day = Integer.parseInt(array[0]);
        int month = Month.parseFromString(array[1]).getValue();
        int year = Integer.parseInt(array[2]);

        return genDefaultDateArray(day, month, year);
    }

    private static int[] genDateArrayWithTimePointFromFileInput(String[] array) {
        assert array.length == 4 : "Array length is " + array.length + " while it should be 4";
        String[] dateArray = {array[0], array[1], array[2]};
        int[] outputArray = genDateArrayWithoutTimeFromFileInput(dateArray);

        try {
            int fromHour = Integer.parseInt(array[3].substring(0, 2));
            int fromMin = Integer.parseInt(array[3].substring(3));
            outputArray[3] = fromHour;
            outputArray[4] = fromMin;
            return outputArray;
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeIllegalArgumentException("Invalid input for time. Please key in time in the format 'hhmm'");
        }
    }

    private static int[] genDateArrayWithTimeDurationFromFileInput(String[] array) {
        assert array.length == 6 : "Array length is " + array.length + " while it should be 6";

        String[] tempArray = {array[0], array[1], array[2], array[3]};
        int[] outputArray = genDateArrayWithTimePointFromFileInput(tempArray);

        try {
            int toHour = Integer.parseInt(array[5].substring(0, 2));
            int toMin = Integer.parseInt(array[5].substring(3));
            outputArray[5] = toHour;
            outputArray[6] = toMin;
            return outputArray;
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeIllegalArgumentException("Invalid input for time. Please key in time in the format 'hhmm'");
        }
    }

    private static int[] genDateArrayFromFileInput(String[] array) {
        switch (array.length) {
        case 3:
            return genDateArrayWithoutTimeFromFileInput(array);
        case 4:
            return genDateArrayWithTimePointFromFileInput(array);
        case 6:
            return genDateArrayWithTimeDurationFromFileInput(array);
        default:
            throw new DukeIllegalArgumentException("Invalid input for date & time from file");
        }
    }

    private static int[] genDefaultDateArray(int day, int month, int year) {
        int[] outputArray = new int[NUMBER_OF_PARAMETER];
        outputArray[0] = day;
        outputArray[1] = month;
        outputArray[2] = year;

        for (int i = 3; i < outputArray.length; i++) {
            outputArray[i] = -1;
        }
        return outputArray;
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
