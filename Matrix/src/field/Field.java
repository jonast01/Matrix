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
 * @author henry
 *
 */
interface Field<E> {
	
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
	 * the calculation addition
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
	 * the calculation subtraction
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
	 * the calculation multiplication
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
	 * the calculation division
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
}
