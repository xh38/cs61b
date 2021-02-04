import java.util.Arrays;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        int maxlength = Integer.MIN_VALUE;
        for (String i : asciis) {
            maxlength = maxlength > i.length() ? maxlength : i.length();
        }

        String[] sorted = new String[asciis.length];
        System.arraycopy(asciis, 0, sorted, 0, asciis.length);

        for (int i = maxlength - 1; i >= 0; i--) {
            sortHelperLSD(sorted, i);
        }

        return sorted;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        String[] temp = new String[asciis.length];
        System.arraycopy(asciis, 0, temp, 0, asciis.length);

        int[] counts = new int[256];
        for (String i : asciis) {
            if (index < i.length()){
                counts[(int) i.charAt(index)]++;
            } else {
                counts[0]++;
            }
        }

        int[] starts = new int[256];
        int pos = 0;
        for (int i = 0; i < 256; i++) {
            starts[i] = pos;
            pos += counts[i];
        }

        for (int i = 0; i < asciis.length; i += 1) {
            String item = temp[i];
            if (item.length() > index) {
                int place = starts[(int) item.charAt(index)];
                asciis[place] = item;
                starts[(int) item.charAt(index)] += 1;
            } else {
                int place = starts[0];
                asciis[place] = item;
                starts[0] += 1;
            }
        }
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main(String[] args) {
        String[] unsorted = {"abc", "ee", "zgxw", "hjkl", "xuhao"};
        String[] sorted = sort(unsorted);
        System.out.println(Arrays.toString(unsorted));
        System.out.println(Arrays.toString(sorted));
    }
}
