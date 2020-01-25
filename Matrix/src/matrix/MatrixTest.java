package matrix;

import field.RationalNumber;
import matrix.reforming.MatrixReforming;

/**
 * This class is used to create matrix with 
 * given fields
 * 
 * @author Pinku_Neko
 *
 */
class MatrixTest {

	/**
	 * This is used to create matrix objects with
	 * given 2d array of fields
	 * 
	 * @param args default arguments, not related here
	 */
	public static void main(final String[] args) {
		final RationalNumber[][] rationalArray2D = new RationalNumber[][] {
			{new RationalNumber(4,7),new RationalNumber(-2,6),new RationalNumber(-2,9)},
			{new RationalNumber(-2,1),new RationalNumber(3,-1),new RationalNumber(-4,1)},
			{new RationalNumber(3,-2),new RationalNumber(7,2),new RationalNumber(1,4)}
		};
		final Matrix<RationalNumber> rationalMatrix = new Matrix<RationalNumber>(rationalArray2D);
		System.out.println(rationalMatrix.toString());
		MatrixReforming.gaussianElimination(rationalMatrix);
		System.out.println(rationalMatrix.toString());
	}

}
