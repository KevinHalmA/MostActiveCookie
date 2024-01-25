//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String filename = null;
        String date = null;

        // Parse command-line arguments
        for (int i = 0; i < args.length; i++) {
            if ("-f".equals(args[i]) && i + 1 < args.length) {
                filename = args[++i]; // Increment i and get next argument as filename
            } else if ("-d".equals(args[i]) && i + 1 < args.length) {
                date = args[++i]; // Increment i and get next argument as date
            }
        }

        // Validate arguments
        if (filename == null || date == null) {
            System.out.println("Usage: java Main -f <filename> -d <date>");
            return;
        }

        // Output to system
        System.out.println("Filename: " + filename);
        System.out.println("Date: " + date);

    }

}
