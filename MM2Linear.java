
/**
 * @author robertrichey
 */
class MM2Linear {

	/**
	 * Takes an MM2Pair object, finds its minmax2 4-array in linear time, and
	 * counts the number of comparisons necessary to find it.
	 * 
	 * @param pair a MM2Pair object containing an array
	 * @return     a MM2Pair object containing the minmax2 4-array of an input
	 *             array and the number of comparisons needed to find it
	 */
	public static MM2Pair minmax2Linear(MM2Pair pair) {
		int[] srcArray = pair.getArray();
		int[] mm2Array = new int[4];

		for (int i = 0; i < 4; i++) {
			mm2Array[i] = srcArray[i];
		}

		minmax2Four(mm2Array);
		pair.incrementComparisons(5);

		for (int i = 4; i < srcArray.length; i++) {
			pair.incrementComparisons(1);
			if (srcArray[i] < mm2Array[0]) {
				mm2Array[1] = mm2Array[0];
				mm2Array[0] = srcArray[i];
				continue;
			}
			pair.incrementComparisons(1);
			if (srcArray[i] < mm2Array[1]) {
				mm2Array[1] = srcArray[i];
				continue;
			}
			pair.incrementComparisons(1);
			if (srcArray[i] > mm2Array[3]) {
				mm2Array[2] = mm2Array[3];
				mm2Array[3] = srcArray[i];
				continue;
			}
			pair.incrementComparisons(1);
			if (srcArray[i] > mm2Array[2]) {
				mm2Array[2] = srcArray[i];
			}
		}
		pair.setArray(mm2Array);
		return pair;
	}

	/**
	 * Sorts the first four elements of an array with five comparisons
	 * 
	 * @param arr an array to be sorted
	 */
	private static void minmax2Four(int[] arr) {
		// Comparisons 1, 2, and 3 determine the smallest value
		if (arr[0] > arr[2]) {
			int temp = arr[0];
			arr[0] = arr[2];
			arr[2] = temp;
		}
		if (arr[1] > arr[3]) {
			int temp = arr[1];
			arr[1] = arr[3];
			arr[3] = temp;
		}
		if (arr[0] > arr[1]) {
			int temp = arr[0];
			arr[0] = arr[1];
			arr[1] = temp;
		}
		// Comparison 4 determines largest value
		if (arr[3] < arr[2]) {
			int temp = arr[3];
			arr[3] = arr[2];
			arr[2] = temp;
		}
		// Comparison 5 orders the two middle elements
		if (arr[1] > arr[2]) {
			int temp = arr[1];
			arr[1] = arr[2];
			arr[2] = temp;
		}
	}
}