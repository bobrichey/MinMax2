
//////////////////////////////////////////////////////////////////////////////
// NAME: Bob Richey
//
// CLASS: CSC 560
//
// ASSIGNMENT: MinMax2
//
// FILE NAME: MMmain.java
//
// DATE: May 10, 2017
//
// DESCRIPTION: Demonstrates two algorithms that find the two smallest and two
// largest values in an array. One uses a linear approach while the other uses
// divide and conquer. First the two algorithms are used on five arrays of 64
// random integers from 10 to 99. Each array is printed along with its
// associated 4-array and number of comparisons needed to find it for each
// algorithm. Lastly, the algorithms are run on 10000 additional random arrays
// and the ranges of comparisons needed to find their 4-arrays are printed
// along with the number of times the linear and divide and conquer algorithms
// found the same 4-array
//////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;
import java.util.Random;

public class MMmain {

	public static void main(String[] args) {
		final int SIZE = 64;
		int[] array = new int[SIZE];
		final int NUM_RUNS_1 = 5;

		MM2Pair linearResult = null;
		MM2Pair dacResult = null;

		for (int i = 0; i < NUM_RUNS_1; i++) {
			fillArray(array);
			System.out.print("\nThe random array of " + SIZE + " elements is:\n");
			printArray(array);

			System.out.println();

			// Run of linear algorithm
			linearResult = MM2Linear.minmax2Linear(new MM2Pair(array));

			System.out.print("            MinMax2 using the Linear Algorithm:");
			printArray(linearResult.getArray());
			System.out.print("                    Number of comparisons made:  ");
			System.out.println(linearResult.getComparisons());

			System.out.println();

			// Run of DaC algorithm
			dacResult = MM2DaC.minmax2DaC(new MM2Pair(array));

			System.out.print("MinMax2 using the Divide and Conquer Algorithm:");
			printArray(dacResult.getArray());
			System.out.print("                    Number of comparisons made:  ");
			System.out.println(dacResult.getComparisons());

			System.out.print("\n\n");
		}

		// Run algorithms on 10000 different arrays and compare results
		final int NUM_RUNS_2 = 10000;
		fillArray(array);
		
		linearResult = MM2Linear.minmax2Linear(new MM2Pair(array));
		dacResult = MM2DaC.minmax2DaC(new MM2Pair(array));

		int minLinearComparisons = linearResult.getComparisons();
		int maxLinearComparisons = minLinearComparisons;

		int minDaCComparisons = dacResult.getComparisons();
		int maxDaCComparisons = minDaCComparisons;

		int numMatches = 0;
		
		if (Arrays.equals(linearResult.getArray(), dacResult.getArray())) {
			numMatches++;
		}

		for (int i = 1; i < NUM_RUNS_2; i++) {
			fillArray(array);

			linearResult = MM2Linear.minmax2Linear(new MM2Pair(array));
			dacResult = MM2DaC.minmax2DaC(new MM2Pair(array));

			if (Arrays.equals(linearResult.getArray(), dacResult.getArray())) {
				numMatches++;
			}

			int linearComparison = linearResult.getComparisons();
			int dacComparison = dacResult.getComparisons();

			if (linearComparison < minLinearComparisons) {
				minLinearComparisons = linearComparison;
			} 
			else {
				if (linearComparison > maxLinearComparisons) {
					maxLinearComparisons = linearComparison;
				}
			}
			if (dacComparison < minDaCComparisons) {
				minDaCComparisons = dacComparison;
			} 
			else {
				if (dacComparison > maxDaCComparisons) {
					maxDaCComparisons = dacComparison;
				}
			}
		}

		System.out.println("===================================================================\n\n");

		System.out.println("The two algorithms were compared " + NUM_RUNS_2 + " times. " + "They agreed in "
				+ numMatches + " cases.");

		System.out.println();

		System.out.println(" Range of number of comparisons for Linear Algo " + "(n = " + SIZE + "): "
				+ minLinearComparisons + ".." + maxLinearComparisons);

		System.out.println("Range of number of comparisons for D and C Algo " + "(n = " + SIZE + "): "
				+ minDaCComparisons + ".." + maxDaCComparisons);
	}

	/**
	 * Fills an array with random values from 10 to 99.
	 * 
	 * @param array
	 *            an array to be filled with random values
	 */
	public static void fillArray(int[] array) {
		Random rand = new Random();

		for (int i = 0; i < array.length; i++) {
			array[i] = rand.nextInt(90) + 10;
		}
	}

	/**
	 * Prints the contends of an integer array. Prints 16 elements per line.
	 * 
	 * @param array
	 *            an array to be printed
	 */
	public static void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			if (i % 16 == 0 && i > 0) {
				System.out.println();
			}
			System.out.print("  " + array[i]);
		}
		System.out.println();
	}
}