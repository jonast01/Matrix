package field;

/**
 * this is a rational number, contains
 * a numerator and a denominator
 * 
 * the value of a rational number is 
 * the quotient of a numerator by a denominator
 * 
 * @author henry
 *
 */
public final class RationalNumber implements Field<RationalNumber> {
	
	/**
	 * a neutral element for addition
	 * 
	 * an element e which applies to
	 * the following:
	 * x + e = x
	 * 
	 * here [ 0 / 1 ]
	 */
	private static final RationalNumber neutralElementAdd = new RationalNumber(0, 1);
	
	/**
	 * a neutral element for multiplication
	 * 
	 * an element n which applies to
	 * the following:
	 * x * n = x
	 * 
	 * here [ 1 / 1 ]
	 */
	private static final RationalNumber neutralElementMult = new RationalNumber(1, 1);
	
	/**
	 * the numerator of a rational number
	 * 
	 * an upper part of a rational number
	 * 
	 * the value of this number can be all integer number
	 * 
	 * represents the value a from a rational number
	 * [ a / b ]
	 */
	private final int numerator;

	/**
	 * the denominator of a rational number
	 * 
	 * an lower part of a rational number
	 * 
	 * the value of this number can be all integer number 
	 * EXCEPT 0
	 * 
	 * represents the value b from a rational number
	 * [ a / b ]
	 */
	private final int denominator;
	
	/**
	 * creates a rational number by given numerator and denominator 
	 * 
	 * precondition: the numerator is an integer number 
	 * AND denominator is an integer number EXCEPT 0 
	 * 
	 * postcondition: create a rational number by given numerator
	 * and denominator
	 * 
	 * @param numerator the upper part of a rational number
	 * @param denominator the lower part of a rational number
	 * @throws IllegalArgumentException when denominator is 0
	 */
	public RationalNumber(int numerator, int denominator) {
		if(denominator == 0) {
			throw new IllegalArgumentException("The denominator cannot be zero.");
		} else {
		this.numerator = numerator;
		this.denominator = denominator;
		}
	}

	/**
	 * returns the number as a String
	 * 
	 * @return the rational number in the form:
	 * [ numerator / denominator ]
	 */
	@Override
	public final String toString() {
		return "[ " + this.numerator + " / " + this.denominator + " ]";
	}
	
	
	/**
	 * get the neutral element for addition
	 * 
	 * gets an element e which applies
	 * the following:
	 * x + e = x
	 * 
	 * @return the element e, which is [ 0 / 1 ]
	 */
	@Override
	public RationalNumber getNeutralElementAdd() {
		return RationalNumber.neutralElementAdd;
	}
	
	/**
	 * get the neutral element for multiplication
	 * 
	 * gets an element n which applies
	 * the following:
	 * x * n = x
	 * 
	 * @return the element n, which is [ 1 / 1 ]
	 */
	@Override
	public RationalNumber getNeutralElementMult() {
		return RationalNumber.neutralElementMult;
	}
	
	/**
	 * gets an inverse element for addition
	 * 
	 * gets an element x' which applies to
	 * the following:
	 * x + x' = e
	 * 
	 * @return the element x', which is [ 0-numerator / denominator ]
	 */
	@Override
	public RationalNumber getInverseElementAdd() {
		return new RationalNumber(0-this.numerator, this.denominator);
	}
	
	/**
	 * an inverse element for multiplication
	 * 
	 * an element x" which applies to
	 * the following:
	 * x * x" = n
	 * 
	 * @return the element x", which is [ denominator / numerator ]
	 */
	@Override
	public RationalNumber getInverseElementMult() {
		return new RationalNumber(this.denominator, this.numerator);
	}
	
	/**
	 * the calculation addition
	 * 
	 * precondition: both elements are rational
	 * 
	 * postcondition: the result is 
	 * [ (1st numerator * 2nd denominator + 2nd numerator * 1st denominator) / 
	 * (1st denominator * 2nd denominator) ]
	 * 
	 * @param the (2nd) element to be added
	 * @return the result of addition as a rational number
	 */
	@Override
	public RationalNumber add(RationalNumber element) {
		int newDenominator = this.denominator * element.denominator;
		int newNumerator = this.numerator * element.denominator
				+ element .numerator * this.denominator;
		return new RationalNumber(newNumerator, newDenominator);
	}

	/**
	 * the calculation subtraction
	 * 
	 * precondition: both elements are rational
	 * 
	 * postcondition: the result is 
	 * [ (1st numerator * 2nd denominator - 2nd numerator * 1st denominator) / 
	 * (1st denominator * 2nd denominator) ]
	 * 
	 * @param the (2nd) element to be subtracted
	 * @return the result of subtraction as a rational number
	 */
	@Override
	public RationalNumber substract(RationalNumber element) {
		return this.add(element.getInverseElementAdd());
	}

	/**
	 * the calculation multiplication
	 * 
	 * precondition: both elements are rational
	 * 
	 * postcondition: the result is 
	 * [ (1st numerator * 2nd numerator) / 
	 * (1st denominator * 2nd denominator) ]
	 * 
	 * @param the (2nd) element to be multiplied
	 * @return the result of multiplication as a rational number
	 */
	@Override
	public RationalNumber multipliesWith(RationalNumber element) {
		int newDenominator = this.denominator * element.denominator;
		int newNumerator = this.numerator * element.numerator;
		return new RationalNumber(newNumerator, newDenominator);
	}

	@Override
	public RationalNumber dividedBy(RationalNumber element) {
		return this.multipliesWith(element.getInverseElementMult());
	}

}	
