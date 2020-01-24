/**
 * 
 */
package field;

/**
 * @author henry
 *
 */
final class NumberTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RationalNumber aRNumber = new RationalNumber(1, 3);
		RationalNumber anotherRNumber = new RationalNumber(1, 3);
		System.out.printf("Value: %s \n", aRNumber.toString());
		System.out.printf("neutral element add: %s \n", aRNumber.getNeutralElementAdd().toString());
		System.out.printf("neutral element mult: %s \n", aRNumber.getNeutralElementMult().toString());
		System.out.printf("inverse element add: %s \n", aRNumber.getNeutralElementAdd().toString());
		System.out.printf("inverse element mult: %s \n", aRNumber.getInverseElementMult().toString());
		System.out.printf("sum %s \n", aRNumber.add(anotherRNumber).toString());
		System.out.printf("difference: %s \n", aRNumber.substract(anotherRNumber).toString());
		System.out.printf("product: %s \n", aRNumber.multipliesWith(anotherRNumber).toString());
		System.out.printf("quotient: %s \n", aRNumber.dividedBy(anotherRNumber).toString());
	}

}
