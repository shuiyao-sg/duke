import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String indentByFour = " " + " " + " " + " ";
        String indentByOne = " ";
        String horizontalLine = indentByFour + "____________________________________________________________";

        Scanner sc = new Scanner(System.in);

        System.out.println(horizontalLine);
        System.out.print(indentByFour + indentByOne);
        System.out.println("Hello! I'm Duke");
        System.out.print(indentByFour + indentByOne);
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);
        System.out.println();

        List<String> list = new ArrayList<>();

        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println(horizontalLine);
                System.out.print(indentByFour + indentByOne);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(horizontalLine);
                sc.close();
                break;
            } else if (input.equals("list")) {
                System.out.println(horizontalLine);
                for (int i = 0; i < list.size(); i++) {
                    int index = i + 1;
                    System.out.print(indentByFour + indentByOne);
                    System.out.println(index + "." + " " + list.get(i));
                }
                System.out.println(horizontalLine);
                System.out.println();
            } else {
                list.add(input);
                System.out.println(horizontalLine);
                System.out.print(indentByFour + indentByOne);
                System.out.println("Added:" + " " + input);
                System.out.println(horizontalLine);
                System.out.println();
            }


        }


    }
}
