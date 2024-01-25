import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
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
            System.out.println("java -cp src Main -f <filename> -d <date>");
            return;
        }

        // Validate filename ends with .csv
        if (!filename.endsWith(".csv")) {
            System.out.println("The file must be a .csv file.");
            return;
        }

        // Validate date format
        if (!isValidDate(date)) {
            System.out.println("The date is invalid. Please use the format YYYY-MM-DD.");
            return;
        }


        // Output List of most active cookies
        List<String> mostActiveCookies = findMostActiveCookies(filename, date);
        for (String cookie : mostActiveCookies) {
            System.out.println(cookie);
        }
    }

    static boolean isValidDate(String date) {
        //Validating the date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    static List<String> findMostActiveCookies(String filename, String date) throws FileNotFoundException {
        // HashMap to store cookie and how many times called in that day
        Map<String, Integer> cookieCounts = new HashMap<>();
        int maxCount = 0;
        // List to store the most active cookies
        List<String> mostActiveCookies = new ArrayList<>();

        // Read the file
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean dateFound = false;
            // Parse through the whole file
            while ((line = reader.readLine()) != null) {
                // Split the line into cookie, and Date/Time accessed
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    // Assign cookie and timestamp
                    String cookie = parts[0];
                    String timestamp = parts[1];

                    // Breaks out of the while loop if the date has passed (Assumption that csv is sorted)
                    boolean correctDate = timestamp.startsWith(date);
                    if (dateFound && !correctDate){
                        break;
                    }

                    // Correct date means process cookie
                    if (correctDate) {
                        // Add to the frequency of that cookie or initialize it
                        int count = cookieCounts.getOrDefault(cookie, 0) + 1;
                        cookieCounts.put(cookie, count);
                        // Set dateFound to true for future iterations
                        dateFound = true;

                        // Current cookie is the most active
                        if (count > maxCount) {
                            maxCount = count;
                            // Clear the current list as new highest count found
                            mostActiveCookies.clear();
                            mostActiveCookies.add(cookie);
                        }
                        // Cookies that have same frequency add to list
                        else if (count == maxCount) {
                            mostActiveCookies.add(cookie);
                        }
                    }
                } else {
                    //csv file is incorrectly formatted
                    System.out.println("csv is not correctly formatted. Should be 'cookie:timestamp'");
                }
            }
        } catch (IOException e) {
            //FileNotFound is thrown while all other IOExceptions print stack trace
            if (!(e instanceof FileNotFoundException)) {
                e.printStackTrace();
            } else {
                throw (FileNotFoundException) e;
            }
        }

        return mostActiveCookies;
    }

}
