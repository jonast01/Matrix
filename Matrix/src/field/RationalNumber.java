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

	public final double toDouble() {
		return (double)this.numerator / (double)this.denominator;
	}
	
	/**
	 * returns the number as a String
	 * 
	 * @return the rational number in the form:
	 * [ numerator / denominator ]
	 */
	@Override
	public final String toString() {
		return this.numerator + "/" + this.denominator;
	}
	
	@Override
	public final boolean isSameAs(RationalNumber element) {
		if(element == null) {
			return false;
		}
		
		if((this.numerator == element.numerator)
				&&(this.denominator == element.denominator)) {
			return true;
		} else {
			return false;
		}
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
		return new RationalNumber(0, 1);
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
		return new RationalNumber(1, 1);
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
		return lowestTerms(new RationalNumber((0 - this.numerator), this.denominator));
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
		return lowestTerms(new RationalNumber(this.denominator, this.numerator));
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
		return lowestTerms(new RationalNumber(newNumerator, newDenominator));
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
		return lowestTerms(new RationalNumber(newNumerator, newDenominator));
	}

	/**
	 * the calculation division
	 * 
	 * precondition: both elements are rational
	 * 
	 * postcondition: the result is 
	 * [ (1st numerator * 2nd denominator) / 
	 * (1st denominator * 2nd numerator) ]
	 * 
	 * @param the (2nd) element to be divided
	 * @return the result of division as a rational number
	 */
	@Override
	public RationalNumber dividedBy(RationalNumber element) {
		return this.multipliesWith(element.getInverseElementMult());
	}
	
	private final RationalNumber lowestTerms(RationalNumber rationalNumber) {
		int thisDenominator = rationalNumber.denominator;
		int thisNumerator = rationalNumber.numerator;
		int gcd = greatestCommonDivisor(thisDenominator, thisNumerator);
		int newDenominator = thisDenominator / gcd;
		int newNumerator =thisNumerator / gcd;
		return new RationalNumber(newNumerator, newDenominator);
	}

	private final int greatestCommonDivisor(int greaterNumber, int lessNumber) {
		if(greaterNumber < lessNumber) {
			exchangeNumbers(greaterNumber, lessNumber);
		}
		if(lessNumber == 0) {
			return(greaterNumber);
		} else
			return greatestCommonDivisor(lessNumber, greaterNumber % lessNumber);
	}
	
	private final void exchangeNumbers(int number1, int number2) {
		int cache = number1;
		number1 = number2;
		number2 = cache;
	}
}	
