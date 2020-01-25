package matrix.reforming;

import field.Field;
import matrix.Matrix;

final class GaussianElimination {
	public static final <E extends Field<E>> void gaussianElimination (final Matrix<E> thisMatrix) {
		if (thisMatrix == null) {
			throw new IllegalArgumentException("The given matrix is a null-refernce");
		}
		int amountEchelon = 0;
		for (int columnCount = 0; columnCount < thisMatrix.getLength(); columnCount++) {
			for (int currentEchelonRow = amountEchelon; currentEchelonRow < thisMatrix.getHeight(); currentEchelonRow++) {
				final E scannedElement = thisMatrix.getMatrix().elementAt(currentEchelonRow).elementAt(columnCount);
				if (notZero(scannedElement)) {
					thisMatrix.saveEchelonPosition(amountEchelon, columnCount);
					cleanEchelonColumn(thisMatrix, amountEchelon, currentEchelonRow);
					amountEchelon++;
				}
			}
		}
	}
	
	private static final <E extends Field<E>> boolean notZero(final E pickedElement) {
		return !(pickedElement.isSameAs(pickedElement.getNeutralElementAdd()));
	}
	
	/**
	 * @param actualCurrentEchelonIndex
	 * @param columnCount
	 * @param currentEchelonRowIndex
	 */
	private static final <E extends Field<E>> void cleanEchelonColumn(
			final Matrix<E> thisMatrix, 
			final int actualCurrentEchelonIndex, 
			final int currentEchelonRowIndex) {
		final Integer currentEchelonColumnPosition = 
				thisMatrix.getMapEchelonColumn().get(actualCurrentEchelonIndex);
		RowsInteraction.exchangeRows(thisMatrix, currentEchelonRowIndex, actualCurrentEchelonIndex);
		RowTransformation.reduceRow(thisMatrix, actualCurrentEchelonIndex, currentEchelonColumnPosition);
		RowsInteraction.cleanOtherRows(thisMatrix, actualCurrentEchelonIndex, currentEchelonColumnPosition);
	}
	
}