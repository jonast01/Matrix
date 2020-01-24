/**
 * 
 */
package matrix;

import field.RationalNumber;

/**
 * @author henry
 *
 */
class MatrixTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RationalNumber[][] rationalArray2D = new RationalNumber[][] {
			{new RationalNumber(0,1),new RationalNumber(2,1),new RationalNumber(2,1)},
			{new RationalNumber(2,1),new RationalNumber(3,1),new RationalNumber(4,1)},
			{new RationalNumber(1,2),new RationalNumber(1,2),new RationalNumber(1,2)}
		};
		Matrix<RationalNumber> rationalMatrix = new Matrix<RationalNumber>(rationalArray2D);
		System.out.println(rationalMatrix.toString());
		rationalMatrix.gaussElimination();
		System.out.println(rationalMatrix.toString());
	}

}
