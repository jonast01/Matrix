/**
 * 
 */
package matrix;

import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import field.*;

/**
 * @author henry
 * @param <E>
 *
 */
class Matrix<E extends Field<E>> {

	private final Vector<Vector<E>> matrix = new Vector<Vector<E>>(0, 1);
	private final Map<Integer, Integer> columnEchelonPosition 
	= new TreeMap<Integer, Integer>();
	
	public Matrix(E[][] array2D) {
		checkValidity(array2D);
		fillElements(array2D);
	}

	/**
	 * @param array2D
	 */
	private final void checkValidity(E[][] array2D) {
		int previousArrayLength = array2D[0].length;
		for (int i = 0; i < array2D.length; i++) {
			if (rowsUnequalInLength(array2D, previousArrayLength, i)) {
				throw new IllegalArgumentException("array not allowed");
			}
		}
	}

	/**
	 * @param array2D
	 * @param previousArrayLength
	 * @param i
	 * @return
	 */
	private boolean rowsUnequalInLength(E[][] array2D, int previousArrayLength, int i) {
		return previousArrayLength != array2D[i].length;
	}

	/**
	 * @param array2D
	 */
	private final void fillElements(E[][] array2D) {
		for (int i = 0; i < array2D.length; i++) {
			matrix.add(new Vector<E>(0, 1));
			fillOneRow(array2D, i);
		}
	}

	/**
	 * @param array2D
	 * @param i
	 */
	private void fillOneRow(E[][] array2D, int i) {
		for (int j = 0; j < array2D[i].length; j++) {
			matrix.elementAt(i).add(array2D[i][j]);
		}
	}

	@Override
	public String toString() {
		String content = "";
		for (Vector<E> row : matrix) {
			content += "{ ";
			for (int i = 0; i < row.size() - 1; i++) {
				content += String.format("%s, ", row.get(i));
			}
			content += String.format("%s", row.get(row.size() - 1));
			content += " } \n";
		}
		return content;
	}

	public final void gaussElimination() {
		int amountReducedRow = 0;
		for (int columnCount = 0; columnCount < this.matrix.firstElement().size(); columnCount++) {
			for (int rowCount = amountReducedRow; rowCount < this.matrix.size(); rowCount++) {
//				System.out.println("rowcount: " + rowCount);
//				System.out.println("columncount: " + columnCount);
//				System.out.println("the Value is " + this.matrix.elementAt(rowCount).elementAt(columnCount));
				if (notZero(this.matrix.elementAt(rowCount).elementAt(columnCount))) {
//					System.out.println("Its not a zero");
					columnEchelonPosition.put(amountReducedRow, columnCount);
//					System.out.println(this.toString());
//					System.out.println("rowcount: " + rowCount);
//					System.out.println("exchanging rows");
					exchangeRow(this.matrix.elementAt(rowCount), this.matrix.elementAt(amountReducedRow));
//					System.out.println(this.toString());
//					System.out.println("reducing row " + amountReducedRow);
					reduceRow(this.matrix.elementAt(amountReducedRow), columnEchelonPosition.get(amountReducedRow));
//					System.out.println(this.toString());
					substractOtherRows(this.matrix.elementAt(amountReducedRow), amountReducedRow);
//					System.out.println(this.toString());
					amountReducedRow++;
				}
			}
		}
//		exchangeRow(this.matrix.elementAt(0), this.matrix.elementAt(1));
//		reduceRow(this.matrix.elementAt(0), 1);
	}

	private final void substractOtherRows(Vector<E> echelonRow, int echelonCount) {
		for (Vector<E> row : this.matrix) {
			E factor = row.elementAt(this.columnEchelonPosition.get(echelonCount));
			if(!row.equals(echelonRow)) {
//				System.out.println("substracting row " + echelonRow + " to " + row);
				subtractRow(row, multiplyRow(echelonRow, factor));
//				System.out.println("subtracted row " + row);
			}
		}
	}

	private final void subtractRow(Vector<E> rowToBesubtracted, Vector<E> rowToSubstract) {
		for (int i = 0; i < rowToBesubtracted.size(); i++) {
			rowToBesubtracted.set(i, 
					rowToBesubtracted.elementAt(i).substract(rowToSubstract.elementAt(i)));
		}
	}
	
	private final Vector<E> multiplyRow(Vector<E> row, E factor) {
		Vector<E> rowClone = new Vector<E>(0,1);
		rowClone.addAll(row);
		for (int i = 0; i < row.size(); i++) {
			rowClone.set(i, row.elementAt(i).multipliesWith(factor));
		}
		return rowClone;
	}
	
	private final void exchangeRow(Vector<E> echelonRow, Vector<E> theOtherRow) {
		Vector<E> cache = new Vector<E>(0,1);
		cache.addAll(theOtherRow);
		theOtherRow.clear();
		theOtherRow.addAll(echelonRow);
		echelonRow.clear();
		echelonRow.addAll(cache);
	}

	private void reduceRow(final Vector<E> thisRow, final int columnPosition) {
//		System.out.println(thisRow.size());
		E theElementToBeDivided = thisRow.elementAt(columnPosition);
		for (int i = 0; i < thisRow.size(); i++) {
//			System.out.println("the value to be divided " + theElementToBeDivided);
//			System.out.println("column "+ i + " operated");
			thisRow.set(i, thisRow.elementAt(i).dividedBy(theElementToBeDivided));
		}
	}

	private final boolean notZero(E pickedElement) {
		return !(pickedElement.isSameAs(pickedElement.getNeutralElementAdd()));
	}
}
