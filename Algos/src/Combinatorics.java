import java.util.Arrays;


public class Combinatorics {
	/* nextPowerSet: represent members of set as 0-1 vector, e.g. [0,1,0,1] -> {b, d}
	 * Then treat vector as integer and increment by one.
	 */
	/* Cartesian products of sets A, B, C, etc
	 * Let A = {a0,a1,a2}
	 * Let B = {b0,b1,b2,b3}
	 * Let C = {c0,c1}
	 * Then let [0,0,0] -> [a0,b0,c0]
	 * [0,0,1] -> [a0,b0,c1]
	 * [0,1,0] -> [a0,b1,c0]
	 * etc
	 */
	/* e.g. [0, 1, 2, 1] = { {a}, {b, d} {c} } */
	public static int[] nextPartition(int[] arr) {
		int len = arr.length;
		if(arr[len - 1] >= len - 1)
			return null;
	    int i;
        boolean finished = false;
        boolean changed = false;
        /* Find the rightmost element no more than the other elements */
        for (i = len - 1; !finished && !changed; i--) {
            int j, max = 0;
            /* Find the highest element to the left of this one */
            for (j = 0; j < i; j++) {
                if (arr[j] > max) {
                    max = arr[j];
                }
            }
            if (arr[i] <= max) {
                /* Increment */
                arr[i]++;
                changed = true;
                /* Set the following elements to 0 */
                for (j = i + 1; j < len; j++) {
                    arr[j] = 0;
                }
            }
            finished = i == 1;
        }
        return arr;
	}

}