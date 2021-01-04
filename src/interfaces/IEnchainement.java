package interfaces;
/**
 * 
 */

import java.util.Set;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author teill
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public interface IEnchainement {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public ISection getDepart();

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param numero
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void entrerSectionArrivee(ISection numero);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param numero
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void entrerSectionDepart(ISection numero);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public ISection getArrive();

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param cond
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void ajouterCondition(IObjet... cond);

	public Set<IObjet> getCondition();
}