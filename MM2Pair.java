/**
 * @author robertrichey
 * 
 * A class designed to encapsulate a minmax2 4-array and the number of
 * comparisons needed to obtain it from a given array
 */
class MM2Pair {
	private int comparisons;
	private int[] array;

	public MM2Pair(int[] arr) {
		this(0, arr);
	}

	public MM2Pair(int n, int[] arr) {
		comparisons = n;
		array = arr;
	}

	/**
	 * @return the number of comparisons of a MMPair
	 */
	public int getComparisons() {
		return comparisons;
	}

	/**
	 * @return the array of a MM2Pair
	 */
	public int[] getArray() {
		return array;
	}

	/**
	 * Sets the array of a MM2Pair to a given array
	 * 
	 * @param array the array that an MM2Pair will be set to
	 */
	public void setArray(int[] array) {
		this.array = array;
	}


	/**
	 * Increases the number of comparisons in a MM2Pair by a given value
	 * 
	 * @param n the number that comparisons will be increased by
	 */
	public void incrementComparisons(int n) {
		comparisons = getComparisons() + n;
	}
}