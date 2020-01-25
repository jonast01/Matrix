/**
 * 
 */
package field;

/**
 * this is an interface of field
 * every field must have defined: 
 * 
 * -<E>: what kind of element it is
 * -addition: neutral and inverse elements
 * -multiplication: neutral and inverse elements
 * 
 * and all the calculations including:
 * addition, subtraction, multiplication, division
 * 
 * @author Pinku_Neko
 *
 */
public interface Field<E> {
	
	/**
	 * a neutral element for addition
	 * 
	 * an element e which applies to
	 * the following:
	 * x + e = x
	 * 
	 * @return the element e
	 */
	E getNeutralElementAdd();
	
	/**
	 * a neutral element for multiplication
	 * 
	 * an element n which applies to
	 * the following:
	 * x * n = x
	 * 
	 * @return the element n
	 */
	E getNeutralElementMult();
	
	/**
	 * an inverse element for addition
	 * 
	 * an element x' which applies to
	 * the following:
	 * x + x' = e
	 * 
	 * @return the element x'
	 */
	E getInverseElementAdd();
	
	/**
	 * an inverse element for multiplication
	 * 
	 * an element x" which applies to
	 * the following:
	 * x * x" = n
	 * 
	 * @return the element x"
	 */
	E getInverseElementMult();
	
	/**
	 * the arithmetic operation addition
	 * 
	 * precondition: both elements are in the SAME field
	 * 
	 * postcondition: the result is CORRECT according to 
	 * the definition of addition in this field 
	 * AND is an element in the SAME field
	 * 
	 * @param the element to be added
	 * @return the result of addition
	 */
	E add(E element);
	
	/**
	 * the arithmetic operation subtraction
	 * 
	 * precondition: both elements are in the SAME field
	 * 
	 * postcondition: the result is CORRECT according to 
	 * the definition of subtraction in this field 
	 * AND is an element in the SAME field
	 * 
	 * @param the element to be subtracted
	 * @return the result of subtraction
	 */
	E substract(E element);
	
	/**
	 * the arithmetic operation multiplication
	 * 
	 * precondition: both elements are in the SAME field
	 * 
	 * postcondition: the result is CORRECT according to 
	 * the definition of multiplication in this field 
	 * AND is an element in the SAME field
	 * 
	 * @param the element to be multiplied
	 * @return the result of multiplication
	 */
	E multipliesWith(E element);
	
	/**
	 * the arithmetic operation division
	 * 
	 * precondition: both elements are in the SAME field
	 * 
	 * postcondition: the result is CORRECT according to 
	 * the definition of division in this field 
	 * AND is an element in the SAME field
	 * 
	 * @param the element to be divided
	 * @return the result of division
	 */
	E dividedBy(E element);

	/**
	 * check if two elements are same
	 * 
	 * verify if this element has exact the same value as
	 * the given element
	 * 
	 * @param element to be compared to this element
	 * @return true if they have the same value, 
	 * otherwise false
	 */
	boolean isSameAs(E element);
	
	/**
	 * check if the element is zero
	 * 
	 * verify if this element has exact the same value as
	 * the neutral element for addition has
	 * 
	 * @return true if it is zero, otherwise false
	 */
	boolean isZero();
}
