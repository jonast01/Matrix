/**
 * 
 */
package matrix;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import field.Field;

/**
 * @author Pinku_Neko
 * @param <E>
 *
 */
public class Matrix<E extends Field<E>> {

	private final Vector<Vector<E>> matrix = new Vector<Vector<E>>(0, 1);
	private final int length;
	private final int height;
	private final Map<Integer, Integer> mapEchelonColumn 
	= new TreeMap<Integer, Integer>();
	
	public Matrix(final E[][] array2D) {
		checkValidity(array2D);
		fillElements(array2D);
		this.height = this.matrix.size();
		this.length = this.matrix.firstElement().size();
	}

	/**
	 * @param array2D
	 */
	private final void checkValidity(final E[][] array2D) {
		final int previousArrayLength = array2D[0].length;
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
	private boolean rowsUnequalInLength(final E[][] array2D, final int previousArrayLength, final int i) {
		return previousArrayLength != array2D[i].length;
	}

	/**
	 * @param array2D
	 */
	private final void fillElements(final E[][] array2D) {
		for (int row = 0; row < array2D.length; row++) {
			this.matrix.add(new Vector<E>(0, 1));
			fillOneRow(array2D, row);
		}
	}

	/**
	 * @param array2D
	 * @param row
	 */
	private void fillOneRow(final E[][] array2D, final int row) {
		for (int column = 0; column < array2D[row].length; column++) {
			this.matrix.elementAt(row).add(array2D[row][column]);
		}
	}

	/**
	 * @return the immutable matrix
	 */
	@SuppressWarnings("unchecked")
	public Vector<Vector<E>> getMatrix() {
		return (Vector<Vector<E>>) this.matrix.clone();
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @return the immutable mapEchelonColumn
	 */
	public Map<Integer, Integer> getMapEchelonColumn() {
		return Collections.unmodifiableMap(mapEchelonColumn);
	}

	@Override
	public String toString() {
		String content = "";
		for (final Vector<E> row : this.matrix) {
			content += "{ ";
			for (int i = 0; i < row.size() - 1; i++) {
				content += String.format("%s, ", row.get(i));
			}
			content += String.format("%s", row.get(row.size() - 1));
			content += " } \n";
		}
		return content;
	}

	/**
	 * @param amountEchelon
	 * @param columnCount
	 */
	public final void saveEchelonPosition(final int amountEchelon, final int columnCount) {
		this.mapEchelonColumn.put(amountEchelon, columnCount);
	}

	
	public final void setMatrixRow(final int rowToBeReplaced, final Vector<E> rowToReplace) {
		for (int index = 0; index < this.matrix.elementAt(rowToBeReplaced).size(); index++) {
			this.matrix.elementAt(rowToBeReplaced).set(index, rowToReplace.elementAt(index));
		}
	}
}
