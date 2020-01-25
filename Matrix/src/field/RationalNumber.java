package field;

/**
 * this is a rational number, contains
 * a numerator and a denominator
 * 
 * the value of a rational number is 
 * the quotient of a numerator by a denominator
 * 
 * @author Pinku_Neko
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
	public RationalNumber(final int numerator, final int denominator) {
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
		if(this.denominator < 0) {
			return new RationalNumber(0 - this.numerator, 0 - this.denominator).toString();
		} else {
			String theNumber = "";
			if(this.numerator >= 0) {
				theNumber += " ";
			}
			theNumber += String.format("%d/%d", this.numerator, this.denominator);
			return theNumber;
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
		return new RationalNumber((0 - this.numerator), this.denominator).lowestTerms();
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
		return new RationalNumber(this.denominator, this.numerator).lowestTerms();
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
	public RationalNumber add(final RationalNumber element) {
		final int newDenominator = this.denominator * element.denominator;
		final int newNumerator = this.numerator * element.denominator
				+ element .numerator * this.denominator;
		return new RationalNumber(newNumerator, newDenominator).lowestTerms();
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
	public RationalNumber substract(final RationalNumber element) {
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
	public RationalNumber multipliesWith(final RationalNumber element) {
		final int newDenominator = this.denominator * element.denominator;
		final int newNumerator = this.numerator * element.numerator;
		return new RationalNumber(newNumerator, newDenominator).lowestTerms();
	}

	/**
	 * the calculation division. 
	 * 
	 * precondition: both elements are rational
	 * 
	 * postcondition: the result is 
	 * [ (1st numerator * 2nd denominator) / 
	 * (1st denominator * 2nd numerator) ]
	 * 
	 * @param element the (2nd) element used to divide
	 * @return the result of division as a rational number
	 */
	@Override
	public RationalNumber dividedBy(final RationalNumber element) {
		return this.multipliesWith(element.getInverseElementMult());
	}
	
	/**
	 * FIXME should actually override Object.equals() but failed
	 * check if two elements are same. 
	 * 
	 * verify if this element has exact the same value as
	 * the given element
	 * 
	 * precondition: the given element is not a null-reference 
	 * 
	 * postcondition: if both rational numbers in lowest terms have 
	 * the same value of numerator and denominator, returns true, 
	 * otherwise false
	 * 
	 * @param element to be compared to this element
	 * @return true if they have the same value, 
	 * otherwise false
	 */
	@Override
	public final boolean isSameAs(final RationalNumber element) {
		if(element == null) {
			return false;
		}
		final RationalNumber thisElement = this.lowestTerms();
		final RationalNumber givenElement = element.lowestTerms();
		if((thisElement.numerator == givenElement.numerator)
				&&(thisElement.denominator == givenElement.denominator)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * check if the element is zero. 
	 * 
	 * verify if this element has exact the same value as
	 * the neutral element for addition has 
	 * here [ 0 / 1 ]
	 * 
	 * precondition: the neutral element for addition is defined
	 * 
	 * postcondition: return true if it is zero, otherwise false
	 * 
	 * @return true if it is zero, otherwise false
	 */
	@Override
	public boolean isZero() {
		if (this.isSameAs(this.getNeutralElementAdd())) {
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * reduce a rational number to lowest terms and return it 
	 * 
	 * @return a rational number having the lowest terms
	 */
	private final RationalNumber lowestTerms() {
		final int gcd = greatestCommonDivisor(this.denominator, this.numerator);
		final int newDenominator = this.denominator / gcd;
		final int newNumerator =this.numerator / gcd;
		return new RationalNumber(newNumerator, newDenominator);
	}

	/**
	 * return a greatest common divisor of two integer numbers
	 * 
	 * further information available on wikipedia...
	 * 
	 * @param greaterNumber the number supposed to be greater
	 * @param lessNumber the number supposed to be less
	 * @return the largest number, which can divide both two numbers
	 */
	private final int greatestCommonDivisor(final int greaterNumber, final int lessNumber) {
		if(greaterNumber < lessNumber) {
			exchangeNumbers(greaterNumber, lessNumber);
		}
		if(lessNumber == 0) {
			return(greaterNumber);
		} else
			return greatestCommonDivisor(lessNumber, greaterNumber % lessNumber);
	}
	
	/**
	 * exchange the position of two integer numbers
	 * 
	 * @param number1 a number
	 * @param number2 another number
	 */
	private final void exchangeNumbers(int number1, int number2) {
		final int cache = number1;
		number1 = number2;
		number2 = cache;
	}

}	
