/**
 * THE FOLLOWING IS BORROWED FOR USE WITH FILEUTILS ONLY
 * @author Sean Grimes, sean@seanpgrimes.com
 *
 * Validate that values are what they're supposed to be. Very helpful when getting user
 * input.
 */
@SuppressWarnings({"WeakerAccess", "unused", "BooleanMethodIsAlwaysInverted"})
public class Validation {
    /**
     * Validate that a string is a number
     *
     * @param num The string to be validated
     * @return True if the String can be parsed to an int, else false
     */
    public static boolean isInt(String num) {
        try {
            Integer.parseInt(num);
            return true;
        }
        // Not an int
        catch (Exception e) {
            return false;
        }
    }

    /**
     * Validate that a string is a double
     *
     * @param num The string to be validated
     * @return True if it's a double, else false
     */
    public static boolean isDouble(String num) {
        try {
            Double.parseDouble(num);
            return true;
        }
        // Not a double
        catch (Exception e) {
            return false;
        }
    }

    /**
     * Validate that a String is a valid integer between an inclusive range
     *
     * @param start The start of the inclusive range
     * @param end   The end of the inclusive range
     * @param num   The String to be validated
     * @return True if it's a valid int between the inclusive range, else false
     */
    public static boolean isIntBetweenInclusive(int start, int end, String num) {
        if (!isInt(num)) return false;
        int val = Integer.parseInt(num);
        return val >= start && val <= end;
    }
}
