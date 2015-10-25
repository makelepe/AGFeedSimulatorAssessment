package za.co.allangray.assessment.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Utility class for string manipulation
 *
 * @author radingwanes
 *
 */
public final class StringUtil {

    private StringUtil() {
    }

    public static String getUserName(String line) {
        String name;
        try (Scanner scan = new Scanner(line)) {
            scan.useDelimiter(" ");
            name = scan.next();
        }
        return name;
    }

    public static List<String> getFavourates(String line) {
        List<String> favourates = new ArrayList<>();

        try (Scanner scan = new Scanner(line)) {
            scan.useDelimiter("follows");
            scan.next(); // user
            
            String favouratesString = scan.next();
            try (Scanner favouratesScan = new Scanner(favouratesString)) {
                favouratesScan.useDelimiter(",");
                while (favouratesScan.hasNext()) {
                    favourates.add(favouratesScan.next().trim());
                }
            }
        }
        return favourates;
    }

    public static String getFeedCreatorName(String line) {
        String name;
        try (Scanner scan = new Scanner(line)) {
            scan.useDelimiter(">");
            name = scan.next();
        }
        return name.trim();
    }

    public static String getFeedMessage(String line) {
        String message;
        try (Scanner scan = new Scanner(line)) {
            scan.useDelimiter(">");
            scan.next(); // feed creator name
            message = scan.next();
        }
        return message.trim();
    }
}
