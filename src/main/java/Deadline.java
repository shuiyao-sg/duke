import cs2103t.duke.date.MyDate;
import cs2103t.duke.exceptions.DukeIllegalArgumentException;

public class Deadline extends Task {

//    protected String by;
    protected MyDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = MyDate.genMyDate(by);
    }

    public static Deadline genDeadline(String s) {
        String[] newInputArray = s.split("/by");
        String des, by;
        try {
            des = newInputArray[0].trim();
            by = newInputArray[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeIllegalArgumentException("Illegal input for deadline. "
                    + "Please key in 'deadline <task> /by <time>'");
        }
        return new Deadline(des, by);
    }


}
