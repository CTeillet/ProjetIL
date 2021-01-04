package interfaces;
/**
 * 
 */

import java.io.File;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author teill
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public interface IExportation {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param chemin
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void generationHTML(File chemin);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param chemin
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void genererVersionImprimable(File chemin);

}