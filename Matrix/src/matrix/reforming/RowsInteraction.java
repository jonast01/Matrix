package matrix.reforming;

import java.util.Vector;

import field.Field;
import matrix.Matrix;

/**
 * this class contains all available interactions between rows 
 * in the matrix, such as exchanging positions, subtraction etc.
 * 
 * @author Pinku_Neko
 *
 */
final class RowsInteraction {
	
	/**
	 * check if such index of row exists in the matrix
	 * 
	 * precondition: matrix is initialized
	 * 
	 * postcondition: if index < 0 || index > thisMatrix.getHeight()
	 * throws exception, otherwise does nothing
	 * 
	 * @param <E> the field this matrix is using
	 * @param thisMatrix the matrix
	 * @param index the index of a row in the matrix
	 */
	private static final <E extends Field<E>> void doesRowExist(final Matrix<E> thisMatrix, final int index) {
		isMatrixInited(thisMatrix);
		if((index < 0) || (index > thisMatrix.getHeight())) {
			throw new IllegalArgumentException("Such row index does not exist.");
		} 
	}

	/**
	 * check if such index of row exists in the matrix
	 * 
	 * precondition: matrix is initialized
	 * 
	 * postcondition: if index < 0 || index > thisMatrix.getHeight()
	 * throws exception, otherwise does nothing
	 * 
	 * @param <E> the field this matrix is using
	 * @param thisMatrix the matrix
	 * @param index the index of a row in the matrix
	 */
	private static final <E extends Field<E>> void doesColumnExist(final Matrix<E> thisMatrix, final int index) {
		isMatrixInited(thisMatrix);
		if((index < 0) || (index > thisMatrix.getLength())) {
			throw new IllegalArgumentException("Such column index does not exist.");
		} 
	}
	
	/**
	 * check if the given matrix is initialized
	 * 
	 * returns true if the matrix is initialized, otherwise false
	 * 
	 * @param <E> the field this matrix is using
	 * @param thisMatrix the matrix
	 */
	private static <E extends Field<E>> void isMatrixInited(final Matrix<E> thisMatrix) {
		if(thisMatrix == null) {
			throw new IllegalArgumentException("The matrix cannot be a null-reference");
		}
	}
	
	/**
	 * exchange the positions of two rows of one matrix
	 * 
	 * precondition: matrix is inited, 
	 * indexOfOneRow exists in matrix, 
	 * indexOfAnotherRow exists in matrix 
	 * 
	 * postcondition: the positions of two rows are exchanged
	 * 
	 * @param <E> the field this matrix is using
	 * @param thisMatrix the matrix
	 * @param indexOfOneRow the position of one row
	 * @param indexOfAnotherRow the position of another row
	 */
	static final <E extends Field<E>> void exchangeRows(
			final Matrix<E> thisMatrix, 
			final int indexOfOneRow, 
			final int indexOfAnotherRow) {
		isMatrixInited(thisMatrix);
		doesRowExist(thisMatrix, indexOfOneRow);
		doesRowExist(thisMatrix, indexOfAnotherRow);
		final Vector<E> cache = new Vector<E>(0,1);
		cache.addAll(thisMatrix.getMatrix().elementAt(indexOfAnotherRow));
		thisMatrix.setMatrixRow(indexOfAnotherRow, thisMatrix.getMatrix().elementAt(indexOfOneRow));
		thisMatrix.setMatrixRow(indexOfOneRow, cache);
		System.out.println("Row swapped. \n" + thisMatrix.toString());
	}
	
	/**
	 * clean the other rows in the matrix. 
	 * 
	 * precondition: matrix is inited, 
	 * rowIndex exists in matrix, 
	 * columnPosition exists in matrix 
	 * 
	 * clean the other rows in the matrix except the chosen row 
	 * on the specified column. 
	 * this is done by subtracting other rows by the chosen row multiplied 
	 * by the value the row on this column has.
	 * 
	 * for example:
	 * clean row {2, 4, -2}
	 * by choosing {1, 1, 1}
	 * will result {1, 3, -3}
	 * 
	 * @param <E> the field this matrix is using
	 * @param thisMatrix the matrix
	 * @param rowIndex the position of the row
	 * @param columnPosition the column of the specified element
	 */
	static final <E extends Field<E>> void cleanOtherRows(
			final Matrix<E> thisMatrix, 
			final int rowIndex, 
			final int columnPosition) {
		isMatrixInited(thisMatrix);
		doesRowExist(thisMatrix, rowIndex);
		doesColumnExist(thisMatrix, columnPosition);
		for (int i = 0; i < thisMatrix.getHeight(); i++) {
			final E factor = thisMatrix.getMatrix().elementAt(i).elementAt(columnPosition);
			if(i != rowIndex) {
				subtractRow(thisMatrix, i, RowTransformation.multiplyRow(thisMatrix.getMatrix().elementAt(rowIndex), factor));
			}
		}
		System.out.println("All other rows cleaned. \n" + thisMatrix.toString());
	}
	
	/**
	 * subtract a row to another row
	 * 
	 * precondition: matrix is inited, 
	 * indexOfRowToBesubtracted exists in matrix 
	 * 
	 * postcondition: all elements on the row will 
	 * be subtracted by corresponding elements on another row
	 * 
	 * @param <E>  the field this matrix is using
	 * @param thisMatrix the matrix
	 * @param indexOfRowToBesubtracted the index of the row to be subtracted
	 * @param rowToSubstract the row used to subtract other rows
	 */
	private static final <E extends Field<E>> void subtractRow(
			final Matrix<E> thisMatrix, 
			final int indexOfRowToBesubtracted, 
			final Vector<E> rowToSubstract) {
		isMatrixInited(thisMatrix);
		doesRowExist(thisMatrix, indexOfRowToBesubtracted);
		
		final Vector<E> rowToBeSubtracted = new Vector<E>(0, 1);
		for (int i = 0; i < thisMatrix.getLength(); i++) {
			final E currentElement = thisMatrix.getMatrix().elementAt(indexOfRowToBesubtracted).elementAt(i);
			rowToBeSubtracted.add(currentElement.substract(rowToSubstract.elementAt(i)));
		}
		System.out.println("rowToSubstract: " + rowToSubstract.toString());
		thisMatrix.setMatrixRow(indexOfRowToBesubtracted, rowToBeSubtracted);
		System.out.println("Row cleaned. \n" + thisMatrix.toString());
	}
}
