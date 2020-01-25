/**
 * 
 */
package matrix.reforming;

import java.util.Vector;

import field.Field;
import matrix.Matrix;

/**
 * @author Pinku_Neko
 *
 */
final class RowTransformation {
	static final <E extends Field<E>> void reduceRow(
			final Matrix<E> thisMatrix, 
			final int actualCurrentEchelonIndex, 
			final int columnPosition) {
		final Vector<E> actualCurrentEchelonRow = thisMatrix.getMatrix().elementAt(actualCurrentEchelonIndex);
		final E theElementToBeDivided = actualCurrentEchelonRow.elementAt(columnPosition);
		for (int i = 0; i < actualCurrentEchelonRow.size(); i++) {
			actualCurrentEchelonRow.set(i, actualCurrentEchelonRow.elementAt(i).dividedBy(theElementToBeDivided));
		}
		thisMatrix.setMatrixRow(actualCurrentEchelonIndex, actualCurrentEchelonRow);
		System.out.println("Row reduced. \n" + thisMatrix.toString());
	}
	
	static final <E extends Field<E>> Vector<E> multiplyRow(final Vector<E> row, final E factor) {
		final Vector<E> rowClone = new Vector<E>(0,1);
		for (int i = 0; i < row.size(); i++) {
			rowClone.add(row.elementAt(i).multipliesWith(factor));
		}
		System.out.println("factor " + factor.toString());
		System.out.println("cloned row: " + rowClone.toString());
		return rowClone;
	}
}
