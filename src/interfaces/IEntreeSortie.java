package interfaces;
/**
 * 
 */

import java.io.File;

import exception.ProblemeOuvertureFichier;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author teill
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public interface IEntreeSortie {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param chemin
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void chargerLivre(File chemin) throws ProblemeOuvertureFichier;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param nom
	 * @throws ProblemeOuvertureFichier
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 * @return
	*/
	public boolean sauvegardeLivre(File nom) throws ProblemeOuvertureFichier;
}