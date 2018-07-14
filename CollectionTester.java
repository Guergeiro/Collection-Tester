import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeSet;

public class CollectionTester {
	public static void main(String[] args) {
		// Number of elements to be tested
		final int[] DIM = new int[6];
		DIM[0] = 1000;
		DIM[1] = 5000;
		DIM[2] = 10000;
		DIM[3] = 20000;
		DIM[4] = 40000;
		DIM[5] = 100000;

		Collection<Integer> col1;
		Collection<Integer> col2;
		Collection<Integer> col3;
		Collection<Integer> col4;

		col1 = new ArrayList<Integer>();
		col2 = new LinkedList<Integer>();
		col3 = new Stack<Integer>();
		col4 = new TreeSet<Integer>();

		System.out.println("Collection\tn:\t1000\t\t\t5000\t\t\t10000\t\t\t20000\t\t\t40000\t\t\t100000");
		System.out.println("(time(ms))");

		for (int i = 4; i != 0; i--) {
			switch (i) {
			case 1:
				System.out.println("\n" + col1.getClass().getSimpleName());
				checkPerfomance(col1, DIM);
				System.out.println();
				break;
			case 2:
				System.out.println("\n" + col2.getClass().getSimpleName());
				checkPerfomance(col2, DIM);
				System.out.println();
				break;
			case 3:
				System.out.println("\n" + col3.getClass().getSimpleName());
				checkPerfomance(col3, DIM);
				System.out.println();
				break;
			case 4:
				System.out.println("\n" + col4.getClass().getSimpleName());
				checkPerfomance(col4, DIM);
				System.out.println();
				break;
			}
		}
	}

	private static void checkPerfomance(Collection<Integer> col, int[] DIM) {
		Collection<Integer> col2 = col; // Duplicates the collection to use it in both removes

		Iterator<Integer> iterator;
		double start, stop, delta;

		// Add
		System.out.print("add");
		for (int dim : DIM) {
			start = System.nanoTime();

			for (int i = 0; i < dim; i++) {
				col.add(i);
			}

			stop = System.nanoTime();
			delta = (stop - start) / 1e6;

			if (delta > 9) {
				System.out.printf("\t\t\t%.2f", delta);
			} else {
				System.out.printf("\t\t\t%.4f", delta);
			}
		}

		// Search
		System.out.print("\nsearch");
		for (int dim : DIM) {
			start = System.nanoTime();

			for (int i = 0; i < dim; i++) {
				int n = (int) (Math.random() * dim);
				if (!col.contains(n)) {
					System.out.print("Not Found: " + n);
				}
			}

			stop = System.nanoTime();
			delta = (stop - start) / 1e6;

			if (delta > 9) {
				System.out.printf("\t\t\t%.2f", delta);
			} else {
				System.out.printf("\t\t\t%.4f", delta);
			}
		}

		// Remove1 : Uses iterator
		System.out.print("\nremove1");
		for (int dim : DIM) {
			start = System.nanoTime();
			iterator = col.iterator();

			while (iterator.hasNext()) {
				iterator.next();
				iterator.remove();
			}

			stop = System.nanoTime();
			delta = (stop - start) / 1e6;

			if (delta > 9) {
				System.out.printf("\t\t\t%.2f", delta);
			} else {
				System.out.printf("\t\t\t%.4f", delta);
			}
		}

		// Remove2 : Uses the index/objet of collection
		System.out.print("\nremove2");
		for (int dim : DIM) {
			start = System.nanoTime();

			for (int i : col2) {
				col2.remove(i);
			}
			stop = System.nanoTime();
			delta = (stop - start) / 1e6;

			if (delta > 9) {
				System.out.printf("\t\t\t%.2f", delta);
			} else {
				System.out.printf("\t\t\t%.4f", delta);
			}
		}
	}
}