
/**
 * @author robertrichey
 */
class MM2DaC {

	/**
	 * Takes an MM2Pair object, finds its minmax2 4-array using a divide and 
	 * conquer algorithm, and counts the number of comparisons necessary to 
	 * find it.
	 * 
	 * @param pair a MM2Pair object containing an array
	 * @return     a MM2Pair object containing the minmax2 4-array of an input
	 *             array and the number of comparisons needed to find it
	 */
	public static MM2Pair minmax2DaC(MM2Pair pair) {
		if (pair.getArray().length == 4) {
			minmax2Four(pair.getArray());
			// Increment comparisons to reflect minmax2four method
			pair.incrementComparisons(5);
			return pair;
		}

		final int SIZE = pair.getArray().length / 2;

		int[] pairArray = pair.getArray();
		int[] a1 = new int[SIZE];
		int[] a2 = new int[SIZE];

		for (int i = 0; i < a1.length; i++) {
			a1[i] = pairArray[i];
		}
		for (int i = 0; i < a2.length; i++) {
			a2[i] = pairArray[SIZE + i];
		}

		MM2Pair pair1 = minmax2DaC(new MM2Pair(pair.getComparisons(), a1));
		MM2Pair pair2 = minmax2DaC(new MM2Pair(pair.getComparisons(), a2));

		pair.setArray(merge(pair1.getArray(), pair2.getArray()));
		// Increment comparisons to reflect merge method
		pair.incrementComparisons(pair1.getComparisons() + pair2.getComparisons() + 4);
		return pair;
	}

	/**
	 * Sorts the first four elements of an array with five comparisons
	 * 
	 * @param arr an array to be sorted
	 */
	private static int[] minmax2Four(int[] arr) {
		// Comparisons 1 and 2 place two smallest values in elements 0, 1 and
		// two largest in 2, 3
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
		// Comparisons 3 determines smallest value
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
		// Comparison 5 orders the remaining two middle elements
		if (arr[1] > arr[2]) {
			int temp = arr[1];
			arr[1] = arr[2];
			arr[2] = temp;
		}
		return arr;
	}

	/**
	 * Returns a new 4-array based on the elements of two given 4-arrays
	 * 
	 * @param a1 a 4-array
	 * @param a2 a 4-array
	 * @return   a new 4-array containing the minmax2 elements of a1 and a2
	 */
	private static int[] merge(int[] a1, int[] a2) {
		int[] result = new int[4];

		// find minimums
		int min1;
		int min2;

		// first comparison
		if (a1[0] < a2[0]) {
			result[0] = a1[0];
			min1 = a1[1];
			min2 = a2[0];
		} 
		else {
			result[0] = a2[0];
			min1 = a1[0];
			min2 = a2[1];
		}

		// second comparison
		if (min1 < min2) {
			result[1] = min1;
		} 
		else {
			result[1] = min2;
		}

		// find maximums
		int max1;
		int max2;

		// third comparison
		if (a1[3] > a2[3]) {
			result[3] = a1[3];
			max1 = a1[2];
			max2 = a2[3];
		} 
		else {
			result[3] = a2[3];
			max1 = a1[3];
			max2 = a2[2];
		}

		// fourth comparison
		if (max1 > max2) {
			result[2] = max1;
		} 
		else {
			result[2] = max2;
		}

		return result;
	}
}