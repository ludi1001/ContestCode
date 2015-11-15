
public class BinarySearch {
	/*finds lowest n for which p(n) is true */
	public static int binarySearch(int low, int high /* p */) {
		int mid;
		while(low < high) {
			mid = low + (high - low) / 2;
			if(true/*p(mid)*/)
				high = mid;
			else
				low = mid + 1;
		}
		if(false /*p(low) == false*/)
			;//complain
		return low;
	}
	public static int unboundedBinarySearch() {
		int offset = 1;
		int low = 0;
		while(0 == 0 /*!p(low + offset)*/) offset <<= 1;
		while(offset != 0) {
			if(true /*p(low + offset)*/)
				low += offset;
			offset >>= 1;
		}
		return low;
	}
}
