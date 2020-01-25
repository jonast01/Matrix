package matrix.reforming;

import field.Field;
import matrix.Matrix;

/**
 * @author Pinku_Neko
 *
 */
public final class MatrixReforming {
	/**
	 * @param <E>
	 * @param thisMatrix
	 */
	public static final <E extends Field<E>> void gaussianElimination(final Matrix<E> thisMatrix) {
		GaussianElimination.gaussianElimination(thisMatrix);
	}
}
