public class ToDo extends Task {

    public ToDo(String descprition) {
        super(descprition);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
